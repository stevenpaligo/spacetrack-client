package com.stevenpaligo.spacetrack.client.predicate;

import java.time.Instant;
import java.util.Date;
import com.stevenpaligo.spacetrack.client.query.QueryField;

public class GreaterThan<T extends QueryField> implements Predicate<T> {

  private T field;
  private String value;


  public GreaterThan(T field, String value) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (value == null) {
      throw new IllegalArgumentException("The value is null");
    }


    this.field = field;
    this.value = value;
  }


  public GreaterThan(T field, Number value) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (value == null) {
      throw new IllegalArgumentException("The value is null");
    }


    this.field = field;
    this.value = value.toString();
  }


  public GreaterThan(T field, Date value) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (value == null) {
      throw new IllegalArgumentException("The value is null");
    }


    this.field = field;
    this.value = PredicateFormatter.format(Instant.ofEpochMilli(value.getTime()));
  }


  public GreaterThan(T field, Instant value) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (value == null) {
      throw new IllegalArgumentException("The value is null");
    }


    this.field = field;
    this.value = PredicateFormatter.format(value);
  }


  public GreaterThan(T field, CurrentDateTimeOffset currentDateTimeOffset) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (currentDateTimeOffset == null) {
      throw new IllegalArgumentException("The current date/time offset is null");
    }


    this.field = field;
    this.value = toValue(currentDateTimeOffset);
  }


  public String toQueryParameter() {
    return field.getQueryFieldName() + "/>" + value;
  }


  private static String toValue(CurrentDateTimeOffset currentDateTimeOffset) {

    if (currentDateTimeOffset.getOffsetDays() < 0.0) {
      return "now" + currentDateTimeOffset.getOffsetDays().toString();
    } else {
      return "now+" + currentDateTimeOffset.getOffsetDays().toString();
    }
  }
}
