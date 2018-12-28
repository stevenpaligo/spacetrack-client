package com.stevenpaligo.spacetrack.client.predicate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.query.QueryField;

public class InTests {

  @Test
  @DisplayName("In: Constructor parameter validation")
  public void test1() {

    // disallowed values
    assertThrows(IllegalArgumentException.class, () -> {
      new In<>(null, "TEST");
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new In<>(null, new Date());
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new In<>(null, Instant.now());
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new In<>(null, 6.0);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new In<>(null, Arrays.asList(6.0), Double.class);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new In<>(new TestQueryField(), null, Double.class);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new In<>(new TestQueryField(), Arrays.asList(6.0), null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new In<>(new TestQueryField(), Arrays.asList(new Object()), Object.class);
    });


    // allowed values
    assertDoesNotThrow(() -> {
      new In<>(new TestQueryField(), "TEST");
    });

    assertDoesNotThrow(() -> {
      new In<>(new TestQueryField(), new Date());
    });

    assertDoesNotThrow(() -> {
      new In<>(new TestQueryField(), Instant.now());
    });

    assertDoesNotThrow(() -> {
      new In<>(new TestQueryField(), 6.0);
    });

    assertDoesNotThrow(() -> {
      new In<>(new TestQueryField(), Arrays.asList(6.0), Double.class);
    });
  }


  @Test
  @DisplayName("In: Query parameter format and correct contents")
  public void test2() {

    // string values
    assertTrue(Arrays.asList("NORAD_CAT_ID/ABC,DEF", "NORAD_CAT_ID/DEF,ABC").contains(new In<>(new TestQueryField(), "ABC", "DEF").toQueryParameter()));


    // date values
    assertTrue(Arrays.asList("NORAD_CAT_ID/2018-01-02 03:04:05.678,2018-02-03 04:05:06.789", "NORAD_CAT_ID/2018-02-03 04:05:06.789,2018-01-02 03:04:05.678")
        .contains(new In<>(new TestQueryField(), new Date(Instant.parse("2018-01-02T03:04:05.678Z").toEpochMilli()), new Date(Instant.parse("2018-02-03T04:05:06.789Z").toEpochMilli())).toQueryParameter()));


    // instant values
    assertTrue(Arrays.asList("NORAD_CAT_ID/2018-01-02 03:04:05.678,2018-02-03 04:05:06.789", "NORAD_CAT_ID/2018-02-03 04:05:06.789,2018-01-02 03:04:05.678")
        .contains(new In<>(new TestQueryField(), Instant.parse("2018-01-02T03:04:05.678Z"), Instant.parse("2018-02-03T04:05:06.789Z")).toQueryParameter()));


    // number values
    assertTrue(Arrays.asList("NORAD_CAT_ID/6.0,7.0", "NORAD_CAT_ID/7.0,6.0").contains(new In<>(new TestQueryField(), 6.0, 7.0).toQueryParameter()));


    // values as a collection
    assertTrue(Arrays.asList("NORAD_CAT_ID/6.0,7.0", "NORAD_CAT_ID/7.0,6.0").contains(new In<>(new TestQueryField(), Arrays.asList(6.0, 7.0), Double.class).toQueryParameter()));
  }


  @Test
  @DisplayName("In: Values are made unique")
  public void test3() {

    assertEquals("NORAD_CAT_ID/ABC,DEF", new In<>(new TestQueryField(), "ABC", "DEF", "ABC", "DEF").toQueryParameter());
  }


  private static class TestQueryField implements QueryField {

    @Override
    public String getQueryFieldName() {
      return "NORAD_CAT_ID";
    }
  }
}