package com.qaschool.hapifyme.api;

import com.qaschool.hapifyme.support.EnvironmentChecks;
import io.restassured.RestAssured;
import org.junit.Assume;
import org.junit.Test;

public class HapifyMeApiSmokeTest {
    private static final String BASE_URL = System.getProperty("hapifyme.baseUrl", "https://test.hapifyme.com");

    @Test
    public void loginRegisterPageIsAvailable() {
        Assume.assumeTrue(
                "Skipping API smoke test because the HapifyMe host cannot be resolved: " + BASE_URL,
                EnvironmentChecks.baseUrlHostCanResolve(BASE_URL)
        );

        RestAssured
                .given()
                    .baseUri(BASE_URL)
                .when()
                    .get("/login_register.php")
                .then()
                    .statusCode(200);
    }
}