package api;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;


public class TestGetAction {
	
	@Test
	public void testGetUser_ExpectedMaleInactive() {
	
	given().
    pathParam("userId", "101").
    log().all().
    when().get("https://gorest.co.in/public-api/users/{userId}").
    then().
    assertThat().
    statusCode(200).
    body("code", is(200)).
    body("data.gender", is("Male")).
    body("data.status", is("Inactive")).
    log().all();
}
	@Test
	public void testGetUserList_FromPage5() {
	
	given().
	queryParam("page", "5").
    log().all().
    when().get("https://gorest.co.in/public-api/users").
    then().
    assertThat().
    statusCode(200).
    body("code", is(200)).
    body("data", hasSize(20)).
    log().all();
}
}