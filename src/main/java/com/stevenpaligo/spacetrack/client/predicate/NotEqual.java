package com.stevenpaligo.spacetrack.client.predicate;

import java.time.Instant;
import java.util.Date;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.NonNull;

public class NotEqual<T extends QueryField> implements Predicate<T> {

  private T field;
  private String value;


  public NotEqual(@NonNull T field, @NonNull String value) {

    this.field = field;
    this.value = value;
  }


  public NotEqual(@NonNull T field, @NonNull Number value) {

    this.field = field;
    this.value = value.toString();
  }


  public NotEqual(@NonNull T field, @NonNull Date value) {

    this.field = field;
    this.value = PredicateFormatter.format(Instant.ofEpochMilli(value.getTime()));
  }


  public NotEqual(@NonNull T field, @NonNull Instant value) {

    this.field = field;
    this.value = PredicateFormatter.format(value);
  }


  public NotEqual(@NonNull T field, @NonNull CurrentDateTimeOffset currentDateTimeOffset) {

    this.field = field;
    this.value = toValue(currentDateTimeOffset);
  }


  public String toQueryParameter() {
    return field.getQueryFieldName() + "/<>" + value;
  }


  private static String toValue(@NonNull CurrentDateTimeOffset currentDateTimeOffset) {

    if (currentDateTimeOffset.getOffsetDays() < 0.0) {
      return "now" + currentDateTimeOffset.getOffsetDays().toString();
    } else {
      return "now+" + currentDateTimeOffset.getOffsetDays().toString();
    }
  }
}
