package com.stevenpaligo.spacetrack;


import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.credential.DefaultCredentialProvider;

public class TestUtils {

  private static final String SPACE_TRACK_USER_NAME_PROPERTY = "space-track.user.name";
  private static final String SPACE_TRACK_USER_PASSWORD_PROPERTY = "space-track.user.password";


  private TestUtils() {

    // enforce static method usage
  }


  public static CredentialProvider getCredentials() {

    // verify the Space-Track credentials are available as system properties
    if (System.getProperty(SPACE_TRACK_USER_NAME_PROPERTY) == null) {

      throw new RuntimeException("The Space-Track user name is missing from the system properties (" + SPACE_TRACK_USER_NAME_PROPERTY + ")");

    } else if (System.getProperty(SPACE_TRACK_USER_PASSWORD_PROPERTY) == null) {

      throw new RuntimeException("The Space-Track user password is missing from the system properties (" + SPACE_TRACK_USER_PASSWORD_PROPERTY + ")");
    }


    // return the Space-Track credentials
    return new DefaultCredentialProvider(System.getProperty(SPACE_TRACK_USER_NAME_PROPERTY), System.getProperty(SPACE_TRACK_USER_PASSWORD_PROPERTY));
  }


  public static CredentialProvider getIncorrectCredentials() {

    return new DefaultCredentialProvider("incorrect-test-account", "testTESTtest");
  }
}
