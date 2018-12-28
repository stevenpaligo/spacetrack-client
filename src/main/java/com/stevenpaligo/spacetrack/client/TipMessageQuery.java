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
public class TipMessageQuery {

  @NonNull
  private CredentialProvider credentials;

  @Singular
  private Collection<Predicate<TipMessageQueryField>> predicates;

  private Limit limit;

  @Singular
  private List<Sort<TipMessageQueryField>> sorts;

  @Singular
  private Set<String> favorites;


  public List<TipMessage> execute() throws JsonParseException, JsonMappingException, IOException {

    // create a query
    Query<TipMessageQueryField, TipMessage> query = new Query<>(TipMessage.class, credentials, "tip", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));


    // execute the query and return the results
    return query.execute();
  }


  public static enum TipMessageQueryField implements QueryField {

    CATALOG_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "NORAD_CAT_ID";
      }
    },

    MESSAGE_EPOCH {

      @Override
      public String getQueryFieldName() {
        return "MSG_EPOCH";
      }
    },

    INSERT_EPOCH {

      @Override
      public String getQueryFieldName() {
        return "INSERT_EPOCH";
      }
    },

    DECAY_EPOCH {

      @Override
      public String getQueryFieldName() {
        return "DECAY_EPOCH";
      }
    },

    WINDOW_SIZE_MINUTES {

      @Override
      public String getQueryFieldName() {
        return "WINDOW";
      }
    },

    REV_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "REV";
      }
    },

    DIRECTION {

      @Override
      public String getQueryFieldName() {
        return "DIRECTION";
      }
    },

    TEN_KM_LATITUDE_DEGREES {

      @Override
      public String getQueryFieldName() {
        return "LAT";
      }
    },

    TEN_KM_LONGITUDE_DEGREES {

      @Override
      public String getQueryFieldName() {
        return "LON";
      }
    },

    INCLINATION_DEGREES {

      @Override
      public String getQueryFieldName() {
        return "INCL";
      }
    },

    NEXT_REPORT_TIME_HOURS {

      @Override
      public String getQueryFieldName() {
        return "NEXT_REPORT";
      }
    },

    MESSAGE_ID {

      @Override
      public String getQueryFieldName() {
        return "ID";
      }
    },

    HIGH_INTEREST {

      @Override
      public String getQueryFieldName() {
        return "HIGH_INTEREST";
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
  public static class TipMessage {

    @JsonProperty("NORAD_CAT_ID")
    private Integer catalogNumber;

    @JsonProperty("MSG_EPOCH")
    private Instant messageEpoch;

    @JsonProperty("INSERT_EPOCH")
    private Instant insertEpoch;

    @JsonProperty("DECAY_EPOCH")
    private Instant decayEpoch;

    @JsonProperty("WINDOW")
    private Integer windowSizeMinutes;

    @JsonProperty("REV")
    private Integer revNumber;

    @JsonProperty("DIRECTION")
    private Optional<String> direction;

    @JsonProperty("LAT")
    private Float tenKmLatitudeDegrees;

    @JsonProperty("LON")
    private Float tenKmLongitudeDegrees;

    @JsonProperty("INCL")
    private Float inclinationDegrees;

    @JsonProperty("NEXT_REPORT")
    private Integer nextReportTimeHours;

    @JsonProperty("ID")
    private Integer messageId;

    @JsonProperty("HIGH_INTEREST")
    private Optional<Boolean> highInterest;

    @JsonProperty("OBJECT_NUMBER")
    private Integer objectNumber;

  }
}
