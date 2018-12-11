package com.stevenpaligo.spacetrack.client.predicate;

import java.time.Instant;
import java.util.Date;

public class InclusiveRange<T extends Enum<T>> implements Predicate<T> {

  private T field;
  private String lowerValue;
  private String upperValue;


  public InclusiveRange(T field, Number lowerValue, Number upperValue) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (lowerValue == null) {
      throw new IllegalArgumentException("The lower value is null");
    } else if (upperValue == null) {
      throw new IllegalArgumentException("The upper value is null");
    }


    this.field = field;
    this.lowerValue = lowerValue.toString();
    this.upperValue = upperValue.toString();
  }


  public InclusiveRange(T field, Date lowerValue, Date upperValue) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (lowerValue == null) {
      throw new IllegalArgumentException("The lower value is null");
    } else if (upperValue == null) {
      throw new IllegalArgumentException("The upper value is null");
    }


    this.field = field;
    this.lowerValue = PredicateFormatter.format(Instant.ofEpochMilli(lowerValue.getTime()));
    this.upperValue = PredicateFormatter.format(Instant.ofEpochMilli(upperValue.getTime()));
  }


  public InclusiveRange(T field, CurrentDateTimeOffset lowerValue, Date upperValue) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (lowerValue == null) {
      throw new IllegalArgumentException("The lower value is null");
    } else if (upperValue == null) {
      throw new IllegalArgumentException("The upper value is null");
    }


    this.field = field;
    this.lowerValue = toValue(lowerValue);
    this.upperValue = PredicateFormatter.format(Instant.ofEpochMilli(upperValue.getTime()));
  }


  public InclusiveRange(T field, Date lowerValue, CurrentDateTimeOffset upperValue) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (lowerValue == null) {
      throw new IllegalArgumentException("The lower value is null");
    } else if (upperValue == null) {
      throw new IllegalArgumentException("The upper value is null");
    }


    this.field = field;
    this.lowerValue = PredicateFormatter.format(Instant.ofEpochMilli(lowerValue.getTime()));
    this.upperValue = toValue(upperValue);
  }


  public InclusiveRange(T field, Instant lowerValue, Instant upperValue) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (lowerValue == null) {
      throw new IllegalArgumentException("The lower value is null");
    } else if (upperValue == null) {
      throw new IllegalArgumentException("The upper value is null");
    }


    this.field = field;
    this.lowerValue = PredicateFormatter.format(lowerValue);
    this.upperValue = PredicateFormatter.format(upperValue);
  }


  public InclusiveRange(T field, CurrentDateTimeOffset lowerValue, Instant upperValue) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (lowerValue == null) {
      throw new IllegalArgumentException("The lower value is null");
    } else if (upperValue == null) {
      throw new IllegalArgumentException("The upper value is null");
    }


    this.field = field;
    this.lowerValue = toValue(lowerValue);
    this.upperValue = PredicateFormatter.format(upperValue);
  }


  public InclusiveRange(T field, Instant lowerValue, CurrentDateTimeOffset upperValue) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (lowerValue == null) {
      throw new IllegalArgumentException("The lower value is null");
    } else if (upperValue == null) {
      throw new IllegalArgumentException("The upper value is null");
    }


    this.field = field;
    this.lowerValue = PredicateFormatter.format(lowerValue);
    this.upperValue = toValue(upperValue);
  }


  public InclusiveRange(T field, CurrentDateTimeOffset lowerValue, CurrentDateTimeOffset upperValue) {

    // validate
    if (field == null) {
      throw new IllegalArgumentException("The field parameter is null");
    } else if (lowerValue == null) {
      throw new IllegalArgumentException("The lower value is null");
    } else if (upperValue == null) {
      throw new IllegalArgumentException("The upper value is null");
    }


    this.field = field;
    this.lowerValue = toValue(lowerValue);
    this.upperValue = toValue(upperValue);
  }


  public String toQueryParameter() {
    return field.toString() + "/" + lowerValue + "--" + upperValue;
  }


  private static String toValue(CurrentDateTimeOffset currentDateTimeOffset) {

    if (currentDateTimeOffset.getOffsetDays() < 0.0) {
      return "now" + currentDateTimeOffset.getOffsetDays().toString();
    } else {
      return "now+" + currentDateTimeOffset.getOffsetDays().toString();
    }
  }
}
