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

import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * A value that represents an offset from the current date/time
 * 
 * @author Steven Paligo
 */
@Getter
@AllArgsConstructor
public class CurrentDateTimeOffset {

  @NonNull
  private Duration offset;


  public String toQueryValue() {

    double offsetDays = offset.toMillis() / 86400000.0; // TODO: this assumes a certain number of seconds in a day, which will not be correct on days with leap seconds

    if (offsetDays < 0.0) {
      return "now" + offsetDays; // no hyphen is needed because the offset is negative
    } else {
      return "now+" + offsetDays;
    }
  }
}
