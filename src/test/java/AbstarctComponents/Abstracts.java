package AbstarctComponents;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Abstracts {

    WebDriver driver;

    public Abstracts(WebDriver driver){
     this.driver = driver;
    }

    public void waitForElementToAppear(By findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElement(By findBy)
    {
        {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(findBy));
        }
    }

    public void waitForElementToBeSelected(By findBy)
    {
        {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeSelected(findBy));
        }
    }

    public void waitForElementToDisappear(By findBy)
    {
        {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
        }
    }

    public void waitForElementToAppearWebElement(WebElement findBy)
    {
        {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOfElementLocated((By) findBy));
        }
    }

    public void waitForText(By findBy, String orderType)
    {

            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.textToBePresentInElementLocated(findBy,orderType));

    }



}