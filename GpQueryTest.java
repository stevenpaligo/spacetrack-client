package com.stevenpaligo.spacetrack.client;

import com.stevenpaligo.spacetrack.client.credential.CredentialProvider;
import com.stevenpaligo.spacetrack.client.predicate.InclusiveRange;
import com.stevenpaligo.spacetrack.client.predicate.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GpQueryTest {
    private static CredentialProvider credentials = TestUtils.getCredentials();


    @Test
    @DisplayName("GpQuery: Result type matches the SpaceTrack schema")
    public void test1() {

        assertDoesNotThrow(() -> {

            ResultTypeValidator.validate(GpQuery.Gp.class, new URL("https://www.space-track.org/basicspacedata/modeldef/class/gp/format/json"));
        });
    }

    @Test
    @DisplayName("GpQuery: Query field enum matches the result type")
    public void test2() {

        assertDoesNotThrow(() -> {

            QueryFieldEnumValidator.validate(GpQuery.GpQueryField.class, GpQuery.Gp.class);
        });
    }

    @Test
    @DisplayName("GpQuery: Successful multi call")
    public void test5() {

        assertDoesNotThrow(() -> {

            Predicate<GpQuery.GpQueryField> predicate1 =
                    new InclusiveRange<GpQuery.GpQueryField>(GpQuery.GpQueryField.NORAD_CAT_ID, 44713,44715);

            List<GpQuery.Gp> gpQuery = new GpQuery().setCredentials(credentials).addPredicates(Arrays.asList(predicate1)).execute();
            System.out.println(gpQuery.size());
        });
    }
}