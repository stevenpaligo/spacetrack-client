package com.stevenpaligo.spacetrack.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.AnnouncementQuery.Announcement;
import com.stevenpaligo.spacetrack.client.AnnouncementQuery.AnnouncementQueryField;

public class AnnouncementQueryTests {

  @Test
  @DisplayName("AnnouncementQuery: Result type matches the Space Track schema")
  public void test1() {

    assertDoesNotThrow(() -> {
      ResultTypeValidator.validate(Announcement.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/announcement/format/json"));
    });
  }


  @Test
  @DisplayName("AnnouncementQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {
      QueryFieldEnumValidator.validate(AnnouncementQueryField.class, Announcement.class);
    });
  }


  @Test
  @DisplayName("AnnouncementQuery: Credentials are required")
  public void test3() {

    assertThrows(IllegalArgumentException.class, () -> {
      AnnouncementQuery.builder().credentials(null);
    });
  }
}
