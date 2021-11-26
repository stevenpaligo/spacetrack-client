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
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.io.IOUtils;
import org.threeten.extra.scale.TaiInstant;
import org.threeten.extra.scale.UtcInstant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.json.JsonSanitizer;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.credential.DefaultCredentialProvider;
import com.stevenpaligo.spacetrack.client.predicate.Contains;
import com.stevenpaligo.spacetrack.client.predicate.CurrentDateTimeOffset;
import com.stevenpaligo.spacetrack.client.predicate.Equal;
import com.stevenpaligo.spacetrack.client.predicate.GreaterThan;
import com.stevenpaligo.spacetrack.client.predicate.In;
import com.stevenpaligo.spacetrack.client.predicate.InclusiveRange;
import com.stevenpaligo.spacetrack.client.predicate.IsNotNull;
import com.stevenpaligo.spacetrack.client.predicate.IsNull;
import com.stevenpaligo.spacetrack.client.predicate.LessThan;
import com.stevenpaligo.spacetrack.client.predicate.NotEqual;
import com.stevenpaligo.spacetrack.client.predicate.Predicate;
import com.stevenpaligo.spacetrack.client.predicate.StartsWith;
import com.stevenpaligo.spacetrack.client.query.Limit;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.query.Sort;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Base class for querying from <a href="https://www.space-track.org/">Space-Track.org</a>. The class follows the builder pattern: the query is constructed using methods like
 * {@link #addPredicate(com.stevenpaligo.spacetrack.client.predicate.Predicate)} and then executed with {@link #execute()}.
 * 
 * @author Steven Paligo
 */
@Slf4j
public abstract class Query<T extends QueryField, R, Q extends Query<T, R, Q>> {

  private static final ObjectMapper jsonMapper;
  private static final Charset charsetUtf8 = Charset.forName("UTF-8");


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


  /**
   * Adds credentials to the query using a credential provider
   * 
   * @param credentials A non-null object providing credentials for the query
   * @return This query, for use in the builder pattern
   */
  @SuppressWarnings("unchecked")
  public Q setCredentials(@NonNull CredentialProvider credentials) {

    this.credentials = credentials;
    return (Q) this;
  }


  /**
   * Adds credentials to the query using an explicit user name and password
   * 
   * @param userName A non-null user name for authenticating the query
   * @param password A non-null password for authenticating the query
   * @return This query, for use in the builder pattern
   */
  public Q setCredentials(@NonNull String userName, @NonNull String password) {

    return setCredentials(new DefaultCredentialProvider(userName, password));
  }


  /**
   * Removes any predicates that have been added to the query
   * 
   * @return This query, for use in the builder pattern
   */
  @SuppressWarnings("unchecked")
  public Q clearPredicates() {

    predicates.clear();
    return (Q) this;
  }


  /**
   * Adds a predicate to the query
   * 
   * @param predicate A non-null predicate to add to the query
   * @return This query, for use in the builder pattern
   */
  @SuppressWarnings("unchecked")
  public Q addPredicate(@NonNull Predicate<T> predicate) {

    predicates.add(predicate);
    return (Q) this;
  }


  /**
   * Adds a collection of predicates to the query
   * 
   * @param predicates A non-null collection of predicates to add to the query
   * @return This query, for use in the builder pattern
   */
  @SuppressWarnings("unchecked")
  public Q addPredicates(@NonNull Collection<Predicate<T>> predicates) {

    this.predicates.addAll(predicates);
    return (Q) this;
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field contains the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q contains(@NonNull T field, @NonNull String value) {

    return addPredicate(new Contains<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field contains the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q contains(@NonNull T field, @NonNull Number value) {

    return addPredicate(new Contains<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field equals the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q equal(@NonNull T field, @NonNull String value) {

    return addPredicate(new Equal<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field equals the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q equal(@NonNull T field, @NonNull Number value) {

    return addPredicate(new Equal<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field equals the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q equal(@NonNull T field, @NonNull Date value) {

    return addPredicate(new Equal<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field equals the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q equal(@NonNull T field, @NonNull Instant value) {

    return addPredicate(new Equal<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field equals the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q equal(@NonNull T field, @NonNull UtcInstant value) {

    return addPredicate(new Equal<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field equals the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q equal(@NonNull T field, @NonNull TaiInstant value) {

    return addPredicate(new Equal<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field equals the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q equal(@NonNull T field, @NonNull CurrentDateTimeOffset value) {

    return addPredicate(new Equal<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is greater than the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q greaterThan(@NonNull T field, @NonNull String value) {

    return addPredicate(new GreaterThan<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is greater than the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q greaterThan(@NonNull T field, @NonNull Number value) {

    return addPredicate(new GreaterThan<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is greater than the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q greaterThan(@NonNull T field, @NonNull Date value) {

    return addPredicate(new GreaterThan<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is greater than the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q greaterThan(@NonNull T field, @NonNull Instant value) {

    return addPredicate(new GreaterThan<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is greater than the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q greaterThan(@NonNull T field, @NonNull UtcInstant value) {

    return addPredicate(new GreaterThan<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is greater than the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q greaterThan(@NonNull T field, @NonNull TaiInstant value) {

    return addPredicate(new GreaterThan<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is greater than the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q greaterThan(@NonNull T field, @NonNull CurrentDateTimeOffset value) {

    return addPredicate(new GreaterThan<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is equal to any one of the given values
   * 
   * @param field The non-null name of the field to test against
   * @param value The collection of values to test against
   * @return This query, for use in the builder pattern
   */
  public Q in(@NonNull T field, String... values) {

    return addPredicate(new In<>(field, values));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is equal to any one of the given values
   * 
   * @param field The non-null name of the field to test against
   * @param value The collection of values to test against
   * @return This query, for use in the builder pattern
   */
  public Q in(@NonNull T field, Date... values) {

    return addPredicate(new In<>(field, values));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is equal to any one of the given values
   * 
   * @param field The non-null name of the field to test against
   * @param value The collection of values to test against
   * @return This query, for use in the builder pattern
   */
  public Q in(@NonNull T field, Instant... values) {

    return addPredicate(new In<>(field, values));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is equal to any one of the given values
   * 
   * @param field The non-null name of the field to test against
   * @param value The collection of values to test against
   * @return This query, for use in the builder pattern
   */
  public Q in(@NonNull T field, UtcInstant... values) {

    return addPredicate(new In<>(field, values));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is equal to any one of the given values
   * 
   * @param field The non-null name of the field to test against
   * @param value The collection of values to test against
   * @return This query, for use in the builder pattern
   */
  public Q in(@NonNull T field, TaiInstant... values) {

    return addPredicate(new In<>(field, values));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is equal to any one of the given values
   * 
   * @param field The non-null name of the field to test against
   * @param value The collection of values to test against
   * @return This query, for use in the builder pattern
   */
  public Q in(@NonNull T field, Number... values) {

    return addPredicate(new In<>(field, values));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is equal to any one of the given values
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null collections of value to test against
   * @param valueType The type of values in the collection
   * @return This query, for use in the builder pattern
   */
  public <V> Q in(@NonNull T field, @NonNull Collection<V> values, @NonNull Class<V> valueType) {

    return addPredicate(new In<>(field, values, valueType));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is within the given range of values (inclusive)
   * 
   * @param field The non-null name of the field to test against
   * @param minValue The minimum value in the range to test against (non-null)
   * @param maxValue The maximum value in the range to test against (non-null)
   * @return This query, for use in the builder pattern
   */
  public Q inclusiveRange(@NonNull T field, @NonNull Number minValue, @NonNull Number maxValue) {

    return addPredicate(new InclusiveRange<>(field, minValue, maxValue));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is within the given range of values (inclusive)
   * 
   * @param field The non-null name of the field to test against
   * @param minValue The minimum value in the range to test against (non-null)
   * @param maxValue The maximum value in the range to test against (non-null)
   * @return This query, for use in the builder pattern
   */
  public Q inclusiveRange(@NonNull T field, @NonNull Date minValue, @NonNull Date maxValue) {

    return addPredicate(new InclusiveRange<>(field, minValue, maxValue));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is within the given range of values (inclusive)
   * 
   * @param field The non-null name of the field to test against
   * @param minValue The minimum value in the range to test against (non-null)
   * @param maxValue The maximum value in the range to test against (non-null)
   * @return This query, for use in the builder pattern
   */
  public Q inclusiveRange(@NonNull T field, @NonNull CurrentDateTimeOffset minValue, @NonNull Date maxValue) {

    return addPredicate(new InclusiveRange<>(field, minValue, maxValue));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is within the given range of values (inclusive)
   * 
   * @param field The non-null name of the field to test against
   * @param minValue The minimum value in the range to test against (non-null)
   * @param maxValue The maximum value in the range to test against (non-null)
   * @return This query, for use in the builder pattern
   */
  public Q inclusiveRange(@NonNull T field, @NonNull Date minValue, @NonNull CurrentDateTimeOffset maxValue) {

    return addPredicate(new InclusiveRange<>(field, minValue, maxValue));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is within the given range of values (inclusive)
   * 
   * @param field The non-null name of the field to test against
   * @param minValue The minimum value in the range to test against (non-null)
   * @param maxValue The maximum value in the range to test against (non-null)
   * @return This query, for use in the builder pattern
   */
  public Q inclusiveRange(@NonNull T field, @NonNull Instant minValue, @NonNull Instant maxValue) {

    return addPredicate(new InclusiveRange<>(field, minValue, maxValue));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is within the given range of values (inclusive)
   * 
   * @param field The non-null name of the field to test against
   * @param minValue The minimum value in the range to test against (non-null)
   * @param maxValue The maximum value in the range to test against (non-null)
   * @return This query, for use in the builder pattern
   */
  public Q inclusiveRange(@NonNull T field, @NonNull CurrentDateTimeOffset minValue, @NonNull Instant maxValue) {

    return addPredicate(new InclusiveRange<>(field, minValue, maxValue));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is within the given range of values (inclusive)
   * 
   * @param field The non-null name of the field to test against
   * @param minValue The minimum value in the range to test against (non-null)
   * @param maxValue The maximum value in the range to test against (non-null)
   * @return This query, for use in the builder pattern
   */
  public Q inclusiveRange(@NonNull T field, @NonNull Instant minValue, @NonNull CurrentDateTimeOffset maxValue) {

    return addPredicate(new InclusiveRange<>(field, minValue, maxValue));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is within the given range of values (inclusive)
   * 
   * @param field The non-null name of the field to test against
   * @param minValue The minimum value in the range to test against (non-null)
   * @param maxValue The maximum value in the range to test against (non-null)
   * @return This query, for use in the builder pattern
   */
  public Q inclusiveRange(@NonNull T field, @NonNull UtcInstant minValue, @NonNull UtcInstant maxValue) {

    return addPredicate(new InclusiveRange<>(field, minValue, maxValue));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is within the given range of values (inclusive)
   * 
   * @param field The non-null name of the field to test against
   * @param minValue The minimum value in the range to test against (non-null)
   * @param maxValue The maximum value in the range to test against (non-null)
   * @return This query, for use in the builder pattern
   */
  public Q inclusiveRange(@NonNull T field, @NonNull CurrentDateTimeOffset minValue, @NonNull UtcInstant maxValue) {

    return addPredicate(new InclusiveRange<>(field, minValue, maxValue));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is within the given range of values (inclusive)
   * 
   * @param field The non-null name of the field to test against
   * @param minValue The minimum value in the range to test against (non-null)
   * @param maxValue The maximum value in the range to test against (non-null)
   * @return This query, for use in the builder pattern
   */
  public Q inclusiveRange(@NonNull T field, @NonNull UtcInstant minValue, @NonNull CurrentDateTimeOffset maxValue) {

    return addPredicate(new InclusiveRange<>(field, minValue, maxValue));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is within the given range of values (inclusive)
   * 
   * @param field The non-null name of the field to test against
   * @param minValue The minimum value in the range to test against (non-null)
   * @param maxValue The maximum value in the range to test against (non-null)
   * @return This query, for use in the builder pattern
   */
  public Q inclusiveRange(@NonNull T field, @NonNull TaiInstant minValue, @NonNull TaiInstant maxValue) {

    return addPredicate(new InclusiveRange<>(field, minValue, maxValue));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is within the given range of values (inclusive)
   * 
   * @param field The non-null name of the field to test against
   * @param minValue The minimum value in the range to test against (non-null)
   * @param maxValue The maximum value in the range to test against (non-null)
   * @return This query, for use in the builder pattern
   */
  public Q inclusiveRange(@NonNull T field, @NonNull CurrentDateTimeOffset minValue, @NonNull TaiInstant maxValue) {

    return addPredicate(new InclusiveRange<>(field, minValue, maxValue));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is within the given range of values (inclusive)
   * 
   * @param field The non-null name of the field to test against
   * @param minValue The minimum value in the range to test against (non-null)
   * @param maxValue The maximum value in the range to test against (non-null)
   * @return This query, for use in the builder pattern
   */
  public Q inclusiveRange(@NonNull T field, @NonNull TaiInstant minValue, @NonNull CurrentDateTimeOffset maxValue) {

    return addPredicate(new InclusiveRange<>(field, minValue, maxValue));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is within the given range of values (inclusive)
   * 
   * @param field The non-null name of the field to test against
   * @param minValue The minimum value in the range to test against (non-null)
   * @param maxValue The maximum value in the range to test against (non-null)
   * @return This query, for use in the builder pattern
   */
  public Q inclusiveRange(@NonNull T field, @NonNull CurrentDateTimeOffset minValue, @NonNull CurrentDateTimeOffset maxValue) {

    return addPredicate(new InclusiveRange<>(field, minValue, maxValue));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is not null
   * 
   * @param field The non-null name of the field to test against
   * @return This query, for use in the builder pattern
   */
  public Q isNotNull(@NonNull T field) {

    return addPredicate(new IsNotNull<>(field));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is null
   * 
   * @param field The non-null name of the field to test against
   * @return This query, for use in the builder pattern
   */
  public Q isNull(@NonNull T field) {

    return addPredicate(new IsNull<>(field));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is less than the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q lessThan(@NonNull T field, @NonNull String value) {

    return addPredicate(new LessThan<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is less than the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q lessThan(@NonNull T field, @NonNull Number value) {

    return addPredicate(new LessThan<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is less than the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q lessThan(@NonNull T field, @NonNull Date value) {

    return addPredicate(new LessThan<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is less than the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q lessThan(@NonNull T field, @NonNull Instant value) {

    return addPredicate(new LessThan<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is less than the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q lessThan(@NonNull T field, @NonNull UtcInstant value) {

    return addPredicate(new LessThan<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is less than the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q lessThan(@NonNull T field, @NonNull TaiInstant value) {

    return addPredicate(new LessThan<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is less than the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q lessThan(@NonNull T field, @NonNull CurrentDateTimeOffset value) {

    return addPredicate(new LessThan<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is not equal to the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q notEqual(@NonNull T field, @NonNull String value) {

    return addPredicate(new NotEqual<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is not equal to the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q notEqual(@NonNull T field, @NonNull Number value) {

    return addPredicate(new NotEqual<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is not equal to the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q notEqual(@NonNull T field, @NonNull Date value) {

    return addPredicate(new NotEqual<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is not equal to the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q notEqual(@NonNull T field, @NonNull Instant value) {

    return addPredicate(new NotEqual<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is not equal to the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q notEqual(@NonNull T field, @NonNull UtcInstant value) {

    return addPredicate(new NotEqual<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is not equal to the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q notEqual(@NonNull T field, @NonNull TaiInstant value) {

    return addPredicate(new NotEqual<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field is not equal to the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q notEqual(@NonNull T field, @NonNull CurrentDateTimeOffset value) {

    return addPredicate(new NotEqual<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field starts with the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q startsWith(@NonNull T field, @NonNull String value) {

    return addPredicate(new StartsWith<>(field, value));
  }


  /**
   * Adds a {@link Predicate} that filters results based on whether or not the given field starts with the given value
   * 
   * @param field The non-null name of the field to test against
   * @param value The non-null value to test against
   * @return This query, for use in the builder pattern
   */
  public Q startsWith(@NonNull T field, @NonNull Number value) {

    return addPredicate(new StartsWith<>(field, value));
  }


  /**
   * Set or remove the query's result limit, based on whether or not the {@link Optional} is empty
   * 
   * @param limit A non-null {@link Optional} that may or may not contain a {@link Limit} object
   * @return This query, for use in the builder pattern
   */
  @SuppressWarnings("unchecked")
  public Q setLimit(@NonNull Optional<Limit> limit) {

    this.limit = limit;
    return (Q) this;
  }


  /**
   * Set the query's result limit
   * 
   * @param limit A non-null {@link Limit} to set on the query
   * @return This query, for use in the builder pattern
   */
  @SuppressWarnings("unchecked")
  public Q setLimit(@NonNull Limit limit) {

    this.limit = Optional.of(limit);
    return (Q) this;
  }


  /**
   * Removes any sorting fields that have been added to the query
   * 
   * @return This query, for use in the builder pattern
   */
  @SuppressWarnings("unchecked")
  public Q clearSorts() {

    sorts.clear();
    return (Q) this;
  }


  /**
   * Adds a sorting field to the query. The order of the sorting fields <strong>does</strong> matter. They will be added to the query in the order of the calls to this method and {@link #addSorts(List)}.
   * 
   * @param sort A non-null sorting field to add to the query
   * @return This query, for use in the builder pattern
   */
  @SuppressWarnings("unchecked")
  public Q addSort(@NonNull Sort<T> sort) {

    sorts.add(sort);
    return (Q) this;
  }


  /**
   * Adds a collection of sorting fields to the query. The order of the sorting fields <strong>does</strong> matter. They will be added to the query in the order of the calls to this method and {@link #addSort(Sort)}.
   * 
   * @param sorts A non-null collection of sorting fields to add to the query
   * @return This query, for use in the builder pattern
   */
  @SuppressWarnings("unchecked")
  public Q addSorts(@NonNull List<Sort<T>> sorts) {

    this.sorts.addAll(sorts);
    return (Q) this;
  }


  /**
   * Removes any satellite lists ("favorites") that have been added to the query
   * 
   * @return This query, for use in the builder pattern
   */
  @SuppressWarnings("unchecked")
  public Q clearFavorites() {

    favorites.clear();
    return (Q) this;
  }


  /**
   * Adds a satellite list (a "favorite") to the query for use in filtering results
   * 
   * @param favorite The non-null name of a satellite list to add to the query
   * @return This query, for use in the builder pattern
   */
  @SuppressWarnings("unchecked")
  public Q addFavorite(@NonNull String favorite) {

    favorites.add(favorite);
    return (Q) this;
  }


  /**
   * Adds a collection of satellite lists ("favorites") to the query for use in filtering results
   * 
   * @param favorites The non-null collection of satellite list names to add to the query
   * @return This query, for use in the builder pattern
   */
  @SuppressWarnings("unchecked")
  public Q addFavorites(@NonNull Collection<String> favorites) {

    this.favorites.addAll(favorites);
    return (Q) this;
  }


  /**
   * Gets the <a href="https://www.space-track.org/">Space-Track.org</a> URL that represents this query. This URL is the same as what will be generated by invoking {@link #execute()}.
   * 
   * <p>
   * <strong>Note:</strong> This method does not need to be called prior to calling {@link #execute()}. That method will automatically generate the URL as necessary.
   * </p>
   * 
   * @return The <a href="https://www.space-track.org/">Space-Track.org</a> URL generated from building this query.
   */
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
    builder.append("/format/json/emptyresult/show");


    return builder.toString();
  }


  /**
   * Executes the query against the <a href="https://www.space-track.org/">Space-Track.org</a> API and returns the results
   * 
   * @return The results from executing the query
   * @throws JsonParseException Space-Track.org returned results that are unable to be parsed
   * @throws JsonMappingException Space-Track.org returned unexpected fields in the results
   * @throws IOException The Space-Track.org API was unable to be queried successfully
   */
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
      log.debug("Querying Space-Track (user: {}, query: {})", userName, queryString);


      // connect to the Space-Track API
      URL url = new URL("https://www.space-track.org/ajaxauth/login");

      connection = (HttpsURLConnection) url.openConnection();
      connection.setDoOutput(true);
      connection.setRequestMethod("POST");
      connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");


      // send the request (log in and query at the same time)
      String request = "identity=" + userName + "&password=" + credentials.getPassword() + "&query=" + queryString;

      outputStream = connection.getOutputStream();
      outputStream.write(request.getBytes(charsetUtf8));
      outputStream.flush();


      // read the entire response
      String response = IOUtils.toString(connection.getInputStream(), charsetUtf8);
      log.debug("Space-Track response message: {}", connection.getResponseMessage());
      log.debug("Space-Track response body: {}", response);

      if (response.length() == 0) {

        throw new IOException("Space-Track returned an empty response");
      }

      if (connection.getResponseCode() != HttpsURLConnection.HTTP_OK) {

        throw new IOException("Space-Track returned an unsuccessful response: " + connection.getResponseMessage());
      }

      if (response.contains("Horribly Wrong")) { // currently Space-Track returns an HTML page when there is an error instead of using a proper HTTP response code - TODO: this is brittle

        throw new IOException("Space-Track returned an unsuccessful response");
      }


      // ensure the response is well-formed JSON
      String wellFormedResponse = JsonSanitizer.sanitize(response);


      // convert the response to a list of the return data type
      JavaType listType = jsonMapper.getTypeFactory().constructCollectionType(List.class, resultType);
      List<R> results = jsonMapper.readValue(wellFormedResponse, listType);

      log.debug("Space-Track returned {} {} results", results.size(), queryClass);


      return results;


    } finally {


      // clean up
      try {

        outputStream.close();

      } catch (Exception e) {

        log.error("An exception occurred while closing the Space-Track request stream", e);
      }

      try {

        connection.disconnect();

      } catch (Exception e) {

        log.error("An exception occurred while disconnecting from the Space-Track API", e);
      }
    }
  }
}
