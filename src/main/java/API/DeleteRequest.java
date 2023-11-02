package API;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class DeleteRequest {

    public void deleteCrocoById(String token, String crocoID) {

        given().auth().oauth2(token)
                .when().delete("/my/crocodiles/" + crocoID + "/")
                .then().log().all()
                .assertThat().time(lessThan(1000L))
                .assertThat().statusCode(204);

    }
}