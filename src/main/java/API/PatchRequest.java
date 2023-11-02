package API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import io.restassured.response.Response;
import org.testng.Assert;

public class PatchRequest {
    public void updateCrocoPropertyByID(String token, String ID) {

        Response response = given().header("Content-Type","application/json")
                .auth().oauth2(token)
                .body("{\n" +
                        "    \"date_of_birth\": \"1930-1-13\"\n" +
                        "}")
                .when().patch("/my/crocodiles/" + ID + "/")
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