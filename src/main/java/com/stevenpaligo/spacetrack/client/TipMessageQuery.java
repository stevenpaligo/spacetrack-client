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

import java.util.Optional;
import org.threeten.extra.scale.UtcInstant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stevenpaligo.spacetrack.client.TipMessageQuery.TipMessage;
import com.stevenpaligo.spacetrack.client.TipMessageQuery.TipMessageQueryField;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.util.OptionalYesNoEnumToBooleanDeserializer;
import com.stevenpaligo.spacetrack.client.util.UtcInstantDeserializer;
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

        return TipMessage.CATALOG_NUMBER_JSON_PROPERTY;
      }
    },

    MESSAGE_EPOCH {

      @Override
      public String getQueryFieldName() {

        return TipMessage.MESSAGE_EPOCH_JSON_PROPERTY;
      }
    },

    INSERT_EPOCH {

      @Override
      public String getQueryFieldName() {

        return TipMessage.INSERT_EPOCH_JSON_PROPERTY;
      }
    },

    DECAY_EPOCH {

      @Override
      public String getQueryFieldName() {

        return TipMessage.DECAY_EPOCH_JSON_PROPERTY;
      }
    },

    WINDOW_SIZE_MINUTES {

      @Override
      public String getQueryFieldName() {

        return TipMessage.WINDOW_SIZE_JSON_PROPERTY;
      }
    },

    REV_NUMBER {

      @Override
      public String getQueryFieldName() {

        return TipMessage.REV_NUMBER_JSON_PROPERTY;
      }
    },

    DIRECTION {

      @Override
      public String getQueryFieldName() {

        return TipMessage.DIRECTION_JSON_PROPERTY;
      }
    },

    TEN_KM_LATITUDE_DEGREES {

      @Override
      public String getQueryFieldName() {

        return TipMessage.TEN_KM_LATITUDE_JSON_PROPERTY;
      }
    },

    TEN_KM_LONGITUDE_DEGREES {

      @Override
      public String getQueryFieldName() {

        return TipMessage.TEN_KM_LONGITUDE_JSON_PROPERTY;
      }
    },

    INCLINATION_DEGREES {

      @Override
      public String getQueryFieldName() {

        return TipMessage.INCLINATION_JSON_PROPERTY;
      }
    },

    NEXT_REPORT_TIME_HOURS {

      @Override
      public String getQueryFieldName() {

        return TipMessage.NEXT_REPORT_TIME_JSON_PROPERTY;
      }
    },

    MESSAGE_ID {

      @Override
      public String getQueryFieldName() {

        return TipMessage.MESSAGE_ID_JSON_PROPERTY;
      }
    },

    HIGH_INTEREST {

      @Override
      public String getQueryFieldName() {

        return TipMessage.HIGH_INTEREST_JSON_PROPERTY;
      }
    },

    OBJECT_NUMBER {

      @Override
      public String getQueryFieldName() {

        return TipMessage.OBJECT_NUMBER_JSON_PROPERTY;
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
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class TipMessage {

    private static final String CATALOG_NUMBER_JSON_PROPERTY = "NORAD_CAT_ID";
    private static final String MESSAGE_EPOCH_JSON_PROPERTY = "MSG_EPOCH";
    private static final String INSERT_EPOCH_JSON_PROPERTY = "INSERT_EPOCH";
    private static final String DECAY_EPOCH_JSON_PROPERTY = "DECAY_EPOCH";
    private static final String WINDOW_SIZE_JSON_PROPERTY = "WINDOW";
    private static final String REV_NUMBER_JSON_PROPERTY = "REV";
    private static final String DIRECTION_JSON_PROPERTY = "DIRECTION";
    private static final String TEN_KM_LATITUDE_JSON_PROPERTY = "LAT";
    private static final String TEN_KM_LONGITUDE_JSON_PROPERTY = "LON";
    private static final String INCLINATION_JSON_PROPERTY = "INCL";
    private static final String NEXT_REPORT_TIME_JSON_PROPERTY = "NEXT_REPORT";
    private static final String MESSAGE_ID_JSON_PROPERTY = "ID";
    private static final String HIGH_INTEREST_JSON_PROPERTY = "HIGH_INTEREST";
    private static final String OBJECT_NUMBER_JSON_PROPERTY = "OBJECT_NUMBER";


    @JsonProperty(CATALOG_NUMBER_JSON_PROPERTY)
    private Optional<Integer> catalogNumber = Optional.empty();

    @JsonProperty(MESSAGE_EPOCH_JSON_PROPERTY)
    @JsonDeserialize(using = UtcInstantDeserializer.class)
    private UtcInstant messageEpoch;

    @JsonProperty(INSERT_EPOCH_JSON_PROPERTY)
    @JsonDeserialize(using = UtcInstantDeserializer.class)
    private UtcInstant insertEpoch;

    @JsonProperty(DECAY_EPOCH_JSON_PROPERTY)
    @JsonDeserialize(using = UtcInstantDeserializer.class)
    private UtcInstant decayEpoch;

    @JsonProperty(WINDOW_SIZE_JSON_PROPERTY)
    private Integer windowSizeMinutes;

    @JsonProperty(REV_NUMBER_JSON_PROPERTY)
    private Integer revNumber;

    @JsonProperty(DIRECTION_JSON_PROPERTY)
    private Optional<String> direction = Optional.empty();

    @JsonProperty(TEN_KM_LATITUDE_JSON_PROPERTY)
    private Float tenKmLatitudeDegrees;

    @JsonProperty(TEN_KM_LONGITUDE_JSON_PROPERTY)
    private Float tenKmLongitudeDegrees;

    @JsonProperty(INCLINATION_JSON_PROPERTY)
    private Float inclinationDegrees;

    @JsonProperty(NEXT_REPORT_TIME_JSON_PROPERTY)
    private Integer nextReportTimeHours;

    @JsonProperty(MESSAGE_ID_JSON_PROPERTY)
    private Integer messageId;

    @JsonProperty(HIGH_INTEREST_JSON_PROPERTY)
    @JsonDeserialize(using = OptionalYesNoEnumToBooleanDeserializer.class)
    private Optional<Boolean> highInterest = Optional.empty();

    @JsonProperty(OBJECT_NUMBER_JSON_PROPERTY)
    private Optional<Integer> objectNumber = Optional.empty();

  }
}
