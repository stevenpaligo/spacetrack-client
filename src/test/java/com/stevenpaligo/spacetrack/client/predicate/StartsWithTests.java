package com.stevenpaligo.spacetrack.client.predicate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.query.QueryField;

public class StartsWithTests {

  @Test
  @DisplayName("StartsWith: Constructor parameter validation")
  public void test1() {

    // disallowed values
    assertThrows(IllegalArgumentException.class, () -> {
      new StartsWith<>(null, "ABC");
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new StartsWith<>(new TestQueryField(), (String) null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new StartsWith<>(null, 1);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new StartsWith<>(new TestQueryField(), (Integer) null);
    });


    // allowed values
    assertDoesNotThrow(() -> {
      new StartsWith<>(new TestQueryField(), "ABC");
    });

    assertDoesNotThrow(() -> {
      new StartsWith<>(new TestQueryField(), 1);
    });
  }


  @Test
  @DisplayName("StartsWith: Query parameter format and correct contents")
  public void test2() {

    // string value
    assertEquals("NORAD_CAT_ID/^ABC", new StartsWith<>(new TestQueryField(), "ABC").toQueryParameter());


    // numeric value
    assertEquals("NORAD_CAT_ID/^1", new StartsWith<>(new TestQueryField(), 1).toQueryParameter());
  }


  private static class TestQueryField implements QueryField {

    @Override
    public String getQueryFieldName() {
      return "NORAD_CAT_ID";
    }
  }
}
