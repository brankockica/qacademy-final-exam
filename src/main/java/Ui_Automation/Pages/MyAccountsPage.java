package Ui_Automation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class MyAccountsPage {
    WebDriver driver;
    Properties properties;
    WebDriverWait wait;
    public By firstNameField = By.id("user-settings-firstName-input");
    By firstNameNotif = By.id("user-settings-firstName-input-helper-text");
    public By lastNameField = By.id("user-settings-lastName-input");
    By lastNameNotif = By.id("user-settings-lastName-input-helper-text");
    public By emailField = By.id("user-settings-email-input");
    By emailFieldNotif = By.id("user-settings-email-input-helper-text");
    public By phoneNumberField = By.id("user-settings-phoneNumber-input");
    By phoneNumberNotif = By.id("user-settings-phoneNumber-input-helper-text");
    By saveButton = By.cssSelector("[data-test='user-settings-submit']");


    public MyAccountsPage (WebDriver driver, Properties properties, WebDriverWait wait){
        this.driver = driver;
        this.properties = properties;
        this.wait = wait;
    }
    public void enterFirstName(String firstname) {
        driver.findElement(firstNameField).sendKeys(firstname);
    }
    public void enterLastName(String lastname) {
        driver.findElement(lastNameField).sendKeys(lastname);
    }
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    public void enterPhoneNumber(String phonenumber) {
        driver.findElement(phoneNumberField).sendKeys(phonenumber);
    }
    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }
    public void open (){
        driver.get(properties.getProperty("BASE_URL")+ "/user/settings");
        driver.manage().window().maximize();
    }
    public void  savingViaValidData() {
        this.open();
        this.enterEmail("asdb@asd.com");
        this.enterPhoneNumber("12345661");
        this.clickSaveButton();
    }
    public void phoneNumberEnforce() {
        this.open();
        this.enterPhoneNumber("1234");
        assertTrue(driver.findElement(phoneNumberNotif).isDisplayed(), "There was no helper text notification");
    }
    public void phoneNumberAplhabetChar() {
        this.open();
        this.enterPhoneNumber("Ar12345678");
        assertTrue(driver.findElement(phoneNumberNotif).isDisplayed(), "There was no helper text notification");
    }

}
