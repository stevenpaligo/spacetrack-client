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
    assertEquals("1", new Limit(1).toQueryParameter());


    // max results and offset
    assertEquals("1,2", new Limit(1, 2).toQueryParameter());
  }
}
