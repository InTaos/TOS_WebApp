package TOSWebTests;

import AbstarctComponents.Abstracts;
import AbstarctComponents.Browsers;
import AbstarctComponents.Retry;
import LocatorsAndMethods.TOSWebLoginElementsAndMethods;
import LocatorsAndMethods.TOSWebTradeElementsAndMethods;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;

public class BUYOrderCancellation {
    public  WebDriver driver;

    @Parameters({"URL","user","password","Instrument","BuyPrice"})
    @Test(retryAnalyzer = Retry.class)
    public void loginOrderCreationBuy(String url,String user, String password, String instrument, int BuyPrice) throws IOException, InterruptedException {

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

        abstracts.waitForElement(elementsTrading.buyButton);
        elementsTrading.pressBUYButton();
        elementsTrading.pressOrderType();

        Thread.sleep(1000);
        elementsTrading.pressOrderTypeLIMIT();
        Thread.sleep(1000);
        elementsTrading.insertPriceValue(BuyPrice);
        Thread.sleep(1000);

        elementsTrading.pressReviewButton();

        elementsTrading.pressSendButton();
        Thread.sleep(1000);
    }


    @Test(retryAnalyzer = Retry.class)
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
        elementsTrading.pressActivityTab();
        elementsLogin.takeSnapShot();

    }
    @AfterClass(alwaysRun = true)
    public void browserQuit(){
        driver.quit();
    }
    }
