package org.acme;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class ControllerTest {
  

    @Test
    void testGetMedicals() {
        given()
          .when().get("/api/medicals")
          .then()
             .statusCode(200);
    }

    @Test
    void testGetAppointments() {
        given()
          .when().get("/api/appointments")
          .then()
             .statusCode(200);
    }

    @Test
    void testGetUsers() {
        given()
            .when().get("/api/users")
            .then()
                .statusCode(200);
    }

}
