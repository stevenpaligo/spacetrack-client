package com.stevenpaligo.spacetrack.client.query;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.query.Sort.Direction;

public class SortTests {

  @Test
  @DisplayName("Sort: Constructor parameter validation")
  public void test1() {

    // disallowed values
    assertThrows(IllegalArgumentException.class, () -> {
      new Sort<>(null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Sort<>(null, Direction.ASC);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Sort<>(new TestQueryField(), null);
    });


    // allowed values
    assertDoesNotThrow(() -> {
      new Sort<>(new TestQueryField());
    });

    assertDoesNotThrow(() -> {
      new Sort<>(new TestQueryField(), Direction.ASC);
    });
  }


  @Test
  @DisplayName("Sort: Query parameter format and correct contents")
  public void test2() {

    // field only
    assertEquals("ORDINAL asc", new Sort<>(new TestQueryField()).toQueryParameter());


    // field and direction
    assertEquals("ORDINAL desc", new Sort<>(new TestQueryField(), Direction.DESC).toQueryParameter());
  }


  private static class TestQueryField implements QueryField {

    @Override
    public String getQueryFieldName() {
      return "ORDINAL";
    }
  }
}
