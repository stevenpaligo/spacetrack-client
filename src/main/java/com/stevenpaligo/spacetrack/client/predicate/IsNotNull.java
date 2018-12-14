package com.stevenpaligo.spacetrack.client.predicate;

import com.stevenpaligo.spacetrack.client.query.QueryField;

public class IsNotNull<T extends QueryField> implements Predicate<T> {

  private T field;


  public IsNotNull(T field) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    }


    this.field = field;
  }


  public String toQueryParameter() {
    return field.getQueryFieldName() + "/<>null-val";
  }
}
