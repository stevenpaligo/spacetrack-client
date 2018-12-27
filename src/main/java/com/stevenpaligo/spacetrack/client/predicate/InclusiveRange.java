package com.stevenpaligo.spacetrack.client.predicate;

import java.time.Instant;
import java.util.Date;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.NonNull;

public class InclusiveRange<T extends QueryField> implements Predicate<T> {

  private T field;
  private String lowerValue;
  private String upperValue;


  public InclusiveRange(@NonNull T field, @NonNull Number lowerValue, @NonNull Number upperValue) {

    this.field = field;
    this.lowerValue = lowerValue.toString();
    this.upperValue = upperValue.toString();
  }


  public InclusiveRange(@NonNull T field, @NonNull Date lowerValue, @NonNull Date upperValue) {

    this.field = field;
    this.lowerValue = PredicateFormatter.format(Instant.ofEpochMilli(lowerValue.getTime()));
    this.upperValue = PredicateFormatter.format(Instant.ofEpochMilli(upperValue.getTime()));
  }


  public InclusiveRange(@NonNull T field, @NonNull CurrentDateTimeOffset lowerValue, @NonNull Date upperValue) {

    this.field = field;
    this.lowerValue = toValue(lowerValue);
    this.upperValue = PredicateFormatter.format(Instant.ofEpochMilli(upperValue.getTime()));
  }


  public InclusiveRange(@NonNull T field, @NonNull Date lowerValue, @NonNull CurrentDateTimeOffset upperValue) {

    this.field = field;
    this.lowerValue = PredicateFormatter.format(Instant.ofEpochMilli(lowerValue.getTime()));
    this.upperValue = toValue(upperValue);
  }


  public InclusiveRange(@NonNull T field, @NonNull Instant lowerValue, @NonNull Instant upperValue) {

    this.field = field;
    this.lowerValue = PredicateFormatter.format(lowerValue);
    this.upperValue = PredicateFormatter.format(upperValue);
  }


  public InclusiveRange(@NonNull T field, @NonNull CurrentDateTimeOffset lowerValue, @NonNull Instant upperValue) {

    this.field = field;
    this.lowerValue = toValue(lowerValue);
    this.upperValue = PredicateFormatter.format(upperValue);
  }


  public InclusiveRange(@NonNull T field, @NonNull Instant lowerValue, @NonNull CurrentDateTimeOffset upperValue) {

    this.field = field;
    this.lowerValue = PredicateFormatter.format(lowerValue);
    this.upperValue = toValue(upperValue);
  }


  public InclusiveRange(@NonNull T field, @NonNull CurrentDateTimeOffset lowerValue, @NonNull CurrentDateTimeOffset upperValue) {

    this.field = field;
    this.lowerValue = toValue(lowerValue);
    this.upperValue = toValue(upperValue);
  }


  public String toQueryParameter() {
    return field.getQueryFieldName() + "/" + lowerValue + "--" + upperValue;
  }


  private static String toValue(@NonNull CurrentDateTimeOffset currentDateTimeOffset) {

    if (currentDateTimeOffset.getOffsetDays() < 0.0) {
      return "now" + currentDateTimeOffset.getOffsetDays().toString();
    } else {
      return "now+" + currentDateTimeOffset.getOffsetDays().toString();
    }
  }
}
