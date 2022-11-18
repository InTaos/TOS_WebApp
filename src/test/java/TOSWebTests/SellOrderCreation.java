package TOSWebTests;

import AbstarctComponents.Abstracts;
import AbstarctComponents.Browsers;
import AbstarctComponents.Retry;
import LocatorsAndMethods.OrderHistoryElements;
import LocatorsAndMethods.TOSWebLoginElementsAndMethods;
import LocatorsAndMethods.TOSWebTradeElementsAndMethods;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class SellOrderCreation {

   public WebDriver driver;

    @Parameters({"URL","user","password","Instrument"})
    @BeforeClass(alwaysRun = true)
    public void login(String url,String user, String password, String instrument) throws IOException, InterruptedException {

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
    }

    @Parameters({"Instrument"})
    @Test(retryAnalyzer = Retry.class)
    public void orderMarket() throws InterruptedException, IOException {
        Abstracts abstracts = new Abstracts(driver);
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);

        abstracts.waitForElement(elementsTrading.sellButton);
        elementsTrading.pressSELLButton();
        elementsTrading.pressOrderType();

        Thread.sleep(1000);
        elementsTrading.pressOrderTypeMARKET();

        Thread.sleep(1000);
        abstracts.waitForElement(elementsTrading.reviewButton);
        elementsTrading.pressReviewButton();
        orderHistory.getDescriptionPrint();
        Assert.assertEquals(elementsTrading.getText(),"MARKET");

        elementsTrading.pressSendButton();

        Thread.sleep(3000);
        elementsLogin.takeSnapShot();

    }

    @Parameters({"SellPrice"})
    @Test(retryAnalyzer = Retry.class)
    public void orderLimit(int SellPrice) throws InterruptedException, IOException {


        Abstracts abstracts = new Abstracts(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);

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
        orderHistory.getDescriptionPrint();
        Assert.assertEquals(elementsTrading.getText(),"LIMIT");
        elementsTrading.pressSendButton();

    }

    @Parameters({"Instrument"})
    @Test(retryAnalyzer = Retry.class)
    public void orderStop() throws InterruptedException, IOException {
        Abstracts abstracts = new Abstracts(driver);
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);

        abstracts.waitForElement(elementsTrading.sellButton);
        elementsTrading.pressSELLButton();
        elementsTrading.pressOrderType();

        Thread.sleep(1000);
        elementsTrading.pressOrderTypeSTOP();

        Thread.sleep(1000);
        abstracts.waitForElement(elementsTrading.reviewButton);
        elementsTrading.pressReviewButton();

        orderHistory.getDescriptionPrint();
        Assert.assertEquals(elementsTrading.getText(),"STOP");

        elementsTrading.pressSendButton();

        Thread.sleep(3000);
        elementsLogin.takeSnapShot();

    }

    @Parameters({"Instrument"})
    @Test(retryAnalyzer = Retry.class)
    public void orderMoc() throws InterruptedException, IOException {

        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);
        Abstracts abstracts = new Abstracts(driver);

        abstracts.waitForElement(elementsTrading.sellButton);
        elementsTrading.pressSELLButton();
        elementsTrading.pressOrderType();

        Thread.sleep(1000);
        elementsTrading.pressOrderTypeMOC();

        Thread.sleep(1000);
        abstracts.waitForElement(elementsTrading.reviewButton);
        elementsTrading.pressReviewButton();
        orderHistory.getDescriptionPrint();
        Assert.assertEquals(elementsTrading.getText(),"MOC");

        elementsTrading.pressSendButton();

        Thread.sleep(3000);
        elementsLogin.takeSnapShot();

    }

    @Parameters({"Instrument"})
    @Test(retryAnalyzer = Retry.class)
    public void orderLoc() throws InterruptedException, IOException {
        Abstracts abstracts = new Abstracts(driver);
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);

        Thread.sleep(2000);
        abstracts.waitForElement(elementsTrading.sellButton);
        elementsTrading.pressSELLButton();
        elementsTrading.pressOrderType();

        Thread.sleep(1000);
        elementsTrading.pressOrderTypeLOC();

        Thread.sleep(1000);
        abstracts.waitForElement(elementsTrading.reviewButton);
        elementsTrading.pressReviewButton();
        orderHistory.getDescriptionPrint();
        Assert.assertEquals(elementsTrading.getText(),"LOC");

        elementsTrading.pressSendButton();

        Thread.sleep(3000);
        elementsLogin.takeSnapShot();

    }

    @Parameters({"Instrument"})
    @Test(retryAnalyzer = Retry.class)
    public void orderStopLimit() throws InterruptedException, IOException {


        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);

        abstracts.waitForElement(elementsTrading.sellButton);
        elementsTrading.pressSELLButton();
        elementsTrading.pressOrderType();

        Thread.sleep(1000);
        elementsTrading.pressOrderTypeSTOPLIMIT();

        Thread.sleep(1000);
        abstracts.waitForElement(elementsTrading.reviewButton);
        elementsTrading.pressReviewButton();
        orderHistory.getDescriptionPrint();
        Assert.assertEquals(elementsTrading.getText(),"STOPLIMIT");

        elementsTrading.pressSendButton();

        Thread.sleep(3000);
        elementsLogin.takeSnapShot();

    }

    @Parameters({"Instrument"})
    @Test(retryAnalyzer = Retry.class)
    public void orderTrailStop() throws InterruptedException, IOException {
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);

        abstracts.waitForElement(elementsTrading.sellButton);
        elementsTrading.pressSELLButton();
        elementsTrading.pressOrderType();

        Thread.sleep(1000);
        elementsTrading.pressOrderTypeTRAILSTOP();

        Thread.sleep(1000);
        abstracts.waitForElement(elementsTrading.reviewButton);
        elementsTrading.pressReviewButton();
        orderHistory.getDescriptionPrint();
        Assert.assertEquals(elementsTrading.getText(),"TRAILSTOP");

        elementsTrading.pressSendButton();

        Thread.sleep(3000);
        elementsLogin.takeSnapShot();

    }


    @AfterClass(alwaysRun = true)
    public void orderCancellation() throws InterruptedException, IOException {

        Abstracts abstracts = new Abstracts(driver);
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
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
        elementsLogin.takeSnapShot();
        elementsTrading.pressActivityTab();
        driver.quit();

    }

}