package LocatorsAndMethods;

import AbstarctComponents.Abstracts;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TOSWebLoginElementsAndMethods extends Abstracts {

    WebDriver driver;

    public TOSWebLoginElementsAndMethods(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By loginButton = By.linkText("Log in to thinkorswim Web");

    By userId = By.id("username0");


    By password = By.id("password1");

    By rememberId = By.className("checkboxContainer");

    By loginFailNotification = By.xpath("//div[@id='user_message_inline']");


    public By Accept = By.id("accept");


    public void Login(String userID, String password) {

        driver.findElement(loginButton).click();
        driver.findElement(userId).sendKeys(userID);
        driver.findElement(this.password).sendKeys(password);

    }

    public void pressAcceptButton() {
        driver.findElement(Accept).click();
    }


    public String getURL() {
        String url = driver.getCurrentUrl();
        return url;
    }


    public void pressRememberID() {
        driver.findElement(rememberId).click();
    }

    public void takeSnapShot() throws IOException {
        TakesScreenshot srcShot = ((TakesScreenshot) driver);
        File SrcFile = srcShot.getScreenshotAs(OutputType.FILE);
        Date d = new Date();
        File destFile = new File(System.getProperty("user.dir") + "//Screenshot_Steps//", d.toString().replace(":", "_") + ".png");
        FileHandler.copy(SrcFile, destFile);

    }

    public String loginFailValidation() {
        return driver.findElement(loginFailNotification).getText();

    }

    }