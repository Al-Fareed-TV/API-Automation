package tests;


import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class FirstTest {
    @Test
    public void firstTest() throws Exception {

        Response response = RestAssured.get("https://reqres.in/api/users?page=2");


        System.out.println("Status code :" + response.getStatusCode());
        System.out.println("Body :" + response.getBody().asString());
        System.out.println("Header : " + response.getHeader("content-type"));
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test
    public void secondTest() throws Exception {

        baseURI = "https://reqres.in/api";
        given().get("/users?page=2")
                .then().statusCode(200)
                .body("data[1].id", equalTo(8))
        ;
    }

    @Test
    public void getTest() {
        baseURI = "https://reqres.in/api";
        given().get("/users?page=2")
                .then().statusCode(200)
                .body("data.first_name", hasItem("George"));
    }

    @Test
    public void shouldPost() {
        JSONObject request = new JSONObject();
        request.put("name", "Suresh");
        request.put("job", "PO");
        System.out.println(request.toJSONString());
        baseURI = "https://reqres.in/api";
        given().body(request.toJSONString())
                .when().post("/users").then()
                .statusCode(201).log().all();
    }

    @Test
    public void shouldPut() {
        //1.Arrange
        JSONObject obj = new JSONObject();
        //2.Act
        obj.put("name", "Mahesh");
        obj.put("job", "PO");
        baseURI = "https://reqres.in/api";
        given().header("Content-Type", "application/json")
                .body(obj.toJSONString())
                .when().put("/users/2").then()
                .statusCode(200)
                .log().all();
        //3.Assert
    }

    @Test
    public void shouldPatch() {
        //1.Arrange
        JSONObject obj = new JSONObject();
        //2.Act
        obj.put("name", "Kamlesh");
        obj.put("job", "Teacher");
        baseURI = "https://reqres.in/api";
        given().header("Content-Type", "application/json")
                .body(obj.toJSONString())
                .when().patch("/users/2").then()
                .statusCode(200)
                .log().all();
        //3.Assert
    }

    @Test
    public void shouldDelete() {
        //1.Arrange
        //2.Act

        baseURI = "https://reqres.in/api";

                 when().delete("/users/2").then()
                .statusCode(204)
                .log().all();
        //3.Assert
    }

}
