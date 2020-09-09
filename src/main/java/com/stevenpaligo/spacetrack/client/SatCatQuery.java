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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stevenpaligo.spacetrack.client.SatCatQuery.SatCat;
import com.stevenpaligo.spacetrack.client.SatCatQuery.SatCatQueryField;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.util.BooleanYesNoDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for querying satellite data from <a href="https://www.space-track.org/">Space-Track.org</a>. The class follows the builder pattern: the query is constructed using methods like
 * {@link #addPredicate(com.stevenpaligo.spacetrack.client.predicate.Predicate)} and then executed with {@link #execute()}.
 * 
 * @author Steven Paligo
 */
public class SatCatQuery extends Query<SatCatQueryField, SatCat, SatCatQuery> {

  public SatCatQuery() {

    super("satcat", SatCat.class);
  }


  /**
   * Fields referenced in "satellite data" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.SatCatQuery
   */
  public static enum SatCatQueryField implements QueryField {

    INTERNATIONAL_DESIGNATOR {

      @Override
      public String getQueryFieldName() {

        return SatCat.INTERNATIONAL_DESIGNATOR_JSON_PROPERTY;
      }
    },

    CATALOG_NUMBER {

      @Override
      public String getQueryFieldName() {

        return SatCat.CATALOG_NUMBER_JSON_PROPERTY;
      }
    },

    OBJECT_TYPE {

      @Override
      public String getQueryFieldName() {

        return SatCat.OBJECT_TYPE_JSON_PROPERTY;
      }
    },

    SATNAME {

      @Override
      public String getQueryFieldName() {

        return SatCat.SAT_NAME_JSON_PROPERTY;
      }
    },

    COUNTRY {

      @Override
      public String getQueryFieldName() {

        return SatCat.COUNTRY_JSON_PROPERTY;
      }
    },

    LAUNCH_DATE {

      @Override
      public String getQueryFieldName() {

        return SatCat.LAUNCH_DATE_JSON_PROPERTY;
      }
    },

    LAUNCH_SITE {

      @Override
      public String getQueryFieldName() {

        return SatCat.LAUNCH_SITE_JSON_PROPERTY;
      }
    },

    DECAY_DATE {

      @Override
      public String getQueryFieldName() {

        return SatCat.DECAY_DATE_JSON_PROPERTY;
      }
    },

    PERIOD_MINUTES {

      @Override
      public String getQueryFieldName() {

        return SatCat.PERIOD_JSON_PROPERTY;
      }
    },

    INCLINATION_DEGREES {

      @Override
      public String getQueryFieldName() {

        return SatCat.INCLINATION_JSON_PROPERTY;
      }
    },

    APOGEE_HEIGHT_KILOMETERS {

      @Override
      public String getQueryFieldName() {

        return SatCat.APOGEE_HEIGHT_JSON_PROPERTY;
      }
    },

    PERIGEE_HEIGHT_KILOMETERS {

      @Override
      public String getQueryFieldName() {

        return SatCat.PERIGEE_HEIGHT_JSON_PROPERTY;
      }
    },

    COMMENT {

      @Override
      public String getQueryFieldName() {

        return SatCat.COMMENT_JSON_PROPERTY;
      }
    },

    COMMENT_CODE {

      @Override
      public String getQueryFieldName() {

        return SatCat.COMMENT_CODE_JSON_PROPERTY;
      }
    },

    RCS_METERS_SQUARED {

      @Override
      public String getQueryFieldName() {

        return SatCat.RCS_JSON_PROPERTY;
      }
    },

    RCS_CHARACTERIZATION {

      @Override
      public String getQueryFieldName() {

        return SatCat.RCS_CHARACTERIZATION_JSON_PROPERTY;
      }
    },

    FILE_NUMBER {

      @Override
      public String getQueryFieldName() {

        return SatCat.FILE_NUMBER_JSON_PROPERTY;
      }
    },

    LAUNCH_YEAR {

      @Override
      public String getQueryFieldName() {

        return SatCat.LAUNCH_YEAR_JSON_PROPERTY;
      }
    },

    LAUNCH_NUMBER {

      @Override
      public String getQueryFieldName() {

        return SatCat.LAUNCH_NUMBER_JSON_PROPERTY;
      }
    },

    LAUNCH_PIECE {

      @Override
      public String getQueryFieldName() {

        return SatCat.LAUNCH_PIECE_JSON_PROPERTY;
      }
    },

    CURRENT_RECORD {

      @Override
      public String getQueryFieldName() {

        return SatCat.CURRENT_RECORD_JSON_PROPERTY;
      }
    },

    OBJECT_NAME {

      @Override
      public String getQueryFieldName() {

        return SatCat.OBJECT_NAME_JSON_PROPERTY;
      }
    },

    OBJECT_ID {

      @Override
      public String getQueryFieldName() {

        return SatCat.OBJECT_ID_JSON_PROPERTY;
      }
    },

    OBJECT_NUMBER {

      @Override
      public String getQueryFieldName() {

        return SatCat.OBJECT_NUMBER_JSON_PROPERTY;
      }
    }
  }


  /**
   * Class representing results returned from "satellite data" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.SatCatQuery
   */
  @Getter
  @Setter
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class SatCat {

    private static final String INTERNATIONAL_DESIGNATOR_JSON_PROPERTY = "INTLDES";
    private static final String CATALOG_NUMBER_JSON_PROPERTY = "NORAD_CAT_ID";
    private static final String OBJECT_TYPE_JSON_PROPERTY = "OBJECT_TYPE";
    private static final String SAT_NAME_JSON_PROPERTY = "SATNAME";
    private static final String COUNTRY_JSON_PROPERTY = "COUNTRY";
    private static final String LAUNCH_DATE_JSON_PROPERTY = "LAUNCH";
    private static final String LAUNCH_SITE_JSON_PROPERTY = "SITE";
    private static final String DECAY_DATE_JSON_PROPERTY = "DECAY";
    private static final String PERIOD_JSON_PROPERTY = "PERIOD";
    private static final String INCLINATION_JSON_PROPERTY = "INCLINATION";
    private static final String APOGEE_HEIGHT_JSON_PROPERTY = "APOGEE";
    private static final String PERIGEE_HEIGHT_JSON_PROPERTY = "PERIGEE";
    private static final String COMMENT_JSON_PROPERTY = "COMMENT";
    private static final String COMMENT_CODE_JSON_PROPERTY = "COMMENTCODE";
    private static final String RCS_JSON_PROPERTY = "RCSVALUE";
    private static final String RCS_CHARACTERIZATION_JSON_PROPERTY = "RCS_SIZE";
    private static final String FILE_NUMBER_JSON_PROPERTY = "FILE";
    private static final String LAUNCH_YEAR_JSON_PROPERTY = "LAUNCH_YEAR";
    private static final String LAUNCH_NUMBER_JSON_PROPERTY = "LAUNCH_NUM";
    private static final String LAUNCH_PIECE_JSON_PROPERTY = "LAUNCH_PIECE";
    private static final String CURRENT_RECORD_JSON_PROPERTY = "CURRENT";
    private static final String OBJECT_NAME_JSON_PROPERTY = "OBJECT_NAME";
    private static final String OBJECT_ID_JSON_PROPERTY = "OBJECT_ID";
    private static final String OBJECT_NUMBER_JSON_PROPERTY = "OBJECT_NUMBER";


    @JsonProperty(INTERNATIONAL_DESIGNATOR_JSON_PROPERTY)
    private String internationalDesignator;

    @JsonProperty(CATALOG_NUMBER_JSON_PROPERTY)
    private Optional<Integer> catalogNumber;

    @JsonProperty(OBJECT_TYPE_JSON_PROPERTY)
    private Optional<String> objectType;

    @JsonProperty(SAT_NAME_JSON_PROPERTY)
    private String satName;

    @JsonProperty(COUNTRY_JSON_PROPERTY)
    private String country;

    @JsonProperty(LAUNCH_DATE_JSON_PROPERTY)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> launchDate;

    @JsonProperty(LAUNCH_SITE_JSON_PROPERTY)
    private Optional<String> launchSite;

    @JsonProperty(DECAY_DATE_JSON_PROPERTY)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> decayDate;

    @JsonProperty(PERIOD_JSON_PROPERTY)
    private Optional<BigDecimal> periodMinutes;

    @JsonProperty(INCLINATION_JSON_PROPERTY)
    private Optional<BigDecimal> inclinationDegrees;

    @JsonProperty(APOGEE_HEIGHT_JSON_PROPERTY)
    private Optional<Long> apogeeHeightKilometers;

    @JsonProperty(PERIGEE_HEIGHT_JSON_PROPERTY)
    private Optional<Long> perigeeHeightKilometers;

    @JsonProperty(COMMENT_JSON_PROPERTY)
    private Optional<String> comment;

    @JsonProperty(COMMENT_CODE_JSON_PROPERTY)
    private Optional<Integer> commentCode;

    @JsonProperty(RCS_JSON_PROPERTY)
    private Integer rcsMetersSquared;

    @JsonProperty(RCS_CHARACTERIZATION_JSON_PROPERTY)
    private Optional<String> rcsCharacterization;

    @JsonProperty(FILE_NUMBER_JSON_PROPERTY)
    private Integer fileNumber;

    @JsonProperty(LAUNCH_YEAR_JSON_PROPERTY)
    private Integer launchYear;

    @JsonProperty(LAUNCH_NUMBER_JSON_PROPERTY)
    private Integer launchNumber;

    @JsonProperty(LAUNCH_PIECE_JSON_PROPERTY)
    private String launchPiece;

    @JsonProperty(CURRENT_RECORD_JSON_PROPERTY)
    @JsonDeserialize(using = BooleanYesNoDeserializer.class)
    private Boolean currentRecord;

    @JsonProperty(OBJECT_NAME_JSON_PROPERTY)
    private String objectName;

    @JsonProperty(OBJECT_ID_JSON_PROPERTY)
    private String objectId;

    @JsonProperty(OBJECT_NUMBER_JSON_PROPERTY)
    private Optional<Integer> objectNumber;

  }
}
