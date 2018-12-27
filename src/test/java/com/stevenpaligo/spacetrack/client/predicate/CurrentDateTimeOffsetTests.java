package com.stevenpaligo.spacetrack.client.predicate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CurrentDateTimeOffsetTests {

  @Test
  @DisplayName("CurrentDateTimeOffset: Constructor parameter validation")
  public void test1() {

    // disallowed value
    assertThrows(IllegalArgumentException.class, () -> {
      new CurrentDateTimeOffset(null);
    });


    // allowed value
    assertDoesNotThrow(() -> {
      new CurrentDateTimeOffset(1.0);
    });
  }


  @Test
  @DisplayName("CurrentDateTimeOffset: Correct contents")
  public void test2() {

    // string value
    assertEquals((Double) 1.0, new CurrentDateTimeOffset(1.0).getOffsetDays());
  }
}
