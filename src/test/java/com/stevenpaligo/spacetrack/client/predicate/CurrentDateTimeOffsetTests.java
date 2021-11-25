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
import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.stevenpaligo.spacetrack.DelayBeforeEachTestExtension;
import com.stevenpaligo.spacetrack.TestUtils;
import com.stevenpaligo.spacetrack.client.LatestTleQuery;
import com.stevenpaligo.spacetrack.client.LatestTleQuery.LatestTleQueryField;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;

@ExtendWith(DelayBeforeEachTestExtension.class)
public class CurrentDateTimeOffsetTests {

  private static CredentialProvider credentials = TestUtils.getCredentials();


  @Test
  @DisplayName("CurrentDateTimeOffset: Constructor parameter validation")
  public void test1() {

    // disallowed value
    assertThrows(IllegalArgumentException.class, () -> {
      new CurrentDateTimeOffset(null);
    });


    // allowed value
    assertDoesNotThrow(() -> {
      new CurrentDateTimeOffset(Duration.ofDays(1));
    });
  }


  @Test
  @DisplayName("CurrentDateTimeOffset: Correct contents")
  public void test2() {

    // verify the query value when the offset is specified in days
    assertEquals("now+1.0", new CurrentDateTimeOffset(Duration.ofDays(1)).toQueryValue());
    assertEquals("now-1.0", new CurrentDateTimeOffset(Duration.ofDays(-1)).toQueryValue());


    // verify the query value when the offset is specified in units smaller than a day
    assertEquals("now+0.020833333333333332", new CurrentDateTimeOffset(Duration.ofMinutes(30)).toQueryValue());
    assertEquals("now-0.020833333333333332", new CurrentDateTimeOffset(Duration.ofMinutes(-30)).toQueryValue());
  }


  @Test
  @DisplayName("CurrentDateTimeOffset: Getters")
  public void test3() {

    // offset
    assertEquals(Duration.ofDays(1), new CurrentDateTimeOffset(Duration.ofDays(1)).getOffset());
  }


  @Test
  @DisplayName("CurrentDateTimeOffset: Successful call")
  public void test4() {

    assertDoesNotThrow(() -> {

      new LatestTleQuery().setCredentials(credentials).addPredicate(new InclusiveRange<>(LatestTleQueryField.EPOCH_YMD_HMS, new CurrentDateTimeOffset(Duration.ofMinutes(144)), new CurrentDateTimeOffset(Duration.ofMinutes(0)))).execute();
    });
  }
}
