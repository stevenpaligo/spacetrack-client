package com.stevenpaligo.spacetrack.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.io.IOUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.json.JsonSanitizer;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.predicate.Predicate;
import com.stevenpaligo.spacetrack.client.query.Limit;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.query.Sort;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class Query<T extends QueryField, R> {

  private static final ObjectMapper jsonMapper;


  static {

    // instantiate and configure the JSON mapper
    jsonMapper = new ObjectMapper().registerModule(new Jdk8Module()).registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }


  @NonNull
  private Class<R> resultType;

  @NonNull
  private CredentialProvider credentials;

  @NonNull
  private String queryClass;

  @NonNull
  private Collection<Predicate<T>> predicates;

  @NonNull
  private Optional<Limit> limits;

  @NonNull
  private List<Sort<T>> sorts;

  @NonNull
  private Set<String> favorites;


  public String getQueryString() {

    StringBuilder builder = new StringBuilder("https://www.space-track.org/basicspacedata/query");
    builder.append("/class/").append(queryClass);

    for (Predicate<T> predicate : predicates) {
      builder.append("/").append(predicate.toQueryParameter());
    }

    if (limits.isPresent()) {
      builder.append("/").append(limits.get().toQueryParameter());
    }

    for (Sort<T> sort : sorts) {
      builder.append("/").append(sort.toQueryParameter());
    }

    if (favorites.size() > 0) {
      builder.append("/favorites/").append(String.join(",", favorites));
    }

    builder.append("/format/json");

    return builder.toString();
  }


  public List<R> execute() throws JsonParseException, JsonMappingException, IOException {

    HttpsURLConnection connection = null;
    OutputStream outputStream = null;


    try {

      String userName = credentials.getUserName();
      String query = getQueryString();
      log.debug("Querying SpaceTrack (user: {}, query: {})", userName, query);


      // connect to the SpaceTrack API
      URL url = new URL("https://www.space-track.org/ajaxauth/login");

      connection = (HttpsURLConnection) url.openConnection();
      connection.setDoOutput(true);
      connection.setRequestMethod("POST");


      // send the request (log in and query at the same time)
      String request = "identity=" + userName + "&password=" + credentials.getPassword() + "&query=" + query;

      outputStream = connection.getOutputStream();
      outputStream.write(request.getBytes());
      outputStream.flush();


      // read the entire response
      String response = IOUtils.toString(connection.getInputStream(), Charset.forName("UTF-8"));
      // TODO: throw an exception if the response is not a 200


      // ensure the response is well-formed JSON
      String wellFormedResponse = JsonSanitizer.sanitize(response);


      // convert the response to a list of the return data type
      JavaType listType = jsonMapper.getTypeFactory().constructCollectionType(List.class, resultType);
      List<R> results = jsonMapper.readValue(wellFormedResponse, listType);

      log.debug("SpaceTrack returned {} {} results", results.size(), queryClass);


      return results;


    } finally {


      // clean up
      try {
        outputStream.close();
      } catch (Exception e) {
        log.error("An exception occurred while closing the SpaceTrack request stream", e);
      }

      try {
        connection.disconnect();
      } catch (Exception e) {
        log.error("An exception occurred while disconnecting from the SpaceTrack API", e);
      }
    }
  }
}
