package com.stevenpaligo.spacetrack.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.TipMessageQuery.TipMessage;
import com.stevenpaligo.spacetrack.client.TipMessageQuery.TipMessageQueryField;

public class TipMessageQueryTests {

  @Test
  @DisplayName("TipMessageQuery: Result type matches the Space Track schema")
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
  @DisplayName("TipMessageQuery: Credentials are required")
  public void test3() {

    assertThrows(IllegalArgumentException.class, () -> {
      TipMessageQuery.builder().credentials(null);
    });
  }
}
