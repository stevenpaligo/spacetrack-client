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
import org.junit.jupiter.api.extension.ExtendWith;
import org.threeten.extra.scale.TaiInstant;
import org.threeten.extra.scale.UtcInstant;
import com.stevenpaligo.spacetrack.DelayBeforeEachTestExtension;
import com.stevenpaligo.spacetrack.TestUtils;
import com.stevenpaligo.spacetrack.client.SatCatQuery;
import com.stevenpaligo.spacetrack.client.SatCatQuery.SatCatQueryField;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.EqualsAndHashCode;

@ExtendWith(DelayBeforeEachTestExtension.class)
public class NotEqualTests {

  private static CredentialProvider credentials = TestUtils.getCredentials();


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
      new NotEqual<>(null, UtcInstant.of(Instant.now()));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new NotEqual<>(new TestQueryField(), (UtcInstant) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new NotEqual<>(null, TaiInstant.of(Instant.now()));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new NotEqual<>(new TestQueryField(), (TaiInstant) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new NotEqual<>(null, new CurrentDateTimeOffset(Duration.ofDays(1)));
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
      new NotEqual<>(new TestQueryField(), UtcInstant.of(Instant.now()));
    });

    assertDoesNotThrow(() -> {
      new NotEqual<>(new TestQueryField(), TaiInstant.of(Instant.now()));
    });

    assertDoesNotThrow(() -> {
      new NotEqual<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(1)));
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


    // UTC instant value
    assertEquals("NORAD_CAT_ID/<>2018-12-14 06:26:28.123", new NotEqual<>(new TestQueryField(), UtcInstant.parse("2018-12-14T06:26:28.123Z")).toQueryParameter());


    // TAI instant value
    assertEquals("NORAD_CAT_ID/<>2018-12-14 06:26:28.123", new NotEqual<>(new TestQueryField(), TaiInstant.of(UtcInstant.parse("2018-12-14T06:26:28.123Z"))).toQueryParameter());


    // offset value
    assertEquals("NORAD_CAT_ID/<>now+1.0", new NotEqual<>(new TestQueryField(), new CurrentDateTimeOffset(Duration.ofDays(1))).toQueryParameter());
  }


  @Test
  @DisplayName("NotEqual: Getters")
  public void test3() {

    // field
    assertEquals(new TestQueryField(), new NotEqual<>(new TestQueryField(), "ABC").getField());


    // value
    assertEquals("ABC", new NotEqual<>(new TestQueryField(), "ABC").getValue());
  }


  @Test
  @DisplayName("NotEqual: Successful call")
  public void test4() {

    assertDoesNotThrow(() -> {

      new SatCatQuery().setCredentials(credentials).addPredicate(new NotEqual<>(SatCatQueryField.INTERNATIONAL_DESIGNATOR, "2021-069A")).execute();
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
