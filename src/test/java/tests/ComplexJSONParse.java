package tests;

import files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJSONParse {
    public static void main(String[] args) {
        JsonPath jsonPath = new JsonPath(Payload.CoursePrice());
//        no of courses
        int count = jsonPath.getInt("courses.size()");
        System.out.println("No of courses : " + count);

//        printing purchase amount
        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase Amount : " + purchaseAmount);

        String title = jsonPath.getString("courses[3].title");
        System.out.println("Title of first course : " + title);

        for (int i = 0; i < count; i++) {
            String courseTitle = jsonPath.getString("courses[" + i + "].title");
            int coursePrice = jsonPath.getInt("courses[" + i + "].price");
            System.out.println("Title :" + courseTitle + ",\tPrice : " + coursePrice);

        }
// get the number of copies of RPA
        for (int i = 0; i < count; i++) {
            String titles = jsonPath.getString("courses[" + i + "].title");
            if (titles.equalsIgnoreCase("rpa")) {
                int copies = jsonPath.getInt("courses[" + i + "].copies");
                System.out.println("Copies of RPA is : " + copies);
                break;
            }
        }
//        verify the total sum of price
        int sum = 0;;
        for (int i = 0; i < count; i++) {
            int individualCoursePrice = jsonPath.getInt("courses[" + i + "].price");
            int copies = jsonPath.getInt("courses[" + i + "].copies");
            sum += individualCoursePrice * copies;
        }
        System.out.println("Total price of the courses : " + sum);
        int purchaseAmount1 = jsonPath.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(purchaseAmount1,sum);
    }
}
