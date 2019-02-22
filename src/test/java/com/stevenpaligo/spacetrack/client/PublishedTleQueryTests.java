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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.PublishedTleQuery.PublishedTle;
import com.stevenpaligo.spacetrack.client.PublishedTleQuery.PublishedTleQueryField;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.credential.DefaultCredentialProvider;
import com.stevenpaligo.spacetrack.client.predicate.CurrentDateTimeOffset;
import com.stevenpaligo.spacetrack.client.predicate.GreaterThan;
import com.stevenpaligo.spacetrack.client.predicate.Predicate;
import com.stevenpaligo.spacetrack.client.query.Limit;
import com.stevenpaligo.spacetrack.client.query.Sort;
import com.stevenpaligo.spacetrack.client.query.Sort.Direction;

public class PublishedTleQueryTests {

  private static final String SPACETRACK_USER_NAME_PROPERTY = "spacetrack.user.name";
  private static final String SPACETRACK_PASSWORD_PROPERTY = "spacetrack.password";


  private static CredentialProvider credentials;


  @BeforeAll
  protected static void init() throws Exception {

    // verify the SpaceTrack credentials are available as system properties
    if (System.getProperty(SPACETRACK_USER_NAME_PROPERTY) == null) {
      throw new Exception("The SpaceTrack user name is missing from the system properties (" + SPACETRACK_USER_NAME_PROPERTY + ")");
    } else if (System.getProperty(SPACETRACK_PASSWORD_PROPERTY) == null) {
      throw new Exception("The SpaceTrack password is missing from the system properties (" + SPACETRACK_PASSWORD_PROPERTY + ")");
    }


    // save the SpaceTrack credentials
    credentials = new DefaultCredentialProvider(System.getProperty(SPACETRACK_USER_NAME_PROPERTY), System.getProperty(SPACETRACK_PASSWORD_PROPERTY));
  }


  @Test
  @DisplayName("PublishedTleQuery: Result type matches the SpaceTrack schema")
  public void test1() {

    assertDoesNotThrow(() -> {
      ResultTypeValidator.validate(PublishedTle.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/tle_publish/format/json"));
    });
  }


  @Test
  @DisplayName("PublishedTleQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {
      QueryFieldEnumValidator.validate(PublishedTleQueryField.class, PublishedTle.class);
    });
  }


  @Test
  @DisplayName("PublishedTleQuery: Successful call")
  public void test3() {

    assertDoesNotThrow(() -> {

      Predicate<PublishedTleQueryField> predicate = new GreaterThan<>(PublishedTleQueryField.PUBLISH_TIME, new CurrentDateTimeOffset(Duration.ofMinutes(-15)));
      new PublishedTleQuery().setCredentials(credentials).addPredicate(predicate).addSort(new Sort<>(PublishedTleQueryField.PUBLISH_TIME, Direction.DESC)).setLimit(Limit.ONE).execute();
    });
  }
}
