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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stevenpaligo.spacetrack.client.LaunchSiteQuery.LaunchSite;
import com.stevenpaligo.spacetrack.client.LaunchSiteQuery.LaunchSiteQueryField;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for querying launch sites from <a href="https://www.space-track.org/">Space-Track.org</a>. The class follows the builder pattern: the query is constructed using methods like
 * {@link #addPredicate(com.stevenpaligo.spacetrack.client.predicate.Predicate)} and then executed with {@link #execute()}.
 * 
 * @author Steven Paligo
 */
public class LaunchSiteQuery extends Query<LaunchSiteQueryField, LaunchSite, LaunchSiteQuery> {

  public LaunchSiteQuery() {

    super("launch_site", LaunchSite.class);
  }


  /**
   * Fields referenced in "launch site" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.LaunchSiteQuery
   */
  public static enum LaunchSiteQueryField implements QueryField {

    SITE_CODE {

      @Override
      public String getQueryFieldName() {

        return LaunchSite.SITE_CODE_JSON_PROPERTY;
      }
    },

    SITE_NAME {

      @Override
      public String getQueryFieldName() {

        return LaunchSite.SITE_NAME_JSON_PROPERTY;
      }
    }
  }


  /**
   * Class representing results returned from "launch site" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
   * 
   * @author Steven Paligo
   * @see com.stevenpaligo.spacetrack.client.LaunchSiteQuery
   */
  @Getter
  @Setter
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class LaunchSite {

    private static final String SITE_CODE_JSON_PROPERTY = "SITE_CODE";
    private static final String SITE_NAME_JSON_PROPERTY = "LAUNCH_SITE";


    @JsonProperty(SITE_CODE_JSON_PROPERTY)
    private String siteCode;

    @JsonProperty(SITE_NAME_JSON_PROPERTY)
    private String siteName;

  }
}
