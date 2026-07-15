package tests;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.*;

public class StatusTests {

@Test
    public void statusBaseTest() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("state.total",is(20));
    }
    @Test
    public void statusWithLogsTest() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("state.total",is(20));
    }
    @Test
    public void statusWithBDDTest() {
    given()
            .log().uri()
            .log().method()
            .log().headers()
            .when()
            .get("https://selenoid.autotests.cloud/status")
            .then()
            .log().ifError()
            .log().status()
            .body("state.total",is(20));
    }
    @Test
    public void statusWithStatusCodeTest() {
    given()
            .log().uri()
            .log().method()
            .when()
            .get("https://selenoid.autotests.cloud/status")
            .then()
            .log().status()
            .body("state.total",is(20))
            .statusCode(200);
    }
    @Test
    public void statusStatusCodeTest() {
    given()
            .log().uri()
            .log().method()
            .when()
            .get("https://selenoid.autotests.cloud/status")
            .then()
            .log().status()
            .body("state.total",is(20))
            .statusCode(200);
    }
    @Test
    public void statusBrowserVersionsTest() {
    given()
            .log().uri()
            .log().method()
            .when()
            .get("https://selenoid.autotests.cloud/status")
            .then()
            .log().status()
            .body("state.total",is(20))
            .statusCode(200)
            .body("state.browsers.chrome",hasKey("148.0"))
            .body("state.browsers.chrome",hasKey("149.0"));
    }
    @Test
    public void statusSchemaTest() {
    given()
            .log().uri()
            .log().method()
            .when()
            .get("https://selenoid.autotests.cloud/status")
            .then()
            .log().status()
            .body("state.total",is(20))
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/status_response_schema.JSON"));
    }
}
