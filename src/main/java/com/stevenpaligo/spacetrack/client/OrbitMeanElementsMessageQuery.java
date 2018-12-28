package com.stevenpaligo.spacetrack.client;

import java.io.IOException;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.SetUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.predicate.Predicate;
import com.stevenpaligo.spacetrack.client.query.Limit;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.query.Sort;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;

@Builder
public class OrbitMeanElementsMessageQuery {

  @NonNull
  private CredentialProvider credentials;

  @Singular
  private Collection<Predicate<OrbitMeanElementsMessageQueryField>> predicates;

  private Limit limit;

  @Singular
  private List<Sort<OrbitMeanElementsMessageQueryField>> sorts;

  @Singular
  private Set<String> favorites;


  public List<OrbitMeanElementsMessage> execute() throws JsonParseException, JsonMappingException, IOException {

    // create a query
    Query<OrbitMeanElementsMessageQueryField, OrbitMeanElementsMessage> query =
        new Query<>(OrbitMeanElementsMessage.class, credentials, "omm", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));


    // execute the query and return the results
    return query.execute();
  }


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
        return "USER_DEFINED_TLE_LINE0";
      }
    },

    TLE_LINE1 {

      @Override
      public String getQueryFieldName() {
        return "USER_DEFINED_TLE_LINE1";
      }
    },

    TLE_LINE2 {

      @Override
      public String getQueryFieldName() {
        return "USER_DEFINED_TLE_LINE2";
      }
    }
  }


  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class OrbitMeanElementsMessage {

    @JsonProperty("CCSDS_OMM_VERS")
    private String ccsdsOmmVersion;

    @JsonProperty("COMMENT")
    private String comment;

    @JsonProperty("CREATION_DATE")
    private Instant createTime;

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
    private String epoch;

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
    private Integer catalogNumber;

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

    @JsonProperty("USER_DEFINED_TLE_LINE0")
    private String tleLine0;

    @JsonProperty("USER_DEFINED_TLE_LINE1")
    private String tleLine1;

    @JsonProperty("USER_DEFINED_TLE_LINE2")
    private String tleLine2;

  }
}
