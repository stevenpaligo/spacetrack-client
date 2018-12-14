package com.stevenpaligo.spacetrack.client.predicate;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import com.stevenpaligo.spacetrack.client.query.QueryField;

public class In<T extends QueryField> implements Predicate<T> {

  private T field;
  private String values;


  public In(T field, String... values) {
    this(field, Arrays.asList(values), String.class);
  }


  public In(T field, Date... values) {
    this(field, Arrays.asList(values), Date.class);
  }


  public In(T field, Instant... values) {
    this(field, Arrays.asList(values), Instant.class);
  }


  public In(T field, Number... values) {
    this(field, Arrays.asList(values), Number.class);
  }


  @SuppressWarnings("unchecked")
  public <V> In(T field, Collection<V> values, Class<V> valueType) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (values == null) {
      throw new IllegalArgumentException("The values collection is null");
    } else if (valueType == null) {
      throw new IllegalArgumentException("The value type is null");
    } else if (valueType != String.class && valueType != Date.class && valueType != Instant.class && Number.class.isAssignableFrom(valueType) == false) {
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
