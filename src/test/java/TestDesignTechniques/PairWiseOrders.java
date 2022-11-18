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

public class PairWiseOrders {

    public WebDriver driver;
    public String[] description;
    public String[] descriptionForex;


    @Parameters({"URL","user","password","Instrument"})
    @BeforeMethod(alwaysRun = true)
    public void login(String url,String user, String password, String instrument) throws IOException, InterruptedException {

        Browsers browsers = new Browsers();
        driver = browsers.browserInitialization(url);
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);

        elementsLogin.Login(user, password);
        abstracts.waitForElement(elementsLogin.Accept);
        elementsLogin.pressAcceptButton();

    }

    @Parameters({"ForexInstrument"})
    @Test(retryAnalyzer = Retry.class)
    public void buyForexLimit(String instrument) throws InterruptedException {
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);


        abstracts.waitForElement(elementsTrading.sarchBar);
        elementsTrading.sendKeyToSearchBar(instrument);

        abstracts.waitForElement(elementsTrading.buyButton);
        Thread.sleep(1000);
        elementsTrading.pressBUYButton();
        elementsTrading.pressOrderType();

        Thread.sleep(1000);
        elementsTrading.pressOrderTypeLIMIT();

        Thread.sleep(1000);
        abstracts.waitForElement(elementsTrading.reviewButton);
        elementsTrading.pressReviewButton();
        Thread.sleep(1000);
        orderHistory.getForexDescription();
        descriptionForex = orderHistory.getForexDescription();
        elementsTrading.pressSendButton();

        Thread.sleep(1000);
        elementsTrading.pressLogo();

        Thread.sleep(1000);
        elementsTrading.pressActivityTab();

        Thread.sleep(1000);
        orderHistory.getTextOrderRowHistoryForex();
        System.out.println();

       Assert.assertEquals(orderHistory.getTextOrderRow_SIDE().toLowerCase(),descriptionForex[0].toLowerCase());
       Assert.assertEquals(orderHistory.getTextOrderRow_FOREXSYMBOL().toLowerCase(),descriptionForex[1].toLowerCase());
       Assert.assertEquals(orderHistory.getTextOrderRow_TYPE().toLowerCase(),descriptionForex[2].toLowerCase());

        elementsTrading.pressAllOrdersCheck();
        abstracts.waitForElementToDisappear(elementsTrading.notification);
        Thread.sleep(1000);
        elementsTrading.pressCancelSelected();
        driver.navigate().back();
        Thread.sleep(1000);
    }

    @Parameters({"Instrument"})
    @Test(retryAnalyzer = Retry.class)
    public void buyStockMarket(String instrument) throws InterruptedException, IOException {
        Abstracts abstracts = new Abstracts(driver);
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);

        abstracts.waitForElement(elementsTrading.sarchBar);
        elementsTrading.sendKeyToSearchBar(instrument);

        abstracts.waitForElement(elementsTrading.buyButton);
        elementsTrading.pressBUYButton();
        elementsTrading.pressOrderType();

        Thread.sleep(1000);
        elementsTrading.pressOrderTypeMARKET();

        Thread.sleep(1000);
        abstracts.waitForElement(elementsTrading.reviewButton);
        elementsTrading.pressReviewButton();
        orderHistory.getDescriptionPrint();
        description = orderHistory.getDescription();
        elementsTrading.pressSendButton();
        Thread.sleep(2000);
        elementsLogin.takeSnapShot();
        elementsTrading.pressLogo();

        Thread.sleep(1000);
        elementsTrading.pressActivityTab();

        Thread.sleep(1000);
        orderHistory.getTextOrderRowHistoryMarket();


        Assert.assertEquals(orderHistory.getTextOrderRow_SIDE().toLowerCase(),description[0].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_QNT().toLowerCase(),description[1].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_SYMBOL().toLowerCase(),description[2].toLowerCase());
        elementsTrading.pressAllOrdersCheck();
        abstracts.waitForElementToDisappear(elementsTrading.notification);
        Thread.sleep(1000);
        elementsTrading.pressCancelSelected();
        driver.navigate().back();
        Thread.sleep(1000);

    }

    @Parameters({"InstrumentFutures","BuyPrice"})
    @Test(retryAnalyzer = Retry.class)
    public void buyFuturesStopLimit(String instrumentFutures, int price) throws InterruptedException, IOException {
        Abstracts abstracts = new Abstracts(driver);
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);

        abstracts.waitForElement(elementsTrading.sarchBar);
        elementsTrading.sendKeyToSearchBar(instrumentFutures);

        abstracts.waitForElement(elementsTrading.buyButton);
        elementsTrading.pressBUYButton();
        elementsTrading.pressOrderType();

        Thread.sleep(1000);
        elementsTrading.pressOrderTypeSTOPLIMIT();
        elementsTrading.insertPriceValue(price);
        Thread.sleep(1000);
        elementsTrading.insertActivationPrice(price);
        Thread.sleep(1000);
        abstracts.waitForElement(elementsTrading.reviewButton);
        elementsTrading.pressReviewButton();
        orderHistory.getDescriptionPrint();
        description = orderHistory.getDescription();
        elementsTrading.pressSendButton();
        Thread.sleep(2000);
        elementsLogin.takeSnapShot();
        elementsTrading.pressLogo();

        Thread.sleep(1000);
        elementsTrading.pressActivityTab();

        Thread.sleep(1000);
        orderHistory.getTextOrderRowHistory();

        elementsTrading.pressAllOrdersCheck();
        abstracts.waitForElementToDisappear(elementsTrading.notification);
        Thread.sleep(1000);
        elementsTrading.pressCancelSelected();
        driver.navigate().back();
        Thread.sleep(1000);

    }

    @AfterMethod
    public void browserQuit(){
        driver.quit();
    }
}
