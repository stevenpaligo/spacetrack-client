package com.stevenpaligo.spacetrack.client.query;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LimitTests {

  @Test
  @DisplayName("Limit: Constructor parameter validation")
  public void test1() {

    // disallowed values
    assertThrows(IllegalArgumentException.class, () -> {
      new Limit(null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Limit(-1);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Limit(0);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Limit(null, 1);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Limit(-1, 1);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Limit(0, 1);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Limit(1, null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new Limit(1, -1);
    });


    // allowed values
    assertDoesNotThrow(() -> {
      new Limit(1);
    });

    assertDoesNotThrow(() -> {
      new Limit(1, 0);
    });

    assertDoesNotThrow(() -> {
      new Limit(1, 1);
    });
  }


  @Test
  @DisplayName("Limit: Query parameter format and correct contents")
  public void test2() {

    // max results only
    assertEquals("limit/1", new Limit(1).toQueryParameter());


    // max results and offset
    assertEquals("limit/1,2", new Limit(1, 2).toQueryParameter());
  }
}
