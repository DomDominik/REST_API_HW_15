package tests;

import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;

public class SelenoidHubStatusTests extends TestsBase{
    @Test
    public void statusTest() {
        given()
                .log().all()
                .auth().basic("user1", "1234")
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/wbhub_status_responce_schema.json"));

    }

    @Test
    public void textTest() {
        given()
                .log().all()
                .auth().basic("user1", "1234")
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .body("value.message", is("Selenoid v2.3.0 built at 2026-07-13_04:07:28AM"));
    }

    @Test
    public void keyReadyTest() {
        given()
                .log().all()
                .auth().basic("user1", "1234")
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .body("value.ready", is(true));
    }

    @Test
    public void unauthorizedTest() {
        given()
                .log().all()
                .auth().basic("user1", "12345")
                .when()
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(401);
    }
}
