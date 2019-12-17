package com.student.requests;

import com.student.specs.SpecificationFactory;
import com.student.tests.TestBase;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestClient extends TestBase{
	
	/**
	 *   Get Request
	 */
	public Response doGetRequest(String requestPath) {
		return given().spec(SpecificationFactory.logPayloadResponseInfo()).
				when().get(requestPath);
	}
	
	/**
	 *   Post Request
	 */
	public Response doPostRequest(String url, Object body) {
		return given().contentType(ContentType.JSON).spec(SpecificationFactory.logPayloadResponseInfo()).
				when().body(body).post(url);
	}
	
	
	/**
	 *   Put Request
	 */
	public Response doPutRequest(String res, Object body) {
		Response response = given().
							when().body(body).put(res);
		return response;
	}
	
	
	/**
	 *   Patch Request
	 */
	public Response doPatchRequest(String res, Object body) {
		Response response = given().
							when().body(body).patch(res);
		return response;
	}
	
	/**
	 *   Delete Request
	 */
	public Response doDeleteRequest(String res) {
		Response response = given().
							when().delete(res);
		return response;
	}
}
