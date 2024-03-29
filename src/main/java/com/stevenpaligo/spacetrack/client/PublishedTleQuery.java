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

import java.util.Optional;
import org.threeten.extra.scale.UtcInstant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stevenpaligo.spacetrack.client.PublishedTleQuery.PublishedTle;
import com.stevenpaligo.spacetrack.client.PublishedTleQuery.PublishedTleQueryField;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.util.UtcInstantDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for querying TLEs by their publishing date from <a href="https://www.space-track.org/">Space-Track.org</a>. The class follows the builder pattern: the query is constructed using methods like
 * {@link #addPredicate(com.stevenpaligo.spacetrack.client.predicate.Predicate)} and then executed with {@link #execute()}.
 * 
 * @author Steven Paligo
 */
public class PublishedTleQuery extends Query<PublishedTleQueryField, PublishedTle, PublishedTleQuery> {

  public PublishedTleQuery() {

    super("tle_publish", PublishedTle.class);
  }


  /**
   * Fields referenced in "published TLE" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.PublishedTleQuery
   */
  public static enum PublishedTleQueryField implements QueryField {

    PUBLISH_TIME {

      @Override
      public String getQueryFieldName() {

        return PublishedTle.PUBLISH_TIME_JSON_PROPERTY;
      }
    },

    TLE_LINE1 {

      @Override
      public String getQueryFieldName() {

        return PublishedTle.TLE_LINE_1_JSON_PROPERTY;
      }
    },

    TLE_LINE2 {

      @Override
      public String getQueryFieldName() {

        return PublishedTle.TLE_LINE_2_JSON_PROPERTY;
      }
    },

    CATALOG_NUMBER {

      @Override
      public String getQueryFieldName() {

        return PublishedTle.CATALOG_NUMBER_JSON_PROPERTY;
      }
    }
  }


  /**
   * Class representing results returned from "published TLE" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.PublishedTleQuery
   */
  @Getter
  @Setter
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class PublishedTle {

    private static final String PUBLISH_TIME_JSON_PROPERTY = "PUBLISH_EPOCH";
    private static final String TLE_LINE_1_JSON_PROPERTY = "TLE_LINE1";
    private static final String TLE_LINE_2_JSON_PROPERTY = "TLE_LINE2";
    private static final String CATALOG_NUMBER_JSON_PROPERTY = "NORAD_CAT_ID";


    @JsonProperty(PUBLISH_TIME_JSON_PROPERTY)
    @JsonDeserialize(using = UtcInstantDeserializer.class)
    private UtcInstant publishTime;

    @JsonProperty(TLE_LINE_1_JSON_PROPERTY)
    private String tleLine1;

    @JsonProperty(TLE_LINE_2_JSON_PROPERTY)
    private String tleLine2;

    @JsonProperty(CATALOG_NUMBER_JSON_PROPERTY)
    private Optional<Integer> catalogNumber = Optional.empty();

  }
}
