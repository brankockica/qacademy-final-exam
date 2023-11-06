package Ui_Automation.Tests;

import Ui_Automation.Pages.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

import static org.testng.Assert.*;
public class MyAccountsTests extends BaseTest{
    HomePage homePage;
    MyAccountsPage myAccountsPage;
    LoginPage loginPage;
    RegisterPage registerPage;

    @BeforeTest
    void setupBrowser() throws IOException {
        this.setup();
        homePage = new HomePage(driver, properties, wait);
        myAccountsPage = new MyAccountsPage(driver, properties, wait );
        loginPage = new LoginPage(driver, properties, wait);
        registerPage = new RegisterPage(driver, properties, wait);

    }
    @BeforeMethod
    void registerFreshLogin() {
        String random = generateRandom();
        registerPage.registerFresh(random);
        loginPage.loginUserFresh(random);
        homePage.fillForm();
    }
    @AfterMethod
    void logout() {
        homePage.logoutUser();
    }

    @Test (description = "Verifying My Account saving process using valid data")
    void caseVerifySavingProcess() throws IOException, InterruptedException {
        myAccountsPage.savingViaValidData();
        Thread.sleep(504);
        driver.navigate().refresh();
        homePage.clickHomeButton();
        homePage.clickMyAccount();

        String mail = getTextfromElement(myAccountsPage.emailField);
        String phone = getTextfromElement(myAccountsPage.phoneNumberField);
        takeAScreenShot("MyAccountSave");

        assertEquals(mail, "asdb@asd.com");
        assertEquals(phone, "12345661");
    }
    @Test (description = "Verifying if MyAccount imports data from registration into fields")
        void caseVerifyAutoImport() throws InterruptedException {
        homePage.clickMyAccount();
        Thread.sleep(504);
        //wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
        String firstname = getTextfromElement(myAccountsPage.firstNameField);
        String lastname = getTextfromElement(myAccountsPage.lastNameField);

        assertNotEquals(firstname, null, "The firstname field was empty");
        assertNotEquals(lastname, null, "The lastname field was empty");
    }
    @Test (description = "Verifying if Phone number field enforces the minimum number of characters")
    void caseVerifyPhoneNumberEnforce() {
        myAccountsPage.phoneNumberEnforce();
    }
    @Test (description = "Verifying if Phone number field declines alphabet characters")
    void caseVerifyPhoneNumberAplhaChar() {
        myAccountsPage.phoneNumberAplhabetChar();
    }

    @AfterTest
    void tearDown() {
        this.cleanUp();
    }
}
