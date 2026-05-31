package com.qaschool.hapifyme.api;

import io.restassured.RestAssured;
import org.junit.Test;

public class HapifyMeApiSmokeTest {
    private static final String BASE_URL = System.getProperty("hapifyme.baseUrl", "https://apps.qualiadept.eu/hapifyme");

    @Test
    public void loginRegisterPageIsAvailable() {
        RestAssured
                .given()
                    .baseUri(BASE_URL)
                .when()
                    .get("/login_register.php")
                .then()
                    .statusCode(200);
    }
}