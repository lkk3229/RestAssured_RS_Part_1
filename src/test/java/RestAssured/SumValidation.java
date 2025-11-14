package RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sumOfCourses()
	{
		JsonPath js= new JsonPath(payload.CoursePrice());
		
		//Print No of courses returned by API
		int count=js.getInt("courses.size()");
		int sum=0;
		for(int i=0;i<count;i++)
		{
			int coursePrice=js.getInt("courses["+i+"].price");
			int courseCopies=js.getInt("courses["+i+"].copies");
			int totalCoursePrice=coursePrice*courseCopies;
			sum=sum+totalCoursePrice;
		}
		
		System.out.println("Sum of all course prices: "+sum);
		
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount: "+purchaseAmount);
		
		Assert.assertEquals(sum, purchaseAmount);
	}

}
