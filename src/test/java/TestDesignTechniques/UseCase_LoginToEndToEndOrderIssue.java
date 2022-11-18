package TestDesignTechniques;

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

public class UseCase_LoginToEndToEndOrderIssue {

    public WebDriver driver;
    public String[] description;


    @Parameters({"URL","user","password","Instrument"})
    @BeforeMethod
    public void loginInstrumentInit(String url,String user, String password, String instrument) throws IOException, InterruptedException {

        Browsers browsers = new Browsers();
        driver = browsers.browserInitialization(url);

        Thread.sleep(2000);

    }

    @Parameters({"user", "password", "Instrument", "BuyPrice"})
    @Test(retryAnalyzer = Retry.class)
    public void endToEndOrderLimit(String user, String password, String instrument, int BuyPrice) throws InterruptedException, IOException {

        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);

        elementsLogin.Login(user, password);
        abstracts.waitForElement(elementsLogin.Accept);
        elementsLogin.pressAcceptButton();

        abstracts.waitForElement(elementsTrading.logo);
        elementsTrading.pressLogo();
        Thread.sleep(1000);
        abstracts.waitForElement(elementsTrading.activityTab);
        elementsTrading.pressActivityTab();
        elementsTrading.pressTimeOrder();


        abstracts.waitForElement(elementsTrading.sarchBar);
        elementsTrading.sendKeyToSearchBar(instrument);

        abstracts.waitForElement(elementsTrading.buyButton);
        elementsTrading.pressBUYButton();
        elementsTrading.pressOrderType();

        Thread.sleep(1000);
        elementsTrading.pressOrderTypeLIMIT();

        Thread.sleep(1000);
        elementsTrading.insertPriceValue(BuyPrice);
        Thread.sleep(1000);
        elementsTrading.pressReviewButton();

        orderHistory.getDescriptionPrint();

        description = orderHistory.getDescription();

        Assert.assertEquals(elementsTrading.getText(),"LIMIT");
        elementsTrading.pressSendButton();

        Thread.sleep(1000);
        elementsTrading.pressLogo();
        Thread.sleep(2000);
        orderHistory.getTextOrderRowHistory();
        System.out.println();
        Assert.assertEquals(orderHistory.getTextOrderRow_SIDE().toLowerCase(),description[0].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_QNT().toLowerCase(),description[1].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_SYMBOL().toLowerCase(),description[2].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_TYPE().toLowerCase(),description[3].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_TIME_FORCE().toLowerCase(),description[4].toLowerCase());
        elementsTrading.pressAllOrdersCheck();

