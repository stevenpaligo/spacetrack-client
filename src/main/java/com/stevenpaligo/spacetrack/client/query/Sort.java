package com.stevenpaligo.spacetrack.client.query;

import lombok.NonNull;

public class Sort<T extends QueryField> {

  public static enum Direction {
    ASC, DESC
  }

  private T field;
  private Direction direction;


  public Sort(@NonNull T field) {

    this.field = field;
    this.direction = Direction.ASC;
  }


  public Sort(@NonNull T field, @NonNull Direction direction) {

    this.field = field;
    this.direction = direction;
  }


  public String toQueryParameter() {

    if (direction == Direction.ASC) {
      return field.getQueryFieldName() + " asc";
    } else if (direction == Direction.DESC) {
      return field.getQueryFieldName() + " desc";
    } else {
      throw new RuntimeException("Unsupported sort direction: " + direction);
    }
  }
}
