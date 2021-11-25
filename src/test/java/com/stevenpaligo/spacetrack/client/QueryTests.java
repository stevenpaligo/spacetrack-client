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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.stevenpaligo.spacetrack.DelayBeforeEachTestExtension;
import com.stevenpaligo.spacetrack.TestUtils;
import com.stevenpaligo.spacetrack.client.SatCatQuery.SatCat;
import com.stevenpaligo.spacetrack.client.SatCatQuery.SatCatQueryField;
import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.predicate.Equal;
import com.stevenpaligo.spacetrack.client.query.Limit;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.query.Sort;
import lombok.NonNull;

@ExtendWith(DelayBeforeEachTestExtension.class)
public class QueryTests {

  private static CredentialProvider credentials = TestUtils.getCredentials();
  private static CredentialProvider badCredentials = TestUtils.getIncorrectCredentials();


  @Test
  @DisplayName("Builder method parameter validation")
  public void test1() {

    // the call to set the credentials will not accept a null
    assertThrows(IllegalArgumentException.class, () -> {

      new AnnouncementQuery().setCredentials(null);
    });


    // the call to addPredicate(...) will not accept a null
    assertThrows(IllegalArgumentException.class, () -> {

      new AnnouncementQuery().addPredicate(null);
    });


    // the call to addPredicates(...) will not accept a null
    assertThrows(IllegalArgumentException.class, () -> {

      new AnnouncementQuery().addPredicates(null);
    });


    // the call to addPredicates(...) will accept an empty collection
    assertDoesNotThrow(() -> {

      new AnnouncementQuery().addPredicates(Collections.emptyList());
    });


    // the call to setLimit(Optional) will not accept a null
    assertThrows(IllegalArgumentException.class, () -> {

      new AnnouncementQuery().setLimit((Optional<Limit>) null);
    });


    // the call to setLimit(Limit) will not accept a null
    assertThrows(IllegalArgumentException.class, () -> {

      new AnnouncementQuery().setLimit((Limit) null);
    });


    // the call to addSort(...) will not accept a null
    assertThrows(IllegalArgumentException.class, () -> {

      new AnnouncementQuery().addSort(null);
    });


    // the call to addSorts(...) will not accept a null
    assertThrows(IllegalArgumentException.class, () -> {

      new AnnouncementQuery().addSorts(null);
    });


    // the call to addSorts(...) will accept an empty collection
    assertDoesNotThrow(() -> {

      new AnnouncementQuery().addSorts(Collections.emptyList());
    });


    // the call to addFavorite(...) will not accept a null
    assertThrows(IllegalArgumentException.class, () -> {

      new AnnouncementQuery().addFavorite(null);
    });


    // the call to addFavorites(...) will not accept a null
    assertThrows(IllegalArgumentException.class, () -> {

      new AnnouncementQuery().addFavorites(null);
    });


    // the call to addFavorites(...) will accept an empty collection
    assertDoesNotThrow(() -> {

      new AnnouncementQuery().addFavorites(Collections.emptySet());
    });
  }


