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
package com.stevenpaligo.spacetrack.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.stevenpaligo.spacetrack.DelayBeforeEachTestExtension;
import com.stevenpaligo.spacetrack.QueryFieldEnumValidator;
import com.stevenpaligo.spacetrack.ResultTypeValidator;
import com.stevenpaligo.spacetrack.TestUtils;
import com.stevenpaligo.spacetrack.client.TleQuery.Tle;
import com.stevenpaligo.spacetrack.client.TleQuery.TleQueryField;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.predicate.CurrentDateTimeOffset;
import com.stevenpaligo.spacetrack.client.predicate.Equal;
import com.stevenpaligo.spacetrack.client.predicate.GreaterThan;
import com.stevenpaligo.spacetrack.client.predicate.Predicate;
import com.stevenpaligo.spacetrack.client.query.Limit;

@ExtendWith(DelayBeforeEachTestExtension.class)
public class TleQueryTests {

  private static CredentialProvider credentials = TestUtils.getCredentials();


  @Test
  @DisplayName("TleQuery: Result type matches the SpaceTrack schema")
  public void test1() {

    assertDoesNotThrow(() -> {

      ResultTypeValidator.validate(Tle.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/tle/format/json"));
    });
  }


  @Test
  @DisplayName("TleQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {

      QueryFieldEnumValidator.validate(TleQueryField.class, Tle.class);
    });
  }


  @Test
  @DisplayName("TleQuery: Successful call")
  public void test3() {

    assertDoesNotThrow(() -> {

      Predicate<TleQueryField> predicate1 = new Equal<>(TleQueryField.CATALOG_NUMBER, 25544);
      Predicate<TleQueryField> predicate2 = new GreaterThan<>(TleQueryField.EPOCH_YMD_HMS, new CurrentDateTimeOffset(Duration.ofDays(-5)));
      new TleQuery().setCredentials(credentials).addPredicates(Arrays.asList(predicate1, predicate2)).setLimit(Limit.ONE).execute();
    });
  }


  @Test
  @DisplayName("TleQuery: Calculation of apogee/perigee radius")
  public void test4() {

    // setup
    Tle tle = new Tle();
    tle.setApogeeHeightKilometers(420.992);
    tle.setPerigeeHeightKilometers(412.002);


    // apogee radius
    assertEquals(6799.127, tle.getApogeeRadiusKilometers(), 0.001);


    // perigee radius
    assertEquals(6790.137, tle.getPerigeeRadiusKilometers(), 0.001);
  }
}
