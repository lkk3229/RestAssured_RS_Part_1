package RestAssured;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js= new JsonPath(payload.CoursePrice());
		
		//Print No of courses returned by API
		System.out.println("----- Print No of courses returned by API -----");
		int count=js.getInt("courses.size()");
		System.out.println("Number of courses: "+count);
		
		//Print Purchase Amount
		System.out.println("----- Print Purchase Amount -----");
		int totalAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount: "+totalAmount);
		
		//Print Title of the first course
		System.out.println("----- Print Title of the first course -----");
		String firstCourseTitle=js.getString("courses[0].title");
		System.out.println("Title of first course: "+firstCourseTitle);
		
		//Print all course titles and their respective Prices
		System.out.println("----- Print all course titles and their respective Prices -----");
		for(int i=0;i<count;i++)
		{
			String courseTitle=js.getString("courses["+i+"].title");
			//int coursePrice=js.getInt("courses["+i+"].price");
			System.out.println("Course Title: "+courseTitle);
			System.out.println(" ==> Price: "+js.get("courses["+i+"].price").toString());
		}
		
		//Print no of copies sold by RPA Course
		System.out.println("----- Print no of copies sold by RPA Course -----");
		for(int i=0;i<count;i++)
		{
			String courseTitle=js.getString("courses["+i+"].title");
			if(courseTitle.equalsIgnoreCase("RPA"))
			{
				int copiesSold=js.getInt("courses["+i+"].copies");
				System.out.println("Number of copies sold by RPA Course: "+copiesSold);
				break;
			}
		}
		
		
		

	}

}
