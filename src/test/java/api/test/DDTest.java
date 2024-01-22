package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTest {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class) // Here we Passing
																						// DataProviderclass because
																						// DataProvider is available in
																						// diff Package if it is same
																						// package then user dont need
																						// to pass the Dataproviderclass
	public void testPostuser(String userID, String userName, String fname, String lname, String useremail, String pwd,
			String phone) {

		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUserName(userName);
		userPayload.setUserName(fname);
		userPayload.setUserName(lname);
		userPayload.setUserName(useremail);
		userPayload.setUserName(pwd);
		userPayload.setUserName(phone);

		Response response = UserEndPoints.createUser(userPayload);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

	}

	//@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	@Test
	public void testDeleteUserByName(String userName) {
		Response response = UserEndPoints.deleteUser(userName);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
}
