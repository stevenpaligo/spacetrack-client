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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.LatestTleQuery.LatestTle;
import com.stevenpaligo.spacetrack.client.LatestTleQuery.LatestTleQueryField;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.predicate.Equal;
import com.stevenpaligo.spacetrack.client.predicate.Predicate;

public class LatestTleQueryTests {

  private static CredentialProvider credentials = TestUtils.getCredentials();


  @Test
  @DisplayName("LatestTleQuery: Result type matches the SpaceTrack schema")
  public void test1() {

    assertDoesNotThrow(() -> {

      ResultTypeValidator.validate(LatestTle.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/tle_latest/format/json"));
    });
  }


  @Test
  @DisplayName("LatestTleQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {

      QueryFieldEnumValidator.validate(LatestTleQueryField.class, LatestTle.class);
    });
  }


  @Test
  @DisplayName("LatestTleQuery: Successful call")
  public void test3() {

    assertDoesNotThrow(() -> {

      Predicate<LatestTleQueryField> predicate = new Equal<>(LatestTleQueryField.CATALOG_NUMBER, 25544);
      new LatestTleQuery().setCredentials(credentials).addPredicate(predicate).execute();
    });
  }


  @Test
  @DisplayName("LatestTleQuery: Calculation of apogee/perigee radius")
  public void test4() {

    // setup
    LatestTle tle = new LatestTle();
    tle.setApogeeHeightKilometers(420.992);
    tle.setPerigeeHeightKilometers(412.002);


    // apogee radius
    assertEquals(6799.127, tle.getApogeeRadiusKilometers(), 0.001);


    // perigee radius
    assertEquals(6790.137, tle.getPerigeeRadiusKilometers(), 0.001);
  }
}
