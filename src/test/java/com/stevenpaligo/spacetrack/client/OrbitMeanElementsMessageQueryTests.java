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
import com.stevenpaligo.spacetrack.client.OrbitMeanElementsMessageQuery.OrbitMeanElementsMessage;
import com.stevenpaligo.spacetrack.client.OrbitMeanElementsMessageQuery.OrbitMeanElementsMessageQueryField;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.predicate.Equal;
import com.stevenpaligo.spacetrack.client.predicate.Predicate;
import com.stevenpaligo.spacetrack.client.query.Limit;
import com.stevenpaligo.spacetrack.client.query.Sort;
import com.stevenpaligo.spacetrack.client.query.Sort.Direction;

public class OrbitMeanElementsMessageQueryTests {

  private static CredentialProvider credentials = TestUtils.getCredentials();


  @Test
  @DisplayName("OrbitMeanElementsMessageQuery: Result type matches the SpaceTrack schema")
  public void test1() {

    assertDoesNotThrow(() -> {
      ResultTypeValidator.validate(OrbitMeanElementsMessage.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/omm/format/json"));
    });
  }


  @Test
  @DisplayName("OrbitMeanElementsMessageQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {
      QueryFieldEnumValidator.validate(OrbitMeanElementsMessageQueryField.class, OrbitMeanElementsMessage.class);
    });
  }


  @Test
  @DisplayName("OrbitMeanElementsMessageQuery: Successful call")
  public void test3() {

    assertDoesNotThrow(() -> {

      Predicate<OrbitMeanElementsMessageQueryField> predicate = new Equal<>(OrbitMeanElementsMessageQueryField.CATALOG_NUMBER, 25544);
      new OrbitMeanElementsMessageQuery().setCredentials(credentials).addPredicate(predicate).addSort(new Sort<>(OrbitMeanElementsMessageQueryField.EPOCH, Direction.DESC)).setLimit(Limit.ONE).execute();
    });
  }
}
