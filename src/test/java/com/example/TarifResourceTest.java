package com.example;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TarifResourceTest {

   @Test
   public void testTariffEndpoint() {
      TestModel[] tariffs = given()
            .when().get("/test")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract().body().as(TestModel[].class);

      assertThat(tariffs).isNotEmpty();
      assertThat(tariffs.length).isOne();
      assertThat(tariffs[0].getId()).isEqualTo(1);
      assertThat(tariffs[0].getValue()).isEqualTo(12.1);

      tariffs = given()
            .when().get("/test2")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract().body().as(TestModel[].class);

      assertThat(tariffs).isNotEmpty();
      assertThat(tariffs.length).isOne();
      assertThat(tariffs[0].getId()).isEqualTo(2);
      assertThat(tariffs[0].getValue()).isEqualTo(45.2);
   }
}
