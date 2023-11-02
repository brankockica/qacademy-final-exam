package API;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class TestApi {

    GetRequests getRequest;
    PostRequests postRequest;
    PutRequests putRequest;
    DeleteRequest deleteRequest;
    PatchRequest patchRequest;

    @BeforeTest
    void setup () {
        baseURI = "https://test-api.k6.io/";
        getRequest = new GetRequests();
        postRequest = new PostRequests();
        putRequest = new PutRequests();
        deleteRequest = new DeleteRequest();
        patchRequest = new PatchRequest();

    }
    @BeforeClass
    public void createUser () {
        postRequest.userCreate();
    }
    @BeforeMethod
    public void bearerToken () {
        postRequest.getToken();
    }
    @Test (description = "Testing /get API for single Public Crocodile")
    public void getSinglePublicCroco() {
        getRequest.singlePublicCrocoByID();

    }
    @Test (description = "Testing /get API for all Public Crocodile")
    public void getPublicCrocos() {
        getRequest.listPublicCrocos();
    }
    @Test (description = "Testing /post API for creating new user")
    public void postCreateNewUser() {
        postRequest.createNewUser();
    }
    @Test (description = "Testing /post API for logging in ")
    public void postUserLogin() {
        postRequest.userLogin();
    }
    @Test (description = "Testing /post API for creating new Croco")
    public void postCreateNewCroco() {
        postRequest.createNewCroco("Pera", "M", "1900-10-09");
    }

    @Test (description = "Testing /get API for getting all private Crocos")
    public void getListMyCrocos() {
        postRequest.createNewCroco("Mika", "M", "1950-05-12");
        postRequest.createNewCroco("Zika", "F", "1955-12-15");
        getRequest.listMyCrocos(postRequest.token);
    }
    @Test (description = "Testing /get API for single private Croco by ID")
    public void getCrocoByID() {
        postRequest.createNewCroco("Vida", "F", "1956-01-16");
        getRequest.getMyCrocoById(postRequest.token, postRequest.crocoID);
    }
    @Test (description = "Testing /put API for updating a existing private Croco")
    public void putUpdateCrocoByID() {
        postRequest.createNewCroco("Vidan", "M", "1921-01-16");
        putRequest.updateCrocobyID(postRequest.token, postRequest.crocoID);
    }
    @Test (description = "Testing /patch API for updating a existing private Croco parameter")
    public void patchUpdateCrocoPropertyByID() {
        postRequest.createNewCroco("Dragan", "M", "1981-05-01");
        patchRequest.updateCrocoPropertyByID(postRequest.token, postRequest.crocoID);
    }
    @Test (description = "Testing /del API for deleting private Croco by ID")
    public void delCrocoByID () {
        postRequest.createNewCroco("Dragana", "F", "1982-03-12");
        deleteRequest.deleteCrocoById(postRequest.token, postRequest.crocoID);
    }
    @Test (description = "Testing /post API for logging out")
    public void postUserLogout() {
        postRequest.userLogout(postRequest.token);
    }

}
