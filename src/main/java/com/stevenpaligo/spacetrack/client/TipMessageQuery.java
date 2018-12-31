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
import com.stevenpaligo.spacetrack.client.TipMessageQuery.TipMessage;
import com.stevenpaligo.spacetrack.client.TipMessageQuery.TipMessageQueryField;
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

public class TipMessageQuery extends Query<TipMessageQueryField, TipMessage> {

  @Builder
  public TipMessageQuery(@NonNull CredentialProvider credentials, @NonNull @Singular Collection<@NonNull Predicate<@NonNull TipMessageQueryField>> predicates, Limit limit, @NonNull @Singular List<@NonNull Sort<TipMessageQueryField>> sorts,
      @NonNull @Singular Set<@NonNull String> favorites) {

    super(TipMessage.class, credentials, "tip", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));
  }


  public static enum TipMessageQueryField implements QueryField {

    CATALOG_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "NORAD_CAT_ID";
      }
    },

    MESSAGE_EPOCH {

      @Override
      public String getQueryFieldName() {
        return "MSG_EPOCH";
      }
    },

    INSERT_EPOCH {

      @Override
      public String getQueryFieldName() {
        return "INSERT_EPOCH";
      }
    },

    DECAY_EPOCH {

      @Override
      public String getQueryFieldName() {
        return "DECAY_EPOCH";
      }
    },

    WINDOW_SIZE_MINUTES {

      @Override
      public String getQueryFieldName() {
        return "WINDOW";
      }
    },

    REV_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "REV";
      }
    },

    DIRECTION {

      @Override
      public String getQueryFieldName() {
        return "DIRECTION";
      }
    },

    TEN_KM_LATITUDE_DEGREES {

      @Override
      public String getQueryFieldName() {
        return "LAT";
      }
    },

    TEN_KM_LONGITUDE_DEGREES {

      @Override
      public String getQueryFieldName() {
        return "LON";
      }
    },

    INCLINATION_DEGREES {

      @Override
      public String getQueryFieldName() {
        return "INCL";
      }
    },

    NEXT_REPORT_TIME_HOURS {

      @Override
      public String getQueryFieldName() {
        return "NEXT_REPORT";
      }
    },

    MESSAGE_ID {

      @Override
      public String getQueryFieldName() {
        return "ID";
      }
    },

    HIGH_INTEREST {

      @Override
      public String getQueryFieldName() {
        return "HIGH_INTEREST";
      }
    },

    OBJECT_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_NUMBER";
      }
    }
  }


  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class TipMessage {

    @JsonProperty("NORAD_CAT_ID")
    private Integer catalogNumber;

    @JsonProperty("MSG_EPOCH")
    private Instant messageEpoch;

    @JsonProperty("INSERT_EPOCH")
    private Instant insertEpoch;

    @JsonProperty("DECAY_EPOCH")
    private Instant decayEpoch;

    @JsonProperty("WINDOW")
    private Integer windowSizeMinutes;

    @JsonProperty("REV")
    private Integer revNumber;

    @JsonProperty("DIRECTION")
    private Optional<String> direction;

    @JsonProperty("LAT")
    private Float tenKmLatitudeDegrees;

    @JsonProperty("LON")
    private Float tenKmLongitudeDegrees;

    @JsonProperty("INCL")
    private Float inclinationDegrees;

    @JsonProperty("NEXT_REPORT")
    private Integer nextReportTimeHours;

    @JsonProperty("ID")
    private Integer messageId;

    @JsonProperty("HIGH_INTEREST")
    private Optional<Boolean> highInterest;

    @JsonProperty("OBJECT_NUMBER")
    private Integer objectNumber;

  }
}
