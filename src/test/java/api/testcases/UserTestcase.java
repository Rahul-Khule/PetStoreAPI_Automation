package api.testcases;
//4 create test cases

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserModule_Endpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestcase {
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	//create setup method 
	//this 	method generates data using faker library and pass it to pojo 
	@BeforeClass
	public void setup()
	{
		faker = new Faker();
		userPayload = new User();
		//set id
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());
	}
	
	//3 test method
	
	@Test(priority = 1)
	public void test_Post_User() 
	{
		logger.info("--------------------Creating user--------------------");
		Response response =UserModule_Endpoints.createUser(userPayload);
		response.then().log().all();
		System.out.println("Post User");
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("-------------------- user Created--------------------");
	}
	@Test(priority = 2)
	public void test_User_By_Name()
	{
		logger.info("--------------------Reading user info--------------------");
		//this.userplayload.getusername to get the created post user
		Response response=UserModule_Endpoints.readUser(this.userPayload.getUsername());
		Response resbody = response.then().extract().response();
		System.out.println(resbody.getBody());
		System.out.println("Get User");
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("--------------------user info is displayed--------------------");
	}
	
	
	@Test(priority = 3)
	//check if it updated
	public void test_Post_User_after_Update() 
	{
		logger.info("--------------------Updating user--------------------");
		Response response =UserModule_Endpoints.createUser(userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("Before user update ");
		logger.info("-------------------- user updated--------------------");
	}
	
	@Test(priority = 4)
	public void test_Update_By_username()
	{
		//update data using payload
		logger.info("--------------------Update_By_username--------------------");
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		Response beforeResponse =UserModule_Endpoints.updateUser(this.userPayload.getUsername(),userPayload);
		beforeResponse.then().log().all();
		System.out.println("Updated User");
		Assert.assertEquals(beforeResponse.getStatusCode(), 200);
		logger.info("--------------------updated--------------------");
		
		
		
	}
	
	
	@Test(priority = 5)
	public void test_Delete_User_By_Username() 
	{
		logger.info("--------------------Delete_User--------------------");
		Response response=UserModule_Endpoints.deleteUser(this.userPayload.getUsername());
		System.out.println("deleted User");
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("--------------------UserDelete_--------------------");
	}
	

}
