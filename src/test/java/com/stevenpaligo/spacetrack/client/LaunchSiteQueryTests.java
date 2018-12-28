package com.stevenpaligo.spacetrack.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.LaunchSiteQuery.LaunchSite;
import com.stevenpaligo.spacetrack.client.LaunchSiteQuery.LaunchSiteQueryField;

public class LaunchSiteQueryTests {

  @Test
  @DisplayName("LaunchSiteQuery: Result type matches the Space Track schema")
  public void test1() {

    assertDoesNotThrow(() -> {
      ResultTypeValidator.validate(LaunchSite.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/launch_site/format/json"));
    });
  }


  @Test
  @DisplayName("LaunchSiteQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {
      QueryFieldEnumValidator.validate(LaunchSiteQueryField.class, LaunchSite.class);
    });
  }


  @Test
  @DisplayName("LaunchSiteQuery: Credentials are required")
  public void test3() {

    assertThrows(IllegalArgumentException.class, () -> {
      LaunchSiteQuery.builder().credentials(null);
    });
  }
}
