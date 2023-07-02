package Onlinetest;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
public class PostAPIUsingPojo {

	@Test(enabled=false)
	public void testComplexJson1() {

		EmployeePojo ep = new EmployeePojo("morhheus", "leader", 
				new String[] {"Java", "C"}, "Xyz", "morhheus@Xyz.com");
		
						given()
				        .auth().none()
				        .header("Content-Type", "application/json")
				        .contentType(ContentType.JSON)
				        .when()
				        .body(ep).log().all()
				        .post("https://reqres.in/api/users")									
				        .then().log().all()
				        .body("name", equalTo("morhheus"),
				        		"job", equalTo("leader"),
				        		"skills[0]", equalTo("Java"),
				        		"skills[1]", equalTo("C"),
				        		"details.companyName", equalTo("Xyz"),
				        		"details.emailId", equalTo("morhheus@Xyz.com"));

	}
	@Test(enabled=false)
	public void testComplexJson2() {

		EmployeePojo ep = new EmployeePojo("morhheus", "leader", 
				new String[] {"Java", "C"}, "Xyz", "morhheus@Xyz.com");
		
						Response response=given()
				        .auth().none()
				        .header("Content-Type", "application/json")
				        .contentType(ContentType.JSON)
				        .when()
				        .body(ep).log().all()
				        .post("https://reqres.in/api/users");						
						Assert.assertEquals(response.getBody().path("name"), "morhheus");
						Assert.assertEquals(response.getBody().path("job"), "leader");
						Assert.assertEquals(response.getBody().path("skills[0]"), "Java");
						Assert.assertEquals(response.getBody().path("skills[1]"), "C");
						Assert.assertEquals(response.getBody().path("details.companyName"), "Xyz");
						Assert.assertEquals(response.getBody().path("details.emailId"), "morhheus@Xyz.com");
						// OR use jsonPath
						Assert.assertEquals(response.jsonPath().get("name"), "morhheus");
						Assert.assertEquals(response.jsonPath().get("job"), "leader");

	}
	
	@Test(enabled=false)
	public void testComplexJson3() {

		EmployeePojo ep = new EmployeePojo("morhheus", "leader", 
				new String[] {"Java", "C"}, "Xyz", "morhheus@Xyz.com");
		
						Response response=given()
				        .auth().none()
				        .header("Content-Type", "application/json")
				        .contentType(ContentType.JSON)
				        .when()
				        .body(ep).log().all()
				        .post("https://reqres.in/api/users");						

					//	response.getHeaders();
					//	System.out.println("All the headers are:  \n"+response.headers());
						System.out.println("ContentType:  \n"+response.header("Content-Type"));
						System.out.println("Date:  \n"+response.header("date"));
						System.out.println("Cookies:  \n"+response.getCookie("Cookies"));
						System.out.println("ContentType::  \n"+response.getContentType());
	}
}

/*
 {
   "name": "morhheus",
   "job": "leader",
   "skills": ["Java", "c"],
   "details":{
     "companyName": "Xyz",
     "emailId": "morhheus@Xyz.com"
  }
}
 */
