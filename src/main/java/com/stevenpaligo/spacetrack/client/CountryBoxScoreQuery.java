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
public class CountryBoxScoreQuery {

  @NonNull
  private CredentialProvider credentials;

  @Singular
  private Collection<Predicate<CountryBoxScoreQueryField>> predicates;

  private Limit limit;

  @Singular
  private List<Sort<CountryBoxScoreQueryField>> sorts;

  @Singular
  private Set<String> favorites;


  public List<CountryBoxScore> execute() throws JsonParseException, JsonMappingException, IOException {

    // create a query
    Query<CountryBoxScoreQueryField, CountryBoxScore> query = new Query<>(credentials, "boxscore", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));


    // execute the query and return the results
    return query.execute();
  }


  public static enum CountryBoxScoreQueryField implements QueryField {

    COUNTRY {

      @Override
      public String getQueryFieldName() {
        return "COUNTRY";
      }
    },

    SPADOC_COUNTRY_DESIGNATOR {

      @Override
      public String getQueryFieldName() {
        return "SPADOC_CD";
      }
    },

    ORBITAL_UNASSIGNED_TYPE_COUNT {

      @Override
      public String getQueryFieldName() {
        return "ORBITAL_TBA";
      }
    },

    ORBITAL_PAYLOAD_COUNT {

      @Override
      public String getQueryFieldName() {
        return "ORBITAL_PAYLOAD_COUNT";
      }
    },

    ORBITAL_ROCKET_BODY_COUNT {

      @Override
      public String getQueryFieldName() {
        return "ORBITAL_ROCKET_BODY_COUNT";
      }
    },

    ORBITAL_DEBRIS_COUNT {

      @Override
      public String getQueryFieldName() {
        return "ORBITAL_DEBRIS_COUNT";
      }
    },

    ORBITAL_TOTAL_COUNT {

      @Override
      public String getQueryFieldName() {
        return "ORBITAL_TOTAL_COUNT";
      }
    },

    DECAYED_PAYLOAD_COUNT {

      @Override
      public String getQueryFieldName() {
        return "DECAYED_PAYLOAD_COUNT";
      }
    },

    DECAYED_ROCKET_BODY_COUNT {

      @Override
      public String getQueryFieldName() {
        return "DECAYED_ROCKET_BODY_COUNT";
      }
    },

    DECAYED_DEBRIS_COUNT {

      @Override
      public String getQueryFieldName() {
        return "DECAYED_DEBRIS_COUNT";
      }
    },

    DECAYED_TOTAL_COUNT {

      @Override
      public String getQueryFieldName() {
        return "DECAYED_TOTAL_COUNT";
      }
    },

    TOTAL_COUNT {

      @Override
      public String getQueryFieldName() {
        return "COUNTRY_TOTAL";
      }
    }
  }


  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class CountryBoxScore {

    @JsonProperty("COUNTRY")
    private Optional<String> country;

    @JsonProperty("SPADOC_CD")
    private Optional<String> spadocCountryDesignator;

    @JsonProperty("ORBITAL_TBA")
    private Optional<Long> orbitalUnassignedTypeCount;

    @JsonProperty("ORBITAL_PAYLOAD_COUNT")
    private Optional<Long> orbitalPayloadCount;

    @JsonProperty("ORBITAL_ROCKET_BODY_COUNT")
    private Optional<Long> orbitalRocketBodyCount;

    @JsonProperty("ORBITAL_DEBRIS_COUNT")
    private Optional<Long> orbitalDebrisCount;

    @JsonProperty("ORBITAL_TOTAL_COUNT")
    private Optional<Long> orbitalTotalCount;

    @JsonProperty("DECAYED_PAYLOAD_COUNT")
    private Optional<Long> decayedPayloadCount;

    @JsonProperty("DECAYED_ROCKET_BODY_COUNT")
    private Optional<Long> decayedRocketBodyCount;

    @JsonProperty("DECAYED_DEBRIS_COUNT")
    private Optional<Long> decayedDebrisCount;

    @JsonProperty("DECAYED_TOTAL_COUNT")
    private Optional<Long> decayedTotalCount;

    @JsonProperty("COUNTRY_TOTAL")
    private Optional<Long> totalCount;

  }
}