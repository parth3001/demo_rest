import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

class FuntionalTest {

	@Test
	void getUserDetailsTest() {
		//The base URI to be used
		RestAssured.baseURI = "http://10.106.210.84:9090/book";

		//Define the specification of request. Server is specified by baseURI above.
		RequestSpecification httpRequest = RestAssured.given();

		//Makes calls to the server using Method type.
		Response response = httpRequest.request(Method.GET, "2");

		//Checks the Status Code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
}
