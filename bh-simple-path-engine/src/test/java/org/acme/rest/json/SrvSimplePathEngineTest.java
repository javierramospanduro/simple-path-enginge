package org.acme.rest.json;

import static io.restassured.RestAssured.given;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class SrvSimplePathEngineTest {
	@Inject
	@DataSource("enginedb")
	AgroalDataSource usersDataSource;
	
	@Test
    public void testParamsAxisTypesGET() {
		// Test with no data
        given()
          .when().get("/simple-path-engine/params/axis/types")
          .then()
             .statusCode(204)
             ;
    }
	@Test
    public void testOperatorsGET() {
		// Test with no data
        given()
          .when().get("/simple-path-engine/params/operators")
          .then()
             .statusCode(200)
             ;
    }

}