package com.stevenpaligo.spacetrack.client;

import java.io.IOException;
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
public class LaunchSiteRequest {

  @NonNull
  private CredentialProvider credentials;

  @Singular
  private Collection<Predicate<LaunchSiteFields>> predicates;

  private Limit limit;

  @Singular
  private List<Sort<LaunchSiteFields>> sorts;

  @Singular
  private Set<String> favorites;


  public List<LaunchSite> execute() throws JsonParseException, JsonMappingException, IOException {

    // create a request
    Request<LaunchSiteFields, LaunchSite> request = new Request<>(credentials, "launch_site", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));


    // execute the request and return the results
    return request.execute();
  }


  public static enum LaunchSiteFields {
    SITE_CODE, LAUNCH_SITE
  }


  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class LaunchSite {

    @JsonProperty("SITE_CODE")
    private String siteCode;

    @JsonProperty("LAUNCH_SITE")
    private String siteName;

  }
}
