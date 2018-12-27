package com.stevenpaligo.spacetrack.client.predicate;

import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.NonNull;

public class IsNotNull<T extends QueryField> implements Predicate<T> {

  private T field;


  public IsNotNull(@NonNull T field) {
    this.field = field;
  }


  public String toQueryParameter() {
    return field.getQueryFieldName() + "/<>null-val";
  }
}
