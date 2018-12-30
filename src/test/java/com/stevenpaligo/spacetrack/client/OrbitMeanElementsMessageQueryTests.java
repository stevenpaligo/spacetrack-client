package com.stevenpaligo.spacetrack.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URL;
import java.util.Collections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.OrbitMeanElementsMessageQuery.OrbitMeanElementsMessage;
import com.stevenpaligo.spacetrack.client.OrbitMeanElementsMessageQuery.OrbitMeanElementsMessageQueryField;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.credential.DefaultCredentialProvider;

public class OrbitMeanElementsMessageQueryTests {

  private static final String SPACE_TRACK_USER_NAME_PROPERTY = "space.track.user.name";
  private static final String SPACE_TRACK_PASSWORD_PROPERTY = "space.track.password";


  private static CredentialProvider credentials;


  @BeforeAll
  protected static void init() throws Exception {

    // verify the Space Track credentials are available as system properties
    if (System.getProperty(SPACE_TRACK_USER_NAME_PROPERTY) == null) {
      throw new Exception("The Space Track user name is missing from the system properties (" + SPACE_TRACK_USER_NAME_PROPERTY + ")");
    } else if (System.getProperty(SPACE_TRACK_PASSWORD_PROPERTY) == null) {
      throw new Exception("The Space Track password is missing from the system properties (" + SPACE_TRACK_PASSWORD_PROPERTY + ")");
    }


    // save the Space Track credentials
    credentials = new DefaultCredentialProvider(System.getProperty(SPACE_TRACK_USER_NAME_PROPERTY), System.getProperty(SPACE_TRACK_PASSWORD_PROPERTY));
  }


  @Test
  @DisplayName("OrbitMeanElementsMessageQuery: Result type matches the Space Track schema")
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
  @DisplayName("OrbitMeanElementsMessageQuery: Builder method parameter validation")
  public void test3() {

    // a call to set the credentials is required
    assertThrows(IllegalArgumentException.class, () -> {
      OrbitMeanElementsMessageQuery.builder().build();
    });


    // the call to set the credentials will not accept a null
    assertThrows(IllegalArgumentException.class, () -> {
      OrbitMeanElementsMessageQuery.builder().credentials(null).build();
    });


    // none of the following calls are required: favorite(...), favorites(...), limit(...), predicate(...), predicates(...), sort(...), sorts(...)
    assertDoesNotThrow(() -> {
      OrbitMeanElementsMessageQuery.builder().credentials(credentials).build();
    });


    // the call to favorite(...) will not accept a null
    assertThrows(IllegalArgumentException.class, () -> {
      OrbitMeanElementsMessageQuery.builder().credentials(credentials).favorite(null).build();
    });


    // the call to favorites(...) will not accept a null
    assertThrows(NullPointerException.class, () -> { // TODO: change to IllegalArgumentException if/when https://github.com/rzwitserloot/lombok/issues/1999 is worked
      OrbitMeanElementsMessageQuery.builder().credentials(credentials).favorites(null).build();
    });


    // the call to favorites(...) will accept an empty collection
    assertDoesNotThrow(() -> {
      OrbitMeanElementsMessageQuery.builder().credentials(credentials).favorites(Collections.emptyList()).build();
    });


    // the call to limit(...) will accept a null
    assertDoesNotThrow(() -> {
      OrbitMeanElementsMessageQuery.builder().credentials(credentials).limit(null).build();
    });


    // TODO: this won't pass until Lombok is fixed
    // the call to predicate(...) will not accept a null
    // assertThrows(IllegalArgumentException.class, () -> {
    // OrbitMeanElementsMessageQuery.builder().credentials(credentials).predicate(null).build();
    // });


    // the call to predicates(...) will not accept a null
    assertThrows(NullPointerException.class, () -> { // TODO: change to IllegalArgumentException if/when https://github.com/rzwitserloot/lombok/issues/1999 is worked
      OrbitMeanElementsMessageQuery.builder().credentials(credentials).predicates(null).build();
    });


    // the call to predicates(...) will accept an empty collection
    assertDoesNotThrow(() -> {
      OrbitMeanElementsMessageQuery.builder().credentials(credentials).predicates(Collections.emptyList()).build();
    });


    // TODO: this won't pass until Lombok is fixed
    // the call to sort(...) will not accept a null
    // assertThrows(IllegalArgumentException.class, () -> {
    // OrbitMeanElementsMessageQuery.builder().credentials(credentials).sort(null).build();
    // });


    // the call to sorts(...) will not accept a null
    assertThrows(NullPointerException.class, () -> { // TODO: change to IllegalArgumentException if/when https://github.com/rzwitserloot/lombok/issues/1999 is worked
      OrbitMeanElementsMessageQuery.builder().credentials(credentials).sorts(null).build();
    });


    // the call to sorts(...) will accept an empty collection
    assertDoesNotThrow(() -> {
      OrbitMeanElementsMessageQuery.builder().credentials(credentials).sorts(Collections.emptyList()).build();
    });
  }
}
