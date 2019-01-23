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

import java.util.Optional;
import lombok.NonNull;

public class Limit {

  public static final Limit ONE = new Limit(1);
  public static final Limit TEN = new Limit(10);


  private Integer maxResults;
  private Optional<Integer> offset;


  public Limit(@NonNull Integer maxResults) {

    // more validation
    if (maxResults <= 0) {
      throw new IllegalArgumentException("The maxResults parameter is not positive: " + maxResults);
    }


    this.maxResults = maxResults;
    this.offset = Optional.empty();
  }


  public Limit(@NonNull Integer maxResults, @NonNull Integer offset) {

    // more validation
    if (maxResults <= 0) {
      throw new IllegalArgumentException("The maxResults parameter is not positive: " + maxResults);
    } else if (offset < 0) {
      throw new IllegalArgumentException("The offset is not >= 0: " + offset);
    }


    this.maxResults = maxResults;
    this.offset = Optional.of(offset);
  }


  public String toQueryParameter() {

    String result = maxResults.toString();

    if (offset.isPresent()) {
      result += "," + offset.get().toString();
    }

    return result;
  }
}
