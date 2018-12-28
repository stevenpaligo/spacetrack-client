package com.stevenpaligo.spacetrack.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.SatCatDebutQuery.SatCatDebut;
import com.stevenpaligo.spacetrack.client.SatCatDebutQuery.SatCatDebutQueryField;

public class SatCatDebutQueryTests {

  @Test
  @DisplayName("SatCatDebutQuery: Result type matches the Space Track schema")
  public void test1() {

    assertDoesNotThrow(() -> {
      ResultTypeValidator.validate(SatCatDebut.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/satcat_debut/format/json"));
    });
  }


  @Test
  @DisplayName("SatCatDebutQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {
      QueryFieldEnumValidator.validate(SatCatDebutQueryField.class, SatCatDebut.class);
    });
  }


  @Test
  @DisplayName("SatCatDebutQuery: Credentials are required")
  public void test3() {

    assertThrows(IllegalArgumentException.class, () -> {
      SatCatDebutQuery.builder().credentials(null);
    });
  }
}
