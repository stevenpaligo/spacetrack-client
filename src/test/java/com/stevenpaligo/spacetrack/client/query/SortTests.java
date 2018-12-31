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
