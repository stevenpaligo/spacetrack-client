package com.stevenpaligo.spacetrack.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.LatestTleQuery.LatestTle;
import com.stevenpaligo.spacetrack.client.LatestTleQuery.LatestTleQueryField;

public class LatestTleQueryTests {

  @Test
  @DisplayName("LatestTleQuery: Result type matches the Space Track schema")
  public void test1() {

    assertDoesNotThrow(() -> {
      ResultTypeValidator.validate(LatestTle.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/tle_latest/format/json"));
    });
  }


  @Test
  @DisplayName("LatestTleQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {
      QueryFieldEnumValidator.validate(LatestTleQueryField.class, LatestTle.class);
    });
  }


  @Test
  @DisplayName("LatestTleQuery: Credentials are required")
  public void test3() {

    assertThrows(IllegalArgumentException.class, () -> {
      LatestTleQuery.builder().credentials(null);
    });
  }
}
