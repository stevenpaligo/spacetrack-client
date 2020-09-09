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

import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.Getter;
import lombok.NonNull;

/**
 * A {@link Predicate} that filters results based on whether or not the given field contains the given value
 * 
 * @author Steven Paligo
 */
@Getter
public class Contains<T extends QueryField> implements Predicate<T> {

  private T field;
  private String value;


  public Contains(@NonNull T field, @NonNull String value) {

    this.field = field;
    this.value = value;
  }


  public Contains(@NonNull T field, @NonNull Number value) {

    this.field = field;
    this.value = value.toString();
  }


  public String toQueryParameter() {

    return field.getQueryFieldName() + "/~~" + value;
  }
}
