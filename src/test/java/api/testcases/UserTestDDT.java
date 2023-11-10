package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserModule_Endpoints;
import api.payload.User;
import api.utilities.Data_Providers;
import io.restassured.response.Response;

public class UserTestDDT {
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = Data_Providers.class)
	public void test_post_user(String userID , String username, String fname,String lname,String useremail,String pwd, String phnum ) {
		User userpayload = new User();
		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(username);
		userpayload.setFirstname(fname);
		userpayload.setLastname(lname);
		userpayload.setEmail(useremail);
		userpayload.setPassword(pwd);
		userpayload.setPhone(phnum);
		
		Response response =UserModule_Endpoints.createUser(userpayload);
		System.out.println("Post User");
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	//delete the post user from the above
	@Test(priority = 1,dataProvider = "UserNames", dataProviderClass = Data_Providers.class)
	public void test_Delete_User(String userName) {
		Response response= UserModule_Endpoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
