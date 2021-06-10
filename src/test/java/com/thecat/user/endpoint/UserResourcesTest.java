package com.thecat.user.endpoint;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import com.thecat.user.model.User;

@QuarkusTest
public class UserResourcesTest {

    @Test
    public void testHealthEndpoint() {
        given()
          .when().get("/user/health")
          .then()
             .statusCode(200)
             .body(is("200"));
    }
}