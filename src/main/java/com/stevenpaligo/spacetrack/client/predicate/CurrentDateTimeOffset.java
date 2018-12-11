package com.stevenpaligo.spacetrack.client.predicate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter(AccessLevel.PACKAGE)
@AllArgsConstructor
public class CurrentDateTimeOffset {

  @NonNull
  private Double offsetDays;

}
