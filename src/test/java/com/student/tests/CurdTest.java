package com.student.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.github.javafaker.Faker;
import com.student.requests.RequestFactory;
import com.student.specs.SpecificationFactory;
import com.student.util.ReadExcel;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;

@Story("This is CRUD testing story")
public class CurdTest extends TestBase{
	
	static String filePath  ="C:\\Users\\lokes\\eclipse-workspace\\StudentApp\\src\\test\\resources\\test-data\\Data\\studentData.xls";	
	static String sheetName ="Sheet1";
	
	RequestFactory requests = new RequestFactory();
	
	@Story("This is CRUD testing story")
	@Feature("This is to test get all students from the database")
	@Test(groups = {"funTest","regTest"},description = "This is to test get all students from the database")
	public void getAllStudents() {
		requests.getAllStudents().
						then().spec(SpecificationFactory.getHeaderResponseSpec()).log().all();
	}
	
	
	@Description("This is to test create a New Student...")
	@Story("This is CRUD testing story")
	@Feature("This is to test Create & Verify a new student")
	@Test(groups = {"funTest","regTest"},description = "This is to test Create & Verify a new student",dataProvider = "getExcelData")
	public void createNewStudent(String firstName,String lastName,String email, String programme, String coursess) {
		
		/* Faker plugin will generate some random date to use testdata..
		Faker fakeData = new Faker();
		String firstName =fakeData.name().firstName(); 
		String lastName =fakeData.name().lastName(); 
		String email =fakeData.internet().emailAddress();
		String programme ="Computer";
		*/
		
		List<String>courses = new ArrayList<String>();
		courses.add("Selenium");
		courses.add("Cypress");
		
		requests.createStudent("", firstName, lastName, email, programme, courses).
						then().spec(SpecificationFactory.getHeaderResponseSpec()).statusCode(201);
	}
	
	
	
	@Story("This is simple Third test story")
	@Feature("This is simple Third test story")
	@Test(groups = {"regTest"})
	public void testThree() {
		System.out.println("Test 3..");
	}
	
	
	@DataProvider
	public Object[][] getExcelData() throws Exception{
		Object[][] testObjArray =ReadExcel.getExcelData(filePath, sheetName);
		return (testObjArray);
	}

}
