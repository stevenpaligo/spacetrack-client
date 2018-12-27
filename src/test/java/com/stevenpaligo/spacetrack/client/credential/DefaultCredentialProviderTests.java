package com.stevenpaligo.spacetrack.client.credential;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DefaultCredentialProviderTests {

  @Test
  @DisplayName("DefaultCredentialProvider: Constructor parameter validation")
  public void test1() {

    // disallowed values
    assertThrows(IllegalArgumentException.class, () -> {
      new DefaultCredentialProvider(null, "password");
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new DefaultCredentialProvider("user", null);
    });


    // allowed values
    assertDoesNotThrow(() -> {
      new DefaultCredentialProvider("user", "password");
    });
  }


  @Test
  @DisplayName("DefaultCredentialProvider: Correct contents")
  public void test2() {

    assertEquals("user", new DefaultCredentialProvider("user", "password").getUserName());
    assertEquals("password", new DefaultCredentialProvider("user", "password").getPassword());
  }
}
