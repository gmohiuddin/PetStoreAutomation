package Onlinetest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
//https://www.youtube.com/watch?v=XlD46CBmeyk
public class Practice1 {

	@Test(enabled=false)
	public void getRequest() {
		given()
		 .param("page", "2")
		 .auth().none()
		 .auth().basic("gmohiuddin", "pass")
		 .auth().digest("gmohiuddin", "pass")
		 .auth().form("gmohiuddin", "pass")
		 .auth().oauth2("accessToken-8877%&&*FD234")
		 .when().get("https://reqres.in/api/users")
		 .then()
		 .log().all()
		 .statusCode(200).body("page", equalTo(2));
		 
	}
	
	@Test(enabled=false)
	public void getRequest2() {
		Response response = given()
		 .param("page", "2")
		 .auth().none()
		 .when().get("https://reqres.in/api/users");
		
		 System.out.println("Time in seconds: "+response.getTime());
		 response.then().log().all();
		 response.getTimeIn(TimeUnit.SECONDS);
	}
	
	@Test(enabled=true)
	public void getBodyDetails() {
		
	    baseURI = ("https://reqres.in/api/users");
		RequestSpecification htt = RestAssured.given();
		Response response = htt.request(Method.GET, "/2");
		
	//	ResponseBody body = response.getBody();
		ResponseBody body = response.body();
		
		String bodyobj = body.asString();
		System.out.println("My response(bodyobj) is: \n"+bodyobj);
		Assert.assertEquals(bodyobj.contains("id"), true, "My response contains id");
		Assert.assertEquals(bodyobj.contains("email"), true, "My response contains email");
		Assert.assertEquals(bodyobj.contains("first_name"), true, "My response contains first_name");
		Assert.assertEquals(bodyobj.contains("last_name"), true, "My response contains last_name");
		Assert.assertEquals(bodyobj.contains("support"), true, "My response contains support");
		
		System.out.println("----------------------------------------------------");
		
		JsonPath js = response.jsonPath();
		int value1_id = js.getInt("data.id");
		String value1_email = js.getString("data.email");
		String value1_first_name = js.getString("data.first_name");
		String value1_last_name = js.getString("data.last_name");
		
		Assert.assertEquals(value1_id,2);
		Assert.assertEquals(value1_email.equals("janet.weaver@reqres.in"), true, "My response contains email");
		Assert.assertEquals(value1_first_name.equals("Janet"), true, "My response contains Janet");
		Assert.assertEquals(value1_last_name.equals("Weaver"), true, "My response contains Weaver");		
	}
}


























