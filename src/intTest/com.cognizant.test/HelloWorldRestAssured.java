package com.cognizant.test;

import static com.cognizant.restassured.RestAssured.given;

import org.junit.Test;

public class FunctionalTest {

	@BeforeClass
	public static void setup() {
		String port = System.getProperty("server.port");
		if (port == null) {
			RestAssured.port = Integer.valueOf(9090);
		}
		else{
			RestAssured.port = Integer.valueOf(port);
		}


		String basePath = System.getProperty("server.base");
		if(basePath==null){
			basePath = "/books/";
		}
		RestAssured.basePath = basePath;

		String baseHost = System.getProperty("server.host");
		if(baseHost==null){
			baseHost = "http://10.106.210.84";
		}
		RestAssured.baseURI = baseHost;

	}

}
