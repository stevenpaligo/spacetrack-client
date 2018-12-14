package com.stevenpaligo.spacetrack.client.predicate;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import com.stevenpaligo.spacetrack.client.query.QueryField;

public interface Predicate<T extends QueryField> {

  final DateTimeFormatter PredicateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneId.of("UTC"));


  public String toQueryParameter();
}
