package tests;


import files.Payload;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GoogleAPITesting {
    public static void main(String[] args) {
// post
        baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().statusCode(200)
                .assertThat().body("scope", equalTo("APP"))
                .extract().response().asString();
        System.out.println("Got the response : " + response);
        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");
        System.out.println("Place ID :" + placeId);
        String newAddress = "Summer Walk, uganda";
//        update through put
        given().queryParam("key", "qaclick123")
                .contentType(ContentType.JSON)
                .body("{\r\n" +
                        "\"place_id\":\"" + placeId + "\",\r\n" +
                        "\"address\":\"" + newAddress + "\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().log().all().statusCode(200);
//get request
        String getResponse = given().queryParam("key", "qaclick123")
                .queryParam("place_id", placeId).when().get("maps/api/place/get/json")
                .then().log().all().statusCode(200)
                .extract().asString();
        JsonPath jsonPath = new JsonPath(getResponse);
        String updatedAddress = jsonPath.getString("address");

        Assert.assertEquals(updatedAddress, newAddress);
        System.out.println("Address to be updated : " + newAddress + "\nUpdated Address : " + updatedAddress);
    }

}
