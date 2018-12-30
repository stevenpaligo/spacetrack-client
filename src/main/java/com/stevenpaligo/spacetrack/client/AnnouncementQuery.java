package com.stevenpaligo.spacetrack.client;

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
import com.stevenpaligo.spacetrack.client.AnnouncementQuery.Announcement;
import com.stevenpaligo.spacetrack.client.AnnouncementQuery.AnnouncementQueryField;
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

public class AnnouncementQuery extends Query<AnnouncementQueryField, Announcement> {

  @Builder
  public AnnouncementQuery(@NonNull CredentialProvider credentials, @NonNull @Singular Collection<@NonNull Predicate<@NonNull AnnouncementQueryField>> predicates, Limit limit, @NonNull @Singular List<@NonNull Sort<AnnouncementQueryField>> sorts,
      @NonNull @Singular Set<@NonNull String> favorites) {

    super(Announcement.class, credentials, "announcement", CollectionUtils.emptyIfNull(predicates), Optional.ofNullable(limit), ListUtils.emptyIfNull(sorts), SetUtils.emptyIfNull(favorites));
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
    private Instant startTime;

    @JsonProperty("announcement_end")
    private Instant endTime;

  }
}
