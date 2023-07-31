package com.apiautomation.LearningAPI.tests;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import com.apiautomation.utils.Utils;
import io.restassured.RestAssured;

public class CreateIssueMetadataTest {

	@Test
	public void testApiCall() {
		Utils.print();
		RestAssured.baseURI = "https://asharanisv.atlassian.net";
		given().header("Authorization","Basic YXNoYXJhbmlzdjk0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBxZ3NlNFlKYTNhMUwtY3I3Mno3OE0xU0lsQXVaLWVNeF9uU1RIVDFsMEdqTDJrU0IwbFZpTGJhWGhRd1NBRWotWG5hYmZjSjY3ZGJpN1RINUVCUUk0U1VFVkYwMFVEai00NTBBZXIxQUtmLVRNUWkxa29jZGtEdDBwLWItSWg3MkpBQm1aWTAxVHNMbmQxWmRHbmMzbXlqU3RCY0dxNDQ5VDNWUXRIVjFPRFU9NkNGMkIzQzA=").
		when().log().all().get("/rest/api/3/issue/createmeta").then().log().all().statusCode(200);
	}
}
