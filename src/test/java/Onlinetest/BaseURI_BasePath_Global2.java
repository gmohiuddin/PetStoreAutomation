package Onlinetest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//http://makeseleniumeasy.com/2019/12/05/rest-assured-tutorial-14-baseuri-and-basepath-introduction-and-configuration/
public class BaseURI_BasePath_Global2 {

	final String BASE_URI = "https://restful-booker.herokuapp.com";
	final String BASE_PATH = "/booking";
	
	
	@BeforeClass
	public void setup() {
		// Setting BaseURI once
		RestAssured.baseURI = BASE_URI;
		// Setting BasePath once
		RestAssured.basePath = BASE_PATH;
	}
	
	@Test
	public void request1() {
		RequestSpecification request = RestAssured.given();
 
		Response response = request.get();
 
		System.out.println(response.asString());
	}
}
