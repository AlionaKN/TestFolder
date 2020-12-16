package api;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import io.restassured.http.ContentType;

public class TestCrudAction {

		private final String TOKEN = "ea9f80cf9c17ab1990ddb6259bc85d85c7f5826f8c71d76d5e76441a5ef5916b";
		private final String pageUrl ="https://gorest.co.in/";
		
		@Test
		public void testCreateAndDeleteUser() {
			
			String userName = "TestNameAN";
			String userEmail = "test_" + new Random().nextInt(1000) + "@gmail.com";
			String endpointPostUser = "public-api/users";
			
			Map user = new HashMap<>();
			user.put("name", userName);
			user.put("email", userEmail);
			user.put("gender", "Female");
			user.put("status", "Active");
			
			// POST - create user
		int userId = given().
			auth().
			oauth2(TOKEN).
			contentType(ContentType.JSON).
			body(user).
			log().all().
		when().
			post(pageUrl + endpointPostUser).
		then().
			assertThat().
			statusCode(200).
			body("code", is(201)).
			log().all().
		    extract().
			path("data.id");
		
		// GET - check new user
		
//		String endpointUser = "public-api/users/{userId}";
//		
//		given().
//			pathParam("userId",userId).
//			log().all().
//		when().
//			get(pageUrl + endpointUser).
//		then().
//		assertThat().
//		body("data.name", is(userName)).
//		log().all();
			
String endpointUser = "public-api/users/{userId}";
		
		given().
		    auth().
		    oauth2(TOKEN).
			pathParam("userId",userId).
			log().all().
		when().
			delete(pageUrl + endpointUser).
		then().
			assertThat().
			body("code", is(204)).
			log().all();
		
		given().
			pathParam("userId",userId).
			log().all().
	    when().
			get(pageUrl + endpointUser).
		then().
			assertThat().
			body("code", is(404)).
			log().all();
		
		}
}
