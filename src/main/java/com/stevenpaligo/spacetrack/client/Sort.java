package com.stevenpaligo.spacetrack.client;

public class Sort<T extends Enum<T>> {

  public static enum Direction {
    ASC, DESC
  }

  private T field;
  private Direction direction;


  public Sort(T field) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    }


    this.field = field;
    this.direction = Direction.ASC;
  }


  public Sort(T field, Direction direction) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (direction == null) {
      throw new IllegalArgumentException("The direction is null");
    }


    this.field = field;
    this.direction = direction;
  }


  String toQueryParameter() {

    if (direction == Direction.ASC) {
      return field.toString() + " asc";
    } else if (direction == Direction.DESC) {
      return field.toString() + " desc";
    } else {
      throw new RuntimeException("Unsupported sort direction: " + direction);
    }
  }
}
