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

import org.threeten.extra.scale.UtcInstant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stevenpaligo.spacetrack.client.AnnouncementQuery.Announcement;
import com.stevenpaligo.spacetrack.client.AnnouncementQuery.AnnouncementQueryField;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.util.UtcInstantDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for querying announcements from <a href="https://www.space-track.org/">Space-Track.org</a>. The class follows the builder pattern: the query is constructed using methods like
 * {@link #addPredicate(com.stevenpaligo.spacetrack.client.predicate.Predicate)} and then executed with {@link #execute()}.
 * 
 * @author Steven Paligo
 */
public class AnnouncementQuery extends Query<AnnouncementQueryField, Announcement, AnnouncementQuery> {

  public AnnouncementQuery() {

    super("announcement", Announcement.class);
  }


  /**
   * Fields referenced in "announcement" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.AnnouncementQuery
   */
  public static enum AnnouncementQueryField implements QueryField {

    TYPE {

      @Override
      public String getQueryFieldName() {

        return Announcement.TYPE_JSON_PROPERTY;
      }
    },

    TEXT {

      @Override
      public String getQueryFieldName() {

        return Announcement.TEXT_JSON_PROPERTY;
      }
    },

    START_TIME {

      @Override
      public String getQueryFieldName() {

        return Announcement.START_TIME_JSON_PROPERTY;
      }
    },

    END_TIME {

      @Override
      public String getQueryFieldName() {

        return Announcement.END_TIME_JSON_PROPERTY;
      }
    }
  }


  /**
   * Class representing results returned from "announcement" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.AnnouncementQuery
   */
  @Getter
  @Setter
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Announcement {

    private static final String TYPE_JSON_PROPERTY = "announcement_type";
    private static final String TEXT_JSON_PROPERTY = "announcement_text";
    private static final String START_TIME_JSON_PROPERTY = "announcement_start";
    private static final String END_TIME_JSON_PROPERTY = "announcement_end";


    @JsonProperty(TYPE_JSON_PROPERTY)
    private String type;

    @JsonProperty(TEXT_JSON_PROPERTY)
    private String text;

    @JsonProperty(START_TIME_JSON_PROPERTY)
    @JsonDeserialize(using = UtcInstantDeserializer.class)
    private UtcInstant startTime;

    @JsonProperty(END_TIME_JSON_PROPERTY)
    @JsonDeserialize(using = UtcInstantDeserializer.class)
    private UtcInstant endTime;

  }
}
