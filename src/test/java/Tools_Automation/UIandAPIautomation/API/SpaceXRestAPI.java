package Tools_Automation.UIandAPIautomation.API;

import static io.restassured.RestAssured.when;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SpaceXRestAPI {
	
	
	@Test(description="TC to verify status code and content type of the response")
	public void testspacexrestgetapi()
	{
		when().request("GET", "https://api.spacexdata.com/v4/launches/latest").then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
	}
	
	@Test(description="TC to measure response time")
	public void testtomeasureresponsetime() {
	    Response response = RestAssured.get("https://api.spacexdata.com/v4/launches/latest");
	    long timeInMS = response.time();
	    long timeInS = response.timeIn(TimeUnit.SECONDS);
	    Assert.assertEquals(timeInS, timeInMS/1000);
	}
	
	@Test(description="TC to verify response")
	public void testgetresponse() {
	    when().get("https://api.spacexdata.com/v4/launches/latest").then().log().body().statusCode(200);
	    
	}
	
	@Test(description="TC to verify response for different error codes")
	public void testerrorcodes() {
		when().get("https://api.spacexdata.com/v4/launches/latest")
	      .then().log().ifError();
	    when().get("https://api.spacexdata.com/v4/launches/latest")
	      .then().log().ifStatusCodeIsEqualTo(500);
	    when().get("https://api.spacexdata.com/v4/launches/latest")
	      .then().log().ifStatusCodeIsEqualTo(404);
	}

}
