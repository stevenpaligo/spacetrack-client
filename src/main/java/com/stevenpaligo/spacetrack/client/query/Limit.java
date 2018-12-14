package com.stevenpaligo.spacetrack.client.query;

import java.util.Optional;
import lombok.NonNull;

public class Limit {

  private Integer maxResults;
  private Optional<Integer> offset;


  public Limit(@NonNull Integer maxResults) {

    // more validation
    if (maxResults <= 0) {
      throw new IllegalArgumentException("The maxResults parameter is not positive: " + maxResults);
    }


    this.maxResults = maxResults;
    this.offset = Optional.empty();
  }


  public Limit(@NonNull Integer maxResults, @NonNull Integer offset) {

    // more validation
    if (maxResults <= 0) {
      throw new IllegalArgumentException("The maxResults parameter is not positive: " + maxResults);
    } else if (offset < 0) {
      throw new IllegalArgumentException("The offset is not >= 0: " + offset);
    }


    this.maxResults = maxResults;
    this.offset = Optional.of(offset);
  }


  public String toQueryParameter() {

    String result = "limit/" + maxResults.toString();

    if (offset.isPresent()) {
      result += "," + offset.get().toString();
    }

    return result;
  }
}
