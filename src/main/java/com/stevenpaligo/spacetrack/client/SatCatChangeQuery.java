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

import java.time.LocalDate;
import java.util.Optional;
import org.threeten.extra.scale.UtcInstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stevenpaligo.spacetrack.client.SatCatChangeQuery.SatCatChange;
import com.stevenpaligo.spacetrack.client.SatCatChangeQuery.SatCatChangeQueryField;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.util.OptionalUtcInstantDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for querying recent changes to satellites from <a href="https://www.space-track.org/">Space-Track.org</a>. The class follows the builder pattern: the query is constructed using methods like
 * {@link #addPredicate(com.stevenpaligo.spacetrack.client.predicate.Predicate)} and then executed with {@link #execute()}.
 * 
 * @author Steven Paligo
 */
public class SatCatChangeQuery extends Query<SatCatChangeQueryField, SatCatChange, SatCatChangeQuery> {

  public SatCatChangeQuery() {

    super("satcat_change", SatCatChange.class);
  }


  /**
   * Fields referenced in "recent satellite changes" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.SatCatChangeQuery
   */
  public static enum SatCatChangeQueryField implements QueryField {

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

    CURRENT_NAME {

      @Override
      public String getQueryFieldName() {
        return "CURRENT_NAME";
      }
    },

    PREVIOUS_NAME {

      @Override
      public String getQueryFieldName() {
        return "PREVIOUS_NAME";
      }
    },

    CURRENT_INTERNATIONAL_DESIGNATOR {

      @Override
      public String getQueryFieldName() {
        return "CURRENT_INTLDES";
      }
    },

    PREVIOUS_INTERNATIONAL_DESIGNATOR {

      @Override
      public String getQueryFieldName() {
        return "PREVIOUS_INTLDES";
      }
    },

    CURRENT_COUNTRY {

      @Override
      public String getQueryFieldName() {
        return "CURRENT_COUNTRY";
      }
    },

    PREVIOUS_COUNTRY {

      @Override
      public String getQueryFieldName() {
        return "PREVIOUS_COUNTRY";
      }
    },

    CURRENT_LAUNCH_DATE {

      @Override
      public String getQueryFieldName() {
        return "CURRENT_LAUNCH";
      }
    },

    PREVIOUS_LAUNCH_DATE {

      @Override
      public String getQueryFieldName() {
        return "PREVIOUS_LAUNCH";
      }
    },

    CURRENT_DECAY_DATE {

      @Override
      public String getQueryFieldName() {
        return "CURRENT_DECAY";
      }
    },

    PREVIOUS_DECAY_DATE {

      @Override
      public String getQueryFieldName() {
        return "PREVIOUS_DECAY";
      }
    },

    UPDATE_TIME {

      @Override
      public String getQueryFieldName() {
        return "CHANGE_MADE";
      }
    }
  }


  /**
   * Class representing results returned from "recent satellite changes" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.SatCatChangeQuery
   */
  @Getter
  @Setter
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class SatCatChange {

    @JsonProperty("NORAD_CAT_ID")
    private Optional<Integer> catalogNumber;

    @JsonProperty("OBJECT_NUMBER")
    private Optional<Integer> objectNumber;

    @JsonProperty("CURRENT_NAME")
    private String currentName;

    @JsonProperty("PREVIOUS_NAME")
    private Optional<String> previousName;

    @JsonProperty("CURRENT_INTLDES")
    private String currentInternationalDesignator;

    @JsonProperty("PREVIOUS_INTLDES")
    private Optional<String> previousInternationalDesignator;

    @JsonProperty("CURRENT_COUNTRY")
    private String currentCountry;

    @JsonProperty("PREVIOUS_COUNTRY")
    private Optional<String> previousCountry;

    @JsonProperty("CURRENT_LAUNCH")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> currentLaunchDate;

    @JsonProperty("PREVIOUS_LAUNCH")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> previousLaunchDate;

    @JsonProperty("CURRENT_DECAY")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> currentDecayDate;

    @JsonProperty("PREVIOUS_DECAY")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> previousDecayDate;

    @JsonProperty("CHANGE_MADE")
    @JsonDeserialize(using = OptionalUtcInstantDeserializer.class)
    private Optional<UtcInstant> updateTime;

  }
}
