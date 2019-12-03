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
import org.threeten.extra.scale.TaiInstant;
import org.threeten.extra.scale.UtcInstant;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.util.SpaceTrackDateTimeFormatter;
import lombok.Getter;
import lombok.NonNull;

/**
 * A {@link Predicate} that filters results based on whether or not the given field is less than the given value
 * 
 * @author Steven Paligo
 */
@Getter
public class LessThan<T extends QueryField> implements Predicate<T> {

  private T field;
  private String value;


  public LessThan(@NonNull T field, @NonNull String value) {

    this.field = field;
    this.value = value;
  }


  public LessThan(@NonNull T field, @NonNull Number value) {

    this.field = field;
    this.value = value.toString();
  }


  /**
   * Create the predicate from a {@link Date} (UTC-SLS)
   * 
   * <p>
   * <strong>Note:</strong> The conversion from UTC-SLS to UTC will not be completely accurate near a leap second. Use {@link #LessThan(QueryField, UtcInstant)} or {@link #LessThan(QueryField, TaiInstant)} if possible.
   * </p>
   */
  public LessThan(@NonNull T field, @NonNull Date value) {

    this.field = field;
    this.value = SpaceTrackDateTimeFormatter.format(value);
  }


  /**
   * Create the predicate from an {@link Instant} (UTC-SLS)
   * 
   * <p>
   * <strong>Note:</strong> The conversion from UTC-SLS to UTC will not be completely accurate near a leap second. Use {@link #LessThan(QueryField, UtcInstant)} or {@link #LessThan(QueryField, TaiInstant)} if possible.
   * </p>
   */
  public LessThan(@NonNull T field, @NonNull Instant value) {

    this.field = field;
    this.value = SpaceTrackDateTimeFormatter.format(value);
  }


  public LessThan(@NonNull T field, @NonNull UtcInstant value) {

    this.field = field;
    this.value = SpaceTrackDateTimeFormatter.format(value);
  }


  public LessThan(@NonNull T field, @NonNull TaiInstant value) {

    this.field = field;
    this.value = SpaceTrackDateTimeFormatter.format(value);
  }


  public LessThan(@NonNull T field, @NonNull CurrentDateTimeOffset currentDateTimeOffset) {

    this.field = field;
    this.value = currentDateTimeOffset.toQueryValue();
  }


  public String toQueryParameter() {
    return field.getQueryFieldName() + "/<" + value;
  }
}
