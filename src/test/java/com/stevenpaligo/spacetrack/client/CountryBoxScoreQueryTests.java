package com.stevenpaligo.spacetrack.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.CountryBoxScoreQuery.CountryBoxScore;
import com.stevenpaligo.spacetrack.client.CountryBoxScoreQuery.CountryBoxScoreQueryField;

public class CountryBoxScoreQueryTests {

  @Test
  @DisplayName("CountryBoxScoreQuery: Result type matches the Space Track schema")
  public void test1() {

    assertDoesNotThrow(() -> {
      ResultTypeValidator.validate(CountryBoxScore.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/boxscore/format/json"));
    });
  }


  @Test
  @DisplayName("CountryBoxScoreQuery: Query field enum matches the result type")
  public void test2() {

    assertDoesNotThrow(() -> {
      QueryFieldEnumValidator.validate(CountryBoxScoreQueryField.class, CountryBoxScore.class);
    });
  }


  @Test
  @DisplayName("CountryBoxScoreQuery: Credentials are required")
  public void test3() {

    assertThrows(IllegalArgumentException.class, () -> {
      CountryBoxScoreQuery.builder().credentials(null);
    });
  }
}
