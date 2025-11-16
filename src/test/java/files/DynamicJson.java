package files;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String resp=given().header("Content-Type","application/json")
		.body(payload.Addbook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js=ReUsableMethods.rawToJson(resp);
		String id=js.get("ID");
		System.out.println(id);
	
		//deleteBook
		
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getdata()
	{
		//array=Collection of elements
		//multidimensional array= collection of arrays
		return new Object[][]{{"oifwty","0963"},{"cwetee","4253"},{"okmfet","533"}};
		
	}

}
