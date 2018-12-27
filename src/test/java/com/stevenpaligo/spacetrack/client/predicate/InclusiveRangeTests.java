package com.stevenpaligo.spacetrack.client.predicate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.Instant;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.query.QueryField;

public class InclusiveRangeTests {

  @Test
  @DisplayName("InclusiveRange: Constructor parameter validation")
  public void test1() {

    // disallowed values
    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, 1, 10);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), null, 10);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), 1, null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, new Date(0), new Date(86400000));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (Date) null, new Date(86400000));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), new Date(0), (Date) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, new CurrentDateTimeOffset(-5.0), new Date());
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (CurrentDateTimeOffset) null, new Date());
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(-5.0), (Date) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, new Date(), new CurrentDateTimeOffset(5.0));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (Date) null, new CurrentDateTimeOffset(5.0));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), new Date(), (CurrentDateTimeOffset) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, Instant.now(), Instant.now().plusMillis(1000));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (Instant) null, Instant.now().plusMillis(1000));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), Instant.now(), (Instant) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, new CurrentDateTimeOffset(-5.0), Instant.now());
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (CurrentDateTimeOffset) null, Instant.now());
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(-5.0), (Instant) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, Instant.now(), new CurrentDateTimeOffset(5.0));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (Instant) null, new CurrentDateTimeOffset(5.0));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), Instant.now(), (CurrentDateTimeOffset) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, new CurrentDateTimeOffset(-5.0), new CurrentDateTimeOffset(5.0));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (CurrentDateTimeOffset) null, new CurrentDateTimeOffset(5.0));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(-5.0), (CurrentDateTimeOffset) null);
    });


    // allowed values
    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), 1, 10);
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), new Date(0), new Date(86400000));
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(-5.0), new Date());
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), new Date(), new CurrentDateTimeOffset(5.0));
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), Instant.now(), Instant.now().plusMillis(1000));
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(-5.0), Instant.now());
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), Instant.now(), new CurrentDateTimeOffset(5.0));
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(-5.0), new CurrentDateTimeOffset(5.0));
    });
  }


  @Test
  @DisplayName("InclusiveRange: Query parameter format and correct contents")
  public void test2() {

    // number values
    assertEquals("NORAD_CAT_ID/1--10", new InclusiveRange<>(new TestQueryField(), 1, 10).toQueryParameter());


    // date values
    assertEquals("NORAD_CAT_ID/1970-01-01 00:00:00.000--1970-01-02 00:00:00.000", new InclusiveRange<>(new TestQueryField(), new Date(0), new Date(86400000)).toQueryParameter());


    // one offset and one date value
    assertEquals("NORAD_CAT_ID/now-5.0--1970-01-01 00:00:00.000", new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(-5.0), new Date(0)).toQueryParameter());


    // one date and one offset value
    assertEquals("NORAD_CAT_ID/1970-01-01 00:00:00.000--now+5.0", new InclusiveRange<>(new TestQueryField(), new Date(0), new CurrentDateTimeOffset(5.0)).toQueryParameter());


    // instant values
    assertEquals("NORAD_CAT_ID/2018-01-02 03:04:05.678--2018-02-03 04:05:06.789", new InclusiveRange<>(new TestQueryField(), Instant.parse("2018-01-02T03:04:05.678Z"), Instant.parse("2018-02-03T04:05:06.789Z")).toQueryParameter());


    // one offset and one instant value
    assertEquals("NORAD_CAT_ID/now-5.0--2018-02-03 04:05:06.789", new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(-5.0), Instant.parse("2018-02-03T04:05:06.789Z")).toQueryParameter());


    // one instant and one offset value
    assertEquals("NORAD_CAT_ID/2018-01-02 03:04:05.678--now+5.0", new InclusiveRange<>(new TestQueryField(), Instant.parse("2018-01-02T03:04:05.678Z"), new CurrentDateTimeOffset(5.0)).toQueryParameter());


    // offset values
    assertEquals("NORAD_CAT_ID/now-5.0--now+5.0", new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(-5.0), new CurrentDateTimeOffset(5.0)).toQueryParameter());
  }


  private static class TestQueryField implements QueryField {

    @Override
    public String getQueryFieldName() {
      return "NORAD_CAT_ID";
    }
  }
}
