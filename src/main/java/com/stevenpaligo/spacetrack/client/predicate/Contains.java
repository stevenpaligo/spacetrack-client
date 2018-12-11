package com.stevenpaligo.spacetrack.client.predicate;

public class Contains<T extends Enum<T>> implements Predicate<T> {

  private T field;
  private String value;


  public Contains(T field, String value) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (value == null) {
      throw new IllegalArgumentException("The value is null");
    }


    this.field = field;
    this.value = value;
  }


  public Contains(T field, Number value) {

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
    return field.toString() + "/~~" + value;
  }
}
