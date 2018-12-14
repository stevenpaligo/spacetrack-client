package com.stevenpaligo.spacetrack.client.predicate;

import com.stevenpaligo.spacetrack.client.query.QueryField;

public class StartsWith<T extends QueryField> implements Predicate<T> {

  private T field;
  private String value;


  public StartsWith(T field, String value) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (value == null) {
      throw new IllegalArgumentException("The value is null");
    }


    this.field = field;
    this.value = value;
  }


  public StartsWith(T field, Number value) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (value == null) {
      throw new IllegalArgumentException("The value is null");
    }


    this.field = field;
    this.value = value.toString();
  }


  public String toQueryParameter() {
    return field.getQueryFieldName() + "/^" + value;
  }
}
