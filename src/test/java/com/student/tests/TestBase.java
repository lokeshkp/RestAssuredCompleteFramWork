package com.student.tests;

import org.testng.annotations.BeforeClass;

import com.student.util.PropertyReader;

import io.restassured.RestAssured;

public class TestBase {

	public static PropertyReader prop;	
	
	@BeforeClass
	public static void initUrl() {
		
		prop = PropertyReader.getInstance();
		RestAssured.baseURI = prop.getProperty("baseUrl");
		RestAssured.port = Integer.valueOf(prop.getProperty("port"));
	}
	
	
}
