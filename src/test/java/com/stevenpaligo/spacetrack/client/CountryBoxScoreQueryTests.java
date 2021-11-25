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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.stevenpaligo.spacetrack.DelayBeforeEachTestExtension;
import com.stevenpaligo.spacetrack.QueryFieldEnumValidator;
import com.stevenpaligo.spacetrack.ResultTypeValidator;
import com.stevenpaligo.spacetrack.TestUtils;
import com.stevenpaligo.spacetrack.client.CountryBoxScoreQuery.CountryBoxScore;
import com.stevenpaligo.spacetrack.client.CountryBoxScoreQuery.CountryBoxScoreQueryField;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.predicate.Equal;
import com.stevenpaligo.spacetrack.client.predicate.Predicate;

@ExtendWith(DelayBeforeEachTestExtension.class)
public class CountryBoxScoreQueryTests {

  private static CredentialProvider credentials = TestUtils.getCredentials();


  @Test
  @DisplayName("CountryBoxScoreQuery: Result type matches the Space-Track schema")
  public void test1() {

    assertDoesNotThrow(() -> {

      ResultTypeValidator.validate(CountryBoxScore.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/boxscore/format/json"));
    });
  }


  @Test
  @DisplayName("CountryBoxScoreQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {

      QueryFieldEnumValidator.validate(CountryBoxScoreQueryField.class, CountryBoxScore.class);
    });
  }


  @Test
  @DisplayName("CountryBoxScoreQuery: Successful call")
  public void test3() {

    assertDoesNotThrow(() -> {

      Predicate<CountryBoxScoreQueryField> predicate = new Equal<>(CountryBoxScoreQueryField.COUNTRY, "ISS");

      new CountryBoxScoreQuery().setCredentials(credentials).addPredicate(predicate).execute();
    });
  }
}
