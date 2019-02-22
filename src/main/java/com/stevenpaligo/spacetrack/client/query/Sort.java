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

import lombok.NonNull;

/**
 * A query clause for sorting the results returned from a query
 * 
 * @author Steven Paligo
 */
public class Sort<T extends QueryField> {

  public static enum Direction {
    ASC, DESC
  }

  private T field;
  private Direction direction;


  public Sort(@NonNull T field) {

    this.field = field;
    this.direction = Direction.ASC;
  }


  public Sort(@NonNull T field, @NonNull Direction direction) {

    this.field = field;
    this.direction = direction;
  }


  public String toQueryParameter() {

    if (direction == Direction.ASC) {
      return field.getQueryFieldName() + " asc";
    } else if (direction == Direction.DESC) {
      return field.getQueryFieldName() + " desc";
    } else {
      throw new RuntimeException("Unsupported sort direction: " + direction);
    }
  }
}
