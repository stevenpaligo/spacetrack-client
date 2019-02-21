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
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stevenpaligo.spacetrack.client.SatCatDebutQuery.SatCatDebut;
import com.stevenpaligo.spacetrack.client.SatCatDebutQuery.SatCatDebutQueryField;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.util.BooleanYesNoDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for querying new satellites from <a href="https://www.space-track.org/">Space-Track.org</a>. The class follows the builder pattern: the query is constructed using methods like
 * {@link #addPredicate(com.stevenpaligo.spacetrack.client.predicate.Predicate)} and then executed with {@link #execute()}.
 * 
 * @author Steven Paligo
 */
public class SatCatDebutQuery extends Query<SatCatDebutQueryField, SatCatDebut, SatCatDebutQuery> {

  public SatCatDebutQuery() {

    super("satcat_debut", SatCatDebut.class);
  }


  /**
   * Fields referenced in "new satellites" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.SatCatDebutQuery
   */
  public static enum SatCatDebutQueryField implements QueryField {

    INTERNATIONAL_DESIGNATOR {

      @Override
      public String getQueryFieldName() {
        return "INTLDES";
      }
    },

    CATALOG_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "NORAD_CAT_ID";
      }
    },

    OBJECT_TYPE {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_TYPE";
      }
    },

    SATNAME {

      @Override
      public String getQueryFieldName() {
        return "SATNAME";
      }
    },

    DEBUT_TIME {

      @Override
      public String getQueryFieldName() {
        return "DEBUT";
      }
    },

    COUNTRY {

      @Override
      public String getQueryFieldName() {
        return "COUNTRY";
      }
    },

    LAUNCH_DATE {

      @Override
      public String getQueryFieldName() {
        return "LAUNCH";
      }
    },

    LAUNCH_SITE {

      @Override
      public String getQueryFieldName() {
        return "SITE";
      }
    },

    DECAY_DATE {

      @Override
      public String getQueryFieldName() {
        return "DECAY";
      }
    },

    PERIOD_MINUTES {

      @Override
      public String getQueryFieldName() {
        return "PERIOD";
      }
    },

    INCLINATION_DEGREES {

      @Override
      public String getQueryFieldName() {
        return "INCLINATION";
      }
    },

    APOGEE_HEIGHT_KILOMETERS {

      @Override
      public String getQueryFieldName() {
        return "APOGEE";
      }
    },

    PERIGEE_HEIGHT_KILOMETERS {

      @Override
      public String getQueryFieldName() {
        return "PERIGEE";
      }
    },

    COMMENT {

      @Override
      public String getQueryFieldName() {
        return "COMMENT";
      }
    },

    COMMENT_CODE {

      @Override
      public String getQueryFieldName() {
        return "COMMENTCODE";
      }
    },

    RCS_METERS_SQUARED {

      @Override
      public String getQueryFieldName() {
        return "RCSVALUE";
      }
    },

    RCS_CHARACTERIZATION {

      @Override
      public String getQueryFieldName() {
        return "RCS_SIZE";
      }
    },

    FILE_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "FILE";
      }
    },

    LAUNCH_YEAR {

      @Override
      public String getQueryFieldName() {
        return "LAUNCH_YEAR";
      }
    },

    LAUNCH_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "LAUNCH_NUM";
      }
    },

    LAUNCH_PIECE {

      @Override
      public String getQueryFieldName() {
        return "LAUNCH_PIECE";
      }
    },

    CURRENT_RECORD {

      @Override
      public String getQueryFieldName() {
        return "CURRENT";
      }
    },

    OBJECT_NAME {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_NAME";
      }
    },

    OBJECT_ID {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_ID";
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
   * Class representing results returned from "new satellites" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.SatCatDebutQuery
   */
  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class SatCatDebut {

    @JsonProperty("INTLDES")
    private String internationalDesignator;

    @JsonProperty("NORAD_CAT_ID")
    private Integer catalogNumber;

    @JsonProperty("OBJECT_TYPE")
    private String objectType;

    @JsonProperty("SATNAME")
    private String satName;

    @JsonProperty("DEBUT")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Optional<Instant> debutTime;

    @JsonProperty("COUNTRY")
    private String country;

    @JsonProperty("LAUNCH")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> launchDate;

    @JsonProperty("SITE")
    private Optional<String> launchSite;

    @JsonProperty("DECAY")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Optional<LocalDate> decayDate;

    @JsonProperty("PERIOD")
    private Optional<BigDecimal> periodMinutes;

    @JsonProperty("INCLINATION")
    private Optional<BigDecimal> inclinationDegrees;

    @JsonProperty("APOGEE")
    private Optional<Long> apogeeHeightKilometers;

    @JsonProperty("PERIGEE")
    private Optional<Long> perigeeHeightKilometers;

    @JsonProperty("COMMENT")
    private Optional<String> comment;

    @JsonProperty("COMMENTCODE")
    private Optional<Integer> commentCode;

    @JsonProperty("RCSVALUE")
    private Integer rcsMetersSquared;

    @JsonProperty("RCS_SIZE")
    private Optional<String> rcsCharacterization;

    @JsonProperty("FILE")
    private Integer fileNumber;

    @JsonProperty("LAUNCH_YEAR")
    private Integer launchYear;

    @JsonProperty("LAUNCH_NUM")
    private Integer launchNumber;

    @JsonProperty("LAUNCH_PIECE")
    private String launchPiece;

    @JsonProperty("CURRENT")
    @JsonDeserialize(using = BooleanYesNoDeserializer.class)
    private Boolean currentRecord;

    @JsonProperty("OBJECT_NAME")
    private String objectName;

    @JsonProperty("OBJECT_ID")
    private String objectId;

    @JsonProperty("OBJECT_NUMBER")
    private Integer objectNumber;

  }
}
