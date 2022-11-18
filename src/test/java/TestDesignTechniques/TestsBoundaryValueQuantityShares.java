package TestDesignTechniques;

import AbstarctComponents.Abstracts;
import AbstarctComponents.Browsers;
import AbstarctComponents.Retry;
import LocatorsAndMethods.OrderHistoryElements;
import LocatorsAndMethods.TOSWebLoginElementsAndMethods;
import LocatorsAndMethods.TOSWebTradeElementsAndMethods;
import net.sourceforge.htmlunit.xpath.operations.String;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class TestsBoundaryValueQuantityShares {
    public WebDriver driver;
    public java.lang.String[] description;

    @Parameters({"URL","user","password","Instrument"})
    @BeforeMethod(alwaysRun = true)
    public void login(java.lang.String url, java.lang.String user, java.lang.String password, String instrument) throws IOException, InterruptedException {

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
        elementsTrading.sendKeyToSearchBar(java.lang.String.valueOf(instrument));
    }

    @Parameters({"positiveQuantity"})
    @Test(retryAnalyzer = Retry.class)
    public void sharesBoundaryMinValue1(int positiveQuantity) throws InterruptedException {
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);


        elementsTrading.pressBUYButton();
        elementsTrading.insertSharesQuantity(positiveQuantity);
        Thread.sleep(1000);
        elementsTrading.pressReviewButton();
        System.out.println("Input");
        orderHistory.getDescriptionPrint();
        description = orderHistory.getDescription();
        elementsTrading.pressSendButton();

        elementsTrading.pressLogo();
        elementsTrading.pressActivityTab();
        elementsTrading.pressTimeOrder();
        Thread.sleep(1000);
        System.out.println("Output");
        orderHistory.getTextOrderRowHistory();

        Assert.assertEquals(orderHistory.getTextOrderRow_SIDE().toLowerCase(),description[0].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_QNT().toLowerCase(),description[1].toLowerCase());


    }
    @Parameters({"negativeQuantity"})
    @Test(retryAnalyzer = Retry.class)
    public void sharesBoundaryNegativeValue1(int negativeQuantity) throws InterruptedException {
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        OrderHistoryElements orderHistory = new OrderHistoryElements(driver);


        elementsTrading.pressBUYButton();
        elementsTrading.insertSharesQuantity(negativeQuantity);
        Thread.sleep(1000);
        elementsTrading.pressReviewButton();
        System.out.println("Input");
        orderHistory.getDescriptionPrint();
        description = orderHistory.getDescription();
        elementsTrading.pressSendButton();

        elementsTrading.pressLogo();
        elementsTrading.pressActivityTab();
        elementsTrading.pressTimeOrder();
        Thread.sleep(1000);
        System.out.println("Output");
        orderHistory.getTextOrderRowHistoryNegativeValue();

        Assert.assertEquals(orderHistory.getTextOrderRow_SIDE().toLowerCase(),description[0].toLowerCase());
        Assert.assertEquals(orderHistory.getTextOrderRow_Negative_QNT().toLowerCase(),description[1].toLowerCase());


    }
    @AfterMethod(alwaysRun = true)
    public void browserQuit() throws InterruptedException {
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);


        Thread.sleep(1000);
        elementsTrading.pressAllOrdersCheck();
        Thread.sleep(1000);
        elementsTrading.pressCancelSelected();
        driver.navigate().back();
        Thread.sleep(1000);
        driver.quit();
    }

}
