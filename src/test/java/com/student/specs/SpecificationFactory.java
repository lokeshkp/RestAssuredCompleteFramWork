package com.student.specs;

import com.student.tests.TestBase;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecificationFactory extends TestBase{

	public static synchronized ResponseSpecification getHeaderResponseSpec() {
		ResponseSpecBuilder responseSpecBuilder;
		ResponseSpecification responseSpecification;
		
		responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectHeader("Content-Type", "application/json;charset=UTF-8");
		responseSpecBuilder.expectHeader("Transfer-Encoding", "chunked");
		responseSpecBuilder.expectResponseTime(lessThan(5L),TimeUnit.SECONDS);
		
		responseSpecification = responseSpecBuilder.build();
		return responseSpecification;
	}
	
	
	public static synchronized RequestSpecification logPayloadResponseInfo() {
		RequestSpecBuilder requestSpecBuilder;
		RequestSpecification requestSpecification;
		
		requestSpecBuilder = new RequestSpecBuilder();
		
		if(prop.getProperty("log").equals("ENABLE")){
			requestSpecBuilder.addFilter(new AllureRestAssured());
		}
		requestSpecification = requestSpecBuilder.build();
		return requestSpecification;
	}
}
