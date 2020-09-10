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
import com.stevenpaligo.spacetrack.client.util.DecimalToBooleanDeserializer;
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

        return OrbitMeanElementsMessage.CCSDS_OMM_VERSION_JSON_PROPERTY;
      }
    },

    COMMENT {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.COMMENT_JSON_PROPERTY;
      }
    },

    CREATE_TIME {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.CREATE_TIME_JSON_PROPERTY;
      }
    },

    ORIGINATOR {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.ORIGINATOR_JSON_PROPERTY;
      }
    },

    OBJECT_NAME {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.OBJECT_NAME_JSON_PROPERTY;
      }
    },

    OBJECT_ID {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.OBJECT_ID_JSON_PROPERTY;
      }
    },

    CENTER_NAME {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.CENTER_NAME_JSON_PROPERTY;
      }
    },

    REFERENCE_FRAME {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.REFERENCE_FRAME_JSON_PROPERTY;
      }
    },

    TIME_SYSTEM {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.TIME_SYSTEM_JSON_PROPERTY;
      }
    },

    MEAN_ELEMENT_THEORY {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.MEAN_ELEMENT_THEORY_JSON_PROPERTY;
      }
    },

    EPOCH {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.EPOCH_JSON_PROPERTY;
      }
    },

    MEAN_MOTION_REVS_PER_DAY {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.MEAN_MOTION_JSON_PROPERTY;
      }
    },

    ECCENTRICITY {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.ECCENTRICITY_JSON_PROPERTY;
      }
    },

    INCLINATION_DEGREES {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.INCLINATION_JSON_PROPERTY;
      }
    },

    RIGHT_ASC_OF_NODE_DEGREES {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.RIGHT_ASC_OF_NODE_JSON_PROPERTY;
      }
    },

    ARG_OF_PERIGEE_DEGREES {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.ARG_OF_PERIGEE_JSON_PROPERTY;
      }
    },

    MEAN_ANOMALY_DEGREES {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.MEAN_ANOMALY_JSON_PROPERTY;
      }
    },

    EPHEMERIS_TYPE {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.EPHEMERIS_TYPE_JSON_PROPERTY;
      }
    },

    CLASSIFICATION {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.CLASSIFICATION_JSON_PROPERTY;
      }
    },

    CATALOG_NUMBER {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.CATALOG_NUMBER_JSON_PROPERTY;
      }
    },

    ELEMENT_SET_NUMBER {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.ELEMENT_SET_NUMBER_JSON_PROPERTY;
      }
    },

    REV_NUMBER {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.REV_NUMBER_JSON_PROPERTY;
      }
    },

    BSTAR {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.BSTAR_JSON_PROPERTY;
      }
    },

    MEAN_MOTION_DOT {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.MEAN_MOTION_DOT_JSON_PROPERTY;
      }
    },

    MEAN_MOTION_DOUBLE_DOT {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.MEAN_MOTION_DOUBLE_DOT_JSON_PROPERTY;
      }
    },

    TLE_LINE0 {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.TLE_LINE_0_JSON_PROPERTY;
      }
    },

    TLE_LINE1 {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.TLE_LINE_1_JSON_PROPERTY;
      }
    },

    TLE_LINE2 {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.TLE_LINE_2_JSON_PROPERTY;
      }
    },

    SEMI_MAJOR_AXIS_KILOMETERS {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.SEMI_MAJOR_AXIS_JSON_PROPERTY;
      }
    },

    PERIOD_MINUTES {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.PERIOD_JSON_PROPERTY;
      }
    },

    APOGEE_HEIGHT_KILOMETERS {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.APOGEE_HEIGHT_JSON_PROPERTY;
      }
    },

    PERIGEE_HEIGHT_KILOMETERS {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.PERIGEE_HEIGHT_JSON_PROPERTY;
      }
    },

    OBJECT_TYPE {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.OBJECT_TYPE_JSON_PROPERTY;
      }
    },

    DECAYED {

      @Override
      public String getQueryFieldName() {

        return OrbitMeanElementsMessage.DECAYED_JSON_PROPERTY;
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

    private static final String CCSDS_OMM_VERSION_JSON_PROPERTY = "CCSDS_OMM_VERS";
    private static final String COMMENT_JSON_PROPERTY = "COMMENT";
    private static final String CREATE_TIME_JSON_PROPERTY = "CREATION_DATE";
    private static final String ORIGINATOR_JSON_PROPERTY = "ORIGINATOR";
    private static final String OBJECT_NAME_JSON_PROPERTY = "OBJECT_NAME";
    private static final String OBJECT_ID_JSON_PROPERTY = "OBJECT_ID";
    private static final String CENTER_NAME_JSON_PROPERTY = "CENTER_NAME";
    private static final String REFERENCE_FRAME_JSON_PROPERTY = "REF_FRAME";
    private static final String TIME_SYSTEM_JSON_PROPERTY = "TIME_SYSTEM";
    private static final String MEAN_ELEMENT_THEORY_JSON_PROPERTY = "MEAN_ELEMENT_THEORY";
    private static final String EPOCH_JSON_PROPERTY = "EPOCH";
    private static final String MEAN_MOTION_JSON_PROPERTY = "MEAN_MOTION";
    private static final String ECCENTRICITY_JSON_PROPERTY = "ECCENTRICITY";
    private static final String INCLINATION_JSON_PROPERTY = "INCLINATION";
    private static final String RIGHT_ASC_OF_NODE_JSON_PROPERTY = "RA_OF_ASC_NODE";
    private static final String ARG_OF_PERIGEE_JSON_PROPERTY = "ARG_OF_PERICENTER";
    private static final String MEAN_ANOMALY_JSON_PROPERTY = "MEAN_ANOMALY";
    private static final String EPHEMERIS_TYPE_JSON_PROPERTY = "EPHEMERIS_TYPE";
    private static final String CLASSIFICATION_JSON_PROPERTY = "CLASSIFICATION_TYPE";
    private static final String CATALOG_NUMBER_JSON_PROPERTY = "NORAD_CAT_ID";
    private static final String ELEMENT_SET_NUMBER_JSON_PROPERTY = "ELEMENT_SET_NO";
    private static final String REV_NUMBER_JSON_PROPERTY = "REV_AT_EPOCH";
    private static final String BSTAR_JSON_PROPERTY = "BSTAR";
    private static final String MEAN_MOTION_DOT_JSON_PROPERTY = "MEAN_MOTION_DOT";
    private static final String MEAN_MOTION_DOUBLE_DOT_JSON_PROPERTY = "MEAN_MOTION_DDOT";
    private static final String TLE_LINE_0_JSON_PROPERTY = "TLE_LINE0";
    private static final String TLE_LINE_1_JSON_PROPERTY = "TLE_LINE1";
    private static final String TLE_LINE_2_JSON_PROPERTY = "TLE_LINE2";
    private static final String SEMI_MAJOR_AXIS_JSON_PROPERTY = "SEMIMAJOR_AXIS";
    private static final String PERIOD_JSON_PROPERTY = "PERIOD";
    private static final String APOGEE_HEIGHT_JSON_PROPERTY = "APOAPSIS";
    private static final String PERIGEE_HEIGHT_JSON_PROPERTY = "PERIAPSIS";
    private static final String OBJECT_TYPE_JSON_PROPERTY = "OBJECT_TYPE";
    private static final String DECAYED_JSON_PROPERTY = "DECAYED";


    @JsonProperty(CCSDS_OMM_VERSION_JSON_PROPERTY)
    private String ccsdsOmmVersion;

    @JsonProperty(COMMENT_JSON_PROPERTY)
    private String comment;

    @JsonProperty(CREATE_TIME_JSON_PROPERTY)
    @JsonDeserialize(using = UtcInstantDeserializer.class)
    private UtcInstant createTime;

    @JsonProperty(ORIGINATOR_JSON_PROPERTY)
    private String originator;

    @JsonProperty(OBJECT_NAME_JSON_PROPERTY)
    private String objectName;

    @JsonProperty(OBJECT_ID_JSON_PROPERTY)
    private Optional<String> objectId = Optional.empty();

    @JsonProperty(CENTER_NAME_JSON_PROPERTY)
    private String centerName;

    @JsonProperty(REFERENCE_FRAME_JSON_PROPERTY)
    private String referenceFrame;

    @JsonProperty(TIME_SYSTEM_JSON_PROPERTY)
    private String timeSystem;

    @JsonProperty(MEAN_ELEMENT_THEORY_JSON_PROPERTY)
    private String meanElementTheory;

    @JsonProperty(EPOCH_JSON_PROPERTY)
    private Optional<String> epoch = Optional.empty();

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

    @JsonProperty(CLASSIFICATION_JSON_PROPERTY)
    private String classification;

    @JsonProperty(CATALOG_NUMBER_JSON_PROPERTY)
    private Optional<Integer> catalogNumber = Optional.empty();

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

    @JsonProperty(TLE_LINE_0_JSON_PROPERTY)
    private String tleLine0;

    @JsonProperty(TLE_LINE_1_JSON_PROPERTY)
    private String tleLine1;

    @JsonProperty(TLE_LINE_2_JSON_PROPERTY)
    private String tleLine2;

    @JsonProperty(SEMI_MAJOR_AXIS_JSON_PROPERTY)
    private Double semiMajorAxisKilometers;

    @JsonProperty(PERIOD_JSON_PROPERTY)
    private Optional<Double> periodMinutes = Optional.empty();

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

    @JsonProperty(OBJECT_TYPE_JSON_PROPERTY)
    private Optional<String> objectType = Optional.empty();

    @JsonProperty(DECAYED_JSON_PROPERTY)
    @JsonDeserialize(using = DecimalToBooleanDeserializer.class)
    private Boolean decayed;


    public Double getApogeeRadiusKilometers() {

      return (apogeeHeightKilometers + 6378.135);
    }


    public Double getPerigeeRadiusKilometers() {

      return (perigeeHeightKilometers + 6378.135);
    }
  }
}
