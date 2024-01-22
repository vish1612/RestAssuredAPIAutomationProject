package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	// Created Different TCs for Fetching Routes from Properties file

	Faker faker;
	User userPayload;
	public Logger logger;

	@BeforeClass
	public void setupData() {

		faker = new Faker();
		userPayload = new User();

		// This faker will generate random test data for testing
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUserName(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		// userPayload.setUserName(faker.name().username());

		// logs
		logger = LogManager.getLogger(this.getClass());

	}

	@Test(priority = 1)
	public void testPostUser() {
		logger.info("*********Creating User***************");
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();

		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("*********User is Created***************");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		logger.info("*********Reading User Info***************");
		Response response = UserEndPoints2.readUser(this.userPayload.getUserName());
		response.then().log().all();

		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("*********Info of User Read Successfully***************");
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {
		logger.info("*********Updating the User Info***************");
		// Update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints2.updateUser(this.userPayload.getUserName(), userPayload);
		response.then().log().all();

		AssertJUnit.assertEquals(response.getStatusCode(), 200);

		// Checking data after update
		logger.info("*********User Info is Updated***************");
		Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUserName());
		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(), 200);

	}

	@Test(priority = 4)
	public void testDeleteUserByName() {
		logger.info("*********Deleting User***************");
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUserName());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("*********User Deleted***************");
	}

}
