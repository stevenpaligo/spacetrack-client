package com.stevenpaligo.spacetrack.client;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.SetUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stevenpaligo.spacetrack.client.SatCatQuery.SatCat;
import com.stevenpaligo.spacetrack.client.SatCatQuery.SatCatQueryField;
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

public class SatCatQuery extends Query<SatCatQueryField, SatCat> {

  @Builder
  public SatCatQuery(@NonNull CredentialProvider credentials, @NonNull @Singular Collection<@NonNull Predicate<@NonNull SatCatQueryField>> predicates, Limit limit, @NonNull @Singular List<@NonNull Sort<SatCatQueryField>> sorts,
      @NonNull @Singular Set<@NonNull String> favorites) {

    super(SatCat.class, credentials, "satcat", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));
  }


  public static enum SatCatQueryField implements QueryField {

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


  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class SatCat {

    @JsonProperty("INTLDES")
    private String internationalDesignator;

    @JsonProperty("NORAD_CAT_ID")
    private Integer catalogNumber;

    @JsonProperty("OBJECT_TYPE")
    private String objectType;

    @JsonProperty("SATNAME")
    private String satName;

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
    private Boolean currentRecord;

    @JsonProperty("OBJECT_NAME")
    private String objectName;

    @JsonProperty("OBJECT_ID")
    private String objectId;

    @JsonProperty("OBJECT_NUMBER")
    private Integer objectNumber;

  }
}
