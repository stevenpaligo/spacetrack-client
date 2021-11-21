package com.stevenpaligo.spacetrack.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stevenpaligo.spacetrack.client.GpQuery.Gp;
import com.stevenpaligo.spacetrack.client.GpQuery.GpQueryField;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import com.stevenpaligo.spacetrack.client.util.OptionalDateTimeToUtcInstantDeserializer;
import com.stevenpaligo.spacetrack.client.util.OptionalTinyIntToBooleanDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.threeten.extra.scale.UtcInstant;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class GpQuery extends Query<GpQueryField, Gp, GpQuery>{

    public GpQuery() {
        super("gp", Gp.class);
    }

    /**
     * Fields referenced in "TLE" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
     *
     * @author Steven Paligo
     * @see TleQuery
     */
    public enum GpQueryField implements QueryField {
        CCSDS_OMM_VERS {
            @Override
            public String getQueryFieldName() {

                return Gp.CCSDS_OMM_VERS_JSON_PROPERTY;
            }
        },
        COMMENT {
            @Override
            public String getQueryFieldName() {

                return Gp.COMMENT_JSON_PROPERTY;
            }
        },
        CREATION_DATE {
            @Override
            public String getQueryFieldName() {

                return Gp.CREATION_DATE_JSON_PROPERTY;
            }
        },
        ORIGINATOR {
            @Override
            public String getQueryFieldName() {

                return Gp.ORIGINATOR_JSON_PROPERTY;
            }
        },
        OBJECT_NAME {

            @Override
            public String getQueryFieldName() {

                return Gp.OBJECT_NAME_JSON_PROPERTY;
            }
        },
        OBJECT_ID {

            @Override
            public String getQueryFieldName() {

                return Gp.OBJECT_ID_JSON_PROPERTY;
            }
        },
        CENTER_NAME {

            @Override
            public String getQueryFieldName() {

                return Gp.CENTER_NAME_JSON_PROPERTY;
            }
        },
        REF_FRAME {

            @Override
            public String getQueryFieldName() {

                return Gp.REF_FRAME_JSON_PROPERTY;
            }
        },
        TIME_SYSTEM {

            @Override
            public String getQueryFieldName() {

                return Gp.TIME_SYSTEM_JSON_PROPERTY;
            }
        },
        MEAN_ELEMENT_THEORY {

            @Override
            public String getQueryFieldName() {

                return Gp.MEAN_ELEMENT_THEORY_JSON_PROPERTY;
            }
        },
        EPOCH {

            @Override
            public String getQueryFieldName() {

                return Gp.EPOCH_YMD_HMS_JSON_PROPERTY;
            }
        },
        MEAN_MOTION {

            @Override
            public String getQueryFieldName() {

                return Gp.MEAN_MOTION_JSON_PROPERTY;
            }
        },
        ECCENTRICITY {

            @Override
            public String getQueryFieldName() {

                return Gp.ECCENTRICITY_JSON_PROPERTY;
            }
        },
        INCLINATION {

            @Override
            public String getQueryFieldName() {

                return Gp.INCLINATION_JSON_PROPERTY;
            }
        },
        RA_OF_ASC_NODE {

            @Override
            public String getQueryFieldName() {

                return Gp.RIGHT_ASC_OF_NODE_JSON_PROPERTY;
            }
        },
        ARG_OF_PERICENTER {

            @Override
            public String getQueryFieldName() {

                return Gp.ARG_OF_PERICENTER_JSON_PROPERTY;
            }
        },
        MEAN_ANOMALY {

            @Override
            public String getQueryFieldName() {

                return Gp.MEAN_ANOMALY_JSON_PROPERTY;
            }
        },
        EPHEMERIS_TYPE {

            @Override
            public String getQueryFieldName() {

                return Gp.EPHEMERIS_TYPE_JSON_PROPERTY;
            }
        },
        CLASSIFICATION_TYPE {

            @Override
            public String getQueryFieldName() {

                return Gp.CLASSIFICATION_TYPE_JSON_PROPERTY;
            }
        },
        NORAD_CAT_ID {

            @Override
            public String getQueryFieldName() {

                return Gp.CATALOG_NUMBER_JSON_PROPERTY;
            }
        },
        ELEMENT_SET_NO {

            @Override
            public String getQueryFieldName() {

                return Gp.ELEMENT_SET_NUMBER_JSON_PROPERTY;
            }
        },
        REV_AT_EPOCH {

            @Override
            public String getQueryFieldName() {

                return Gp.REV_AT_EPOCH_JSON_PROPERTY;
            }
        },
        BSTAR {

            @Override
            public String getQueryFieldName() {

                return Gp.BSTAR_JSON_PROPERTY;
            }
        },
        MEAN_MOTION_DOT {

            @Override
            public String getQueryFieldName() {

                return Gp.MEAN_MOTION_DOT_JSON_PROPERTY;
            }
        },
        MEAN_MOTION_DDOT {

            @Override
            public String getQueryFieldName() {

                return Gp.MEAN_MOTION_DOUBLE_DOT_JSON_PROPERTY;
            }
        },
        SEMIMAJOR_AXIS {

            @Override
            public String getQueryFieldName() {

                return Gp.SEMI_MAJOR_AXIS_JSON_PROPERTY;
            }
        },
        PERIOD {

            @Override
            public String getQueryFieldName() {

                return Gp.PERIOD_JSON_PROPERTY;
            }
        },
        APOAPSIS {

            @Override
            public String getQueryFieldName() {

                return Gp.APOAPSIS_JSON_PROPERTY;
            }
        },
        PERIAPSIS {

            @Override
            public String getQueryFieldName() {

                return Gp.PERIAPSIS_JSON_PROPERTY;
            }
        },
        OBJECT_TYPE {

            @Override
            public String getQueryFieldName() {

                return Gp.OBJECT_TYPE_JSON_PROPERTY;
            }
        },
        RCS_SIZE {

            @Override
            public String getQueryFieldName() {

                return Gp.RCS_SIZE_JSON_PROPERTY;
            }
        },
        COUNTRY_CODE {

            @Override
            public String getQueryFieldName() {

                return Gp.COUNTRY_CODE_JSON_PROPERTY;
            }
        },
        LAUNCH_DATE {

            @Override
            public String getQueryFieldName() {

                return Gp.LAUNCH_DATE_JSON_PROPERTY;
            }
        },
        SITE {

            @Override
            public String getQueryFieldName() {

                return Gp.SITE_JSON_PROPERTY;
            }
        },
        DECAY_DATE {

            @Override
            public String getQueryFieldName() {

                return Gp.DECAY_DATE_JSON_PROPERTY;
            }
        },
        FILE {

            @Override
            public String getQueryFieldName() {

                return Gp.FILE_JSON_PROPERTY;
            }
        },
        GP_ID {

            @Override
            public String getQueryFieldName() {

                return Gp.GP_ID_JSON_PROPERTY;
            }
        },
        TLE_LINE0 {

            @Override
            public String getQueryFieldName() {

                return Gp.TLE_LINE_0_JSON_PROPERTY;
            }
        },

        TLE_LINE1 {

            @Override
            public String getQueryFieldName() {

                return Gp.TLE_LINE_1_JSON_PROPERTY;
            }
        },

        TLE_LINE2 {

            @Override
            public String getQueryFieldName() {

                return Gp.TLE_LINE_2_JSON_PROPERTY;
            }
        },
    }


    /**
     * Class representing results returned from "TLE" queries on <a href="https://www.space-track.org/">Space-Track.org</a>.
     *
     * @author Steven Paligo
     * @see TleQuery
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Gp {
        //OMM Header
        private static final String CCSDS_OMM_VERS_JSON_PROPERTY = "CCSDS_OMM_VERS";
        private static final String COMMENT_JSON_PROPERTY = "COMMENT";
        private static final String CREATION_DATE_JSON_PROPERTY = "CREATION_DATE";
        private static final String ORIGINATOR_JSON_PROPERTY = "ORIGINATOR";
        //OMM Metadata
        private static final String OBJECT_NAME_JSON_PROPERTY = "OBJECT_NAME";
        private static final String OBJECT_ID_JSON_PROPERTY = "OBJECT_ID";
        private static final String CENTER_NAME_JSON_PROPERTY = "CENTER_NAME";
        private static final String REF_FRAME_JSON_PROPERTY = "REF_FRAME";
        private static final String TIME_SYSTEM_JSON_PROPERTY = "TIME_SYSTEM";
        private static final String MEAN_ELEMENT_THEORY_JSON_PROPERTY = "MEAN_ELEMENT_THEORY";
        //OMM Data
        private static final String EPOCH_YMD_HMS_JSON_PROPERTY = "EPOCH";
        private static final String MEAN_MOTION_JSON_PROPERTY = "MEAN_MOTION";
        private static final String ECCENTRICITY_JSON_PROPERTY = "ECCENTRICITY";
        private static final String INCLINATION_JSON_PROPERTY = "INCLINATION";
        private static final String RIGHT_ASC_OF_NODE_JSON_PROPERTY = "RA_OF_ASC_NODE";
        private static final String ARG_OF_PERICENTER_JSON_PROPERTY = "ARG_OF_PERICENTER";
        private static final String MEAN_ANOMALY_JSON_PROPERTY = "MEAN_ANOMALY";
        private static final String EPHEMERIS_TYPE_JSON_PROPERTY = "EPHEMERIS_TYPE";
        private static final String CLASSIFICATION_TYPE_JSON_PROPERTY = "CLASSIFICATION_TYPE";
        private static final String CATALOG_NUMBER_JSON_PROPERTY = "NORAD_CAT_ID";
        private static final String ELEMENT_SET_NUMBER_JSON_PROPERTY = "ELEMENT_SET_NO";
        private static final String REV_AT_EPOCH_JSON_PROPERTY = "REV_AT_EPOCH";
        private static final String BSTAR_JSON_PROPERTY = "BSTAR";
        private static final String MEAN_MOTION_DOT_JSON_PROPERTY = "MEAN_MOTION_DOT";
        private static final String MEAN_MOTION_DOUBLE_DOT_JSON_PROPERTY = "MEAN_MOTION_DDOT";
        private static final String SEMI_MAJOR_AXIS_JSON_PROPERTY = "SEMIMAJOR_AXIS";
        private static final String PERIOD_JSON_PROPERTY = "PERIOD";
        private static final String APOAPSIS_JSON_PROPERTY = "APOAPSIS";
        private static final String PERIAPSIS_JSON_PROPERTY = "PERIAPSIS";
        private static final String OBJECT_TYPE_JSON_PROPERTY = "OBJECT_TYPE";
        private static final String RCS_SIZE_JSON_PROPERTY = "RCS_SIZE";
        private static final String COUNTRY_CODE_JSON_PROPERTY = "COUNTRY_CODE";
        private static final String LAUNCH_DATE_JSON_PROPERTY = "LAUNCH_DATE";
        private static final String SITE_JSON_PROPERTY = "SITE";
        private static final String DECAY_DATE_JSON_PROPERTY = "DECAY_DATE";
        private static final String FILE_JSON_PROPERTY = "FILE";
        private static final String GP_ID_JSON_PROPERTY = "GP_ID";
        private static final String TLE_LINE_0_JSON_PROPERTY = "TLE_LINE0";
        private static final String TLE_LINE_1_JSON_PROPERTY = "TLE_LINE1";
        private static final String TLE_LINE_2_JSON_PROPERTY = "TLE_LINE2";

        // OMM Header
        @JsonProperty(CCSDS_OMM_VERS_JSON_PROPERTY)
        private String ccsdsOmmVers;

        @JsonProperty(COMMENT_JSON_PROPERTY)
        private String comment;

        @JsonProperty(value = CREATION_DATE_JSON_PROPERTY)
        @JsonDeserialize(using = OptionalDateTimeToUtcInstantDeserializer.class)
        private Optional<UtcInstant> creationDate = Optional.empty();

        @JsonProperty(ORIGINATOR_JSON_PROPERTY)
        private String originator;

        //OMM Metadata
        @JsonProperty(OBJECT_NAME_JSON_PROPERTY)
        private Optional<String> objectName = Optional.empty();

        @JsonProperty(OBJECT_ID_JSON_PROPERTY)
        private Optional<String> objectId = Optional.empty();

        @JsonProperty(CENTER_NAME_JSON_PROPERTY)
        private String  centerName;

        @JsonProperty(REF_FRAME_JSON_PROPERTY)
        private String  refFrame;

        @JsonProperty(TIME_SYSTEM_JSON_PROPERTY)
        private String timeSystem;

        @JsonProperty(MEAN_ELEMENT_THEORY_JSON_PROPERTY)
        private String  meanElementTheory;

        // OMM Data
        @JsonProperty(EPOCH_YMD_HMS_JSON_PROPERTY)
        @JsonDeserialize(using = OptionalDateTimeToUtcInstantDeserializer.class)
        private Optional<UtcInstant> epoch = Optional.empty();

        @JsonProperty(MEAN_MOTION_JSON_PROPERTY)
        private Optional<BigDecimal> meanMotion = Optional.empty();

        @JsonProperty(ECCENTRICITY_JSON_PROPERTY)
        private Optional<BigDecimal> eccentricity = Optional.empty();

        @JsonProperty(INCLINATION_JSON_PROPERTY)
        private Optional<BigDecimal> inclination = Optional.empty();

        @JsonProperty(RIGHT_ASC_OF_NODE_JSON_PROPERTY)
        private Optional<BigDecimal> raOfAscNode = Optional.empty();

        @JsonProperty(ARG_OF_PERICENTER_JSON_PROPERTY)
        private Optional<BigDecimal> argOfPericenter = Optional.empty();

        @JsonProperty(MEAN_ANOMALY_JSON_PROPERTY)
        private Optional<BigDecimal> meanAnomaly = Optional.empty();

        @JsonProperty(EPHEMERIS_TYPE_JSON_PROPERTY)
        private Optional<Integer> ephemerisType = Optional.empty();

        @JsonProperty(CLASSIFICATION_TYPE_JSON_PROPERTY)
        private Optional<String> classificationType = Optional.empty();

        @JsonProperty(CATALOG_NUMBER_JSON_PROPERTY)
        private Integer noradCatId;

        @JsonProperty(ELEMENT_SET_NUMBER_JSON_PROPERTY)
        private Optional<Integer> elementSetNo = Optional.empty();

        @JsonProperty(REV_AT_EPOCH_JSON_PROPERTY)
        private Optional<Integer> revAtEpoch = Optional.empty();

        @JsonProperty(BSTAR_JSON_PROPERTY)
        private Optional<BigDecimal> bStar = Optional.empty();

        @JsonProperty(MEAN_MOTION_DOT_JSON_PROPERTY)
        private Optional<BigDecimal> meanMotionDot = Optional.empty();

        @JsonProperty(MEAN_MOTION_DOUBLE_DOT_JSON_PROPERTY)
        private Optional<BigDecimal> meanMotionDDot = Optional.empty();

        @JsonProperty(SEMI_MAJOR_AXIS_JSON_PROPERTY)
        private Optional<Double> semiMajorAxis = Optional.empty();

        @JsonProperty(PERIOD_JSON_PROPERTY)
        private Optional<Double> period = Optional.empty();
        /**
         * Approximate height of the apogee assuming two-body motion and a spherical Earth with radius 6378.135 km
         */
        @JsonProperty(APOAPSIS_JSON_PROPERTY)
        private Optional<Double> apoapsis = Optional.empty();

        /**
         * Approximate height of the perigee assuming two-body motion and a spherical Earth with radius 6378.135 km
         */
        @JsonProperty(PERIAPSIS_JSON_PROPERTY)
        private Optional<Double> periapsis = Optional.empty();

        @JsonProperty(OBJECT_TYPE_JSON_PROPERTY)
        private Optional<String> objectType = Optional.empty();

        @JsonProperty(RCS_SIZE_JSON_PROPERTY)
        private Optional<String> rcsSize = Optional.empty();

        @JsonProperty(COUNTRY_CODE_JSON_PROPERTY)
        private Optional<String> countryCode = Optional.empty();

        @JsonProperty(LAUNCH_DATE_JSON_PROPERTY)
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
        private Optional<LocalDate> launchDate = Optional.empty();

        @JsonProperty(SITE_JSON_PROPERTY)
        private Optional<String> site = Optional.empty();

        @JsonProperty(DECAY_DATE_JSON_PROPERTY)
        @JsonDeserialize(using = OptionalTinyIntToBooleanDeserializer.class)
        private Optional<Boolean> decayDate = Optional.empty();

        @JsonProperty(FILE_JSON_PROPERTY)
        private Optional<Long> file = Optional.empty();

        @JsonProperty(GP_ID_JSON_PROPERTY)
        private Integer gpId;

        @JsonProperty(TLE_LINE_0_JSON_PROPERTY)
        private Optional<String> tleLine0 = Optional.empty();

        @JsonProperty(TLE_LINE_1_JSON_PROPERTY)
        private Optional<String> tleLine1 = Optional.empty();

        @JsonProperty(TLE_LINE_2_JSON_PROPERTY)
        private Optional<String> tleLine2 = Optional.empty();
    }
}
