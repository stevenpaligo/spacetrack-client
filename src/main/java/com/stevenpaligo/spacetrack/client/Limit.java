package com.stevenpaligo.spacetrack.client;

import java.util.Optional;

public class Limit {

  private Integer maxResults;
  private Optional<Integer> offset;


  public Limit(Integer maxResults) {

    // validate
    if (maxResults == null) {
      throw new IllegalArgumentException("The max results parameter is null");
    }


    this.maxResults = maxResults;
  }


  public Limit(Integer maxResults, Integer offset) {

    // validate
    if (maxResults == null) {
      throw new IllegalArgumentException("The max results parameter is null");
    } else if (offset == null) {
      throw new IllegalArgumentException("The offset is null");
    }


    this.maxResults = maxResults;
    this.offset = Optional.of(offset);
  }


  String toQueryParameter() {

    if (offset.isPresent()) {
      return maxResults.toString() + "," + offset.get().toString();
    } else {
      return maxResults.toString();
    }
  }
}
