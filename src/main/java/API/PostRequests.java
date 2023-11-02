package API;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class PostRequests {

    private String username;
    private String password = "12345";

    String token;
    String crocoID;

    public String userCreateRequestBody(String username1, String email1){
        return "{\n" +
                "    \"username\": \""+username1+"\",\n" +
                "    \"first_name\": \"bane\",\n" +
                "    \"last_name\": \"radi\",\n" +
                "    \"email\": \""+email1+"\",\n" +
                "    \"password\": \""+password+"\"\n" +
                "}";
    }
    public String randomString() {
        int desiredLength = 5;
        String random = UUID.randomUUID()
                .toString()
                .substring(0, desiredLength);
        return random;
    }
    public void userCreate() {
        String randomUsername = "brane+test"+ randomString();
        String randomEmail = "brane+test3"+ randomString() + "@gmail.com";
        Response response = given().log().all().header("Content-Type","application/json")
                .body(userCreateRequestBody(randomUsername, randomEmail))
                .when().post("/user/register/")
                .then().log().all()
                .extract().response();

        username = response.path("username").toString();
    }
    public void createNewUser() {
        String randomUsername = "brane+test"+ randomString();
        String randomEmail = "brane+test3"+ randomString() + "@gmail.com";
        Response response = given().log().all().header("Content-Type","application/json")
                .body(userCreateRequestBody(randomUsername, randomEmail))
                .when().post("/user/register/")
                .then().log().all()
                .assertThat().time(lessThan(1600L))
                .assertThat().statusCode(201)
                .extract().response();

        Assert.assertTrue(response.path("username") instanceof String, "username is not a string");
        Assert.assertTrue(response.path("first_name") instanceof String, "first_name field is not a string");
        Assert.assertTrue(response.path("last_name") instanceof String, "last_name field is not a string");
        Assert.assertTrue(response.path("email") instanceof String, "email field is not a string");

    }
    public void userLogin() {
        Response response = given().header("Content-Type","application/json")
                .body("{\n" +
                        "    \"username\": \""+username+"\",\n" +
                        "    \"password\": \""+password+"\"\n" +
                        "}")
                .when().post("/auth/token/login/")
                .then().log().all()
                .assertThat().time(lessThan(1500L))
                .assertThat().statusCode(200)
                .extract().response();

        Assert.assertTrue(response.path("refresh") instanceof String, "refresh is not a string");
        Assert.assertTrue(response.path("access") instanceof String, "access field is not a string");
    }

    public void getToken() {
        Response response = given().log().all().header("Content-Type","application/json")
                .body("{\n" +
                        "    \"username\": \""+username+"\",\n" +
                        "    \"password\": \""+password+"\"\n" +
                        "}")
                .when().post("/auth/token/login/")
                .then().log().all()
                .extract().response();
        token = response.path("access").toString();

    }

    public void createNewCroco(String name, String sex, String date) {
        Response response = given().header("Content-Type","application/json")
                .auth().oauth2(token)
                .body("{\n" +
                        "    \"name\": \""+name+"\",\n" +
                        "    \"sex\": \""+sex+"\",\n" +
                        "    \"date_of_birth\": \""+date+"\"\n" +
                        "}")
                .when().post("/my/crocodiles/")
                .then().log().all()
                .assertThat().time(lessThan(1500L))
                .assertThat().statusCode(201)
                .assertThat().body("name",equalTo(name),
                                    "sex", equalTo(sex),
                                    "date_of_birth", equalTo(date))
                .extract().response();

        crocoID = response.path("id").toString();
        Assert.assertTrue(response.path("id") instanceof Integer, "ID is not a number");
        Assert.assertTrue(response.path("name") instanceof String, "Name field is not a string");
        Assert.assertTrue(response.path("sex") instanceof String, "Sex field is not a string");
        Assert.assertTrue(response.path("date_of_birth") instanceof String, "Date of birth field is not a string");
        Assert.assertTrue(response.path("age") instanceof Integer, "Age is not a number");
    }
    public void userLogout (String token) {
        given().auth().oauth2(token)
                .when().post("/auth/cookie/logout/")
                .then().log().all()
                .assertThat().time(lessThan(1000L))
                .assertThat().statusCode(200);

    }
}