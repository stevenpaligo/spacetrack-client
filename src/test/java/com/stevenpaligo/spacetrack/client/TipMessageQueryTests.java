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
import com.stevenpaligo.spacetrack.client.TipMessageQuery.TipMessage;
import com.stevenpaligo.spacetrack.client.TipMessageQuery.TipMessageQueryField;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.query.Limit;
import com.stevenpaligo.spacetrack.client.query.Sort;
import com.stevenpaligo.spacetrack.client.query.Sort.Direction;

@ExtendWith(DelayBeforeEachTestExtension.class)
public class TipMessageQueryTests {

  private static CredentialProvider credentials = TestUtils.getCredentials();


  @Test
  @DisplayName("TipMessageQuery: Result type matches the SpaceTrack schema")
  public void test1() {

    assertDoesNotThrow(() -> {

      ResultTypeValidator.validate(TipMessage.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/tip/format/json"));
    });
  }


  @Test
  @DisplayName("TipMessageQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {

      QueryFieldEnumValidator.validate(TipMessageQueryField.class, TipMessage.class);
    });
  }


  @Test
  @DisplayName("TipMessageQuery: Successful call")
  public void test3() {

    assertDoesNotThrow(() -> {

      new TipMessageQuery().setCredentials(credentials).addSort(new Sort<>(TipMessageQueryField.MESSAGE_EPOCH, Direction.DESC)).setLimit(Limit.ONE).execute();
    });
  }
}
