package com.stevenpaligo.spacetrack.client.predicate;

public class IsNotNull<T extends Enum<T>> implements Predicate<T> {

  private T field;


  public IsNotNull(T field) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    }


    this.field = field;
  }


  public String toQueryParameter() {
    return field.toString() + "/<>null-val";
  }
}
