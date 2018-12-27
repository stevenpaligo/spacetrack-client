package com.stevenpaligo.spacetrack.client.predicate;

import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.NonNull;

public class IsNull<T extends QueryField> implements Predicate<T> {

  private T field;


  public IsNull(@NonNull T field) {
    this.field = field;
  }


  public String toQueryParameter() {
    return field.getQueryFieldName() + "/null-val";
  }
}
