package tests;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJSON {
    @Test(dataProvider = "BooksData")
    public void addBook() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String postResponse = given()
                .queryParam("key","qaclick123")
                .header("Content-Type", "application/json")
                .log().all()
                .body(Payload.addBook("check", "420"))
                .when().post("/Library/Addbook.php")
                .then().log().all().toString();

        JsonPath jsonPath = new JsonPath(postResponse);
        // Assuming "ID" is at the root level of the JSON
        String id = jsonPath.getString("ID");
        System.out.println("ID: " + id);
    }
    @DataProvider(name = "BooksData")
    public Object[][] getData(){
        return new Object[][] {{"abcd","1234"},{"efgh","5678"},{"ijkl","9012"}};
    }

}
