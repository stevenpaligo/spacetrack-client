package com.stevenpaligo.spacetrack.client.predicate;

import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.NonNull;

public class StartsWith<T extends QueryField> implements Predicate<T> {

  private T field;
  private String value;


  public StartsWith(@NonNull T field, @NonNull String value) {

    this.field = field;
    this.value = value;
  }


  public StartsWith(@NonNull T field, @NonNull Number value) {

    this.field = field;
    this.value = value.toString();
  }


  public String toQueryParameter() {
    return field.getQueryFieldName() + "/^" + value;
  }
}
