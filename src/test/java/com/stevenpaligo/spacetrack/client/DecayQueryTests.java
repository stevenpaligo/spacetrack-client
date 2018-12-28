package com.stevenpaligo.spacetrack.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.DecayQuery.Decay;
import com.stevenpaligo.spacetrack.client.DecayQuery.DecayQueryField;

public class DecayQueryTests {

  @Test
  @DisplayName("DecayQuery: Result type matches the Space Track schema")
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
  @DisplayName("DecayQuery: Credentials are required")
  public void test3() {

    assertThrows(IllegalArgumentException.class, () -> {
      DecayQuery.builder().credentials(null);
    });
  }
}
