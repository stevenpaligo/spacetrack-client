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
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.threeten.extra.scale.TaiInstant;
import org.threeten.extra.scale.UtcInstant;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.EqualsAndHashCode;

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
      new In<>(null, UtcInstant.of(Instant.now()));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new In<>(null, TaiInstant.of(Instant.now()));
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
      new In<>(new TestQueryField(), UtcInstant.of(Instant.now()));
    });

    assertDoesNotThrow(() -> {
      new In<>(new TestQueryField(), TaiInstant.of(Instant.now()));
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


    // UTC instant values
    assertTrue(Arrays.asList("NORAD_CAT_ID/2018-01-02 03:04:05.678,2018-02-03 04:05:06.789", "NORAD_CAT_ID/2018-02-03 04:05:06.789,2018-01-02 03:04:05.678")
        .contains(new In<>(new TestQueryField(), UtcInstant.parse("2018-01-02T03:04:05.678Z"), UtcInstant.parse("2018-02-03T04:05:06.789Z")).toQueryParameter()));


    // TAI instant values
    assertTrue(Arrays.asList("NORAD_CAT_ID/2018-01-02 03:04:05.678,2018-02-03 04:05:06.789", "NORAD_CAT_ID/2018-02-03 04:05:06.789,2018-01-02 03:04:05.678")
        .contains(new In<>(new TestQueryField(), TaiInstant.of(UtcInstant.parse("2018-01-02T03:04:05.678Z")), TaiInstant.of(UtcInstant.parse("2018-02-03T04:05:06.789Z"))).toQueryParameter()));


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


  @Test
  @DisplayName("In: Getters")
  public void test4() {

    // field
    assertEquals(new TestQueryField(), new In<>(new TestQueryField(), "ABC").getField());


    // values
    assertEquals("ABC", new In<>(new TestQueryField(), "ABC").getValues());
  }


  @EqualsAndHashCode
  private static class TestQueryField implements QueryField {

    @Override
    public String getQueryFieldName() {
      return "NORAD_CAT_ID";
    }
  }
}
