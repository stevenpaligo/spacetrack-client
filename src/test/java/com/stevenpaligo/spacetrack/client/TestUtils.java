package com.stevenpaligo.spacetrack.client;


import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.credential.DefaultCredentialProvider;

public class TestUtils {

  private static final String SPACETRACK_USER_NAME_PROPERTY = "spacetrack.user.name";
  private static final String SPACETRACK_USER_PASSWORD_PROPERTY = "spacetrack.user.password";


  private TestUtils() {

    // enforce static method usage
  }


  public static CredentialProvider getCredentials() {

    // verify the SpaceTrack credentials are available as system properties
    if (System.getProperty(SPACETRACK_USER_NAME_PROPERTY) == null) {

      throw new RuntimeException("The SpaceTrack user name is missing from the system properties (" + SPACETRACK_USER_NAME_PROPERTY + ")");

    } else if (System.getProperty(SPACETRACK_USER_PASSWORD_PROPERTY) == null) {

      throw new RuntimeException("The SpaceTrack user password is missing from the system properties (" + SPACETRACK_USER_PASSWORD_PROPERTY + ")");
    }


    // return the SpaceTrack credentials
    return new DefaultCredentialProvider(System.getProperty(SPACETRACK_USER_NAME_PROPERTY), System.getProperty(SPACETRACK_USER_PASSWORD_PROPERTY));
  }


  public static CredentialProvider getIncorrectCredentials() {

    return new DefaultCredentialProvider("incorrect-test-account", "testTESTtest");
  }
}
