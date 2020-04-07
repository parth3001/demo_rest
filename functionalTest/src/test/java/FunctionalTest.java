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
		String port = System.getProperty("server.port");
		if (port == null) {
			RestAssured.port = Integer.valueOf(9090);
		}
		else{
			RestAssured.port = Integer.valueOf(port);
		}

		String basePath = System.getProperty("server.base");
		if(basePath==null){
			basePath = "/book/";
		}
		RestAssured.basePath = basePath;

		String baseHost = System.getProperty("server.host");
		if(baseHost==null){
			baseHost = "http://10.106.210.84";
		}
		RestAssured.baseURI = baseHost;

		//RestAssured.baseURI = "http://10.106.210.84:9090/book";

		//Define the specification of request. Server is specified by baseURI above.
		RequestSpecification httpRequest = RestAssured.given();

		//Makes calls to the server using Method type.
		Response response = httpRequest.request(Method.GET, "2");

		//Checks the Status Code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}


	@Test
	void validateDataOnResponse() {

		//The base URI to be used
		String port = System.getProperty("server.port");
		if (port == null) {
			RestAssured.port = Integer.valueOf(9090);
		}
		else{
			RestAssured.port = Integer.valueOf(port);
		}

		String basePath = System.getProperty("server.base");
		if(basePath==null){
			basePath = "/book/";
		}
		RestAssured.basePath = basePath;

		String baseHost = System.getProperty("server.host");
		if(baseHost==null){
			baseHost = "http://10.106.210.84";
		}
		RestAssured.baseURI = baseHost;


		//Define the specification of request. Server is specified by baseURI above.
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject updateData = new JSONObject();
		updateData.put("name", "Book for Everything");

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(updateData.toJSONString());
		Response response = httpRequest.request(Method.PUT, "4");
		statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

		JsonPath newData = response.jsonPath();
		String name = newData.get("name");
		Assert.assertEquals(name, "Everything");
	}
}
