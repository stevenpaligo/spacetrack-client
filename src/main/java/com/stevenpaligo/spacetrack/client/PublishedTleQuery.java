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
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.SetUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stevenpaligo.spacetrack.client.PublishedTleQuery.PublishedTle;
import com.stevenpaligo.spacetrack.client.PublishedTleQuery.PublishedTleQueryField;
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

public class PublishedTleQuery extends Query<PublishedTleQueryField, PublishedTle> {

  @Builder
  public PublishedTleQuery(@NonNull CredentialProvider credentials, @NonNull @Singular Collection<@NonNull Predicate<@NonNull PublishedTleQueryField>> predicates, Limit limit, @NonNull @Singular List<@NonNull Sort<PublishedTleQueryField>> sorts,
      @NonNull @Singular Set<@NonNull String> favorites) {

    super(PublishedTle.class, credentials, "tle_publish", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));
  }


  public static enum PublishedTleQueryField implements QueryField {

    PUBLISH_TIME {

      @Override
      public String getQueryFieldName() {
        return "PUBLISH_EPOCH";
      }
    },

    TLE_LINE1 {

      @Override
      public String getQueryFieldName() {
        return "TLE_LINE1";
      }
    },

    TLE_LINE2 {

      @Override
      public String getQueryFieldName() {
        return "TLE_LINE2";
      }
    }
  }


  @Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(value = Include.NON_NULL)
  public static class PublishedTle {

    @JsonProperty("PUBLISH_EPOCH")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "UTC")
    private Instant publishTime;

    @JsonProperty("TLE_LINE1")
    private String tleLine1;

    @JsonProperty("TLE_LINE2")
    private String tleLine2;

  }
}
