/*
 * The author licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stevenpaligo.spacetrack.client.predicate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.threeten.extra.scale.TaiInstant;
import org.threeten.extra.scale.UtcInstant;
import com.stevenpaligo.spacetrack.client.SatCatQuery;
import com.stevenpaligo.spacetrack.client.SatCatQuery.SatCatQueryField;
import com.stevenpaligo.spacetrack.client.TestUtils;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.EqualsAndHashCode;

public class InclusiveRangeTests {

  private static CredentialProvider credentials = TestUtils.getCredentials();


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
      new InclusiveRange<>(null, new CurrentDateTimeOffset(Duration.ofDays(-5)), new Date());
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (CurrentDateTimeOffset) null, new Date());
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(-5)), (Date) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, new Date(), new CurrentDateTimeOffset(Duration.ofDays(5)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (Date) null, new CurrentDateTimeOffset(Duration.ofDays(5)));
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
      new InclusiveRange<>(null, new CurrentDateTimeOffset(Duration.ofDays(-5)), Instant.now());
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (CurrentDateTimeOffset) null, Instant.now());
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(-5)), (Instant) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, Instant.now(), new CurrentDateTimeOffset(Duration.ofDays(5)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (Instant) null, new CurrentDateTimeOffset(Duration.ofDays(5)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), Instant.now(), (CurrentDateTimeOffset) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, UtcInstant.of(Instant.now()), UtcInstant.of(Instant.now().plusMillis(1000)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (UtcInstant) null, UtcInstant.of(Instant.now().plusMillis(1000)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), UtcInstant.of(Instant.now()), (UtcInstant) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, new CurrentDateTimeOffset(Duration.ofDays(-5)), UtcInstant.of(Instant.now()));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (CurrentDateTimeOffset) null, UtcInstant.of(Instant.now()));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(-5)), (UtcInstant) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, UtcInstant.of(Instant.now()), new CurrentDateTimeOffset(Duration.ofDays(5)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (UtcInstant) null, new CurrentDateTimeOffset(Duration.ofDays(5)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), UtcInstant.of(Instant.now()), (CurrentDateTimeOffset) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, TaiInstant.of(Instant.now()), TaiInstant.of(Instant.now().plusMillis(1000)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (TaiInstant) null, TaiInstant.of(Instant.now().plusMillis(1000)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), TaiInstant.of(Instant.now()), (TaiInstant) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, new CurrentDateTimeOffset(Duration.ofDays(-5)), TaiInstant.of(Instant.now()));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (CurrentDateTimeOffset) null, TaiInstant.of(Instant.now()));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(-5)), (TaiInstant) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, TaiInstant.of(Instant.now()), new CurrentDateTimeOffset(Duration.ofDays(5)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (TaiInstant) null, new CurrentDateTimeOffset(Duration.ofDays(5)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), TaiInstant.of(Instant.now()), (CurrentDateTimeOffset) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(null, new CurrentDateTimeOffset(Duration.ofDays(-5)), new CurrentDateTimeOffset(Duration.ofDays(5)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), (CurrentDateTimeOffset) null, new CurrentDateTimeOffset(Duration.ofDays(5)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(-5)), (CurrentDateTimeOffset) null);
    });


    // allowed values
    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), 1, 10);
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), new Date(0), new Date(86400000));
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(-5)), new Date());
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), new Date(), new CurrentDateTimeOffset(Duration.ofDays(5)));
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), Instant.now(), Instant.now().plusMillis(1000));
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(-5)), Instant.now());
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), Instant.now(), new CurrentDateTimeOffset(Duration.ofDays(5)));
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), UtcInstant.of(Instant.now()), UtcInstant.of(Instant.now().plusMillis(1000)));
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(-5)), UtcInstant.of(Instant.now()));
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), UtcInstant.of(Instant.now()), new CurrentDateTimeOffset(Duration.ofDays(5)));
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), TaiInstant.of(Instant.now()), TaiInstant.of(Instant.now().plusMillis(1000)));
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(-5)), TaiInstant.of(Instant.now()));
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), TaiInstant.of(Instant.now()), new CurrentDateTimeOffset(Duration.ofDays(5)));
    });

    assertDoesNotThrow(() -> {
      new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(-5)), new CurrentDateTimeOffset(Duration.ofDays(5)));
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
    assertEquals("NORAD_CAT_ID/now-5.0--1970-01-01 00:00:00.000", new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(-5)), new Date(0)).toQueryParameter());


    // one date and one offset value
    assertEquals("NORAD_CAT_ID/1970-01-01 00:00:00.000--now+5.0", new InclusiveRange<>(new TestQueryField(), new Date(0), new CurrentDateTimeOffset(Duration.ofDays(5))).toQueryParameter());


    // instant values
    assertEquals("NORAD_CAT_ID/2018-01-02 03:04:05.678--2018-02-03 04:05:06.789", new InclusiveRange<>(new TestQueryField(), Instant.parse("2018-01-02T03:04:05.678Z"), Instant.parse("2018-02-03T04:05:06.789Z")).toQueryParameter());


    // one offset and one instant value
    assertEquals("NORAD_CAT_ID/now-5.0--2018-02-03 04:05:06.789", new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(-5)), Instant.parse("2018-02-03T04:05:06.789Z")).toQueryParameter());


    // one instant and one offset value
    assertEquals("NORAD_CAT_ID/2018-01-02 03:04:05.678--now+5.0", new InclusiveRange<>(new TestQueryField(), Instant.parse("2018-01-02T03:04:05.678Z"), new CurrentDateTimeOffset(Duration.ofDays(5))).toQueryParameter());


    // UTC instant values
    assertEquals("NORAD_CAT_ID/2018-01-02 03:04:05.678--2018-02-03 04:05:06.789", new InclusiveRange<>(new TestQueryField(), UtcInstant.parse("2018-01-02T03:04:05.678Z"), UtcInstant.parse("2018-02-03T04:05:06.789Z")).toQueryParameter());


    // one offset and one UTC instant value
    assertEquals("NORAD_CAT_ID/now-5.0--2018-02-03 04:05:06.789", new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(-5)), UtcInstant.parse("2018-02-03T04:05:06.789Z")).toQueryParameter());


    // one UTC instant and one offset value
    assertEquals("NORAD_CAT_ID/2018-01-02 03:04:05.678--now+5.0", new InclusiveRange<>(new TestQueryField(), UtcInstant.parse("2018-01-02T03:04:05.678Z"), new CurrentDateTimeOffset(Duration.ofDays(5))).toQueryParameter());


    // TAI instant values
    assertEquals("NORAD_CAT_ID/2018-01-02 03:04:05.678--2018-02-03 04:05:06.789",
        new InclusiveRange<>(new TestQueryField(), TaiInstant.of(UtcInstant.parse("2018-01-02T03:04:05.678Z")), TaiInstant.of(UtcInstant.parse("2018-02-03T04:05:06.789Z"))).toQueryParameter());


    // one offset and one TAI instant value
    assertEquals("NORAD_CAT_ID/now-5.0--2018-02-03 04:05:06.789", new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(-5)), TaiInstant.of(UtcInstant.parse("2018-02-03T04:05:06.789Z"))).toQueryParameter());


    // one TAI instant and one offset value
    assertEquals("NORAD_CAT_ID/2018-01-02 03:04:05.678--now+5.0", new InclusiveRange<>(new TestQueryField(), TaiInstant.of(UtcInstant.parse("2018-01-02T03:04:05.678Z")), new CurrentDateTimeOffset(Duration.ofDays(5))).toQueryParameter());


    // offset values (positive and negative)
    assertEquals("NORAD_CAT_ID/now-5.0--now+5.0", new InclusiveRange<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(-5)), new CurrentDateTimeOffset(Duration.ofDays(5))).toQueryParameter());
  }


  @Test
  @DisplayName("InclusiveRange: Getters")
  public void test3() {

    // field
    assertEquals(new TestQueryField(), new InclusiveRange<>(new TestQueryField(), 1, 2).getField());


    // lower value
    assertEquals("1", new InclusiveRange<>(new TestQueryField(), 1, 2).getLowerValue());


    // upper value
    assertEquals("2", new InclusiveRange<>(new TestQueryField(), 1, 2).getUpperValue());
  }


  @Test
  @DisplayName("InclusiveRange: Successful call")
  public void test4() {

    assertDoesNotThrow(() -> {

      new SatCatQuery().setCredentials(credentials).addPredicate(new InclusiveRange<>(SatCatQueryField.INCLINATION_DEGREES, 45.0, 50.0)).execute();
    });
  }


  @EqualsAndHashCode
  private static class TestQueryField implements QueryField {

    @Override
    public String getQueryFieldName() {

      return "NORAD_CAT_ID";
    }
  }
}
