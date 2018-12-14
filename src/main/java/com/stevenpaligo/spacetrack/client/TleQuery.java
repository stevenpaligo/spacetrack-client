package com.stevenpaligo.spacetrack.client;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
public class TleQuery {

  @NonNull
  private CredentialProvider credentials;

  @Singular
  private Collection<Predicate<TleQueryField>> predicates;

  private Limit limit;

  @Singular
  private List<Sort<TleQueryField>> sorts;

  @Singular
  private Set<String> favorites;


  public List<Tle> execute() throws JsonParseException, JsonMappingException, IOException {

    // create a query
    Query<TleQueryField, Tle> query = new Query<>(credentials, "tle", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));


    // execute the query and return the results
    return query.execute();
  }


  public static enum TleQueryField implements QueryField {

    COMMENT {

      @Override
      public String getQueryFieldName() {
        return "COMMENT";
      }
    },

    ORIGINATOR {

      @Override
      public String getQueryFieldName() {
        return "ORIGINATOR";
      }
    },

    CATALOG_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "NORAD_CAT_ID";
      }
    },

    OBJECT_NAME {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_NAME";
      }
    },

    OBJECT_TYPE {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_TYPE";
      }
    },

    CLASSIFICATION {

      @Override
      public String getQueryFieldName() {
        return "CLASSIFICATION_TYPE";
      }
    },

    INTERNATIONAL_DESIGNATOR {

      @Override
      public String getQueryFieldName() {
        return "INTLDES";
      }
    },

    EPOCH_YMD_HMS {

      @Override
      public String getQueryFieldName() {
        return "EPOCH";
      }
    },

    EPOCH_MICROSECONDS {

      @Override
      public String getQueryFieldName() {
        return "EPOCH_MICROSECONDS";
      }
    },

    MEAN_MOTION {

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

    INCLINATION {

      @Override
      public String getQueryFieldName() {
        return "INCLINATION";
      }
    },

    RIGHT_ASC_OF_NODE {

      @Override
      public String getQueryFieldName() {
        return "RA_OF_ASC_NODE";
      }
    },

    ARG_OF_PERIGEE {

      @Override
      public String getQueryFieldName() {
        return "ARG_OF_PERICENTER";
      }
    },

    MEAN_ANOMALY {

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

    MEAN_MOTION_DDOT {

      @Override
      public String getQueryFieldName() {
        return "MEAN_MOTION_DDOT";
      }
    },

    FILE_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "FILE";
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
    },

    SEMI_MAJOR_AXIS {

      @Override
      public String getQueryFieldName() {
        return "SEMIMAJOR_AXIS";
      }
    },

    PERIOD {

      @Override
      public String getQueryFieldName() {
        return "PERIOD";
      }
    },

    APOGEE_HEIGHT {

      @Override
      public String getQueryFieldName() {
        return "APOGEE";
      }
    },

    PERIGEE_HEIGHT {

      @Override
      public String getQueryFieldName() {
        return "PERIGEE";
      }
    }
  }


  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class Tle {

    @JsonProperty("COMMENT")
    private String comment;

    @JsonProperty("ORIGINATOR")
    private String originator;

    @JsonProperty("NORAD_CAT_ID")
    private Integer catalogNumber;

    @JsonProperty("OBJECT_NAME")
    private String objectName;

    @JsonProperty("OBJECT_TYPE")
    private Optional<String> objectType;

    @JsonProperty("CLASSIFICATION_TYPE")
    private String classification;

    @JsonProperty("INTLDES")
    private Optional<String> internationalDesignator;

    @JsonProperty("EPOCH")
    private Instant epochYmdHms;

    @JsonProperty("EPOCH_MICROSECONDS")
    private Integer epochMicroseconds;

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

    @JsonProperty("FILE")
    private Integer fileNumber;

    @JsonProperty("TLE_LINE0")
    private String tleLine0;

    @JsonProperty("TLE_LINE1")
    private String tleLine1;

    @JsonProperty("TLE_LINE2")
    private String tleLine2;

    @JsonProperty("OBJECT_ID")
    private Optional<String> objectId;

    @JsonProperty("OBJECT_NUMBER")
    private Integer objectNumber;

    @JsonProperty("SEMIMAJOR_AXIS")
    private Double semiMajorAxisKilometers;

    @JsonProperty("PERIOD")
    private Optional<Double> periodMinutes;

    @JsonProperty("APOGEE")
    private Double apogeeHeightKilometers;

    @JsonProperty("PERIGEE")
    private Double perigeeHeightKilometers;


    public Instant getEpoch() {
      return getEpochYmdHms().plus(getEpochMicroseconds(), ChronoUnit.MICROS);
    }
  }
}
