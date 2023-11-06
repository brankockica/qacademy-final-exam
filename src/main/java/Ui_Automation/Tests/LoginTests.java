package Ui_Automation.Tests;

import Ui_Automation.Pages.BaseTest;
import Ui_Automation.Pages.HomePage;
import Ui_Automation.Pages.LoginPage;
import org.testng.annotations.*;

import java.io.IOException;

import static org.testng.Assert.*;

public class LoginTests extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;



    @BeforeTest
    void setupBrowser() throws IOException {
        this.setup();
        loginPage = new LoginPage(driver, properties, wait);
        homePage = new HomePage(driver, properties, wait);
    }

    @Test   (description = "Verify presence of UI elements")
    void validatePresenceOfUiElements () {
        loginPage.verifyUiElements();
    }
    @Test    (description = "Verify login process with valid data")
    void verifyLoginProcess() {
        loginPage.loginUser();
        assertTrue(driver.findElement(homePage.homeButton).isDisplayed());
    }
    @Test   (description = "Verify if \"Don't have an account? Sign Up\" link is working properly")
    void verifySignUpHyperLink() {
        loginPage.verifySignUpLink();
    }
    @Test    (description = "Verify that remember me function is working properly")
    void verifyRememberMeFunction() {
        loginPage.loginUser();
        homePage.logoutUser();
        String usernameField1 = getTextfromElement(loginPage.userNameField);
        String passwordFiled1 = getTextfromElement(loginPage.passwordField);
        assertEquals(usernameField1, properties.getProperty("USERNAME"));
        assertEquals(passwordFiled1, properties.getProperty("PASSWORD"));
    }
    @Test   (description = "Verify QA logo link working properly")
    void verifyQaLogoLink() throws InterruptedException {
        loginPage.openQaLink();
        switchTab();
        String afterClick = driver.getCurrentUrl();
        String url = "https://qacademy.rs/";
        assertEquals(afterClick, url, "The link is not redirecting properly");
    }
    @Test   (description = "Verify login process with empty fields")
    void verifyLoginWithEmptyFields() {
        loginPage.verifyLoginEmptyFields();
    }

    @AfterTest
    void tearDown() {
        this.cleanUp();
    }

}
