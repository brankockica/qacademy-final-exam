package API;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import API.PostRequests;

import java.util.List;
import java.util.Map;

public class GetRequests {


    public void listPublicCrocos() {
        Response response = given().log().all().header("Content-Type","application/json")
                .when().get("/public/crocodiles/")
                .then().log().all()
                .assertThat().time(lessThan(1500L))
                .assertThat().statusCode(200)
                .extract().response();

        List<Map<String, Object>> items = response.jsonPath().getList("");

        for (Map<String, Object> item : items) {
            Assert.assertTrue(item.get("id") instanceof Integer, "ID is not a number");
            Assert.assertTrue(item.get("name") instanceof String, "Name is not a string");
            Assert.assertTrue(item.get("sex").equals("M") || item.get("sex").equals("F"), "Sex is not one of M, F");
            Assert.assertTrue(item.get("date_of_birth") instanceof String, "Date of birth is not a string");
            Assert.assertTrue(item.get("age") instanceof Integer, "Age is not a number");
        }

    }
    public void singlePublicCrocoByID () {
        Response response = given().log().all().header("Content-Type","application/json")
                .when().get("/public/crocodiles/3/")
                .then().log().all()
                .assertThat().time(lessThan(1500L))
                .assertThat().statusCode(200)
                .extract().response();

        Assert.assertTrue(response.path("id") instanceof Integer, "ID is not a number");
        Assert.assertTrue(response.path("name") instanceof String, "Name field is not a string");
        Assert.assertTrue(response.path("sex") instanceof String, "Sex field is not a string");
        Assert.assertTrue(response.path("date_of_birth") instanceof String, "Date of birth field is not a string");
        Assert.assertTrue(response.path("age") instanceof Integer, "Age is not a number");
    }
    public void listMyCrocos(String token) {

        Response response = given()
                .auth().oauth2(token)
                .when().get("/my/crocodiles/")
                .then().log().all()
                .assertThat().time(lessThan(1500L))
                .assertThat().statusCode(200)
                .extract().response();

        List<Map<String, Object>> items = response.jsonPath().getList("");

        for (Map<String, Object> item : items) {
            Assert.assertTrue(item.get("id") instanceof Integer, "ID is not a number");
            Assert.assertTrue(item.get("name") instanceof String, "Name is not a string");
            Assert.assertTrue(item.get("sex").equals("M") || item.get("sex").equals("F"), "Sex is not one of M, F");
            Assert.assertTrue(item.get("date_of_birth") instanceof String, "Date of birth is not a string");
            Assert.assertTrue(item.get("age") instanceof Integer, "Age is not a number");
        }
    }
    public void getMyCrocoById(String token, String crocoID ) {
        Response response = given().log().all().header("Content-Type","application/json")
                .auth().oauth2(token)
                .when().get("/my/crocodiles/" + crocoID + "/")
                .then().log().all()
                .assertThat().time(lessThan(1000L))
                .assertThat().statusCode(200)
                .extract().response();

        Assert.assertTrue(response.path("id") instanceof Integer, "ID is not a number");
        Assert.assertTrue(response.path("name") instanceof String, "Name field is not a string");
        Assert.assertTrue(response.path("sex") instanceof String, "Sex field is not a string");
        Assert.assertTrue(response.path("date_of_birth") instanceof String, "Date of birth field is not a string");
        Assert.assertTrue(response.path("age") instanceof Integer, "Age is not a number");
    }
}