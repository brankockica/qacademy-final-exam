package Ui_Automation.Pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TransactionPage {
    WebDriver driver;
    Properties properties;
    WebDriverWait wait;
    By stepOneComplete  = By.xpath("//*[@class='MuiStep-root MuiStep-horizontal MuiStep-completed'][1]");
    By stepTwoComplete  = By.xpath("//*[@class='MuiStep-root MuiStep-horizontal MuiStep-completed'][2]");
    By stepThreeComplete = By.xpath("//*[@class='MuiStep-root MuiStep-horizontal MuiStep-completed'][3]");
    By userSearchBar    = By.id("user-list-search-input");
    By paymentTarget    = By.xpath("//*[@data-test='users-list']/li[1]");
    By amountField      = By.id("amount");
    By amountInputHelper = By.id("transaction-create-amount-input-helper-text");
    By noteField        = By.cssSelector("[placeholder='Add a note']");
    By payButton        = By.cssSelector("[data-test='transaction-create-submit-payment']");
    By requestButton    = By.cssSelector("[data-test='transaction-create-submit-request']");
    By successMessage = By.xpath("//div[@class='MuiGrid-root MuiGrid-container MuiGrid-align-items-xs-center MuiGrid-justify-content-xs-center']/div/h2");
    By successWindow    = By.className("MuiAlert-message");
    Double amount;

    public TransactionPage(WebDriver driver, Properties properties, WebDriverWait wait) {
        this.driver = driver;
        this.properties = properties;
        this.wait = wait;
    }
    public void open() {
        driver.get(properties.getProperty("BASE_URL")+ "/transaction/new");
    }
    public void choseUserForPayment () {
        driver.findElement(paymentTarget).click();
    }
    public void choseAmountForPayment (Double amount) {
        driver.findElement(amountField).sendKeys("" + amount);
    }
    public void writeNote (String note) {
        driver.findElement(noteField).sendKeys(note);
    }
    public void clickPay() {
        driver.findElement(payButton).click();
    }
    public void clickRequest() {
        driver.findElement(requestButton).click();
    }
    public void checkForPaymentSuccess () {
        WebElement popUpGreen = wait.until(ExpectedConditions.visibilityOfElementLocated(successWindow));
        assertTrue(driver.findElement(stepThreeComplete).isDisplayed());
        assertTrue(popUpGreen.isDisplayed());
    }
    public void accountConditionPlus(Double amount) {
        this.choseUserForPayment();
        this.choseAmountForPayment(amount);
        this.writeNote("Test");
        this.clickPay();
    }

    public void makeAPayment(Double amount) {
        this.choseUserForPayment();
        this.choseAmountForPayment(amount);
        this.writeNote("Test");
        this.clickPay();
        this.checkForPaymentSuccess();
    }
    public void makeARequest (Double amount) {
        this.choseUserForPayment();
        this.choseAmountForPayment(amount);
        this.writeNote("test");
        this.clickRequest();
        this.checkForPaymentSuccess();
    }
    public void makePaymentWithZeroBalance(Double amount) {
        this.choseUserForPayment();
        this.choseAmountForPayment(amount);
        this.writeNote("Test");
        this.clickPay();
        assertFalse(driver.findElement(stepThreeComplete).isDisplayed(), "The payment was a success, the test failed.");
    }
    public void makeANegativPayment(Double amount) {
        this.choseUserForPayment();
        this.choseAmountForPayment(amount);
        this.writeNote("Test");
        this.clickPay();
        assertFalse(driver.findElement(stepThreeComplete).isDisplayed(), "The negative amount was successfully payed");
    }
    public String transactionConfirmationText() {

        return driver.findElement(successMessage).getText();
    }
    public String transactionAmount() {
        String input = this.transactionConfirmationText();

        String[] parts = input.split(" ");
        for (String part : parts) {
            if (part.startsWith("$")) {
                return part;
            }
        }
        return null;
    }
}
