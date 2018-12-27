package com.stevenpaligo.spacetrack.client.predicate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.query.QueryField;

public class ContainsTests {

  @Test
  @DisplayName("Contains: Constructor parameter validation")
  public void test1() {

    // disallowed values
    assertThrows(IllegalArgumentException.class, () -> {
      new Contains<>(null, "TEST");
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Contains<>(new TestQueryField(), (String) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Contains<>(null, 1);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Contains<>(new TestQueryField(), (Integer) null);
    });


    // allowed values
    assertDoesNotThrow(() -> {
      new Contains<>(new TestQueryField(), "TEST");
    });

    assertDoesNotThrow(() -> {
      new Contains<>(new TestQueryField(), 1);
    });
  }


  @Test
  @DisplayName("Contains: Query parameter format and correct contents")
  public void test2() {

    // string value
    assertEquals("NORAD_CAT_ID/~~ABC", new Contains<>(new TestQueryField(), "ABC").toQueryParameter());


    // numeric value
    assertEquals("NORAD_CAT_ID/~~255", new Contains<>(new TestQueryField(), 255).toQueryParameter());
  }


  private static class TestQueryField implements QueryField {

    @Override
    public String getQueryFieldName() {
      return "NORAD_CAT_ID";
    }
  }
}
