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
package com.stevenpaligo.spacetrack.client.credential;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.stevenpaligo.spacetrack.DelayBeforeEachTestExtension;

@ExtendWith(DelayBeforeEachTestExtension.class)
public class DefaultCredentialProviderTests {

  @Test
  @DisplayName("DefaultCredentialProvider: Constructor parameter validation")
  public void test1() {

    // disallowed values
    assertThrows(IllegalArgumentException.class, () -> {
      new DefaultCredentialProvider(null, "password");
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new DefaultCredentialProvider("user", null);
    });


    // allowed values
    assertDoesNotThrow(() -> {
      new DefaultCredentialProvider("user", "password");
    });
  }


  @Test
  @DisplayName("DefaultCredentialProvider: Correct contents")
  public void test2() {

    assertEquals("user", new DefaultCredentialProvider("user", "password").getUserName());
    assertEquals("password", new DefaultCredentialProvider("user", "password").getPassword());
  }
}
