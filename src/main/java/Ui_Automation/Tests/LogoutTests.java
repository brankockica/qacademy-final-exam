package Ui_Automation.Tests;

import Ui_Automation.Pages.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LogoutTests extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    @BeforeTest
    void setupBrowser() throws IOException {
        this.setup();
        loginPage = new LoginPage(driver, properties, wait);
        homePage = new HomePage(driver, properties, wait);
    }
    @BeforeMethod
    void loginUser() {
        loginPage.loginUser();
    }
    @Test   (description = "Verifying if logout process is without error")
    void logoutUser() throws IOException {
        homePage.logoutUser();
        takeAScreenShot("LogoutTestObserveEndState");
        loginPage.assertErorElement();
    }
    @AfterTest
    void tearDown() {
        this.cleanUp();
    }
}
