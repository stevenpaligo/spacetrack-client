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

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
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
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Query<T extends QueryField, R, Q extends Query<T, R, Q>> {

  private static final ObjectMapper jsonMapper;


  static {

    // instantiate and configure the JSON mapper
    jsonMapper = new ObjectMapper().registerModule(new Jdk8Module()).registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }


  private String queryClass;
  private Class<R> resultType;
  private CredentialProvider credentials;
  private Collection<Predicate<T>> predicates = new LinkedList<>();
  private Optional<Limit> limit = Optional.empty();
  private List<Sort<T>> sorts = new LinkedList<>();
  private Set<String> favorites = new HashSet<>();


  public Query(@NonNull String queryClass, @NonNull Class<R> resultType) {

    this.queryClass = queryClass;
    this.resultType = resultType;
  }


  @SuppressWarnings("unchecked")
  public Q setCredentials(@NonNull CredentialProvider credentials) {

    this.credentials = credentials;
    return (Q) this;
  }


  @SuppressWarnings("unchecked")
  public Q clearPredicates() {

    predicates.clear();
    return (Q) this;
  }


  @SuppressWarnings("unchecked")
  public Q addPredicate(@NonNull Predicate<T> predicate) {

    predicates.add(predicate);
    return (Q) this;
  }


  @SuppressWarnings("unchecked")
  public Q addPredicates(@NonNull Collection<Predicate<T>> predicates) {

    this.predicates.addAll(predicates);
    return (Q) this;
  }


  @SuppressWarnings("unchecked")
  public Q setLimit(@NonNull Optional<Limit> limit) {

    this.limit = limit;
    return (Q) this;
  }


  @SuppressWarnings("unchecked")
  public Q setLimit(@NonNull Limit limit) {

    this.limit = Optional.of(limit);
    return (Q) this;
  }


  @SuppressWarnings("unchecked")
  public Q clearSorts() {

    sorts.clear();
    return (Q) this;
  }


  @SuppressWarnings("unchecked")
  public Q addSort(@NonNull Sort<T> sort) {

    sorts.add(sort);
    return (Q) this;
  }


  @SuppressWarnings("unchecked")
  public Q addSorts(@NonNull List<Sort<T>> sorts) {

    this.sorts.addAll(sorts);
    return (Q) this;
  }


  @SuppressWarnings("unchecked")
  public Q clearFavorites() {

    favorites.clear();
    return (Q) this;
  }


  @SuppressWarnings("unchecked")
  public Q addFavorite(@NonNull String favorite) {

    favorites.add(favorite);
    return (Q) this;
  }


  @SuppressWarnings("unchecked")
  public Q addFavorites(@NonNull Collection<String> favorites) {

    this.favorites.addAll(favorites);
    return (Q) this;
  }


  public String getQueryString() {

    // query class
    StringBuilder builder = new StringBuilder("https://www.space-track.org/basicspacedata/query");
    builder.append("/class/").append(queryClass);


    // predicates
    for (Predicate<T> predicate : predicates) {
      builder.append("/").append(predicate.toQueryParameter());
    }


    // limit
    if (limit.isPresent()) {
      builder.append("/limit/").append(limit.get().toQueryParameter());
    }


    // sorts
    if (sorts.size() > 0) {

      builder.append("/orderby/");
      builder.append(sorts.get(0).toQueryParameter());

      for (int i = 1; i < sorts.size(); i++) {
        builder.append(",").append(sorts.get(i).toQueryParameter());
      }
    }


    // favorites
    if (favorites.size() > 0) {
      builder.append("/favorites/").append(String.join(",", favorites));
    }


    // format
    builder.append("/format/json");


    return builder.toString();
  }


  public List<R> execute() throws JsonParseException, JsonMappingException, IOException {

    // validate
    if (credentials == null) {
      throw new IllegalStateException("The credentials have not been set");
    }


    // execute the query
    HttpsURLConnection connection = null;
    OutputStream outputStream = null;

    try {

      String userName = credentials.getUserName();
      String queryString = getQueryString();
      log.debug("Querying SpaceTrack (user: {}, query: {})", userName, queryString);


      // connect to the SpaceTrack API
      URL url = new URL("https://www.space-track.org/ajaxauth/login");

      connection = (HttpsURLConnection) url.openConnection();
      connection.setDoOutput(true);
      connection.setRequestMethod("POST");


      // send the request (log in and query at the same time)
      String request = "identity=" + userName + "&password=" + credentials.getPassword() + "&query=" + queryString;

      outputStream = connection.getOutputStream();
      outputStream.write(request.getBytes());
      outputStream.flush();


      // read the entire response
      String response = IOUtils.toString(connection.getInputStream(), Charset.forName("UTF-8"));
      log.debug("SpaceTrack response message: {}", connection.getResponseMessage());
      log.debug("SpaceTrack response body: {}", response);

      if (connection.getResponseCode() != HttpsURLConnection.HTTP_OK) {
        throw new IOException("SpaceTrack returned an unsuccessful response: " + connection.getResponseMessage());
      }


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
