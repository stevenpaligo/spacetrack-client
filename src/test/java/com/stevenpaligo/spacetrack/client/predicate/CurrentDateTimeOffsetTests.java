/*
 * The author licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
