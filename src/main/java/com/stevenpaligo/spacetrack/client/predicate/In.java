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
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.NonNull;

public class In<T extends QueryField> implements Predicate<T> {

  private T field;
  private String values;


  public In(@NonNull T field, @NonNull String... values) {
    this(field, Arrays.asList(values), String.class);
  }


  public In(@NonNull T field, @NonNull Date... values) {
    this(field, Arrays.asList(values), Date.class);
  }


  public In(@NonNull T field, @NonNull Instant... values) {
    this(field, Arrays.asList(values), Instant.class);
  }


  public In(@NonNull T field, @NonNull Number... values) {
    this(field, Arrays.asList(values), Number.class);
  }


  @SuppressWarnings("unchecked")
  public <V> In(@NonNull T field, @NonNull Collection<V> values, @NonNull Class<V> valueType) {

    // more validation
    if (valueType != String.class && valueType != Date.class && valueType != Instant.class && Number.class.isAssignableFrom(valueType) == false) {
      throw new IllegalArgumentException("The values collection does not contain strings, numbers, dates, or instants: " + valueType);
    }


    // save the field
    this.field = field;


    // make sure the values are unique and convert them to a single string
    Set<V> uniqueValues;

    if (values instanceof Set) {
      uniqueValues = (Set<V>) values;
    } else {
      uniqueValues = new HashSet<>(values);
    }

    if (valueType == String.class) {
      this.values = String.join(",", (Set<String>) uniqueValues);
    } else if (valueType == Date.class) {
      this.values = uniqueValues.stream().map(v -> PredicateFormatter.format(Instant.ofEpochMilli(((Date) v).getTime()))).collect(Collectors.joining(","));
    } else if (valueType == Instant.class) {
      this.values = uniqueValues.stream().map(v -> PredicateFormatter.format((Instant) v)).collect(Collectors.joining(","));
    } else if (Number.class.isAssignableFrom(valueType)) {
      this.values = uniqueValues.stream().map(v -> ((Number) v).toString()).collect(Collectors.joining(","));
    } else {
      throw new RuntimeException("Unsupported value type: " + valueType);
    }
  }


  public String toQueryParameter() {
    return field.getQueryFieldName() + "/" + values;
  }
}
