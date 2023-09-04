package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class LocalAPITest {

    @Test
    public void shouldGet() {
        //1.Arrange
        baseURI = "https://localhost:3000";
        //2.Act
        given().get("/users").then().statusCode(200).log().all();

        //3.Assert
    }

    @Test
    public void shouldPost() {
        //1.Arrange
        baseURI = "http://localhost:3000";

        JSONObject addRequest = new JSONObject();
        addRequest.put("id", 6);
        addRequest.put("firstName", "Suresh");
        addRequest.put("lastName", "Dalle");
        addRequest.put("subjectId", 2);
        //2.Act
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(addRequest.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201);
        //3.Assert
    }

    @Test
    public void shouldDelete() {
        //1.Arrange
        baseURI = "http://localhost:3000";

        //2.Act
        when().delete("/users/6").then().statusCode(200);
        //3.Assert
    }

    @Test
    public void shouldPut() {
        //1.Arrange
        baseURI = "http://localhost:3000";
        //2.Act
        JSONObject patchRequest = new JSONObject();
        patchRequest.put("firstName", "Vaibhav");
        patchRequest.put("lastName", "Chowdhary");
        patchRequest.put("subjectId", 3);
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(patchRequest.toJSONString())
                .when().put("/users/4")
                .then().statusCode(200)
                .log().all();
        //3.Assert
    }

    @Test
    public void shouldPatch() {
        //1.Arrange
        baseURI = "http://localhost:3000";
        JSONObject patchRequest = new JSONObject();
        patchRequest.put("firstName", "Sharath");
        //2.Act
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(patchRequest.toJSONString())
                .when()
                .patch("/users/5")
                .then()
                .statusCode(200);

        //3.Assert
    }
}
