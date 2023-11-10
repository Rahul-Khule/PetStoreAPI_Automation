package api.endpoints;
//1. maintain all the urls here
/* 
 	Create user POST = https://petstore.swagger.io/v2/user 
    get user GET = https://petstore.swagger.io/v2/user/{username}
    Update user PUT =  https://petstore.swagger.io/v2/user/{username}
    Delete user DELETE = https://petstore.swagger.io/v2/user/{username}
*/
	 
public class Routes {
	//1.
	public static String base_Url = "https://petstore.swagger.io/v2";
	
	//this is for user module
	
	//1. create varibale for the USER module http calls
	public static String user_Post_Url = base_Url+"/user";
	public static String user_Get_Url = base_Url+"/user/{username}"; //{username} is a path parameter
	public static String user_Put_Url = base_Url+"/user/{username}";
	public static String user_Delete_Url = base_Url+"/user/{username}";
}
