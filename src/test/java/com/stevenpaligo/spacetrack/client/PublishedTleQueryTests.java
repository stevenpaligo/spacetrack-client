package com.stevenpaligo.spacetrack.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.PublishedTleQuery.PublishedTle;
import com.stevenpaligo.spacetrack.client.PublishedTleQuery.PublishedTleQueryField;

public class PublishedTleQueryTests {

  @Test
  @DisplayName("PublishedTleQuery: Result type matches the Space Track schema")
  public void test1() {

    assertDoesNotThrow(() -> {
      ResultTypeValidator.validate(PublishedTle.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/tle_publish/format/json"));
    });
  }


  @Test
  @DisplayName("PublishedTleQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {
      QueryFieldEnumValidator.validate(PublishedTleQueryField.class, PublishedTle.class);
    });
  }


  @Test
  @DisplayName("PublishedTleQuery: Credentials are required")
  public void test3() {

    assertThrows(IllegalArgumentException.class, () -> {
      PublishedTleQuery.builder().credentials(null);
    });
  }
}
