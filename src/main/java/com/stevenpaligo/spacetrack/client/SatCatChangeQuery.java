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

        return SatCatChange.CATALOG_NUMBER_JSON_PROPERTY;
      }
    },

    OBJECT_NUMBER {

      @Override
      public String getQueryFieldName() {

        return SatCatChange.OBJECT_NUMBER_JSON_PROPERTY;
      }
    },

    CURRENT_NAME {

      @Override
      public String getQueryFieldName() {

        return SatCatChange.CURRENT_NAME_JSON_PROPERTY;
      }
    },

    PREVIOUS_NAME {

      @Override
      public String getQueryFieldName() {

        return SatCatChange.PREVIOUS_NAME_JSON_PROPERTY;
      }
    },

    CURRENT_INTERNATIONAL_DESIGNATOR {

      @Override
      public String getQueryFieldName() {

        return SatCatChange.CURRENT_INTERNATIONAL_DESIGNATOR_JSON_PROPERTY;
      }
    },

    PREVIOUS_INTERNATIONAL_DESIGNATOR {

      @Override
      public String getQueryFieldName() {

        return SatCatChange.PREVIOUS_INTERNATIONAL_DESIGNATOR_JSON_PROPERTY;
      }
    },

    CURRENT_COUNTRY {

      @Override
      public String getQueryFieldName() {

        return SatCatChange.CURRENT_COUNTRY_JSON_PROPERTY;
      }
    },

    PREVIOUS_COUNTRY {

      @Override
      public String getQueryFieldName() {

        return SatCatChange.PREVIOUS_COUNTRY_JSON_PROPERTY;
      }
    },

    CURRENT_LAUNCH_DATE {

      @Override
      public String getQueryFieldName() {

        return SatCatChange.CURRENT_LAUNCH_DATE_JSON_PROPERTY;
      }
    },

    PREVIOUS_LAUNCH_DATE {

      @Override
      public String getQueryFieldName() {

        return SatCatChange.PREVIOUS_LAUNCH_DATE_JSON_PROPERTY;
      }
    },

    CURRENT_DECAY_DATE {

      @Override
      public String getQueryFieldName() {

        return SatCatChange.CURRENT_DECAY_DATE_JSON_PROPERTY;
      }
    },

    PREVIOUS_DECAY_DATE {

      @Override
      public String getQueryFieldName() {

        return SatCatChange.PREVIOUS_DECAY_DATE_JSON_PROPERTY;
      }
    },

    UPDATE_TIME {

      @Override
      public String getQueryFieldName() {

        return SatCatChange.UPDATE_TIME_JSON_PROPERTY;
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

    private static final String CATALOG_NUMBER_JSON_PROPERTY = "NORAD_CAT_ID";
    private static final String OBJECT_NUMBER_JSON_PROPERTY = "OBJECT_NUMBER";
    private static final String CURRENT_NAME_JSON_PROPERTY = "CURRENT_NAME";
    private static final String PREVIOUS_NAME_JSON_PROPERTY = "PREVIOUS_NAME";
    private static final String CURRENT_INTERNATIONAL_DESIGNATOR_JSON_PROPERTY = "CURRENT_INTLDES";
    private static final String PREVIOUS_INTERNATIONAL_DESIGNATOR_JSON_PROPERTY = "PREVIOUS_INTLDES";
    private static final String CURRENT_COUNTRY_JSON_PROPERTY = "CURRENT_COUNTRY";
    private static final String PREVIOUS_COUNTRY_JSON_PROPERTY = "PREVIOUS_COUNTRY";
    private static final String CURRENT_LAUNCH_DATE_JSON_PROPERTY = "CURRENT_LAUNCH";
    private static final String PREVIOUS_LAUNCH_DATE_JSON_PROPERTY = "PREVIOUS_LAUNCH";
    private static final String CURRENT_DECAY_DATE_JSON_PROPERTY = "CURRENT_DECAY";
    private static final String PREVIOUS_DECAY_DATE_JSON_PROPERTY = "PREVIOUS_DECAY";
    private static final String UPDATE_TIME_JSON_PROPERTY = "CHANGE_MADE";


    @JsonProperty(CATALOG_NUMBER_JSON_PROPERTY)
    private Optional<Integer> catalogNumber = Optional.empty();

    @JsonProperty(OBJECT_NUMBER_JSON_PROPERTY)
    private Optional<Integer> objectNumber = Optional.empty();

    @JsonProperty(CURRENT_NAME_JSON_PROPERTY)
    private String currentName;

    @JsonProperty(PREVIOUS_NAME_JSON_PROPERTY)
    private Optional<String> previousName = Optional.empty();

    @JsonProperty(CURRENT_INTERNATIONAL_DESIGNATOR_JSON_PROPERTY)
    private String currentInternationalDesignator;

    @JsonProperty(PREVIOUS_INTERNATIONAL_DESIGNATOR_JSON_PROPERTY)
    private Optional<String> previousInternationalDesignator = Optional.empty();

    @JsonProperty(CURRENT_COUNTRY_JSON_PROPERTY)
    private String currentCountry;

    @JsonProperty(PREVIOUS_COUNTRY_JSON_PROPERTY)
    private Optional<String> previousCountry = Optional.empty();

    @JsonProperty(CURRENT_LAUNCH_DATE_JSON_PROPERTY)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> currentLaunchDate = Optional.empty();

    @JsonProperty(PREVIOUS_LAUNCH_DATE_JSON_PROPERTY)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> previousLaunchDate = Optional.empty();

    @JsonProperty(CURRENT_DECAY_DATE_JSON_PROPERTY)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> currentDecayDate = Optional.empty();

    @JsonProperty(PREVIOUS_DECAY_DATE_JSON_PROPERTY)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> previousDecayDate = Optional.empty();

    @JsonProperty(UPDATE_TIME_JSON_PROPERTY)
    @JsonDeserialize(using = OptionalUtcInstantDeserializer.class)
    private Optional<UtcInstant> updateTime = Optional.empty();

  }
}
