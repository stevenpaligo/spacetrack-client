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

import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stevenpaligo.spacetrack.client.AnnouncementQuery.Announcement;
import com.stevenpaligo.spacetrack.client.AnnouncementQuery.AnnouncementQueryField;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AnnouncementQuery extends Query<AnnouncementQueryField, Announcement, AnnouncementQuery> {

  public AnnouncementQuery() {

    super("announcement", Announcement.class);
  }


  public static enum AnnouncementQueryField implements QueryField {

    TYPE {

      @Override
      public String getQueryFieldName() {
        return "announcement_type";
      }
    },

    TEXT {

      @Override
      public String getQueryFieldName() {
        return "announcement_text";
      }
    },

    START_TIME {

      @Override
      public String getQueryFieldName() {
        return "announcement_start";
      }
    },

    END_TIME {

      @Override
      public String getQueryFieldName() {
        return "announcement_end";
      }
    }
  }


  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class Announcement {

    @JsonProperty("announcement_type")
    private String type;

    @JsonProperty("announcement_text")
    private String text;

    @JsonProperty("announcement_start")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant startTime;

    @JsonProperty("announcement_end")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant endTime;

  }
}
