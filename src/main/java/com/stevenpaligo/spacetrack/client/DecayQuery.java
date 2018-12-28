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
public class DecayQuery {

  @NonNull
  private CredentialProvider credentials;

  @Singular
  private Collection<Predicate<DecayQueryField>> predicates;

  private Limit limit;

  @Singular
  private List<Sort<DecayQueryField>> sorts;

  @Singular
  private Set<String> favorites;


  public List<Decay> execute() throws JsonParseException, JsonMappingException, IOException {

    // create a query
    Query<DecayQueryField, Decay> query = new Query<>(Decay.class, credentials, "decay", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));


    // execute the query and return the results
    return query.execute();
  }


  public static enum DecayQueryField implements QueryField {

    CATALOG_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "NORAD_CAT_ID";
      }
    },

    OBJECT_NUMBER {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_NUMBER";
      }
    },

    OBJECT_NAME {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_NAME";
      }
    },

    INTERNATIONAL_DESIGNATOR {

      @Override
      public String getQueryFieldName() {
        return "INTLDES";
      }
    },

    OBJECT_ID {

      @Override
      public String getQueryFieldName() {
        return "OBJECT_ID";
      }
    },

    RCS_METERS_SQUARED {

      @Override
      public String getQueryFieldName() {
        return "RCS";
      }
    },

    RCS_CHARACTERIZATION {

      @Override
      public String getQueryFieldName() {
        return "RCS_SIZE";
      }
    },

    COUNTRY {

      @Override
      public String getQueryFieldName() {
        return "COUNTRY";
      }
    },

    MESSAGE_EPOCH {

      @Override
      public String getQueryFieldName() {
        return "MSG_EPOCH";
      }
    },

    DECAY_EPOCH {

      @Override
      public String getQueryFieldName() {
        return "DECAY_EPOCH";
      }
    },

    SOURCE {

      @Override
      public String getQueryFieldName() {
        return "SOURCE";
      }
    },

    MESSAGE_TYPE {

      @Override
      public String getQueryFieldName() {
        return "MSG_TYPE";
      }
    },

    DECAY_STAGE {

      @Override
      public String getQueryFieldName() {
        return "PRECEDENCE";
      }
    }
  }


  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class Decay {

    @JsonProperty("NORAD_CAT_ID")
    private Integer catalogNumber;

    @JsonProperty("OBJECT_NUMBER")
    private Integer objectNumber;

    @JsonProperty("OBJECT_NAME")
    private String objectName;

    @JsonProperty("INTLDES")
    private String internationalDesignator;

    @JsonProperty("OBJECT_ID")
    private String objectId;

    @JsonProperty("RCS")
    private Integer rcsMetersSquared;

    @JsonProperty("RCS_SIZE")
    private Optional<String> rcsCharacterization;

    @JsonProperty("COUNTRY")
    private String country;

    @JsonProperty("MSG_EPOCH")
    private Optional<Instant> messageEpoch;

    @JsonProperty("DECAY_EPOCH")
    private Optional<String> decayEpoch;

    @JsonProperty("SOURCE")
    private String source;

    @JsonProperty("MSG_TYPE")
    private String messageType;

    @JsonProperty("PRECEDENCE")
    private Long decayStage;

  }
}
