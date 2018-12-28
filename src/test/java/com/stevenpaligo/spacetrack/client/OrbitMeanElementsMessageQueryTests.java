package com.stevenpaligo.spacetrack.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.OrbitMeanElementsMessageQuery.OrbitMeanElementsMessage;
import com.stevenpaligo.spacetrack.client.OrbitMeanElementsMessageQuery.OrbitMeanElementsMessageQueryField;

public class OrbitMeanElementsMessageQueryTests {

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
  @DisplayName("OrbitMeanElementsMessageQuery: Credentials are required")
  public void test3() {

    assertThrows(IllegalArgumentException.class, () -> {
      OrbitMeanElementsMessageQuery.builder().credentials(null);
    });
  }
}
