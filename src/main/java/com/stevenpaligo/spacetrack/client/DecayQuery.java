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
import com.stevenpaligo.spacetrack.client.DecayQuery.Decay;
import com.stevenpaligo.spacetrack.client.DecayQuery.DecayQueryField;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.util.OptionalUtcInstantDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for querying predicted and historical satellite decay information from <a href="https://www.space-track.org/">Space-Track.org</a>. The class follows the builder pattern: the query is constructed using methods like
 * {@link #addPredicate(com.stevenpaligo.spacetrack.client.predicate.Predicate)} and then executed with {@link #execute()}.
 * 
 * @author Steven Paligo
 */
public class DecayQuery extends Query<DecayQueryField, Decay, DecayQuery> {

  public DecayQuery() {

    super("decay", Decay.class);
  }


  /**
   * Fields referenced in "decay information" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.DecayQuery
   */
  public static enum DecayQueryField implements QueryField {

    CATALOG_NUMBER {

      @Override
      public String getQueryFieldName() {
        return Decay.CATALOG_NUMBER_JSON_PROPERTY;
      }
    },

    OBJECT_NUMBER {

      @Override
      public String getQueryFieldName() {
        return Decay.OBJECT_NUMBER_JSON_PROPERTY;
      }
    },

    OBJECT_NAME {

      @Override
      public String getQueryFieldName() {
        return Decay.OBJECT_NAME_JSON_PROPERTY;
      }
    },

    INTERNATIONAL_DESIGNATOR {

      @Override
      public String getQueryFieldName() {
        return Decay.INTERNATIONAL_DESIGNATOR_JSON_PROPERTY;
      }
    },

    OBJECT_ID {

      @Override
      public String getQueryFieldName() {
        return Decay.OBJECT_ID_JSON_PROPERTY;
      }
    },

    RCS_METERS_SQUARED {

      @Override
      public String getQueryFieldName() {
        return Decay.RCS_JSON_PROPERTY;
      }
    },

    RCS_CHARACTERIZATION {

      @Override
      public String getQueryFieldName() {
        return Decay.RCS_CHARACTERIZATION_JSON_PROPERTY;
      }
    },

    COUNTRY {

      @Override
      public String getQueryFieldName() {
        return Decay.COUNTRY_JSON_PROPERTY;
      }
    },

    MESSAGE_EPOCH {

      @Override
      public String getQueryFieldName() {
        return Decay.MESSAGE_EPOCH_JSON_PROPERTY;
      }
    },

    DECAY_EPOCH {

      @Override
      public String getQueryFieldName() {
        return Decay.DECAY_EPOCH_JSON_PROPERTY;
      }
    },

    SOURCE {

      @Override
      public String getQueryFieldName() {
        return Decay.SOURCE_JSON_PROPERTY;
      }
    },

    MESSAGE_TYPE {

      @Override
      public String getQueryFieldName() {
        return Decay.MESSAGE_TYPE_JSON_PROPERTY;
      }
    },

    DECAY_STAGE {

      @Override
      public String getQueryFieldName() {
        return Decay.DECAY_STAGE_JSON_PROPERTY;
      }
    }
  }


  /**
   * Class representing results returned from "decay information" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.DecayQuery
   */
  @Getter
  @Setter
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Decay {

    private static final String CATALOG_NUMBER_JSON_PROPERTY = "NORAD_CAT_ID";
    private static final String OBJECT_NUMBER_JSON_PROPERTY = "OBJECT_NUMBER";
    private static final String OBJECT_NAME_JSON_PROPERTY = "OBJECT_NAME";
    private static final String INTERNATIONAL_DESIGNATOR_JSON_PROPERTY = "INTLDES";
    private static final String OBJECT_ID_JSON_PROPERTY = "OBJECT_ID";
    private static final String RCS_JSON_PROPERTY = "RCS";
    private static final String RCS_CHARACTERIZATION_JSON_PROPERTY = "RCS_SIZE";
    private static final String COUNTRY_JSON_PROPERTY = "COUNTRY";
    private static final String MESSAGE_EPOCH_JSON_PROPERTY = "MSG_EPOCH";
    private static final String DECAY_EPOCH_JSON_PROPERTY = "DECAY_EPOCH";
    private static final String SOURCE_JSON_PROPERTY = "SOURCE";
    private static final String MESSAGE_TYPE_JSON_PROPERTY = "MSG_TYPE";
    private static final String DECAY_STAGE_JSON_PROPERTY = "PRECEDENCE";


    @JsonProperty(CATALOG_NUMBER_JSON_PROPERTY)
    private Optional<Integer> catalogNumber;

    @JsonProperty(OBJECT_NUMBER_JSON_PROPERTY)
    private Optional<Integer> objectNumber;

    @JsonProperty(OBJECT_NAME_JSON_PROPERTY)
    private String objectName;

    @JsonProperty(INTERNATIONAL_DESIGNATOR_JSON_PROPERTY)
    private String internationalDesignator;

    @JsonProperty(OBJECT_ID_JSON_PROPERTY)
    private String objectId;

    @JsonProperty(RCS_JSON_PROPERTY)
    private Integer rcsMetersSquared;

    @JsonProperty(RCS_CHARACTERIZATION_JSON_PROPERTY)
    private Optional<String> rcsCharacterization;

    @JsonProperty(COUNTRY_JSON_PROPERTY)
    private String country;

    @JsonProperty(MESSAGE_EPOCH_JSON_PROPERTY)
    @JsonDeserialize(using = OptionalUtcInstantDeserializer.class)
    private Optional<UtcInstant> messageEpoch;

    @JsonProperty(DECAY_EPOCH_JSON_PROPERTY)
    private Optional<String> decayEpoch;

    @JsonProperty(SOURCE_JSON_PROPERTY)
    private String source;

    @JsonProperty(MESSAGE_TYPE_JSON_PROPERTY)
    private String messageType;

    @JsonProperty(DECAY_STAGE_JSON_PROPERTY)
    private Long decayStage;

  }
}
