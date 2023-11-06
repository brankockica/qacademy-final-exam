package Ui_Automation.Pages;

import Ui_Automation.Pages.RegisterPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class LoginPage {

    WebDriver driver;
    Properties properties;
    WebDriverWait wait ;
    By requestStatusCode = By.cssSelector("[class='MuiAlert-message']");
    By appLogo = By.cssSelector("[width='235px']");
    public By userNameField = By.id("username");
    By usernameTextNotif = By.id("username-helper-text");
    public By passwordField = By.id("password");
    By passwordTextNotif = By.id("password-helper-text");
    By rememberMeCheckBox = By.xpath("//*[@name='remember']/parent::span/parent::span/parent::label");
    By loginButton   = By.cssSelector("[data-test='signin-submit']");
    By signUpLink = By.cssSelector("[data-test='signup']");
    By qaLogoLink = By.cssSelector("[width='403']");
    By [] uiElements = {
            //requestStatusCode,
            appLogo,
            userNameField,
            passwordField,
            rememberMeCheckBox,
            loginButton,
            signUpLink,
            qaLogoLink
    };
    public LoginPage (WebDriver driver, Properties properties, WebDriverWait wait){
        this.driver = driver;
        this.properties = properties;
        this.wait = wait;
    }
    public void open (){
        driver.get(properties.getProperty("BASE_URL")+ "/signin");
        driver.manage().window().maximize();
    }
    public void assertErorElement() {
        assertFalse(driver.findElement(requestStatusCode).isDisplayed(), "Request failed with status code 500, message appears");
    }
    public void enterUsername(String username) {
        driver.findElement(userNameField).sendKeys(username);
    }
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    public void clickRememberMe() {
        driver.findElement(rememberMeCheckBox).click();
    }
    public void clickSignUpLink() {
        driver.findElement(signUpLink).click();
    }
    public void clickQaLogo() {
        driver.findElement(qaLogoLink).click();
    }
    public void loginUser() {
        this.open();
        this.enterUsername(properties.getProperty("USERNAME"));
        this.enterPassword(properties.getProperty("PASSWORD"));
        this.clickLoginButton();
    }
    public void loginUserFresh(String random) {
        this.open();
        this.enterUsername("Radeee1" + random);
        this.enterPassword("R1234r");
        this.clickLoginButton();
    }
    public void verifySignUpLink() {
        this.open();
        this.clickSignUpLink();
        String afterClick = driver.getCurrentUrl();
        String url = properties.getProperty("BASE_URL")+ "/signup";
        assertEquals(afterClick, url, "The link is not redirecting properly");
    }
    public void openQaLink() throws InterruptedException {
        this.open();
        this.clickQaLogo();
    }
    public void verifyLoginEmptyFields() {
        this.open();
        this.clickLoginButton();
        assertTrue(driver.findElement(usernameTextNotif).isDisplayed());
    }

    public void verifyUiElements() {
        this.open();

        for (By element : uiElements) {
            assertTrue(isElementDisplayed(element), "Element is not displayed: " + element.toString());
        }
    }
    private boolean isElementDisplayed(By element1) {
        try {
            WebElement element = driver.findElement(element1);
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
            return false;
        }
    }
}