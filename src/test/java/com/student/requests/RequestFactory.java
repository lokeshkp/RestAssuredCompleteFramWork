package com.student.requests;

import java.util.List;

import com.student.pojo.StudentClass;
import com.student.tests.TestBase;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RequestFactory extends TestBase{

	RestClient restClient = new RestClient();
	
	@Step("Getting all the student information from the database")
	public Response getAllStudents() {
		Response response = restClient.doGetRequest("/list");
		return response;
	}
	
	
	@Step("Creating a New Student {0}, {1}, {2}, {3}, {4}")
	public Response createStudent(String url, String firstName, String lastName, String email, String programme,List<String>courses) {
		StudentClass studentBody = new StudentClass();
		studentBody.setFirstName(firstName);
		studentBody.setLastName(lastName);
		studentBody.setEmail(email);
		studentBody.setProgramme(programme);
		studentBody.setCourses(courses);
		
		return restClient.doPostRequest(url, studentBody);
	}
	
	@Step("Updating Student info with Id: {0}")
	public Response updateStudent(int studentId, String firstName, String lastName, String email, String programme,List<String>courses) {
		StudentClass studentBody = new StudentClass();
		studentBody.setFirstName(firstName);
		studentBody.setLastName(lastName);
		studentBody.setEmail(email);
		studentBody.setProgramme(programme);
		studentBody.setCourses(courses);
		
		return restClient.doPutRequest("/"+studentId,studentBody);
	}
	
	@Step("Delete Student info with Id: {0}")
	public Response deleteStudent(int studentId) {
		return restClient.doDeleteRequest("/"+studentId);
	}
	
	public Response getStudentById(int studentId) {
		return restClient.doGetRequest("/"+studentId);
	}
}
