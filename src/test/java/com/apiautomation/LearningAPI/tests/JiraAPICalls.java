package com.apiautomation.LearningAPI.tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.apiautomation.utils.Utils;
import com.google.gson.JsonObject;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JiraAPICalls {

	@BeforeMethod
	public void setup() {
		baseURI = "https://asharanisv.atlassian.net";

	}

	@Test(priority = 1)
	public void CreateMetadataApiCall(ITestContext context) {
		Utils.print();

		Response response = given().header("Authorization",
				"Basic YXNoYXJhbmlzdjk0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBxZ3NlNFlKYTNhMUwtY3I3Mno3OE0xU0lsQXVaLWVNeF9uU1RIVDFsMEdqTDJrU0IwbFZpTGJhWGhRd1NBRWotWG5hYmZjSjY3ZGJpN1RINUVCUUk0U1VFVkYwMFVEai00NTBBZXIxQUtmLVRNUWkxa29jZGtEdDBwLWItSWg3MkpBQm1aWTAxVHNMbmQxWmRHbmMzbXlqU3RCY0dxNDQ5VDNWUXRIVjFPRFU9NkNGMkIzQzA=")
				.when().log().all().get("/rest/api/3/issue/createmeta").then().log().all().assertThat().statusCode(200)
				.extract().response();

		JsonPath jsonPath = response.jsonPath();

		System.out.println("projectId: " + jsonPath.get("projects[0].id"));
		context.setAttribute("projectId", jsonPath.get("projects[0].id"));//// The test context allows you to pass data
																			//// between different test methods within
																			//// the same test class or test suite.
		System.out.println("storyIssueTypeId: " + jsonPath.get("projects[0].issuetypes[1].id"));
		context.setAttribute("storyIssueTypeId", jsonPath.get("projects[0].issuetypes[1].id"));
	}

	@Test(priority = 2)
	public void createIssueApiCall(ITestContext context) {
		Utils.print();

		String payload = createBody(context);

		// extract the response and store in response variable
		Response response = given().log().all().contentType("application/json").header("Authorization",
				"Basic YXNoYXJhbmlzdjk0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBxZ3NlNFlKYTNhMUwtY3I3Mno3OE0xU0lsQXVaLWVNeF9uU1RIVDFsMEdqTDJrU0IwbFZpTGJhWGhRd1NBRWotWG5hYmZjSjY3ZGJpN1RINUVCUUk0U1VFVkYwMFVEai00NTBBZXIxQUtmLVRNUWkxa29jZGtEdDBwLWItSWg3MkpBQm1aWTAxVHNMbmQxWmRHbmMzbXlqU3RCY0dxNDQ5VDNWUXRIVjFPRFU9NkNGMkIzQzA=")
				.body(payload).when().log().all().post("/rest/api/2/issue/").then().log().all().assertThat()
				.statusCode(201).assertThat().body("key", notNullValue()).extract().response();

		// convert the respose to a json object, extract the key and store in a string
		// variable
		JsonPath jsonPath = response.jsonPath();
		context.setAttribute("key", jsonPath.get("key"));

	}

	 @Test(priority = 3)
	public void editIssueApiCall(ITestContext context) {
		Utils.print();

		String randomString = Utils.generateRandomString(5) + " " + Utils.generateRandomString(15) + " "
				+ Utils.generateRandomString(10);
		System.out.println(randomString);

		String payload = updateBody(context, randomString);

		Response response = given().log().all().contentType("application/json").header("Authorization",
				"Basic YXNoYXJhbmlzdjk0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBxZ3NlNFlKYTNhMUwtY3I3Mno3OE0xU0lsQXVaLWVNeF9uU1RIVDFsMEdqTDJrU0IwbFZpTGJhWGhRd1NBRWotWG5hYmZjSjY3ZGJpN1RINUVCUUk0U1VFVkYwMFVEai00NTBBZXIxQUtmLVRNUWkxa29jZGtEdDBwLWItSWg3MkpBQm1aWTAxVHNMbmQxWmRHbmMzbXlqU3RCY0dxNDQ5VDNWUXRIVjFPRFU9NkNGMkIzQzA=")
				.body(payload).when().log().all().put("/rest/api/2/issue/"+ context.getAttribute("key").toString()).then().log()
				.all().assertThat().statusCode(204).extract().response();
		System.out.println(response.asPrettyString());

	}

	 @Test(priority = 4)
	public void deleteIssueApiCall(ITestContext context) {
		Utils.print();

		String randomString = Utils.generateRandomString(5) + " " + Utils.generateRandomString(15) + " "
				+ Utils.generateRandomString(10);
		System.out.println(randomString);

		Response response = given().log().all().contentType("application/json").header("Authorization",
				"Basic YXNoYXJhbmlzdjk0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBxZ3NlNFlKYTNhMUwtY3I3Mno3OE0xU0lsQXVaLWVNeF9uU1RIVDFsMEdqTDJrU0IwbFZpTGJhWGhRd1NBRWotWG5hYmZjSjY3ZGJpN1RINUVCUUk0U1VFVkYwMFVEai00NTBBZXIxQUtmLVRNUWkxa29jZGtEdDBwLWItSWg3MkpBQm1aWTAxVHNMbmQxWmRHbmMzbXlqU3RCY0dxNDQ5VDNWUXRIVjFPRFU9NkNGMkIzQzA=")
				.when().log().all().delete("/rest/api/2/issue/"+ context.getAttribute("key").toString()).then().log().all()
				.assertThat().statusCode(204).extract().response();
		System.out.println(response.asPrettyString());

	}

	public String createBody(ITestContext context) {
		// Create requestBody

		JsonObject createIssueRequest = new JsonObject();

		JsonObject fields = new JsonObject();

		fields.addProperty("summary", "Test Summary as shown in class");

		JsonObject project = new JsonObject();
		System.out.println(">>>>>>>>>>>>>>>>> " + context.getAttribute("projectId").toString());
		project.addProperty("id", context.getAttribute("projectId").toString());

		fields.add("project", project);
		fields.addProperty("description", "Test description as shown in class");

//		JsonObject description = new JsonObject();
//			description.addProperty("type", "doc");
//				description.addProperty("version", 1);
//
//				JsonArray content = new JsonArray();
//
//				JsonObject contentObject = new JsonObject();
//
//				contentObject.addProperty("type", "paragraph");
//
//				JsonObject contentInternalObject = new JsonObject();
//				contentInternalObject.addProperty("text", "Test description as shown in class");
//
//				contentInternalObject.addProperty("type", "text");
//
//				JsonArray contentInternalArray = new JsonArray();
//				contentInternalArray.add(contentInternalObject);
//
//				contentObject.add("content", contentInternalArray);
//
//				content.add(contentObject);
//
//				description.add("content", content);
//
//				fields.add("description", description);

		JsonObject issuetype = new JsonObject();
		issuetype.addProperty("id", context.getAttribute("storyIssueTypeId").toString());

		fields.add("issuetype", issuetype);

		createIssueRequest.add("fields", fields);

		System.out.println(createIssueRequest.toString());
		return createIssueRequest.toString();
	}

	public String updateBody(ITestContext context, String randomString) { // Accept the randomString as a parameter

		JsonObject createIssueRequest = new JsonObject();

		JsonObject fields = new JsonObject();

		fields.addProperty("summary", "Test Summary as shown in class");

		JsonObject project = new JsonObject();

		project.addProperty("id", context.getAttribute("projectId").toString());

		fields.add("project", project);

		// Update the description with the randomString parameter
		fields.addProperty("description", "Test description as shown in class - " + randomString);

		JsonObject issuetype = new JsonObject();
		issuetype.addProperty("id", context.getAttribute("storyIssueTypeId").toString());

		fields.add("issuetype", issuetype);

		createIssueRequest.add("fields", fields);

		System.out.println(createIssueRequest.toString());
		return createIssueRequest.toString();
	}

}
