package File;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;

public class GraphQLScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub 8:36, 14:28,18:46,20:00 
    //body content is not currect for both Query and Mutations
		
		//Query
		int characterId = 14;
		
		String response = given().log().all().header("Content-Type","application-json")
		.body("{\"query\":\"query{$characterId : Int!, $episodeId :Int!) \\n{\\n character(characterId: $characterId) {\\}")
		
		.when().post("https:rahulshettyacademy.com/gq/graphql")
		
		.then().extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String characterName = js.getString("data.character.name");
		Assert.assertEquals(characterName, "Robin");
			
		//Mutations
		String newCharacter = "Ruskin Bond";
		String mutationResponse = given().log().all().header("Content-Type","application-json")
				.body("{\"query\":\"query{$characterId : Int!, $episodeId :Int!) \\n{\\n character(characterId: $characterId) {\\}")
				
				.when().post("https:rahulshettyacademy.com/gq/graphql")
				
				.then().extract().response().asString();
				
				System.out.println(mutationResponse);
				

	}

}
