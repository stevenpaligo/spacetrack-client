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
import com.stevenpaligo.spacetrack.client.OrbitMeanElementsMessageQuery.OrbitMeanElementsMessage;
import com.stevenpaligo.spacetrack.client.OrbitMeanElementsMessageQuery.OrbitMeanElementsMessageQueryField;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.util.DecimalToIntegerDeserializer;
import com.stevenpaligo.spacetrack.client.util.UtcInstantDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for querying Orbit Mean-Elements Messages from <a href="https://www.space-track.org/">Space-Track.org</a>. The class follows the builder pattern: the query is constructed using methods like
 * {@link #addPredicate(com.stevenpaligo.spacetrack.client.predicate.Predicate)} and then executed with {@link #execute()}.
 * 
 * @author Steven Paligo
 * @see <a href="https://public.ccsds.org/Pubs/502x0b2c1.pdf">CCSDS Recommended Standard 502.0-B-2</a>
 */
public class OrbitMeanElementsMessageQuery extends Query<OrbitMeanElementsMessageQueryField, OrbitMeanElementsMessage, OrbitMeanElementsMessageQuery> {

  public OrbitMeanElementsMessageQuery() {

    super("omm", OrbitMeanElementsMessage.class);
  }


  /**
   * Fields referenced in "Orbit Mean-Elements Messages" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.OrbitMeanElementsMessageQuery
   */
  public static enum OrbitMeanElementsMessageQueryField implements QueryField {

    CCSDS_OMM_VERSION {

      @Override
      public String getQueryFieldName() {
        return "CCSDS_OMM_VERS";
      }
    },

    COMMENT {

      @Override
      public String getQueryFieldName() {
        return "COMMENT";
      }
    },

    CREATE_TIME {

      @Override
      public String getQueryFieldName() {
        return "CREATION_DATE";
      }
    },

    ORIGINATOR {

      @Override
      public String getQueryFieldName() {
        return "ORIGINATOR";
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

    CENTER_NAME {

      @Override
      public String getQueryFieldName() {
        return "CENTER_NAME";
      }
    },

    REFERENCE_FRAME {

      @Override
      public String getQueryFieldName() {
        return "REF_FRAME";
      }
    },

    TIME_SYSTEM {

      @Override
      public String getQueryFieldName() {
        return "TIME_SYSTEM";
      }
    },

    MEAN_ELEMENT_THEORY {

      @Override
      public String getQueryFieldName() {
        return "MEAN_ELEMENT_THEORY";
      }
    },

    EPOCH {

      @Override
      public String getQueryFieldName() {
        return "EPOCH";
      }
    },

    MEAN_MOTION_REVS_PER_DAY {

      @Override
      public String getQueryFieldName() {
        return "MEAN_MOTION";
      }
    },

    ECCENTRICITY {

      @Override
      public String getQueryFieldName() {
        return "ECCENTRICITY";
      }
    },

    INCLINATION_DEGREES {

      @Override
      public String getQueryFieldName() {
        return "INCLINATION";
      }
    },

    RIGHT_ASC_OF_NODE_DEGREES {

      @Override
      public String getQueryFieldName() {
        return "RA_OF_ASC_NODE";
      }
    },

    ARG_OF_PERIGEE_DEGREES {

      @Override
      public String getQueryFieldName() {
        return "ARG_OF_PERICENTER";
      }
    },

    MEAN_ANOMALY_DEGREES {

      @Override
      public String getQueryFieldName() {
        return "MEAN_ANOMALY";
      }
    },

    EPHEMERIS_TYPE {

      @Override
      public String getQueryFieldName() {
        return "EPHEMERIS_TYPE";
      }
    },

    CLASSIFICATION {

      @Override
      public String getQueryFieldName() {
        return "CLASSIFICATION_TYPE";
      }
    },

    CATALOG_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "NORAD_CAT_ID";
      }
    },

    ELEMENT_SET_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "ELEMENT_SET_NO";
      }
    },

    REV_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "REV_AT_EPOCH";
      }
    },

    BSTAR {

      @Override
      public String getQueryFieldName() {
        return "BSTAR";
      }
    },

    MEAN_MOTION_DOT {

      @Override
      public String getQueryFieldName() {
        return "MEAN_MOTION_DOT";
      }
    },

    MEAN_MOTION_DOUBLE_DOT {

      @Override
      public String getQueryFieldName() {
        return "MEAN_MOTION_DDOT";
      }
    },

    TLE_LINE0 {

      @Override
      public String getQueryFieldName() {
        return "TLE_LINE0";
      }
    },

    TLE_LINE1 {

      @Override
      public String getQueryFieldName() {
        return "TLE_LINE1";
      }
    },

    TLE_LINE2 {

      @Override
      public String getQueryFieldName() {
        return "TLE_LINE2";
      }
    },

    SEMI_MAJOR_AXIS_KILOMETERS {

      @Override
      public String getQueryFieldName() {
        return "SEMIMAJOR_AXIS";
      }
    },

    PERIOD_MINUTES {

      @Override
      public String getQueryFieldName() {
        return "PERIOD";
      }
    },

    APOGEE_HEIGHT_KILOMETERS {

      @Override
      public String getQueryFieldName() {
        return "APOAPSIS";
      }
    },

    PERIGEE_HEIGHT_KILOMETERS {

      @Override
      public String getQueryFieldName() {
        return "PERIAPSIS";
      }
    },

    OBJECT_TYPE {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_TYPE";
      }
    },

    DECAYED {

      @Override
      public String getQueryFieldName() {
        return "DECAYED";
      }
    }
  }


  /**
   * Class representing results returned from "Orbit Mean-Elements Messages" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.OrbitMeanElementsMessageQuery
   */
  @Getter
  @Setter
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class OrbitMeanElementsMessage {

    @JsonProperty("CCSDS_OMM_VERS")
    private String ccsdsOmmVersion;

    @JsonProperty("COMMENT")
    private String comment;

    @JsonProperty("CREATION_DATE")
    @JsonDeserialize(using = UtcInstantDeserializer.class)
    private UtcInstant createTime;

    @JsonProperty("ORIGINATOR")
    private String originator;

    @JsonProperty("OBJECT_NAME")
    private String objectName;

    @JsonProperty("OBJECT_ID")
    private Optional<String> objectId;

    @JsonProperty("CENTER_NAME")
    private String centerName;

    @JsonProperty("REF_FRAME")
    private String referenceFrame;

    @JsonProperty("TIME_SYSTEM")
    private String timeSystem;

    @JsonProperty("MEAN_ELEMENT_THEORY")
    private String meanElementTheory;

    @JsonProperty("EPOCH")
    private Optional<String> epoch;

    @JsonProperty("MEAN_MOTION")
    private Double meanMotionRevsPerDay;

    @JsonProperty("ECCENTRICITY")
    private Double eccentricity;

    @JsonProperty("INCLINATION")
    private Double inclinationDegrees;

    @JsonProperty("RA_OF_ASC_NODE")
    private Double rightAscOfNodeDegrees;

    @JsonProperty("ARG_OF_PERICENTER")
    private Double argOfPerigeeDegrees;

    @JsonProperty("MEAN_ANOMALY")
    private Double meanAnomalyDegrees;

    @JsonProperty("EPHEMERIS_TYPE")
    private Integer ephemerisType;

    @JsonProperty("CLASSIFICATION_TYPE")
    private String classification;

    @JsonProperty("NORAD_CAT_ID")
    private Optional<Integer> catalogNumber;

    @JsonProperty("ELEMENT_SET_NO")
    private Integer elementSetNumber;

    @JsonProperty("REV_AT_EPOCH")
    private Float revNumber;

    @JsonProperty("BSTAR")
    private Double bstar;

    @JsonProperty("MEAN_MOTION_DOT")
    private Double meanMotionDot;

    @JsonProperty("MEAN_MOTION_DDOT")
    private Double meanMotionDoubleDot;

    @JsonProperty("TLE_LINE0")
    private String tleLine0;

    @JsonProperty("TLE_LINE1")
    private String tleLine1;

    @JsonProperty("TLE_LINE2")
    private String tleLine2;

    @JsonProperty("SEMIMAJOR_AXIS")
    private Double semiMajorAxisKilometers;

    @JsonProperty("PERIOD")
    private Optional<Double> periodMinutes;

    /**
     * Approximate height of the apogee assuming two-body motion and a spherical Earth with radius 6378.135 km
     */
    @JsonProperty("APOAPSIS")
    private Double apogeeHeightKilometers;

    /**
     * Approximate height of the perigee assuming two-body motion and a spherical Earth with radius 6378.135 km
     */
    @JsonProperty("PERIAPSIS")
    private Double perigeeHeightKilometers;

    @JsonProperty("OBJECT_TYPE")
    private Optional<String> objectType;

    @JsonProperty("DECAYED")
    @JsonDeserialize(using = DecimalToIntegerDeserializer.class) // TODO: the Space-Track model says this type is "decimal(3,0)", which doesn't make sense at all
    private Integer decayed;


    public Double getApogeeRadiusKilometers() {
      return (getSemiMajorAxisKilometers() * (1.0 + getEccentricity()));
    }


    public Double getPerigeeRadiusKilometers() {
      return (getSemiMajorAxisKilometers() * (1.0 - getEccentricity()));
    }
  }
}
