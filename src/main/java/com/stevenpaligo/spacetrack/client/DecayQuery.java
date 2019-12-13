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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
        return "NORAD_CAT_ID";
      }
    },

    OBJECT_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_NUMBER";
      }
    },

    OBJECT_NAME {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_NAME";
      }
    },

    INTERNATIONAL_DESIGNATOR {

      @Override
      public String getQueryFieldName() {
        return "INTLDES";
      }
    },

    OBJECT_ID {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_ID";
      }
    },

    RCS_METERS_SQUARED {

      @Override
      public String getQueryFieldName() {
        return "RCS";
      }
    },

    RCS_CHARACTERIZATION {

      @Override
      public String getQueryFieldName() {
        return "RCS_SIZE";
      }
    },

    COUNTRY {

      @Override
      public String getQueryFieldName() {
        return "COUNTRY";
      }
    },

    MESSAGE_EPOCH {

      @Override
      public String getQueryFieldName() {
        return "MSG_EPOCH";
      }
    },

    DECAY_EPOCH {

      @Override
      public String getQueryFieldName() {
        return "DECAY_EPOCH";
      }
    },

    SOURCE {

      @Override
      public String getQueryFieldName() {
        return "SOURCE";
      }
    },

    MESSAGE_TYPE {

      @Override
      public String getQueryFieldName() {
        return "MSG_TYPE";
      }
    },

    DECAY_STAGE {

      @Override
      public String getQueryFieldName() {
        return "PRECEDENCE";
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
  @JsonInclude(value = Include.NON_NULL)
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Decay {

    @JsonProperty("NORAD_CAT_ID")
    private Integer catalogNumber;

    @JsonProperty("OBJECT_NUMBER")
    private Integer objectNumber;

    @JsonProperty("OBJECT_NAME")
    private String objectName;

    @JsonProperty("INTLDES")
    private String internationalDesignator;

    @JsonProperty("OBJECT_ID")
    private String objectId;

    @JsonProperty("RCS")
    private Integer rcsMetersSquared;

    @JsonProperty("RCS_SIZE")
    private Optional<String> rcsCharacterization;

    @JsonProperty("COUNTRY")
    private String country;

    @JsonProperty("MSG_EPOCH")
    @JsonDeserialize(using = OptionalUtcInstantDeserializer.class)
    private Optional<UtcInstant> messageEpoch;

    @JsonProperty("DECAY_EPOCH")
    private Optional<String> decayEpoch;

    @JsonProperty("SOURCE")
    private String source;

    @JsonProperty("MSG_TYPE")
    private String messageType;

    @JsonProperty("PRECEDENCE")
    private Long decayStage;

  }
}
