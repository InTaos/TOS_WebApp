package TOSWebTests;

import AbstarctComponents.Abstracts;
import AbstarctComponents.Browsers;
import AbstarctComponents.Retry;
import LocatorsAndMethods.TOSWebLoginElementsAndMethods;
import LocatorsAndMethods.TOSWebTradeElementsAndMethods;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;


public class Login {

   public WebDriver driver;

    @Parameters({"URL","user","password"})
    @Test(retryAnalyzer = Retry.class)
    public void login(String url,String user, String password) throws IOException, InterruptedException {

        Browsers browsers = new Browsers();
        driver = browsers.browserInitialization(url);
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);

        elementsLogin.Login(user, password);
        abstracts.waitForElement(elementsLogin.Accept);
        elementsLogin.pressAcceptButton();

        if (elementsLogin.getURL().contains("https://trade.thinkorswim.com/"))
        {
            System.out.println("SUCCESSFUL LOGIN");

        }
        else{
            System.out.println("URL doesn't match. Unsuccessful Log in!");
        }

        abstracts.waitForElement(elementsTrading.activityTab);
        elementsLogin.takeSnapShot();
        driver.quit();


    }

    @Parameters({"URL","user","password"})
    @Test(retryAnalyzer = Retry.class)
    public void loginRememberId(String url,String user, String password) throws IOException, InterruptedException {

        Browsers browsers = new Browsers();
        driver = browsers.browserInitialization(url);
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);

        elementsLogin.Login(user, password);
        elementsLogin.pressRememberID();
        abstracts.waitForElement(elementsLogin.Accept);
        elementsLogin.pressAcceptButton();

        if (elementsLogin.getURL().contains("https://trade.thinkorswim.com/"))
        {
            System.out.println("SUCCESSFUL LOGIN");

        }
        else{
            System.out.println("URL doesn't match. Unsuccessful Log in!");
        }
        abstracts.waitForElement(elementsTrading.activityTab);
        elementsLogin.takeSnapShot();
        driver.quit();
    }

}