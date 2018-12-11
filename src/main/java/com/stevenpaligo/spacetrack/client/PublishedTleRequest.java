package com.stevenpaligo.spacetrack.client;

import java.io.IOException;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.SetUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.predicate.Predicate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;

@Builder
public class PublishedTleRequest {

  @NonNull
  private CredentialProvider credentials;

  @Singular
  private Collection<Predicate<PublishedTleFields>> predicates;

  private Limit limit;

  @Singular
  private List<Sort<PublishedTleFields>> sorts;

  @Singular
  private Set<String> favorites;


  public List<PublishedTle> execute() throws JsonParseException, JsonMappingException, IOException {

    // create a request
    Request<PublishedTleFields, PublishedTle> request = new Request<>(credentials, "tle_publish", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));


    // execute the request and return the results
    return request.execute();
  }


  public static enum PublishedTleFields {
    PUBLISH_EPOCH, TLE_LINE1, TLE_LINE2
  }


  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class PublishedTle {

    @JsonProperty("PUBLISH_EPOCH")
    private Instant publishTime;

    @JsonProperty("TLE_LINE1")
    private String tleLine1;

    @JsonProperty("TLE_LINE2")
    private String tleLine2;

  }
}
