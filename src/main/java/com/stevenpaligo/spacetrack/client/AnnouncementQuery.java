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
import com.stevenpaligo.spacetrack.client.query.Limit;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.query.Sort;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;

@Builder
public class AnnouncementQuery {

  @NonNull
  private CredentialProvider credentials;

  @Singular
  private Collection<Predicate<AnnouncementQueryField>> predicates;

  private Limit limit;

  @Singular
  private List<Sort<AnnouncementQueryField>> sorts;

  @Singular
  private Set<String> favorites;


  public List<Announcement> execute() throws JsonParseException, JsonMappingException, IOException {

    // create a query
    Query<AnnouncementQueryField, Announcement> query = new Query<>(Announcement.class, credentials, "announcement", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));


    // execute the query and return the results
    return query.execute();
  }


  public static enum AnnouncementQueryField implements QueryField {

    TYPE {

      @Override
      public String getQueryFieldName() {
        return "announcement_type";
      }
    },

    TEXT {

      @Override
      public String getQueryFieldName() {
        return "announcement_text";
      }
    },

    START_TIME {

      @Override
      public String getQueryFieldName() {
        return "announcement_start";
      }
    },

    END_TIME {

      @Override
      public String getQueryFieldName() {
        return "announcement_end";
      }
    }
  }


  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class Announcement {

    @JsonProperty("announcement_type")
    private String type;

    @JsonProperty("announcement_text")
    private String text;

    @JsonProperty("announcement_start")
    private Instant startTime;

    @JsonProperty("announcement_end")
    private Instant endTime;

  }
}