        Thread.sleep(1000);
        elementsTrading.pressCancelSelected();
        driver.navigate().back();
        Thread.sleep(1000);
    }

    @Parameters({"user", "password", "Instrument", "BuyPrice"})
    @Test(retryAnalyzer = Retry.class)
    public void endToEndOrderStop(String user, String password, String instrument, int BuyPrice) throws InterruptedException, IOException {
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);

        elementsLogin.Login(user, password);
        abstracts.waitForElement(elementsLogin.Accept);
        elementsLogin.pressAcceptButton();

        abstracts.waitForElement(elementsTrading.logo);
        elementsTrading.pressLogo();
        Thread.sleep(1000);
        abstracts.waitForElement(elementsTrading.activityTab);
        elementsTrading.pressActivityTab();
        elementsTrading.pressTimeOrder();


        abstracts.waitForElement(elementsTrading.sarchBar);
        elementsTrading.sendKeyToSearchBar(instrument);

        abstracts.waitForElement(elementsTrading.buyButton);
        elementsTrading.pressBUYButton();
        elementsTrading.pressOrderType();

        Thread.sleep(1000);
        elementsTrading.pressOrderTypeSTOP();

        Thread.sleep(1000);
        elementsTrading.insertPriceValue(BuyPrice);
        Thread.sleep(1000);
        elementsTrading.pressReviewButton();

        orderHistory.getDescriptionPrint();

        description = orderHistory.getDescription();

        Assert.assertEquals(elementsTrading.getText(),"STOP");
        elementsTrading.pressSendButton();

        Thread.sleep(1000);
        elementsTrading.pressLogo();
        Thread.sleep(2000);
        orderHistory.getTextOrderRowHistory();
        System.out.println();
        Assert.assertEquals(orderHistory.getTextOrderRow_SIDE().toLowerCase(),description[0].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_QNT().toLowerCase(),description[1].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_SYMBOL().toLowerCase(),description[2].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_TYPE().toLowerCase(),description[3].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_TIME_FORCE().toLowerCase(),description[4].toLowerCase());
        elementsTrading.pressAllOrdersCheck();

        Thread.sleep(1000);
        elementsTrading.pressCancelSelected();
        driver.navigate().back();
        Thread.sleep(1000);
    }

    @Parameters({"user", "password", "Instrument", "BuyPrice"})
    @Test(retryAnalyzer = Retry.class)
    public void endToEndOrderLoc(String user, String password, String instrument, int BuyPrice) throws InterruptedException, IOException {

        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);

        elementsLogin.Login(user, password);
        abstracts.waitForElement(elementsLogin.Accept);
        elementsLogin.pressAcceptButton();

        abstracts.waitForElement(elementsTrading.logo);
        elementsTrading.pressLogo();
        Thread.sleep(1000);
        abstracts.waitForElement(elementsTrading.activityTab);
        elementsTrading.pressActivityTab();
        elementsTrading.pressTimeOrder();


        abstracts.waitForElement(elementsTrading.sarchBar);
        elementsTrading.sendKeyToSearchBar(instrument);

        abstracts.waitForElement(elementsTrading.buyButton);
        elementsTrading.pressBUYButton();
        elementsTrading.pressOrderType();

        Thread.sleep(1000);
        elementsTrading.pressOrderTypeLOC();

        Thread.sleep(1000);
        elementsTrading.insertPriceValue(BuyPrice);
        Thread.sleep(1000);
        elementsTrading.pressReviewButton();

        orderHistory.getDescriptionPrint();

        description = orderHistory.getDescription();

        Assert.assertEquals(elementsTrading.getText(),"LOC");
        elementsTrading.pressSendButton();

        Thread.sleep(1000);
        elementsTrading.pressLogo();
        Thread.sleep(2000);
        orderHistory.getTextOrderRowHistory();
        System.out.println();
        Assert.assertEquals(orderHistory.getTextOrderRow_SIDE().toLowerCase(),description[0].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_QNT().toLowerCase(),description[1].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_SYMBOL().toLowerCase(),description[2].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_TYPE().toLowerCase(),description[3].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_TIME_FORCE().toLowerCase(),description[4].toLowerCase());
        elementsTrading.pressAllOrdersCheck();

        Thread.sleep(1000);
        elementsTrading.pressCancelSelected();
        driver.navigate().back();
        Thread.sleep(1000);
    }

    @Parameters({"user", "password", "Instrument", "SellPrice"})
    @Test(retryAnalyzer = Retry.class)
    public void endToEndOrderStopLimit(String user, String password, String instrument, int SellPrice) throws InterruptedException, IOException {

        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);

        elementsLogin.Login(user, password);
        abstracts.waitForElement(elementsLogin.Accept);
        elementsLogin.pressAcceptButton();

        abstracts.waitForElement(elementsTrading.logo);
        elementsTrading.pressLogo();
        Thread.sleep(1000);
        abstracts.waitForElement(elementsTrading.activityTab);
        elementsTrading.pressActivityTab();
        elementsTrading.pressTimeOrder();


        abstracts.waitForElement(elementsTrading.sarchBar);
        elementsTrading.sendKeyToSearchBar(instrument);

        abstracts.waitForElement(elementsTrading.buyButton);
        elementsTrading.pressBUYButton();
        elementsTrading.pressOrderType();

        Thread.sleep(1000);
        elementsTrading.pressOrderTypeSTOPLIMIT();

        Thread.sleep(1000);
        elementsTrading.insertPriceValue(SellPrice);
        Thread.sleep(1000);
        elementsTrading.pressReviewButton();

        orderHistory.getDescriptionPrint();

        description = orderHistory.getDescription();

        Assert.assertEquals(elementsTrading.getText(),"STOPLIMIT");
        elementsTrading.pressSendButton();

        Thread.sleep(1000);
        elementsTrading.pressLogo();
        Thread.sleep(2000);
        orderHistory.getTextOrderRowHistory();
        System.out.println();
        Assert.assertEquals(orderHistory.getTextOrderRow_SIDE().toLowerCase(),description[0].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_QNT().toLowerCase(),description[1].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_SYMBOL().toLowerCase(),description[2].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_TYPE().toLowerCase(),description[3].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_TIME_FORCE().toLowerCase(),description[4].toLowerCase());
        elementsTrading.pressAllOrdersCheck();
        //abstracts.waitForElementToDisappear(elementsTrading.notification);
        Thread.sleep(1000);
        elementsTrading.pressCancelSelected();
        driver.navigate().back();
        Thread.sleep(1000);
    }

    @AfterMethod(alwaysRun = true)
    public void browserQuit() throws InterruptedException, IOException {
        driver.quit();
    }
}