  @Test
  @DisplayName("Query string tests")
  public void test2() {

    // none of the following calls are required: addPredicate(...), addPredicates(...), setLimit(...), addSort(...), addSorts(...), addFavorite(...), addFavorites(...)
    assertEquals("https://www.space-track.org/basicspacedata/query/class/satcat/format/json/emptyresult/show", new SatCatQuery().getQueryString());


    // addPredicate(...) and addPredicates(...)
    SatCatQuery query =
        new SatCatQuery().addPredicate(new Equal<>(SatCatQueryField.CATALOG_NUMBER, 25544)).addPredicates(Arrays.asList(new Equal<>(SatCatQueryField.INTERNATIONAL_DESIGNATOR, "1998-067A"), new Equal<>(SatCatQueryField.OBJECT_TYPE, "PAYLOAD")));

    assertEquals("https://www.space-track.org/basicspacedata/query/class/satcat/NORAD_CAT_ID/25544/INTLDES/1998-067A/OBJECT_TYPE/PAYLOAD/format/json/emptyresult/show", query.getQueryString());


    // setLimit(...)
    assertEquals("https://www.space-track.org/basicspacedata/query/class/satcat/limit/1/format/json/emptyresult/show", new SatCatQuery().setLimit(Limit.ONE).getQueryString());


    // addSort(...) and addSorts(...)
    query = new SatCatQuery().addSort(new Sort<>(SatCatQueryField.COUNTRY)).addSorts(Arrays.asList(new Sort<>(SatCatQueryField.LAUNCH_YEAR), new Sort<>(SatCatQueryField.CATALOG_NUMBER)));

    assertEquals("https://www.space-track.org/basicspacedata/query/class/satcat/orderby/COUNTRY asc,LAUNCH_YEAR asc,NORAD_CAT_ID asc/format/json/emptyresult/show", query.getQueryString());


    // addFavorite(...) and addFavorites(...)
    query = new SatCatQuery().addFavorite("Amateur").addFavorites(Arrays.asList("Navigation", "Special_Interest"));

    assertEquals("https://www.space-track.org/basicspacedata/query/class/satcat/favorites/Amateur,Navigation,Special_Interest/format/json/emptyresult/show", query.getQueryString());
  }


  @Test
  @DisplayName("Query execution tests")
  public void test3() throws JsonParseException, JsonMappingException, IOException {

    // a call to set the credentials is required
    assertThrows(IllegalStateException.class, () -> {

      new AnnouncementQuery().execute();
    });


    // a successful call does not throw an exception
    assertDoesNotThrow(() -> {

      new SatCatQuery().setCredentials(credentials).addPredicate(new Equal<>(SatCatQueryField.CATALOG_NUMBER, 25544)).execute();
    });


    // a successful call returns the correct number of items that are correctly deserialized
    List<SatCat> satellites = new SatCatQuery().setCredentials(credentials).addPredicate(new Equal<>(SatCatQueryField.CATALOG_NUMBER, 25544)).execute();
    assertEquals(1, satellites.size());
    assertEquals("1998-067A", satellites.get(0).getInternationalDesignator());
    assertEquals((Integer) 25544, satellites.get(0).getCatalogNumber().get());
    assertTrue(satellites.get(0).getObjectType().isPresent()); // if this isn't true, the next line will fail
    assertEquals("PAYLOAD", satellites.get(0).getObjectType().get());
    assertEquals("ISS (ZARYA)", satellites.get(0).getSatName());
    assertEquals("ISS", satellites.get(0).getCountry());
    assertTrue(satellites.get(0).getLaunchDate().isPresent());
    assertEquals("1998-11-20", satellites.get(0).getLaunchDate().get().toString());
    assertTrue(satellites.get(0).getLaunchSite().isPresent());
    assertEquals("TTMTR", satellites.get(0).getLaunchSite().get());
    assertEquals((Integer) 1998, satellites.get(0).getLaunchYear());
    assertEquals((Integer) 67, satellites.get(0).getLaunchNumber());
    assertEquals("A", satellites.get(0).getLaunchPiece());
    assertTrue(satellites.get(0).getCurrentRecord());
    assertEquals("ISS (ZARYA)", satellites.get(0).getObjectName());
    assertEquals("1998-067A", satellites.get(0).getObjectId());
    assertEquals((Integer) 25544, satellites.get(0).getObjectNumber().get());


    // incorrect credentials trigger an exception
    assertThrows(IOException.class, () -> {

      new SatCatQuery().setCredentials(badCredentials).addPredicate(new Equal<>(SatCatQueryField.CATALOG_NUMBER, 25544)).execute();
    });


    // an incorrect query triggers an exception
    assertThrows(IOException.class, () -> {

      new SatCatQuery().setCredentials(credentials).addPredicate(new IncorrectEqualPredicate<>(SatCatQueryField.CATALOG_NUMBER, 25544)).execute();
    });
  }


  private static class IncorrectEqualPredicate<T extends QueryField> extends Equal<T> {

    public IncorrectEqualPredicate(@NonNull T field, @NonNull Number value) {

      super(field, value);
    }


    @Override
    public String toQueryParameter() {

      return "wrongWRONGwrong";
    }
  }
}
