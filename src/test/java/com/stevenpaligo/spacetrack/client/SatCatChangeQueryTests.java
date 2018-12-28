package com.stevenpaligo.spacetrack.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.SatCatChangeQuery.SatCatChange;
import com.stevenpaligo.spacetrack.client.SatCatChangeQuery.SatCatChangeQueryField;

public class SatCatChangeQueryTests {

  @Test
  @DisplayName("SatCatChangeQuery: Result type matches the Space Track schema")
  public void test1() {

    assertDoesNotThrow(() -> {
      ResultTypeValidator.validate(SatCatChange.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/satcat_change/format/json"));
    });
  }


  @Test
  @DisplayName("SatCatChangeQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {
      QueryFieldEnumValidator.validate(SatCatChangeQueryField.class, SatCatChange.class);
    });
  }


  @Test
  @DisplayName("SatCatChangeQuery: Credentials are required")
  public void test3() {

    assertThrows(IllegalArgumentException.class, () -> {
      SatCatChangeQuery.builder().credentials(null);
    });
  }
}
