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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;

@Builder
public class LatestTleRequest {

  @NonNull
  private CredentialProvider credentials;

  @Singular
  private Collection<Predicate<LatestTleFields>> predicates;

  private Limit limit;

  @Singular
  private List<Sort<LatestTleFields>> sorts;

  @Singular
  private Set<String> favorites;


  public List<LatestTle> execute() throws JsonParseException, JsonMappingException, IOException {

    // create a request
    Request<LatestTleFields, LatestTle> request = new Request<>(credentials, "tle_latest", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));


    // execute the request and return the results
    return request.execute();
  }


  public static enum LatestTleFields {
    ORDINAL, COMMENT, ORIGINATOR, NORAD_CAT_ID, OBJECT_NAME, OBJECT_TYPE, CLASSIFICATION_TYPE, INTLDES, EPOCH, EPOCH_MICROSECONDS, MEAN_MOTION, ECCENTRICITY, INCLINATION, RA_OF_ASC_NODE, ARG_OF_PERICENTER, MEAN_ANOMALY, EPHEMERIS_TYPE, ELEMENT_SET_NO, REV_AT_EPOCH, BSTAR, MEAN_MOTION_DOT, MEAN_MOTION_DDOT, FILE, TLE_LINE0, TLE_LINE1, TLE_LINE2, OBJECT_ID, OBJECT_NUMBER, SEMIMAJOR_AXIS, PERIOD, APOGEE, PERIGEE
  }


  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class LatestTle {

    @JsonProperty("ORDINAL")
    private Integer ordinal;

    @JsonProperty("COMMENT")
    private String comment;

    @JsonProperty("ORIGINATOR")
    private String originator;

    @JsonProperty("NORAD_CAT_ID")
    private Integer noradCatalogId;

    @JsonProperty("OBJECT_NAME")
    private String objectName;

    @JsonProperty("OBJECT_TYPE")
    private Optional<String> objectType;

    @JsonProperty("CLASSIFICATION_TYPE")
    private String classification;

    @JsonProperty("INTLDES")
    private Optional<String> internationalDesignator;

    @Getter(AccessLevel.PRIVATE)
    @JsonProperty("EPOCH")
    private Instant epochDateTime;

    @Getter(AccessLevel.PRIVATE)
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
    private Optional<Integer> objectNumber;

    @JsonProperty("SEMIMAJOR_AXIS")
    private Double semiMajorAxisKilometers;

    @JsonProperty("PERIOD")
    private Optional<Double> period;

    @JsonProperty("APOGEE")
    private Double apogeeKilometers;

    @JsonProperty("PERIGEE")
    private Double perigeeKilometers;


    public Instant getEpoch() {
      return getEpochDateTime().plus(getEpochMicroseconds(), ChronoUnit.MICROS);
    }
  }
}
