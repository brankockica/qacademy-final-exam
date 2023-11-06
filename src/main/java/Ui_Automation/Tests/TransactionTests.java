package Ui_Automation.Tests;

import Ui_Automation.Pages.BaseTest;
import Ui_Automation.Pages.HomePage;
import Ui_Automation.Pages.LoginPage;
import Ui_Automation.Pages.RegisterPage;
import Ui_Automation.Pages.TransactionPage;
import org.testng.annotations.*;

import java.io.IOException;

import static org.testng.Assert.*;

public class TransactionTests extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;
    TransactionPage transactionPage;
    RegisterPage registerPage;


    @BeforeTest
    void setupBrowser() throws IOException {
        this.setup();
        registerPage = new RegisterPage(driver,properties,wait);
        loginPage = new LoginPage(driver, properties, wait);
        homePage = new HomePage(driver, properties, wait);
        transactionPage = new TransactionPage(driver, properties, wait);
    }
    @BeforeMethod
    void loginUser() {
        loginPage.loginUser();
    }
    @AfterMethod
    void logoutUser() {
        homePage.logoutUser();
    }
    @Test (description = "Verifying payment process using valid data", priority = 0)
    void casePaymentProcess() throws IOException, InterruptedException {
        double beforePayment = homePage.readBalance();
        takeAScreenShot("ValidTransactionTestBeforePayment");
        homePage.clickNewTransaction();
        transactionPage.makeAPayment(1.0);
        Thread.sleep(504);
        double afterPayment = homePage.readBalance();
        takeAScreenShot("ValidTransactionTestAfterPayment");
        assertNotEquals(beforePayment, afterPayment);
    }
    @Test (description = "Verifying request process using valid data", priority = 1)
    void caseReuestProcess() {
        double amount = 20.00;
        homePage.clickNewTransaction();
        transactionPage.makeARequest(amount);
        assertEquals(transactionPage.transactionAmount(), "$"+amount+"0");
    }
    @Test (description =  "Verifying paying process using valid data and decimal number amount", priority = 2)
    void casePayingWithDecimalAmount() throws IOException {
        double amount = 1.50;
        homePage.clickNewTransaction();
        transactionPage.makeAPayment(amount);
        takeAScreenShot("TransactionTestDecimalAmountPay");
        assertEquals(transactionPage.transactionAmount(), "$"+amount+"0", "Decimal number was not registered");
    }
    @Test (description = "Verifying paying process using negative amount in payment process", priority = 3)
    void casePayingWithNegativAmount() throws IOException {
        double amount = -1.00;
        homePage.clickNewTransaction();
        transactionPage.makeANegativPayment(amount);
        takeAScreenShot("TransactionTestNegativeAmountPay");

    }
    @Test (description = "Verifying paying process when Account balance is zero", priority = 4)
    void casePayProcessWithZeroBalance() {
        homePage.clickNewTransaction();
        transactionPage.makeAPayment(1000.0);
        double balance = homePage.readBalance();
        assertEquals(balance, "0");
        transactionPage.makePaymentWithZeroBalance(10.00);
    }
    @AfterClass
    void accountBalanceCondition() {
        loginPage.loginUser();
        homePage.clickNewTransaction();
        transactionPage.accountConditionPlus(-700.00);
    }

    @AfterTest
    void tearDown() {
        this.cleanUp();
    }

}