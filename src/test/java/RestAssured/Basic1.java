package RestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;


public class Basic1 {

	public static void main(String[] args) throws IOException {
		// validate if Add Place API is working as expected
		
		//Add place --> Update Place with New Address --> Get Place to validate if New address is present in response
		
		//given - all input details
		//when - Submit the API  -resource, http method
		//then - Validate the response
		//content of the file to String ==> Content of file can convert into Byte ==> Byte data to String
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("path of json file"))))
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js= new JsonPath(response);   //for parsing json
		//JsonPath js=ReUsableMethods.rawToJson(response);
		String placeId=js.getString("place_id");
		
		System.out.println(placeId);
		
		//Update Place
		String newAddress="Summer walk, Africa";

		String updatePlaceBody = "{ \r\n"
				+ " \"place_id\":\""+placeId+"\", \r\n"
				+ " \"address\":\""+newAddress+"\", \r\n"
				+ " \"key\":\"qaclick123\" \r\n"
				+ " } ";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(updatePlaceBody)
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200)
		//.body("msg", equalTo("Address successfully updated"))
		;
		 
		//Get Place
		
		String getPlaceResponse=given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse); 
		String actualAddress=js1.getString("address");
		System.out.println(actualAddress);
		
		//Cucumber Junit, TestNG
		
		Assert.assertEquals(actualAddress, newAddress);
		
		 
		
	}

}
