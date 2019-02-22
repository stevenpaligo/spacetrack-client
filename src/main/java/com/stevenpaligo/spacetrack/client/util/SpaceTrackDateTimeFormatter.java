package com.stevenpaligo.spacetrack.client.util;

import java.time.Instant;
import java.util.Date;
import org.threeten.extra.scale.TaiInstant;
import org.threeten.extra.scale.UtcInstant;
import lombok.NonNull;

public class SpaceTrackDateTimeFormatter {

  private SpaceTrackDateTimeFormatter() {
    // prevent instantiation
  }


  /**
   * Converts a {@link Date} (UTC-SLS) instant to a UTC string in the format expected by SpaceTrack
   * 
   * <p>
   * <strong>Note:</strong> The conversion from UTC-SLS to UTC will not be completely accurate near a leap second. Use {@link #format(UtcInstant)} or {@link #format(TaiInstant)} if possible.
   * </p>
   * 
   * @param instant A non-null date
   * @return The UTC string corresponding to the date
   */
  public static String format(@NonNull Date date) {

    return format(Instant.ofEpochMilli(date.getTime()));
  }


  /**
   * Converts a <strong>UTC-SLS</strong> instant to a UTC string in the format expected by SpaceTrack
   * 
   * <p>
   * <strong>Note:</strong> The conversion from UTC-SLS to UTC will not be completely accurate near a leap second. Use {@link #format(UtcInstant)} or {@link #format(TaiInstant)} if possible.
   * </p>
   * 
   * @param instant A non-null <strong>UTC-SLS</strong> instant
   * @return The UTC string corresponding to the <strong>UTC-SLS</strong> instant
   */
  public static String format(@NonNull Instant instant) {

    return format(UtcInstant.of(instant));
  }


  /**
   * Converts a UTC instant to a UTC string in the format expected by SpaceTrack
   * 
   * @param instant A non-null UTC instant
   * @return The UTC string corresponding to the UTC instant
   */
  public static String format(@NonNull UtcInstant instant) {

    // SpaceTrack uses the following for its UTC date/time format: yyyy-MM-dd HH:mm:ss.SSS


    // start formatting the result
    String result = instant.toString().replace('T', ' ').replace("Z", "");


    // limit the fractional seconds to 3 digits
    if (result.indexOf('.') >= 0) {

      String fractionalSeconds = result.substring(result.indexOf('.') + 1);
      result = result.substring(0, result.indexOf('.') + 1);

      switch (fractionalSeconds.length()) {

        case 0:
          result += "000";
          break;


        case 1:
          result += fractionalSeconds + "00";
          break;


        case 2:
          result += fractionalSeconds + "0";
          break;


        case 3:
          result += fractionalSeconds;
          break;


        default:

          if (fractionalSeconds.charAt(3) >= '5') {

            result += fractionalSeconds.substring(0, 2) + (char) (fractionalSeconds.charAt(2) + 1);

          } else {

            result += fractionalSeconds.substring(0, 3);
          }
      }


    } else {


      result += ".000";
    }


    return result;
  }


  /**
   * Converts a TAI instant to a UTC string in the format expected by SpaceTrack
   * 
   * @param instant A non-null TAI instant
   * @return The UTC string corresponding to the TAI instant
   */
  public static String format(@NonNull TaiInstant instant) {

    return format(instant.toUtcInstant());
  }
}
