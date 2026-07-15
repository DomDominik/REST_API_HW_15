package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;


public class TestsBase {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://selenoid.autotests.cloud";
    }
}
