package Ui_Automation.Pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import static org.testng.Assert.assertTrue;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait ;
    protected Properties properties;


    public void setup() throws IOException {
        properties = new Properties();
        FileInputStream configFile = new FileInputStream("src/main/java/Ui_Automation/config.properties");
        properties.load(configFile);

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions().setBinary(properties.getProperty("BROWSER_PATH"));
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));


    }
    public void cleanUp () {
        driver.quit();
    }
    public void checkForElement(By element) {
        WebElement element3 = driver.findElement(element);
        assertTrue(element3.isDisplayed());
    }
    public String getTextfromElement(By element) {
        WebElement element4 = driver.findElement(element);
        return element4.getAttribute("value");
    }
    public void switchTab() {
        String secondTab = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(secondTab);
    }
    public void takeAScreenShot (String screenshotName) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("src/main/java/Ui_Automation/TestScreenshots/" + screenshotName + ".png"));
    }
    public String generateRandom() {
        return String.valueOf(new Random().nextInt(90000) + 10000);
    }
    public void waitPls() {

    }
}