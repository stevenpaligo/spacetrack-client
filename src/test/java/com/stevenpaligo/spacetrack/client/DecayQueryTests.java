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
import com.stevenpaligo.spacetrack.client.DecayQuery.Decay;
import com.stevenpaligo.spacetrack.client.DecayQuery.DecayQueryField;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.predicate.Equal;
import com.stevenpaligo.spacetrack.client.predicate.Predicate;

@ExtendWith(DelayBeforeEachTestExtension.class)
public class DecayQueryTests {

  private static CredentialProvider credentials = TestUtils.getCredentials();


  @Test
  @DisplayName("DecayQuery: Result type matches the Space-Track schema")
  public void test1() {

    assertDoesNotThrow(() -> {

      ResultTypeValidator.validate(Decay.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/decay/format/json"));
    });
  }


  @Test
  @DisplayName("DecayQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {

      QueryFieldEnumValidator.validate(DecayQueryField.class, Decay.class);
    });
  }


  @Test
  @DisplayName("DecayQuery: Successful call")
  public void test3() {

    assertDoesNotThrow(() -> {

      Predicate<DecayQueryField> predicate = new Equal<>(DecayQueryField.CATALOG_NUMBER, 1);

      new DecayQuery().setCredentials(credentials).addPredicate(predicate).execute();
    });
  }
}
