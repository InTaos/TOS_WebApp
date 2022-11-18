package LocatorsAndMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TOSWebTradeElementsAndMethods {
    WebDriver driver;

    public TOSWebTradeElementsAndMethods(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public By logo = By.xpath("//header/div[1]/a[1]/img[1]");

    public By sarchBar = By.xpath("//input[@id='navigation-symbol-search']");

    public By buyButton = By.xpath("//button[contains(text(),'Buy')]");

    public By sellButton = By.xpath("//button[contains(text(),'Sell')]");

    public By reviewButton = By.id("review-order-button");

    By priceField = By.id("limit-price-0 input");

    By activationPrice = By.id("activation-price-0 input");



    By sharesQuantity = By.cssSelector("input[aria-label='Quantity Input']");

    By orderTypeLimit = By.cssSelector("li[data-testid=\"order-type-dropdown:LIMIT\"]");

    By orderTypeMarket = By.xpath("//li[contains(text(),'MARKET')]");

    By getOrderTypeStop = By.cssSelector("ul[data-testid='selector-dropdown-listbox']>li:nth-child(5)");

    By orderTypeLoc = By.xpath("//li[contains(text(),'LOC')]");

    By orderTypeMoc = By.xpath("//li[contains(text(),'MOC')]");

    By orderTypeStopLimit = By.xpath("//li[contains(text(),\'STOPLIMIT\')]");

    By orderTypeTrailStop = By.xpath("//li[contains(text(),'TRAILSTOP')]");

    By timeOrder = By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/section[2]/div[1]/div[1]/div[3]/table[1]/thead[1]/tr[1]/td[1]/div[1]/span[4]/span[1]/span[1]");


    public By notification = By.cssSelector("div[data-testid='notification-toasts']");

    public By activityTab = By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/section[2]/div[1]/div[1]/div[1]/button[1]/span[1]/h2[1]");

    public By allOrdersCheck = By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/section[2]/div[1]/div[1]/div[3]/table[1]/thead[1]/tr[1]/th[1]/div[1]/span[1]");

    public By tradePage = By.cssSelector("div[class='trade-page']");

    By cancelSelected = By.xpath("//button[contains(text(),'Cancel Selected')]");


    By positionsIcon = By.cssSelector("button[data-testid='positions-page-button']");
    By tradeIcon = By.cssSelector("button[data-testid='trade-page-button']");

    By chartIcon = By.cssSelector("button[data-testid='charts-page-button']");



    public By chartContainer = By.cssSelector("div[class='d3chart-container']");




    public void sendKeyToSearchBar(String instrument) throws InterruptedException {
        driver.findElement(sarchBar).sendKeys(instrument);
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("ul[data-testid='selector-dropdown-listbox'] li:nth-child(1)"))));
        driver.findElement(By.cssSelector("ul[data-testid='selector-dropdown-listbox'] li:nth-child(1)")).click();

    }

    public void pressBUYButton() {
        driver.findElement(buyButton).click();
    }

    public void pressSELLButton(){driver.findElement(sellButton).click();}

    public void pressReviewButton() {
        WebElement ButtonReview = new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> driver.findElement(By.id("review-order-button")));
        ButtonReview.click();
    }

    public void pressSendButton() {
        WebElement ButtonSend = new WebDriverWait(driver,Duration.ofSeconds(10)).until(driver1 -> driver.findElement(By.id("send-order-button")));
        ButtonSend.click();
    }

    public void pressOrderType() {

        WebElement OrderType = new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> driver.findElement(By.cssSelector("button[aria-label='Order Type']")));
       OrderType.click();
    }

   public void insertPriceValue(int price) throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement filed = driver.findElement(By.id("limit-price-0 input"));
        filed.sendKeys(Keys.CONTROL,"a");
       Thread.sleep(1000);
      driver.findElement(priceField).sendKeys(Keys.DELETE);
       driver.findElement(priceField).sendKeys(String.valueOf(price));
   }

   public void insertActivationPrice(int price) throws InterruptedException {
       Actions action = new Actions(driver);
       WebElement filed = driver.findElement(By.id("activation-price-0 input"));
       filed.sendKeys(Keys.CONTROL,"a");
       Thread.sleep(1000);
       driver.findElement(activationPrice).sendKeys(Keys.DELETE);
       driver.findElement(activationPrice).sendKeys(String.valueOf(price));
   }

   public void insertSharesQuantity(int sharesQuantity) throws InterruptedException {
       Actions action = new Actions(driver);
       WebElement filed = driver.findElement(this.sharesQuantity);
       filed.sendKeys(Keys.CONTROL,"a");
       Thread.sleep(1000);
       driver.findElement(this.sharesQuantity).sendKeys(Keys.DELETE);
       driver.findElement(this.sharesQuantity).sendKeys(String.valueOf(sharesQuantity));
   }

    public void pressOrderTypeLIMIT() {
        driver.findElement(orderTypeLimit).click();
    }
    public void pressOrderTypeMARKET() {
        driver.findElement(orderTypeMarket).click();
    }

    public void pressOrderTypeSTOP(){
        driver.findElement(getOrderTypeStop).click();
    }

    public void pressOrderTypeLOC(){
        driver.findElement(orderTypeLoc).click();
    }

    public void pressOrderTypeMOC(){driver.findElement(orderTypeMoc).click();}

    public void pressOrderTypeSTOPLIMIT(){driver.findElement(orderTypeStopLimit).click();}

    public void pressOrderTypeTRAILSTOP(){driver.findElement(orderTypeTrailStop).click();}


    public String getText(){

        WebElement OrderType = new WebDriverWait(driver,Duration.ofSeconds(10)).until(driver1 -> driver.findElement(By.cssSelector("button[aria-label='Order Type']")));
        String text = OrderType.getText();
        return text;

    }

    public void pressActivityTab(){
        driver.findElement(activityTab).click();
    }

    public void pressAllOrdersCheck(){
        driver.findElement(allOrdersCheck).click();
    }

    public void pressCancelSelected(){
        driver.findElement(cancelSelected).click();
    }

    public void pressLogo(){
        driver.findElement(logo).click();
    }

    public void pressTimeOrder(){
        driver.findElement(timeOrder).click();
    }

    public void pressTradeIcon(){
        driver.findElement(tradeIcon).click();
    }

    public void pressChartIcon(){
        driver.findElement(chartIcon).click();
    }

    public void pressPostionsIcon(){
        driver.findElement(positionsIcon).click();
    }
}