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
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URL;
import java.util.Collections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.SatCatDebutQuery.SatCatDebut;
import com.stevenpaligo.spacetrack.client.SatCatDebutQuery.SatCatDebutQueryField;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.credential.DefaultCredentialProvider;

public class SatCatDebutQueryTests {

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
  @DisplayName("SatCatDebutQuery: Builder method parameter validation")
  public void test3() {

    // a call to set the credentials is required
    assertThrows(IllegalArgumentException.class, () -> {
      SatCatDebutQuery.builder().build();
    });


    // the call to set the credentials will not accept a null
    assertThrows(IllegalArgumentException.class, () -> {
      SatCatDebutQuery.builder().credentials(null).build();
    });


    // none of the following calls are required: favorite(...), favorites(...), limit(...), predicate(...), predicates(...), sort(...), sorts(...)
    assertDoesNotThrow(() -> {
      SatCatDebutQuery.builder().credentials(credentials).build();
    });


    // the call to favorite(...) will not accept a null
    assertThrows(IllegalArgumentException.class, () -> {
      SatCatDebutQuery.builder().credentials(credentials).favorite(null).build();
    });


    // the call to favorites(...) will not accept a null
    assertThrows(NullPointerException.class, () -> { // TODO: change to IllegalArgumentException if/when https://github.com/rzwitserloot/lombok/issues/1999 is worked
      SatCatDebutQuery.builder().credentials(credentials).favorites(null).build();
    });


    // the call to favorites(...) will accept an empty collection
    assertDoesNotThrow(() -> {
      SatCatDebutQuery.builder().credentials(credentials).favorites(Collections.emptyList()).build();
    });


    // the call to limit(...) will accept a null
    assertDoesNotThrow(() -> {
      SatCatDebutQuery.builder().credentials(credentials).limit(null).build();
    });


    // TODO: this won't pass until Lombok is fixed
    // the call to predicate(...) will not accept a null
    // assertThrows(IllegalArgumentException.class, () -> {
    // SatCatDebutQuery.builder().credentials(credentials).predicate(null).build();
    // });


    // the call to predicates(...) will not accept a null
    assertThrows(NullPointerException.class, () -> { // TODO: change to IllegalArgumentException if/when https://github.com/rzwitserloot/lombok/issues/1999 is worked
      SatCatDebutQuery.builder().credentials(credentials).predicates(null).build();
    });


    // the call to predicates(...) will accept an empty collection
    assertDoesNotThrow(() -> {
      SatCatDebutQuery.builder().credentials(credentials).predicates(Collections.emptyList()).build();
    });


    // TODO: this won't pass until Lombok is fixed
    // the call to sort(...) will not accept a null
    // assertThrows(IllegalArgumentException.class, () -> {
    // SatCatDebutQuery.builder().credentials(credentials).sort(null).build();
    // });


    // the call to sorts(...) will not accept a null
    assertThrows(NullPointerException.class, () -> { // TODO: change to IllegalArgumentException if/when https://github.com/rzwitserloot/lombok/issues/1999 is worked
      SatCatDebutQuery.builder().credentials(credentials).sorts(null).build();
    });


    // the call to sorts(...) will accept an empty collection
    assertDoesNotThrow(() -> {
      SatCatDebutQuery.builder().credentials(credentials).sorts(Collections.emptyList()).build();
    });
  }
}
