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

import java.time.Instant;
import java.util.Date;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.NonNull;

public class GreaterThan<T extends QueryField> implements Predicate<T> {

  private T field;
  private String value;


  public GreaterThan(@NonNull T field, @NonNull String value) {

    this.field = field;
    this.value = value;
  }


  public GreaterThan(@NonNull T field, @NonNull Number value) {

    this.field = field;
    this.value = value.toString();
  }


  public GreaterThan(@NonNull T field, @NonNull Date value) {

    this.field = field;
    this.value = PredicateFormatter.format(Instant.ofEpochMilli(value.getTime()));
  }


  public GreaterThan(@NonNull T field, @NonNull Instant value) {

    this.field = field;
    this.value = PredicateFormatter.format(value);
  }


  public GreaterThan(@NonNull T field, @NonNull CurrentDateTimeOffset currentDateTimeOffset) {

    this.field = field;
    this.value = toValue(currentDateTimeOffset);
  }


  public String toQueryParameter() {
    return field.getQueryFieldName() + "/>" + value;
  }


  private static String toValue(@NonNull CurrentDateTimeOffset currentDateTimeOffset) {

    if (currentDateTimeOffset.getOffsetDays() < 0.0) {
      return "now" + currentDateTimeOffset.getOffsetDays().toString();
    } else {
      return "now+" + currentDateTimeOffset.getOffsetDays().toString();
    }
  }
}
