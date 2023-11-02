package API;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import org.testng.Assert;

public class PutRequests {

    public void updateCrocobyID(String token, String id) {

        Response response = given().log().all().header("Content-Type","application/json")
                .auth().oauth2(token)
                .body("{\n" +
                        "    \"name\": \"Mrs. All-Sunday\",\n" +
                        "    \"sex\": \"F\",\n" +
                        "    \"date_of_birth\": \"1700-11-13\"\n" +
                        "}")
                .when().put("/my/crocodiles/" + id + "/")
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