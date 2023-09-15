package tests;

import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class JiraTest {
    public static void main(String[] args) {
        baseURI = "http://localhost:8080";
        SessionFilter session = new SessionFilter();
        String response = given()
                .header("Content-Type", "application/json")
                .body("{ \"username\": \"Al-Fareed\", \"password\": \"alfa@Jira781\" }").filter(session)
                .when()
                .post("rest/auth/1/session")
                .then().log().all().extract().asString();
//10104
//        given().pathParam("key","10104").header("Content-Type","application/json")
//                .body("{\n" +
//                        "    \"body\": \"Jaldi wahaan se hato\",\n" +
//                        "    \"visibility\": {\n" +
//                        "        \"type\": \"role\",\n" +
//                        "        \"value\": \"Administrators\"\n" +
//                        "    }\n" +
//                        "}").filter(session).when().post("rest/api/2/issue/{key}/comment").then().statusCode(201);

        given().header("X-Atlassian-Token","no-check")
                .filter(session).pathParam("key","10104")
                .header("Content-Type","multipart/form-data")
                .multiPart("file",new File("/Users/testvagrant/Documents/API Testing/src/test/java/tests/jira.txt"))
                .when().post("rest/api/2/issue/{key}/attachments")
                .then().log().all().statusCode(200);
    }
}
