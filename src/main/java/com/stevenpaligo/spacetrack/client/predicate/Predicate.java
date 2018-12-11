package com.stevenpaligo.spacetrack.client.predicate;

import java.time.format.DateTimeFormatter;

public interface Predicate<T extends Enum<T>> {

  final DateTimeFormatter PredicateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");


  public String toQueryParameter();
}
