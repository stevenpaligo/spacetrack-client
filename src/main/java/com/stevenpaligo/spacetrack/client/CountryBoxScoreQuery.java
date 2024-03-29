/*
 * The author licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stevenpaligo.spacetrack.client;

import java.math.BigInteger;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stevenpaligo.spacetrack.client.CountryBoxScoreQuery.CountryBoxScore;
import com.stevenpaligo.spacetrack.client.CountryBoxScoreQuery.CountryBoxScoreQueryField;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for querying countries' box scores from <a href="https://www.space-track.org/">Space-Track.org</a>. The class follows the builder pattern: the query is constructed using methods like
 * {@link #addPredicate(com.stevenpaligo.spacetrack.client.predicate.Predicate)} and then executed with {@link #execute()}.
 * 
 * @author Steven Paligo
 */
public class CountryBoxScoreQuery extends Query<CountryBoxScoreQueryField, CountryBoxScore, CountryBoxScoreQuery> {

  public CountryBoxScoreQuery() {

    super("boxscore", CountryBoxScore.class);
  }


  /**
   * Fields referenced in "country box score" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.CountryBoxScoreQuery
   */
  public static enum CountryBoxScoreQueryField implements QueryField {

    COUNTRY {

      @Override
      public String getQueryFieldName() {

        return CountryBoxScore.COUNTRY_JSON_PROPERTY;
      }
    },

    SPADOC_COUNTRY_DESIGNATOR {

      @Override
      public String getQueryFieldName() {

        return CountryBoxScore.SPADOC_COUNTRY_DESIGNATOR_JSON_PROPERTY;
      }
    },

    ORBITAL_UNASSIGNED_TYPE_COUNT {

      @Override
      public String getQueryFieldName() {

        return CountryBoxScore.ORBITAL_UNASSIGNED_TYPE_COUNT_JSON_PROPERTY;
      }
    },

    ORBITAL_PAYLOAD_COUNT {

      @Override
      public String getQueryFieldName() {

        return CountryBoxScore.ORBITAL_PAYLOAD_COUNT_JSON_PROPERTY;
      }
    },

    ORBITAL_ROCKET_BODY_COUNT {

      @Override
      public String getQueryFieldName() {

        return CountryBoxScore.ORBITAL_ROCKET_BODY_COUNT_JSON_PROPERTY;
      }
    },

    ORBITAL_DEBRIS_COUNT {

      @Override
      public String getQueryFieldName() {

        return CountryBoxScore.ORBITAL_DEBRIS_COUNT_JSON_PROPERTY;
      }
    },

    ORBITAL_TOTAL_COUNT {

      @Override
      public String getQueryFieldName() {

        return CountryBoxScore.ORBITAL_TOTAL_COUNT_JSON_PROPERTY;
      }
    },

    DECAYED_PAYLOAD_COUNT {

      @Override
      public String getQueryFieldName() {

        return CountryBoxScore.DECAYED_PAYLOAD_COUNT_JSON_PROPERTY;
      }
    },

    DECAYED_ROCKET_BODY_COUNT {

      @Override
      public String getQueryFieldName() {

        return CountryBoxScore.DECAYED_ROCKET_BODY_COUNT_JSON_PROPERTY;
      }
    },

    DECAYED_DEBRIS_COUNT {

      @Override
      public String getQueryFieldName() {

        return CountryBoxScore.DECAYED_DEBRIS_COUNT_JSON_PROPERTY;
      }
    },

    DECAYED_TOTAL_COUNT {

      @Override
      public String getQueryFieldName() {

        return CountryBoxScore.DECAYED_TOTAL_COUNT_JSON_PROPERTY;
      }
    },

    TOTAL_COUNT {

      @Override
      public String getQueryFieldName() {

        return CountryBoxScore.TOTAL_COUNT_JSON_PROPERTY;
      }
    }
  }


  /**
   * Class representing results returned from "country box score" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.CountryBoxScoreQuery
   */
  @Getter
  @Setter
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class CountryBoxScore {

    private static final String COUNTRY_JSON_PROPERTY = "COUNTRY";
    private static final String SPADOC_COUNTRY_DESIGNATOR_JSON_PROPERTY = "SPADOC_CD";
    private static final String ORBITAL_UNASSIGNED_TYPE_COUNT_JSON_PROPERTY = "ORBITAL_TBA";
    private static final String ORBITAL_PAYLOAD_COUNT_JSON_PROPERTY = "ORBITAL_PAYLOAD_COUNT";
    private static final String ORBITAL_ROCKET_BODY_COUNT_JSON_PROPERTY = "ORBITAL_ROCKET_BODY_COUNT";
    private static final String ORBITAL_DEBRIS_COUNT_JSON_PROPERTY = "ORBITAL_DEBRIS_COUNT";
    private static final String ORBITAL_TOTAL_COUNT_JSON_PROPERTY = "ORBITAL_TOTAL_COUNT";
    private static final String DECAYED_PAYLOAD_COUNT_JSON_PROPERTY = "DECAYED_PAYLOAD_COUNT";
    private static final String DECAYED_ROCKET_BODY_COUNT_JSON_PROPERTY = "DECAYED_ROCKET_BODY_COUNT";
    private static final String DECAYED_DEBRIS_COUNT_JSON_PROPERTY = "DECAYED_DEBRIS_COUNT";
    private static final String DECAYED_TOTAL_COUNT_JSON_PROPERTY = "DECAYED_TOTAL_COUNT";
    private static final String TOTAL_COUNT_JSON_PROPERTY = "COUNTRY_TOTAL";


    @JsonProperty(COUNTRY_JSON_PROPERTY)
    private String country;

    @JsonProperty(SPADOC_COUNTRY_DESIGNATOR_JSON_PROPERTY)
    private Optional<String> spadocCountryDesignator = Optional.empty();

    @JsonProperty(ORBITAL_UNASSIGNED_TYPE_COUNT_JSON_PROPERTY)
    private Optional<BigInteger> orbitalUnassignedTypeCount = Optional.empty();

    @JsonProperty(ORBITAL_PAYLOAD_COUNT_JSON_PROPERTY)
    private Optional<BigInteger> orbitalPayloadCount = Optional.empty();

    @JsonProperty(ORBITAL_ROCKET_BODY_COUNT_JSON_PROPERTY)
    private Optional<BigInteger> orbitalRocketBodyCount = Optional.empty();

    @JsonProperty(ORBITAL_DEBRIS_COUNT_JSON_PROPERTY)
    private Optional<BigInteger> orbitalDebrisCount = Optional.empty();

    @JsonProperty(ORBITAL_TOTAL_COUNT_JSON_PROPERTY)
    private Optional<BigInteger> orbitalTotalCount = Optional.empty();

    @JsonProperty(DECAYED_PAYLOAD_COUNT_JSON_PROPERTY)
    private Optional<BigInteger> decayedPayloadCount = Optional.empty();

    @JsonProperty(DECAYED_ROCKET_BODY_COUNT_JSON_PROPERTY)
    private Optional<BigInteger> decayedRocketBodyCount = Optional.empty();

    @JsonProperty(DECAYED_DEBRIS_COUNT_JSON_PROPERTY)
    private Optional<BigInteger> decayedDebrisCount = Optional.empty();

    @JsonProperty(DECAYED_TOTAL_COUNT_JSON_PROPERTY)
    private Optional<BigInteger> decayedTotalCount = Optional.empty();

    @JsonProperty(TOTAL_COUNT_JSON_PROPERTY)
    private Long totalCount;

  }
}
