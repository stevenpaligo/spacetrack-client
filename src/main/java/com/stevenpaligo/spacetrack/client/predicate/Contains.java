package com.stevenpaligo.spacetrack.client.predicate;

import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.NonNull;

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
