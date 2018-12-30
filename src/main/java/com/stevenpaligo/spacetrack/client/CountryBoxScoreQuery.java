package com.stevenpaligo.spacetrack.client;

import java.math.BigInteger;
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
import com.stevenpaligo.spacetrack.client.CountryBoxScoreQuery.CountryBoxScore;
import com.stevenpaligo.spacetrack.client.CountryBoxScoreQuery.CountryBoxScoreQueryField;
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

public class CountryBoxScoreQuery extends Query<CountryBoxScoreQueryField, CountryBoxScore> {

  @Builder
  public CountryBoxScoreQuery(@NonNull CredentialProvider credentials, @NonNull @Singular Collection<@NonNull Predicate<@NonNull CountryBoxScoreQueryField>> predicates, Limit limit,
      @NonNull @Singular List<@NonNull Sort<CountryBoxScoreQueryField>> sorts, @NonNull @Singular Set<@NonNull String> favorites) {

    super(CountryBoxScore.class, credentials, "boxscore", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));
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
    private Optional<BigInteger> orbitalUnassignedTypeCount;

    @JsonProperty("ORBITAL_PAYLOAD_COUNT")
    private Optional<BigInteger> orbitalPayloadCount;

    @JsonProperty("ORBITAL_ROCKET_BODY_COUNT")
    private Optional<BigInteger> orbitalRocketBodyCount;

    @JsonProperty("ORBITAL_DEBRIS_COUNT")
    private Optional<BigInteger> orbitalDebrisCount;

    @JsonProperty("ORBITAL_TOTAL_COUNT")
    private Optional<BigInteger> orbitalTotalCount;

    @JsonProperty("DECAYED_PAYLOAD_COUNT")
    private Optional<BigInteger> decayedPayloadCount;

    @JsonProperty("DECAYED_ROCKET_BODY_COUNT")
    private Optional<BigInteger> decayedRocketBodyCount;

    @JsonProperty("DECAYED_DEBRIS_COUNT")
    private Optional<BigInteger> decayedDebrisCount;

    @JsonProperty("DECAYED_TOTAL_COUNT")
    private Optional<BigInteger> decayedTotalCount;

    @JsonProperty("COUNTRY_TOTAL")
    private Long totalCount;

  }
}
