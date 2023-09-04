package tests;


import files.Payload;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GoogleAPITesting {
    public static void main(String[] args) {

        baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(Payload.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().statusCode(200)
                .assertThat().body("scope",equalTo("APP"))
                .extract().response().asString();
        System.out.println("Got the response : " + response );
        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");
        System.out.println("Place ID :" + placeId);
    }
}
