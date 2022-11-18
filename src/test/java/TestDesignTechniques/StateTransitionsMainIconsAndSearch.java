package TestDesignTechniques;

import AbstarctComponents.Abstracts;
import AbstarctComponents.Browsers;
import AbstarctComponents.Retry;
import LocatorsAndMethods.TOSWebLoginElementsAndMethods;
import LocatorsAndMethods.TOSWebTradeElementsAndMethods;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class StateTransitionsMainIconsAndSearch {
   public WebDriver driver;

    @Parameters({"URL","user","password","Instrument"})
    @BeforeClass(alwaysRun = true)
    public void login(String url, String user, String password,  String instrument) throws IOException, InterruptedException {

        Browsers browsers = new Browsers();
        driver = browsers.browserInitialization(url);
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);

        elementsLogin.Login(user, password);
        abstracts.waitForElement(elementsLogin.Accept);
        elementsLogin.pressAcceptButton();

        abstracts.waitForElement(elementsTrading.logo);
        elementsTrading.pressLogo();
        Thread.sleep(1000);

    }

    @Test(retryAnalyzer = Retry.class)
    public void tradeIconValidation(){
        TOSWebTradeElementsAndMethods elementsTrade = new TOSWebTradeElementsAndMethods(driver);


        elementsTrade.pressTradeIcon();

        Boolean tradePage = driver.findElement(elementsTrade.tradePage).isDisplayed();

        Assert.assertTrue(tradePage);

    }

    @Test(retryAnalyzer = Retry.class)
    public void chartIconValidation(){
        TOSWebTradeElementsAndMethods elementsTrade = new TOSWebTradeElementsAndMethods(driver);

            elementsTrade.pressChartIcon();
            Boolean chartContainer = driver.findElement(elementsTrade.chartContainer).isDisplayed();
            Assert.assertTrue(chartContainer);
    }

    @Test(retryAnalyzer = Retry.class)
    public void positionsIconValidation(){
        TOSWebTradeElementsAndMethods elementsTrade = new TOSWebTradeElementsAndMethods(driver);

        elementsTrade.pressPostionsIcon();
        Boolean activityTab = driver.findElement(elementsTrade.activityTab).isDisplayed();
        Assert.assertTrue(activityTab);
    }

    @Parameters({"Instrument"})
    @Test(retryAnalyzer = Retry.class)
    public void instrumentSearchValidation(String instrument) throws InterruptedException {
        TOSWebTradeElementsAndMethods elementsTrade = new TOSWebTradeElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);

        elementsTrade.sendKeyToSearchBar(instrument);
        abstracts.waitForElementToAppear(elementsTrade.buyButton);

        Boolean buyButton = driver.findElement(elementsTrade.buyButton).isDisplayed();

        Assert.assertTrue(buyButton);



    }


    @AfterClass
    public void browserQuit(){
        driver.quit();
    }

}
