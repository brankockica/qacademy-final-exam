package Ui_Automation.Tests;

import Ui_Automation.Pages.BaseTest;
import Ui_Automation.Pages.HomePage;
import Ui_Automation.Pages.LoginPage;
import Ui_Automation.Pages.RegisterPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.io.IOException;

import static org.testng.Assert.*;
public class RegisterTests extends BaseTest{
    LoginPage loginPage;
    HomePage homePage;
    RegisterPage registerPage;

    @BeforeTest
    void setupBrowser() throws IOException {
        this.setup();
        loginPage = new LoginPage(driver, properties, wait);
        homePage = new HomePage(driver, properties, wait);
        registerPage = new RegisterPage(driver, properties, wait);
    }
    @Test   (description = "Verify registering with valid data")
    void caseRegisterWithValidData() {
        registerPage.registerViaValidData();
        WebElement element = driver.findElement(loginPage.userNameField);
        assertTrue(element.isDisplayed());
    }
    @Test   (description = "Verify registering with non-Latin keyboar")
    void caseRegisterWithNonLatinKey() {
        registerPage.registerViaNonLatin();
        WebElement element = driver.findElement(loginPage.userNameField);
        assertTrue(element.isDisplayed());
    }
    @Test   (description = "Verify if 'Have Account' link, works properly")
    void caseVerifyHaveAccountLink() {
        registerPage.verifyHaveAnAccountLink();
    }
    @Test   (description = "Verify if password field enforces minimum of 4 characters")
    void caseVerifyPasswordEnforce() {
        registerPage.verifyPasswordEnforce();
    }
    @Test   (description = "Verify if confirm password field enforces validation")
    void caseVerifyConfirmPasswordEnforce() {
        registerPage.verifyConformationEnforce();
    }
    @AfterTest
    void tearDown() {
        this.cleanUp();
    }
}
