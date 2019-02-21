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
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stevenpaligo.spacetrack.client.TipMessageQuery.TipMessage;
import com.stevenpaligo.spacetrack.client.TipMessageQuery.TipMessageQueryField;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.util.OptionalBooleanYesNoDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for querying Tracking and Impact (TIP) Messages from <a href="https://www.space-track.org/">Space-Track.org</a>. The class follows the builder pattern: the query is constructed using methods like
 * {@link #addPredicate(com.stevenpaligo.spacetrack.client.predicate.Predicate)} and then executed with {@link #execute()}.
 * 
 * @author Steven Paligo
 */
public class TipMessageQuery extends Query<TipMessageQueryField, TipMessage, TipMessageQuery> {

  public TipMessageQuery() {

    super("tip", TipMessage.class);
  }


  /**
   * Fields referenced in "Tracking and Impact (TIP) Message" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.TipMessageQuery
   */
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


  /**
   * Class representing results returned from "Tracking and Impact (TIP) Message" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.TipMessageQuery
   */
  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class TipMessage {

    @JsonProperty("NORAD_CAT_ID")
    private Integer catalogNumber;

    @JsonProperty("MSG_EPOCH")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant messageEpoch;

    @JsonProperty("INSERT_EPOCH")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant insertEpoch;

    @JsonProperty("DECAY_EPOCH")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
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
    @JsonDeserialize(using = OptionalBooleanYesNoDeserializer.class)
    private Optional<Boolean> highInterest;

    @JsonProperty("OBJECT_NUMBER")
    private Integer objectNumber;

  }
}
