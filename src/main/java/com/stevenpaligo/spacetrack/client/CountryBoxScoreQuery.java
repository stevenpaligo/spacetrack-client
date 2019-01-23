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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stevenpaligo.spacetrack.client.CountryBoxScoreQuery.CountryBoxScore;
import com.stevenpaligo.spacetrack.client.CountryBoxScoreQuery.CountryBoxScoreQueryField;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CountryBoxScoreQuery extends Query<CountryBoxScoreQueryField, CountryBoxScore, CountryBoxScoreQuery> {

  public CountryBoxScoreQuery() {

    super("boxscore", CountryBoxScore.class);
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
