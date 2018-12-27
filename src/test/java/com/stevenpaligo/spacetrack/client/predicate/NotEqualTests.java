package com.stevenpaligo.spacetrack.client.predicate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.Instant;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.query.QueryField;

public class NotEqualTests {

  @Test
  @DisplayName("NotEqual: Constructor parameter validation")
  public void test1() {

    // disallowed values
    assertThrows(IllegalArgumentException.class, () -> {
      new NotEqual<>(null, "TEST");
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new NotEqual<>(new TestQueryField(), (String) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new NotEqual<>(null, 1);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new NotEqual<>(new TestQueryField(), (Integer) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new NotEqual<>(null, new Date());
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new NotEqual<>(new TestQueryField(), (Date) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new NotEqual<>(null, Instant.now());
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new NotEqual<>(new TestQueryField(), (Instant) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new NotEqual<>(null, new CurrentDateTimeOffset(1.0));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new NotEqual<>(new TestQueryField(), (CurrentDateTimeOffset) null);
    });


    // allowed values
    assertDoesNotThrow(() -> {
      new NotEqual<>(new TestQueryField(), "TEST");
    });

    assertDoesNotThrow(() -> {
      new NotEqual<>(new TestQueryField(), 1);
    });

    assertDoesNotThrow(() -> {
      new NotEqual<>(new TestQueryField(), new Date());
    });

    assertDoesNotThrow(() -> {
      new NotEqual<>(new TestQueryField(), Instant.now());
    });

    assertDoesNotThrow(() -> {
      new NotEqual<>(new TestQueryField(), new CurrentDateTimeOffset(1.0));
    });
  }


  @Test
  @DisplayName("NotEqual: Query parameter format and correct contents")
  public void test2() {

    // string value
    assertEquals("NORAD_CAT_ID/<>25544", new NotEqual<>(new TestQueryField(), "25544").toQueryParameter());


    // numeric value
    assertEquals("NORAD_CAT_ID/<>25544", new NotEqual<>(new TestQueryField(), 25544).toQueryParameter());


    // date value
    assertEquals("NORAD_CAT_ID/<>2018-12-14 06:26:28.123", new NotEqual<>(new TestQueryField(), new Date(1544768788123L)).toQueryParameter());


    // instant value
    assertEquals("NORAD_CAT_ID/<>2018-12-14 06:26:28.123", new NotEqual<>(new TestQueryField(), Instant.ofEpochMilli(1544768788123L)).toQueryParameter());


    // positive offset value
    assertEquals("NORAD_CAT_ID/<>now+1.0", new NotEqual<>(new TestQueryField(), new CurrentDateTimeOffset(1.0)).toQueryParameter());


    // negative offset value
    assertEquals("NORAD_CAT_ID/<>now-1.0", new NotEqual<>(new TestQueryField(), new CurrentDateTimeOffset(-1.0)).toQueryParameter());
  }


  private static class TestQueryField implements QueryField {

    @Override
    public String getQueryFieldName() {
      return "NORAD_CAT_ID";
    }
  }
}
