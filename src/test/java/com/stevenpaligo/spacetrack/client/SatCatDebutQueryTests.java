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
import java.net.URL;
import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.stevenpaligo.spacetrack.DelayBeforeEachTestExtension;
import com.stevenpaligo.spacetrack.client.SatCatDebutQuery.SatCatDebut;
import com.stevenpaligo.spacetrack.client.SatCatDebutQuery.SatCatDebutQueryField;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.predicate.CurrentDateTimeOffset;
import com.stevenpaligo.spacetrack.client.predicate.GreaterThan;
import com.stevenpaligo.spacetrack.client.predicate.Predicate;
import com.stevenpaligo.spacetrack.client.query.Limit;

@ExtendWith(DelayBeforeEachTestExtension.class)
public class SatCatDebutQueryTests {

  private static CredentialProvider credentials = TestUtils.getCredentials();


  @Test
  @DisplayName("SatCatDebutQuery: Result type matches the SpaceTrack schema")
  public void test1() {

    assertDoesNotThrow(() -> {

      ResultTypeValidator.validate(SatCatDebut.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/satcat_debut/format/json"));
    });
  }


  @Test
  @DisplayName("SatCatDebutQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {

      QueryFieldEnumValidator.validate(SatCatDebutQueryField.class, SatCatDebut.class);
    });
  }


  @Test
  @DisplayName("SatCatDebutQuery: Successful call")
  public void test3() {

    assertDoesNotThrow(() -> {

      Predicate<SatCatDebutQueryField> predicate = new GreaterThan<>(SatCatDebutQueryField.DEBUT_TIME, new CurrentDateTimeOffset(Duration.ofDays(-30)));
      new SatCatDebutQuery().setCredentials(credentials).addPredicate(predicate).setLimit(Limit.ONE).execute();
    });
  }
}
