package api.endpoints;
//2. create User endpoint which has CRUD method implementations
//2. import packages
import static io.restassured.RestAssured.given;


import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class UserModule_Endpoints {
	//2. create user
	//payload is body coming from user class
	public static Response createUser(User payload)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
		//routes class to use the url
			.post(Routes.user_Post_Url);
		
		return response;
		
	}
	
	//2. read user
	//need paramter whc is passed from test cases
	public static Response readUser(String userName)
	{
		Response response = given()	
			.pathParam("username", userName)
			
		.when()
			.get(Routes.user_Get_Url);
		
		return response;
		
	}
	
	//2. update user
	public static Response updateUser(String userName, User payload)
	{
		Response response = given()	
				.contentType("application/json").accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)	
		.when()
			.put(Routes.user_Put_Url);
		
		return response;
		
	}
	
	//2. delete user
	public static Response deleteUser(String userName)
	{
		Response response = given()	
			.pathParam("username", userName)
			
		.when()
			.delete(Routes.user_Delete_Url);
		
		return response;
		
	}
	

}
