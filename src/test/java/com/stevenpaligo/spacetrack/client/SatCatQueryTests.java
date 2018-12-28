package com.stevenpaligo.spacetrack.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.SatCatQuery.SatCat;
import com.stevenpaligo.spacetrack.client.SatCatQuery.SatCatQueryField;

public class SatCatQueryTests {

  @Test
  @DisplayName("SatCatQuery: Result type matches the Space Track schema")
  public void test1() {

    assertDoesNotThrow(() -> {
      ResultTypeValidator.validate(SatCat.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/satcat/format/json"));
    });
  }


  @Test
  @DisplayName("SatCatQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {
      QueryFieldEnumValidator.validate(SatCatQueryField.class, SatCat.class);
    });
  }


  @Test
  @DisplayName("SatCatQuery: Credentials are required")
  public void test3() {

    assertThrows(IllegalArgumentException.class, () -> {
      SatCatQuery.builder().credentials(null);
    });
  }
}
