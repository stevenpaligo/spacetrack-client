package com.stevenpaligo.spacetrack.client.predicate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.query.QueryField;

public class IsNotNullTests {

  @Test
  @DisplayName("IsNotNull: Constructor parameter validation")
  public void test1() {

    // disallowed values
    assertThrows(IllegalArgumentException.class, () -> {
      new IsNotNull<>(null);
    });


    // allowed values
    assertDoesNotThrow(() -> {
      new IsNotNull<>(new TestQueryField());
    });
  }


  @Test
  @DisplayName("IsNotNull: Query parameter format and correct contents")
  public void test2() {

    assertEquals("NORAD_CAT_ID/<>null-val", new IsNotNull<>(new TestQueryField()).toQueryParameter());
  }


  private static class TestQueryField implements QueryField {

    @Override
    public String getQueryFieldName() {
      return "NORAD_CAT_ID";
    }
  }
}
