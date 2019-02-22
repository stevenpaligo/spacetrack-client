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
import lombok.NonNull;

/**
 * A {@link Predicate} that filters results based on whether or not the given field is within the given range of values (inclusive)
 * 
 * @author Steven Paligo
 */
public class InclusiveRange<T extends QueryField> implements Predicate<T> {

  private T field;
  private String lowerValue;
  private String upperValue;


  public InclusiveRange(@NonNull T field, @NonNull Number lowerValue, @NonNull Number upperValue) {

    this.field = field;
    this.lowerValue = lowerValue.toString();
    this.upperValue = upperValue.toString();
  }


  /**
   * Create the predicate from two {@link Date} (UTC-SLS) objects
   * 
   * <p>
   * <strong>Note:</strong> The conversion from UTC-SLS to UTC will not be completely accurate near a leap second. Use {@link #InclusiveRange(QueryField, UtcInstant, UtcInstant)} or {@link #InclusiveRange(QueryField, TaiInstant, TaiInstant)} if
   * possible.
   * </p>
   */
  public InclusiveRange(@NonNull T field, @NonNull Date lowerValue, @NonNull Date upperValue) {

    this.field = field;
    this.lowerValue = SpaceTrackDateTimeFormatter.format(lowerValue);
    this.upperValue = SpaceTrackDateTimeFormatter.format(upperValue);
  }


  /**
   * Create the predicate from a {@link CurrentDateTimeOffset} and a {@link Date} (UTC-SLS)
   * 
   * <p>
   * <strong>Note:</strong> The conversion from UTC-SLS to UTC will not be completely accurate near a leap second. Use {@link #InclusiveRange(QueryField, CurrentDateTimeOffset, UtcInstant)} or
   * {@link #InclusiveRange(QueryField, CurrentDateTimeOffset, TaiInstant)} if possible.
   * </p>
   */
  public InclusiveRange(@NonNull T field, @NonNull CurrentDateTimeOffset lowerValue, @NonNull Date upperValue) {

    this.field = field;
    this.lowerValue = lowerValue.toQueryValue();
    this.upperValue = SpaceTrackDateTimeFormatter.format(upperValue);
  }


  /**
   * Create the predicate from a {@link Date} (UTC-SLS) and a {@link CurrentDateTimeOffset}
   * 
   * <p>
   * <strong>Note:</strong> The conversion from UTC-SLS to UTC will not be completely accurate near a leap second. Use {@link #InclusiveRange(QueryField, UtcInstant, CurrentDateTimeOffset)} or
   * {@link #InclusiveRange(QueryField, TaiInstant, CurrentDateTimeOffset)} if possible.
   * </p>
   */
  public InclusiveRange(@NonNull T field, @NonNull Date lowerValue, @NonNull CurrentDateTimeOffset upperValue) {

    this.field = field;
    this.lowerValue = SpaceTrackDateTimeFormatter.format(lowerValue);
    this.upperValue = upperValue.toQueryValue();
  }


  /**
   * Create the predicate from two {@link Instant} (UTC-SLS) objects
   * 
   * <p>
   * <strong>Note:</strong> The conversion from UTC-SLS to UTC will not be completely accurate near a leap second. Use {@link #InclusiveRange(QueryField, UtcInstant, UtcInstant)} or {@link #InclusiveRange(QueryField, TaiInstant, TaiInstant)} if
   * possible.
   * </p>
   */
  public InclusiveRange(@NonNull T field, @NonNull Instant lowerValue, @NonNull Instant upperValue) {

    this.field = field;
    this.lowerValue = SpaceTrackDateTimeFormatter.format(lowerValue);
    this.upperValue = SpaceTrackDateTimeFormatter.format(upperValue);
  }


  /**
   * Create the predicate from a {@link CurrentDateTimeOffset} and an {@link Instant} (UTC-SLS)
   * 
   * <p>
   * <strong>Note:</strong> The conversion from UTC-SLS to UTC will not be completely accurate near a leap second. Use {@link #InclusiveRange(QueryField, CurrentDateTimeOffset, UtcInstant)} or
   * {@link #InclusiveRange(QueryField, CurrentDateTimeOffset, TaiInstant)} if possible.
   * </p>
   */
  public InclusiveRange(@NonNull T field, @NonNull CurrentDateTimeOffset lowerValue, @NonNull Instant upperValue) {

    this.field = field;
    this.lowerValue = lowerValue.toQueryValue();
    this.upperValue = SpaceTrackDateTimeFormatter.format(upperValue);
  }


  /**
   * Create the predicate from an {@link Instant} (UTC-SLS) and a {@link CurrentDateTimeOffset}
   * 
   * <p>
   * <strong>Note:</strong> The conversion from UTC-SLS to UTC will not be completely accurate near a leap second. Use {@link #InclusiveRange(QueryField, UtcInstant, CurrentDateTimeOffset)} or
   * {@link #InclusiveRange(QueryField, TaiInstant, CurrentDateTimeOffset)} if possible.
   * </p>
   */
  public InclusiveRange(@NonNull T field, @NonNull Instant lowerValue, @NonNull CurrentDateTimeOffset upperValue) {

    this.field = field;
    this.lowerValue = SpaceTrackDateTimeFormatter.format(lowerValue);
    this.upperValue = upperValue.toQueryValue();
  }


  public InclusiveRange(@NonNull T field, @NonNull UtcInstant lowerValue, @NonNull UtcInstant upperValue) {

    this.field = field;
    this.lowerValue = SpaceTrackDateTimeFormatter.format(lowerValue);
    this.upperValue = SpaceTrackDateTimeFormatter.format(upperValue);
  }


  public InclusiveRange(@NonNull T field, @NonNull CurrentDateTimeOffset lowerValue, @NonNull UtcInstant upperValue) {

    this.field = field;
    this.lowerValue = lowerValue.toQueryValue();
    this.upperValue = SpaceTrackDateTimeFormatter.format(upperValue);
  }


  public InclusiveRange(@NonNull T field, @NonNull UtcInstant lowerValue, @NonNull CurrentDateTimeOffset upperValue) {

    this.field = field;
    this.lowerValue = SpaceTrackDateTimeFormatter.format(lowerValue);
    this.upperValue = upperValue.toQueryValue();
  }


  public InclusiveRange(@NonNull T field, @NonNull TaiInstant lowerValue, @NonNull TaiInstant upperValue) {

    this.field = field;
    this.lowerValue = SpaceTrackDateTimeFormatter.format(lowerValue);
    this.upperValue = SpaceTrackDateTimeFormatter.format(upperValue);
  }


  public InclusiveRange(@NonNull T field, @NonNull CurrentDateTimeOffset lowerValue, @NonNull TaiInstant upperValue) {

    this.field = field;
    this.lowerValue = lowerValue.toQueryValue();
    this.upperValue = SpaceTrackDateTimeFormatter.format(upperValue);
  }


  public InclusiveRange(@NonNull T field, @NonNull TaiInstant lowerValue, @NonNull CurrentDateTimeOffset upperValue) {

    this.field = field;
    this.lowerValue = SpaceTrackDateTimeFormatter.format(lowerValue);
    this.upperValue = upperValue.toQueryValue();
  }


  public InclusiveRange(@NonNull T field, @NonNull CurrentDateTimeOffset lowerValue, @NonNull CurrentDateTimeOffset upperValue) {

    this.field = field;
    this.lowerValue = lowerValue.toQueryValue();
    this.upperValue = upperValue.toQueryValue();
  }


  public String toQueryParameter() {
    return field.getQueryFieldName() + "/" + lowerValue + "--" + upperValue;
  }
}
