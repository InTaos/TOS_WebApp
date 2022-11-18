package TOSWebTests;

import AbstarctComponents.Abstracts;
import AbstarctComponents.Browsers;
import AbstarctComponents.Retry;
import LocatorsAndMethods.TOSWebLoginElementsAndMethods;
import LocatorsAndMethods.TOSWebTradeElementsAndMethods;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class SELLOrderCancellation {
    public WebDriver driver;

    @Parameters({"URL","user","password","Instrument","SellPrice"})
    @Test(retryAnalyzer = Retry.class)
    public void login(String url,String user, String password,  String instrument, int SellPrice) throws IOException, InterruptedException {

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

        abstracts.waitForElement(elementsTrading.sarchBar);
        elementsTrading.sendKeyToSearchBar(instrument);


        abstracts.waitForElement(elementsTrading.sellButton);
        elementsTrading.pressSELLButton();
        elementsTrading.pressOrderType();

        Thread.sleep(1000);
        elementsTrading.pressOrderTypeLIMIT();

        Thread.sleep(1000);
        elementsTrading.insertPriceValue(SellPrice);
        Thread.sleep(1000);

        Thread.sleep(1000);
        abstracts.waitForElement(elementsTrading.reviewButton);
        elementsTrading.pressReviewButton();

        Assert.assertEquals(elementsTrading.getText(),"LIMIT");
        elementsTrading.pressSendButton();
        Thread.sleep(1000);
    }


    @Test(retryAnalyzer = Retry.class)
    public void orderCancellation() throws InterruptedException, IOException {

        Abstracts abstracts = new Abstracts(driver);
        TOSWebLoginElementsAndMethods loginElements = new TOSWebLoginElementsAndMethods(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);

        elementsTrading.pressLogo();
        Thread.sleep(1000);
        abstracts.waitForElement(elementsTrading.activityTab);
        elementsTrading.pressActivityTab();
        abstracts.waitForElement(elementsTrading.allOrdersCheck);
        elementsTrading.pressAllOrdersCheck();
        abstracts.waitForElementToDisappear(elementsTrading.notification);
        elementsTrading.pressCancelSelected();
        Thread.sleep(1000);
        elementsTrading.pressActivityTab();
        loginElements.takeSnapShot();


    }
    @AfterClass(alwaysRun = true)
    public void browserQuit(){
        driver.quit();
    }
    }
