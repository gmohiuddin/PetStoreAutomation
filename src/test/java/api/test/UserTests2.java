package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
		
		//logs
		logger = LogManager.getLogger(this.getClass());
	}

	@Test(priority=1, enabled = true)
	public void testPostUser(){
		logger.info("**************** creating user************");
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************** user is created************");
	}
	
	@Test(priority=2, enabled = true)
	public void testGetUserByName(){
		logger.info("**************** reading user info************");
		Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************** displaying user info************");
	}

	@Test(priority=3, enabled = true)
	public void testUpdateUserByName(){
		logger.info("****************updating user info************");
		// update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		//Response
		Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		response.then().log().body().statusCode(200);
		logger.info("**************** user is updated************");
		// checking data after update
		Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}

	@Test(priority=4, enabled = true)
	public void deleteUserByName(){
		logger.info("****************deleting user************");
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
