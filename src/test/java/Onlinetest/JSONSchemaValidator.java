package Onlinetest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.restassured.RestAssured;

public class JSONSchemaValidator {

	@Test(enabled=true)
	public void testGet() {
		
		RestAssured.baseURI = "https://reqres.in/api";
		
		given().
			get("/users?page=2").
			then().
				assertThat().body(matchesJsonSchemaInClasspath("schema.json")).
				statusCode(200).
				body("data.first_name", hasItems("George", "Rachel"));
		 
	}
}
