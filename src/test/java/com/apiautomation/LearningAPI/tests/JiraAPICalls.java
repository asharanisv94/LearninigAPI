package com.apiautomation.LearningAPI.tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.apiautomation.utils.Utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JiraAPICalls {
	String key=null;
	String projectId,storyIssueTypeId;
	
	@BeforeMethod
	public void setup() {
		baseURI = "https://asharanisv.atlassian.net";
		
	}
	
	@Test (priority=1)
	public void CreateMetadataApiCall() {
		Utils.print();
		Response response =given().header("Authorization","Basic YXNoYXJhbmlzdjk0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBxZ3NlNFlKYTNhMUwtY3I3Mno3OE0xU0lsQXVaLWVNeF9uU1RIVDFsMEdqTDJrU0IwbFZpTGJhWGhRd1NBRWotWG5hYmZjSjY3ZGJpN1RINUVCUUk0U1VFVkYwMFVEai00NTBBZXIxQUtmLVRNUWkxa29jZGtEdDBwLWItSWg3MkpBQm1aWTAxVHNMbmQxWmRHbmMzbXlqU3RCY0dxNDQ5VDNWUXRIVjFPRFU9NkNGMkIzQzA=").
		when().log().all().get("/rest/api/3/issue/createmeta").
		then().log().all().assertThat().statusCode(200).extract().response();
	
		JsonPath jsonPath = response.jsonPath();
		projectId = jsonPath.get("projects[0].id");
		storyIssueTypeId = jsonPath.get("projects[0].issuetypes[1].id");
		
	System.out.println("projectId: "+projectId);
	System.out.println("storyIssueTypeId: "+storyIssueTypeId);
	}
	
	@Test (priority=2)
	public void createIssueApiCall() {
		Utils.print();
//		String projectId="10000";
//		String storyIssueTypeId="10004";
		
		String payload= "{\"fields\":{\"summary\":\"Mainorderflowbroken2-Epic\",\"project\":{\"id\":\""+projectId+"\"},\"description\":\"Sample Issue discription\",\"issuetype\":{\"id\":\""+storyIssueTypeId+"\"}}}";
//		RestAssured.baseURI = "https://asharanisv.atlassian.net";	
		
		//extract the response and store in response variable
		Response response=given().log().all().contentType("application/json").header("Authorization","Basic YXNoYXJhbmlzdjk0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBxZ3NlNFlKYTNhMUwtY3I3Mno3OE0xU0lsQXVaLWVNeF9uU1RIVDFsMEdqTDJrU0IwbFZpTGJhWGhRd1NBRWotWG5hYmZjSjY3ZGJpN1RINUVCUUk0U1VFVkYwMFVEai00NTBBZXIxQUtmLVRNUWkxa29jZGtEdDBwLWItSWg3MkpBQm1aWTAxVHNMbmQxWmRHbmMzbXlqU3RCY0dxNDQ5VDNWUXRIVjFPRFU9NkNGMkIzQzA=").body(payload).
		when().log().all().post("/rest/api/2/issue/").
		then().log().all().assertThat().statusCode(201).assertThat().body("key", notNullValue()).extract().response();

		// convert the respose to a json object, extract the key and store in a string variable 
		JsonPath jsonPath = response.jsonPath();
		key = jsonPath.get("key");
		System.out.println("Issue Key:"+key);
		  
	}

	@Test (priority=3)
	public void editIssueApiCall() {
		Utils.print();
//		String projectId="10000";
//		String storyIssueTypeId="10004";
//		String issueKey="AUT-49";
		
		String issueKey=key;
		String randomString = Utils.generateRandomString(5)+" "+Utils.generateRandomString(15)+" "+Utils.generateRandomString(10); 
        System.out.println(randomString);
        
		String payload= "{\"fields\":{\"summary\":\"Mainorderflowbroken2-Epic\",\"project\":{\"id\":\""+projectId+"\"},\"description\":\" "+randomString+ "\",\"issuetype\":{\"id\":\""+storyIssueTypeId+"\"}}}";
//		RestAssured.baseURI = "https://asharanisv.atlassian.net";	
		Response response=given().log().all().contentType("application/json").header("Authorization","Basic YXNoYXJhbmlzdjk0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBxZ3NlNFlKYTNhMUwtY3I3Mno3OE0xU0lsQXVaLWVNeF9uU1RIVDFsMEdqTDJrU0IwbFZpTGJhWGhRd1NBRWotWG5hYmZjSjY3ZGJpN1RINUVCUUk0U1VFVkYwMFVEai00NTBBZXIxQUtmLVRNUWkxa29jZGtEdDBwLWItSWg3MkpBQm1aWTAxVHNMbmQxWmRHbmMzbXlqU3RCY0dxNDQ5VDNWUXRIVjFPRFU9NkNGMkIzQzA=").body(payload).
		when().log().all().put("/rest/api/2/issue/"+issueKey).
		then().log().all().assertThat().statusCode(204).extract().response();		
		System.out.println(response.asPrettyString());
	
		  
	}
	
	@Test (priority=4)
	public void deleteIssueApiCall() {
		Utils.print();
//		String issueKey="AUT-33";
		String issueKey=key;
		String randomString = Utils.generateRandomString(5)+" "+Utils.generateRandomString(15)+" "+Utils.generateRandomString(10); 
        System.out.println(randomString);
        
//		RestAssured.baseURI = "https://asharanisv.atlassian.net";	
		Response response=given().log().all().contentType("application/json").header("Authorization","Basic YXNoYXJhbmlzdjk0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBxZ3NlNFlKYTNhMUwtY3I3Mno3OE0xU0lsQXVaLWVNeF9uU1RIVDFsMEdqTDJrU0IwbFZpTGJhWGhRd1NBRWotWG5hYmZjSjY3ZGJpN1RINUVCUUk0U1VFVkYwMFVEai00NTBBZXIxQUtmLVRNUWkxa29jZGtEdDBwLWItSWg3MkpBQm1aWTAxVHNMbmQxWmRHbmMzbXlqU3RCY0dxNDQ5VDNWUXRIVjFPRFU9NkNGMkIzQzA=").
		when().log().all().delete("/rest/api/2/issue/"+issueKey).
		then().log().all().assertThat().statusCode(204).extract().response();	
		System.out.println(response.asPrettyString());
		
		  
	}	
	
	
}
