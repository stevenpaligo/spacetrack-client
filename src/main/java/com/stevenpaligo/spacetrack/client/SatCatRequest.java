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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;

@Builder
public class SatCatRequest {

  @NonNull
  private CredentialProvider credentials;

  @Singular
  private Collection<Predicate<SatCatFields>> predicates;

  private Limit limit;

  @Singular
  private List<Sort<SatCatFields>> sorts;

  @Singular
  private Set<String> favorites;


  public List<SatCat> execute() throws JsonParseException, JsonMappingException, IOException {

    // create a request
    Request<SatCatFields, SatCat> request = new Request<>(credentials, "satcat", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));


    // execute the request and return the results
    return request.execute();
  }


  public static enum SatCatFields {
    INTLDES, NORAD_CAT_ID, OBJECT_TYPE, SATNAME, COUNTRY, LAUNCH, SITE, DECAY, PERIOD, INCLINATION, APOGEE, PERIGEE, COMMENT, COMMENTCODE, RCSVALUE, RCS_SIZE, FILE, LAUNCH_YEAR, LAUNCH_NUM, LAUNCH_PIECE, CURRENT, OBJECT_NAME, OBJECT_ID, OBJECT_NUMBER
  }


  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class SatCat {

    @JsonProperty("INTLDES")
    private String internationalDesignator;

    @JsonProperty("NORAD_CAT_ID")
    private Integer noradCatalogId;

    @JsonProperty("OBJECT_TYPE")
    private String objectType;

    @JsonProperty("SATNAME")
    private String satName;

    @JsonProperty("COUNTRY")
    private String country;

    @JsonProperty("LAUNCH")
    private Optional<Instant> launchDate;

    @JsonProperty("SITE")
    private Optional<String> launchSite;

    @JsonProperty("DECAY")
    private Optional<Instant> decayDate;

    @JsonProperty("PERIOD")
    private Optional<Double> periodMinutes;

    @JsonProperty("INCLINATION")
    private Optional<Double> inclinationDegrees;

    @JsonProperty("APOGEE")
    private Optional<Double> apogeeKilometers;

    @JsonProperty("PERIGEE")
    private Optional<Double> perigeeKilometers;

    @JsonProperty("COMMENT")
    private Optional<String> comment;

    @JsonProperty("COMMENTCODE")
    private Optional<Integer> commentCode;

    @JsonProperty("RCSVALUE")
    private Integer rcsValue;

    @JsonProperty("RCS_SIZE")
    private Optional<String> rcsSize;

    @JsonProperty("FILE")
    private Integer fileNumber;

    @JsonProperty("LAUNCH_YEAR")
    private Integer launchYear;

    @JsonProperty("LAUNCH_NUM")
    private Integer launchNumber;

    @JsonProperty("LAUNCH_PIECE")
    private String launchPiece;

    @JsonProperty("CURRENT")
    private boolean currentRecord;

    @JsonProperty("OBJECT_NAME")
    private String objectName;

    @JsonProperty("OBJECT_ID")
    private String objectId;

    @JsonProperty("OBJECT_NUMBER")
    private Integer objectNumber;

  }
}
