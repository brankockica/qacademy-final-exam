package Ui_Automation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class RegisterPage {
    WebDriver driver;
    Properties properties;
    WebDriverWait wait;


    By appLogo = By.cssSelector("[width='235px']");
    By firstNameField = By.id("firstName");
    By firstNameTextNotif = By.id("firstName-helper-text");
    By lastNameField = By.id("lastName");
    By userNameField = By.id("username");
    By usernameTextNotif = By.id("username-helper-text");
    By passwordField = By.id("password");
    By passwordTextNotif = By.id("password-helper-text");
    By confirmPasswordField = By.id("confirmPassword");
    By confirmPasswordNotif = By.id("confirmPassword-helper-text");
    By signUpButton = By.cssSelector("[data-test='signup-submit']");

    By signInLink = By.partialLinkText("Have an account? Sign In");
    By qaLogoLink = By.cssSelector("[width='403']");

    public RegisterPage (WebDriver driver, Properties properties, WebDriverWait wait){
        this.driver = driver;
        this.properties = properties;
        this.wait = wait;

    }
    public void open (){
        driver.get(properties.getProperty("BASE_URL")+ "/signup");
        driver.manage().window().maximize();
    }
    public void enterFirstName(String firstname) {
        driver.findElement(firstNameField).sendKeys(firstname);
    }
    public void enterLastName(String lastname) {
        driver.findElement(lastNameField).sendKeys(lastname);
    }
    public void enterUsername(String username) {
        driver.findElement(userNameField).sendKeys(username);
    }
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void enterConfirmPassword(String password1) {
        driver.findElement(confirmPasswordField).sendKeys(password1);
    }
    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }
    public void clickHaveAccountLink() {
        driver.findElement(signInLink).click();
    }

    public void registerFresh(String random) {
        this.open();
        this.enterFirstName("Radoje" + random);
        this.enterLastName("Radojic");
        this.enterUsername("Radeee1"+ random);
        this.enterPassword("R1234r");
        this.enterConfirmPassword("R1234r");
        this.clickSignUpButton();
    }
    public void registerViaValidData() {
        this.open();
        this.enterFirstName("Radoje");
        this.enterLastName("Radojic");
        this.enterUsername("Radeee1");
        this.enterPassword("R1234r");
        this.enterConfirmPassword("R1234r");
        this.clickSignUpButton();
    }
    public void registerViaNonLatin() {
        this.open();
        this.enterFirstName("Радоје");
        this.enterLastName("Радојић");
        this.enterUsername("Раде123");
        this.enterPassword("Раде123");
        this.enterConfirmPassword("Раде123");
        this.clickSignUpButton();
    }
    public void verifyHaveAnAccountLink() {
        this.open();
        this.clickHaveAccountLink();
        String afterClick = driver.getCurrentUrl();
        String url = properties.getProperty("BASE_URL")+ "/signin";
        assertEquals(afterClick, url, "The link is not redirecting properly");
    }
    public void verifyPasswordEnforce() {
        this.open();
        this.enterPassword("123");
        assertTrue(driver.findElement(passwordTextNotif).isDisplayed());
    }
    public void verifyConformationEnforce() {
        this.open();
        this.enterPassword("12345");
        this.enterConfirmPassword("123");
        assertTrue(driver.findElement(confirmPasswordNotif).isDisplayed());
    }
}
