package com.stevenpaligo.spacetrack.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.TleQuery.Tle;
import com.stevenpaligo.spacetrack.client.TleQuery.TleQueryField;

public class TleQueryTests {

  @Test
  @DisplayName("TleQuery: Result type matches the Space Track schema")
  public void test1() {

    assertDoesNotThrow(() -> {
      ResultTypeValidator.validate(Tle.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/tle/format/json"));
    });
  }


  @Test
  @DisplayName("TleQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {
      QueryFieldEnumValidator.validate(TleQueryField.class, Tle.class);
    });
  }


  @Test
  @DisplayName("TleQuery: Credentials are required")
  public void test3() {

    assertThrows(IllegalArgumentException.class, () -> {
      TleQuery.builder().credentials(null);
    });
  }
}
