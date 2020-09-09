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
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stevenpaligo.spacetrack.client.LatestTleQuery.LatestTle;
import com.stevenpaligo.spacetrack.client.LatestTleQuery.LatestTleQueryField;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for querying the satellites' 5 latest TLEs from <a href="https://www.space-track.org/">Space-Track.org</a>. The class follows the builder pattern: the query is constructed using methods like
 * {@link #addPredicate(com.stevenpaligo.spacetrack.client.predicate.Predicate)} and then executed with {@link #execute()}.
 * 
 * @author Steven Paligo
 */
public class LatestTleQuery extends Query<LatestTleQueryField, LatestTle, LatestTleQuery> {

  public LatestTleQuery() {

    super("tle_latest", LatestTle.class);
  }


  /**
   * Fields referenced in "latest TLE" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.LatestTleQuery
   */
  public static enum LatestTleQueryField implements QueryField {

    ORDINAL {

      @Override
      public String getQueryFieldName() {
        return LatestTle.ORDINAL_JSON_PROPERTY;
      }
    },

    COMMENT {

      @Override
      public String getQueryFieldName() {
        return LatestTle.COMMENT_JSON_PROPERTY;
      }
    },

    ORIGINATOR {

      @Override
      public String getQueryFieldName() {
        return LatestTle.ORIGINATOR_JSON_PROPERTY;
      }
    },

    CATALOG_NUMBER {

      @Override
      public String getQueryFieldName() {
        return LatestTle.CATALOG_NUMBER_JSON_PROPERTY;
      }
    },

    OBJECT_NAME {

      @Override
      public String getQueryFieldName() {
        return LatestTle.OBJECT_NAME_JSON_PROPERTY;
      }
    },

    OBJECT_TYPE {

      @Override
      public String getQueryFieldName() {
        return LatestTle.OBJECT_TYPE_JSON_PROPERTY;
      }
    },

    CLASSIFICATION {

      @Override
      public String getQueryFieldName() {
        return LatestTle.CLASSIFICATION_JSON_PROPERTY;
      }
    },

    INTERNATIONAL_DESIGNATOR {

      @Override
      public String getQueryFieldName() {
        return LatestTle.INTERNATIONAL_DESIGNATOR_JSON_PROPERTY;
      }
    },

    EPOCH_YMD_HMS {

      @Override
      public String getQueryFieldName() {
        return LatestTle.EPOCH_YMD_HMS_JSON_PROPERTY;
      }
    },

    EPOCH_MICROSECONDS {

      @Override
      public String getQueryFieldName() {
        return LatestTle.EPOCH_MICROSECONDS_JSON_PROPERTY;
      }
    },

    MEAN_MOTION_REVS_PER_DAY {

      @Override
      public String getQueryFieldName() {
        return LatestTle.MEAN_MOTION_JSON_PROPERTY;
      }
    },

    ECCENTRICITY {

      @Override
      public String getQueryFieldName() {
        return LatestTle.ECCENTRICITY_JSON_PROPERTY;
      }
    },

    INCLINATION_DEGREES {

      @Override
      public String getQueryFieldName() {
        return LatestTle.INCLINATION_JSON_PROPERTY;
      }
    },

    RIGHT_ASC_OF_NODE_DEGREES {

      @Override
      public String getQueryFieldName() {
        return LatestTle.RIGHT_ASC_OF_NODE_JSON_PROPERTY;
      }
    },

    ARG_OF_PERIGEE_DEGREES {

      @Override
      public String getQueryFieldName() {
        return LatestTle.ARG_OF_PERIGEE_JSON_PROPERTY;
      }
    },

    MEAN_ANOMALY_DEGREES {

      @Override
      public String getQueryFieldName() {
        return LatestTle.MEAN_ANOMALY_JSON_PROPERTY;
      }
    },

    EPHEMERIS_TYPE {

      @Override
      public String getQueryFieldName() {
        return LatestTle.EPHEMERIS_TYPE_JSON_PROPERTY;
      }
    },

    ELEMENT_SET_NUMBER {

      @Override
      public String getQueryFieldName() {
        return LatestTle.ELEMENT_SET_NUMBER_JSON_PROPERTY;
      }
    },

    REV_NUMBER {

      @Override
      public String getQueryFieldName() {
        return LatestTle.REV_NUMBER_JSON_PROPERTY;
      }
    },

    BSTAR {

      @Override
      public String getQueryFieldName() {
        return LatestTle.BSTAR_JSON_PROPERTY;
      }
    },

    MEAN_MOTION_DOT {

      @Override
      public String getQueryFieldName() {
        return LatestTle.MEAN_MOTION_DOT_JSON_PROPERTY;
      }
    },

    MEAN_MOTION_DOUBLE_DOT {

      @Override
      public String getQueryFieldName() {
        return LatestTle.MEAN_MOTION_DOUBLE_DOT_JSON_PROPERTY;
      }
    },

    FILE_NUMBER {

      @Override
      public String getQueryFieldName() {
        return LatestTle.FILE_NUMBER_JSON_PROPERTY;
      }
    },

    TLE_LINE0 {

      @Override
      public String getQueryFieldName() {
        return LatestTle.TLE_LINE_0_JSON_PROPERTY;
      }
    },

    TLE_LINE1 {

      @Override
      public String getQueryFieldName() {
        return LatestTle.TLE_LINE_1_JSON_PROPERTY;
      }
    },

    TLE_LINE2 {

      @Override
      public String getQueryFieldName() {
        return LatestTle.TLE_LINE_2_JSON_PROPERTY;
      }
    },

    OBJECT_ID {

      @Override
      public String getQueryFieldName() {
        return LatestTle.OBJECT_ID_JSON_PROPERTY;
      }
    },

    OBJECT_NUMBER {

      @Override
      public String getQueryFieldName() {
        return LatestTle.OBJECT_NUMBER_JSON_PROPERTY;
      }
    },

    SEMI_MAJOR_AXIS_KILOMETERS {

      @Override
      public String getQueryFieldName() {
        return LatestTle.SEMI_MAJOR_AXIS_JSON_PROPERTY;
      }
    },

    PERIOD_MINUTES {

      @Override
      public String getQueryFieldName() {
        return LatestTle.PERIOD_JSON_PROPERTY;
      }
    },

    APOGEE_HEIGHT_KILOMETERS {

      @Override
      public String getQueryFieldName() {
        return LatestTle.APOGEE_HEIGHT_JSON_PROPERTY;
      }
    },

    PERIGEE_HEIGHT_KILOMETERS {

      @Override
      public String getQueryFieldName() {
        return LatestTle.PERIGEE_HEIGHT_JSON_PROPERTY;
      }
    },

    DECAYED {

      @Override
      public String getQueryFieldName() {
        return LatestTle.DECAYED_JSON_PROPERTY;
      }
    }
  }


  /**
   * Class representing results returned from "latest TLE" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.LatestTleQuery
   */
  @Getter
  @Setter
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class LatestTle {

    private static final String ORDINAL_JSON_PROPERTY = "ORDINAL";
    private static final String COMMENT_JSON_PROPERTY = "COMMENT";
    private static final String ORIGINATOR_JSON_PROPERTY = "ORIGINATOR";
    private static final String CATALOG_NUMBER_JSON_PROPERTY = "NORAD_CAT_ID";
    private static final String OBJECT_NAME_JSON_PROPERTY = "OBJECT_NAME";
    private static final String OBJECT_TYPE_JSON_PROPERTY = "OBJECT_TYPE";
    private static final String CLASSIFICATION_JSON_PROPERTY = "CLASSIFICATION_TYPE";
    private static final String INTERNATIONAL_DESIGNATOR_JSON_PROPERTY = "INTLDES";
    private static final String EPOCH_YMD_HMS_JSON_PROPERTY = "EPOCH";
    private static final String EPOCH_MICROSECONDS_JSON_PROPERTY = "EPOCH_MICROSECONDS";
    private static final String MEAN_MOTION_JSON_PROPERTY = "MEAN_MOTION";
    private static final String ECCENTRICITY_JSON_PROPERTY = "ECCENTRICITY";
    private static final String INCLINATION_JSON_PROPERTY = "INCLINATION";
    private static final String RIGHT_ASC_OF_NODE_JSON_PROPERTY = "RA_OF_ASC_NODE";
    private static final String ARG_OF_PERIGEE_JSON_PROPERTY = "ARG_OF_PERICENTER";
    private static final String MEAN_ANOMALY_JSON_PROPERTY = "MEAN_ANOMALY";
    private static final String EPHEMERIS_TYPE_JSON_PROPERTY = "EPHEMERIS_TYPE";
    private static final String ELEMENT_SET_NUMBER_JSON_PROPERTY = "ELEMENT_SET_NO";
    private static final String REV_NUMBER_JSON_PROPERTY = "REV_AT_EPOCH";
    private static final String BSTAR_JSON_PROPERTY = "BSTAR";
    private static final String MEAN_MOTION_DOT_JSON_PROPERTY = "MEAN_MOTION_DOT";
    private static final String MEAN_MOTION_DOUBLE_DOT_JSON_PROPERTY = "MEAN_MOTION_DDOT";
    private static final String FILE_NUMBER_JSON_PROPERTY = "FILE";
    private static final String TLE_LINE_0_JSON_PROPERTY = "TLE_LINE0";
    private static final String TLE_LINE_1_JSON_PROPERTY = "TLE_LINE1";
    private static final String TLE_LINE_2_JSON_PROPERTY = "TLE_LINE2";
    private static final String OBJECT_ID_JSON_PROPERTY = "OBJECT_ID";
    private static final String OBJECT_NUMBER_JSON_PROPERTY = "OBJECT_NUMBER";
    private static final String SEMI_MAJOR_AXIS_JSON_PROPERTY = "SEMIMAJOR_AXIS";
    private static final String PERIOD_JSON_PROPERTY = "PERIOD";
    private static final String APOGEE_HEIGHT_JSON_PROPERTY = "APOGEE";
    private static final String PERIGEE_HEIGHT_JSON_PROPERTY = "PERIGEE";
    private static final String DECAYED_JSON_PROPERTY = "DECAYED";


    @JsonProperty(ORDINAL_JSON_PROPERTY)
    private Integer ordinal;

    @JsonProperty(COMMENT_JSON_PROPERTY)
    private String comment;

    @JsonProperty(ORIGINATOR_JSON_PROPERTY)
    private String originator;

    @JsonProperty(CATALOG_NUMBER_JSON_PROPERTY)
    private Optional<Integer> catalogNumber;

    @JsonProperty(OBJECT_NAME_JSON_PROPERTY)
    private String objectName;

    @JsonProperty(OBJECT_TYPE_JSON_PROPERTY)
    private Optional<String> objectType;

    @JsonProperty(CLASSIFICATION_JSON_PROPERTY)
    private String classification;

    @JsonProperty(INTERNATIONAL_DESIGNATOR_JSON_PROPERTY)
    private Optional<String> internationalDesignator;

    @JsonProperty(EPOCH_YMD_HMS_JSON_PROPERTY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant epochYmdHms;

    @JsonProperty(EPOCH_MICROSECONDS_JSON_PROPERTY)
    private Integer epochMicroseconds;

    @JsonProperty(MEAN_MOTION_JSON_PROPERTY)
    private Double meanMotionRevsPerDay;

    @JsonProperty(ECCENTRICITY_JSON_PROPERTY)
    private Double eccentricity;

    @JsonProperty(INCLINATION_JSON_PROPERTY)
    private Double inclinationDegrees;

    @JsonProperty(RIGHT_ASC_OF_NODE_JSON_PROPERTY)
    private Double rightAscOfNodeDegrees;

    @JsonProperty(ARG_OF_PERIGEE_JSON_PROPERTY)
    private Double argOfPerigeeDegrees;

    @JsonProperty(MEAN_ANOMALY_JSON_PROPERTY)
    private Double meanAnomalyDegrees;

    @JsonProperty(EPHEMERIS_TYPE_JSON_PROPERTY)
    private Integer ephemerisType;

    @JsonProperty(ELEMENT_SET_NUMBER_JSON_PROPERTY)
    private Integer elementSetNumber;

    @JsonProperty(REV_NUMBER_JSON_PROPERTY)
    private Float revNumber;

    @JsonProperty(BSTAR_JSON_PROPERTY)
    private Double bstar;

    @JsonProperty(MEAN_MOTION_DOT_JSON_PROPERTY)
    private Double meanMotionDot;

    @JsonProperty(MEAN_MOTION_DOUBLE_DOT_JSON_PROPERTY)
    private Double meanMotionDoubleDot;

    @JsonProperty(FILE_NUMBER_JSON_PROPERTY)
    private Integer fileNumber;

    @JsonProperty(TLE_LINE_0_JSON_PROPERTY)
    private String tleLine0;

    @JsonProperty(TLE_LINE_1_JSON_PROPERTY)
    private String tleLine1;

    @JsonProperty(TLE_LINE_2_JSON_PROPERTY)
    private String tleLine2;

    @JsonProperty(OBJECT_ID_JSON_PROPERTY)
    private Optional<String> objectId;

    @JsonProperty(OBJECT_NUMBER_JSON_PROPERTY)
    private Optional<Integer> objectNumber;

    @JsonProperty(SEMI_MAJOR_AXIS_JSON_PROPERTY)
    private Double semiMajorAxisKilometers;

    @JsonProperty(PERIOD_JSON_PROPERTY)
    private Optional<Double> periodMinutes;

    /**
     * Approximate height of the apogee assuming two-body motion and a spherical Earth with radius 6378.135 km
     */
    @JsonProperty(APOGEE_HEIGHT_JSON_PROPERTY)
    private Double apogeeHeightKilometers;

    /**
     * Approximate height of the perigee assuming two-body motion and a spherical Earth with radius 6378.135 km
     */
    @JsonProperty(PERIGEE_HEIGHT_JSON_PROPERTY)
    private Double perigeeHeightKilometers;

    @JsonProperty(DECAYED_JSON_PROPERTY)
    private Optional<Integer> decayed;


    public Instant getEpoch() {
      return getEpochYmdHms().plus(getEpochMicroseconds(), ChronoUnit.MICROS);
    }


    public Double getApogeeRadiusKilometers() {
      return (getSemiMajorAxisKilometers() * (1.0 + getEccentricity()));
    }


    public Double getPerigeeRadiusKilometers() {
      return (getSemiMajorAxisKilometers() * (1.0 - getEccentricity()));
    }
  }
}
