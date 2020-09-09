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
package com.stevenpaligo.spacetrack.client.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.Instant;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.threeten.extra.scale.TaiInstant;
import org.threeten.extra.scale.UtcInstant;

public class SpaceTrackDateTimeFormatterTests {

  @Test
  @DisplayName("Date/Time Formatter: Date")
  public void test1() {

    // a null value is not allowed
    assertThrows(IllegalArgumentException.class, () -> {

      SpaceTrackDateTimeFormatter.format((Date) null);
    });


    // verify the correct formatting of a UTC-SLS date
    assertEquals("2019-02-21 22:26:21.000", SpaceTrackDateTimeFormatter.format(new Date(1550787981000L)));
  }


  @Test
  @DisplayName("Date/Time Formatter: Instant")
  public void test2() {

    // a null value is not allowed
    assertThrows(IllegalArgumentException.class, () -> {

      SpaceTrackDateTimeFormatter.format((Instant) null);
    });


    // verify the correct formatting of a UTC-SLS instant
    assertEquals("2007-12-03 10:15:30.000", SpaceTrackDateTimeFormatter.format(Instant.parse("2007-12-03T10:15:30.000Z")));
  }


  @Test
  @DisplayName("Date/Time Formatter: UtcInstant")
  public void test3() {

    // a null value is not allowed
    assertThrows(IllegalArgumentException.class, () -> {

      SpaceTrackDateTimeFormatter.format((UtcInstant) null);
    });


    // verify the 'T' and 'Z' are removed when formatting a UTC instant
    assertFalse(SpaceTrackDateTimeFormatter.format(UtcInstant.parse("2007-12-03T10:15:30.000Z")).contains("T"));
    assertFalse(SpaceTrackDateTimeFormatter.format(UtcInstant.parse("2007-12-03T10:15:30.000Z")).contains("Z"));


    // verify there are always three digits in the fractional seconds when formatting a UTC instant
    assertEquals("2007-12-03 10:15:30.000", SpaceTrackDateTimeFormatter.format(UtcInstant.parse("2007-12-03T10:15:30Z")));
    assertEquals("2007-12-03 10:15:30.000", SpaceTrackDateTimeFormatter.format(UtcInstant.parse("2007-12-03T10:15:30.Z")));
    assertEquals("2007-12-03 10:15:30.100", SpaceTrackDateTimeFormatter.format(UtcInstant.parse("2007-12-03T10:15:30.1Z")));
    assertEquals("2007-12-03 10:15:30.120", SpaceTrackDateTimeFormatter.format(UtcInstant.parse("2007-12-03T10:15:30.12Z")));
    assertEquals("2007-12-03 10:15:30.123", SpaceTrackDateTimeFormatter.format(UtcInstant.parse("2007-12-03T10:15:30.123Z")));
    assertEquals("2007-12-03 10:15:30.123", SpaceTrackDateTimeFormatter.format(UtcInstant.parse("2007-12-03T10:15:30.1234Z")));
    assertEquals("2007-12-03 10:15:30.124", SpaceTrackDateTimeFormatter.format(UtcInstant.parse("2007-12-03T10:15:30.1235Z")));
    assertEquals("2007-12-03 10:15:30.124", SpaceTrackDateTimeFormatter.format(UtcInstant.parse("2007-12-03T10:15:30.1236Z")));


    // verify the correct formatting of a UTC instant that does not fall on a leap second
    assertEquals("2007-12-03 10:15:30.000", SpaceTrackDateTimeFormatter.format(UtcInstant.parse("2007-12-03T10:15:30.000Z")));


    // verify the correct formatting of a UTC instant that does fall on a leap second
    assertEquals("2016-12-31 23:59:60.000", SpaceTrackDateTimeFormatter.format(UtcInstant.parse("2016-12-31T23:59:60.000Z")));
  }


  @Test
  @DisplayName("Date/Time Formatter: TaiInstant")
  public void test4() {

    // a null value is not allowed
    assertThrows(IllegalArgumentException.class, () -> {

      SpaceTrackDateTimeFormatter.format((TaiInstant) null);
    });


    // verify the correct formatting of a TAI instant
    assertEquals("2016-12-31 23:59:60.000", SpaceTrackDateTimeFormatter.format(TaiInstant.of(UtcInstant.parse("2016-12-31T23:59:60.000Z"))));
  }
}
