package Ui_Automation.Pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    Properties properties;
    By logoutButton = By.cssSelector("[data-test='sidenav-signout']");
    By accountBalance = By.cssSelector("[data-test='sidenav-user-balance']");
    By newTransaction = By.cssSelector("[data-test='nav-top-new-transaction']");
    By myAccountButton = By.cssSelector("[data-test='sidenav-user-settings']");
    public By homeButton = By.cssSelector("[data-test='sidenav-home']");
    By newcomerNextButton = By.cssSelector("[data-test='user-onboarding-next']");
    By newcomerBankName = By.id("bankaccount-bankName-input");
    By newcomerRoutingNumb = By.id("bankaccount-routingNumber-input");
    By newcomerAccountNumb = By.id("bankaccount-accountNumber-input");
    By newcomerSaveButton = By.cssSelector("[data-test='bankaccount-submit']");
    public HomePage (WebDriver driver, Properties properties, WebDriverWait wait){
        this.driver = driver;
        this.properties = properties;
        this.wait = wait;
    }
    public void clickNextButton() {
        driver.findElement(newcomerNextButton).click();
    }
    public void enterBankName(String name) {
        driver.findElement(newcomerBankName).sendKeys(name);
    }
    public void enterRoutingNumb(String numb) {
        driver.findElement(newcomerRoutingNumb).sendKeys(numb);
    }
    public void enterAccountNumb(String numb) {
        driver.findElement(newcomerAccountNumb).sendKeys(numb);
    }
    public void clickSaveForm() {
        driver.findElement(newcomerSaveButton).click();
    }
    public void fillForm() {
        this.clickNextButton();
        this.enterBankName("blabla");
        this.enterRoutingNumb("123456712");
        this.enterAccountNumb("123123123");
        this.clickSaveForm();
        this.clickNextButton();
    }
    public double readBalance (){
        return Double.parseDouble(driver.findElement(accountBalance).getText().replace("$", ""));
    }
    public void clickMyAccount() {
        driver.findElement(myAccountButton).click();
    }
    public void clickHomeButton() {
        driver.findElement(homeButton).click();
    }
    public void logoutUser() {
        driver.findElement(logoutButton).click();
    }
    public void clickNewTransaction() {
        driver.findElement(newTransaction).click();
    }

}