package LocatorsAndMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryElements {
    WebDriver driver;

    By symbol = By.cssSelector("h1[class='symbol']");

    By side = By.cssSelector("div[data-testid='description']");

    By quantity = By.cssSelector("input[data-testid='trade-quantity-input']");
    By orderType = By.cssSelector("button[aria-label='Order Type']");

    By timeInForce = By.cssSelector("button[aria-label='Time in Force']");





    By orderRowSIDE = By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/section[2]/div[1]/div[1]/div[3]/table[1]/tbody[1]/tr[1]/td[3]");

    By orderRowQNT = By.cssSelector("tr[class='row order-book__row even']>td[aria-label='Quantity']");

    By orderRowSYMBOL = By.cssSelector("tr[class='row order-book__row even']>td[aria-label='symbol']");

    By orderRowOrderTYPE = By.cssSelector("tr[class='row order-book__row even']>td[aria-label='Price']");

    By orderRowTIMEFORCE = By.cssSelector("tr[class='row order-book__row even']>td[aria-label='Time in Force']");

    public By filledTab = By.cssSelector("span[class='status-flag']");



    public OrderHistoryElements(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public String getTextOrderRow_SIDE()
    {
        String text;
        text = driver.findElement(orderRowSIDE).getText();
        return text;
    }

    public String getTextOrderRow_QNT(){
        String[] text;
        text = driver.findElement(orderRowQNT).getText().split("[+]");
        return text[1];
    }
    public String getTextOrderRow_Negative_QNT(){
        String[] text;
        text = driver.findElement(orderRowQNT).getText().split("-");
        return text[1];
    }


    public String getTextOrderRow_SYMBOL(){
        String text;
        text = driver.findElement(orderRowSYMBOL).getText();
        return text;
    }

    public String getTextOrderRow_FOREXSYMBOL(){
        String text;
        text = driver.findElement(orderRowSYMBOL).getText();
        return text;
    }

    public String getTextOrderRow_TYPE(){

        String[] text = driver.findElement(orderRowOrderTYPE).getText().split(" ");
        return text[1];
    }

    public String getTextOrderRow_TYPE_Market(){

        String text = driver.findElement(orderRowOrderTYPE).getText();
        return text;
    }

    public String getTextOrderRow_TIME_FORCE(){
        String text;
        text = driver.findElement(orderRowTIMEFORCE).getText();
        return text;
    }

    public void getTextOrderRowHistory(){
        System.out.println("Working Order Row: ");
        System.out.print(getTextOrderRow_SIDE() + " ");
        System.out.print(getTextOrderRow_QNT()+ " ");
        System.out.print(getTextOrderRow_SYMBOL()+ " ");
        System.out.print(getTextOrderRow_TYPE()+ " ");
        System.out.print(getTextOrderRow_TIME_FORCE()+ " ");
        System.out.println();
    }

    public void getTextOrderRowHistoryMarket(){
        System.out.println("Working Order Row: ");
        System.out.print(getTextOrderRow_SIDE() + " ");
        System.out.print(getTextOrderRow_QNT()+ " ");
        System.out.print(getTextOrderRow_SYMBOL()+ " ");
        System.out.print(getTextOrderRow_TYPE_Market()+ " ");
        System.out.print(getTextOrderRow_TIME_FORCE()+ " ");
        System.out.println();
    }

    public void getDescriptionPrint(){
        System.out.println("Description: ");
        System.out.print(getTextSide()+ " ");
        System.out.print(getTextQNT()+ " ");
        System.out.print(getTextSymbol()+ " ");
        System.out.print(getTextOrderType() + " ");
        System.out.print(getTextTimeInForce()+ " ");
        System.out.println();

    }

    public void getTextOrderRowHistoryNegativeValue(){

        System.out.print(getTextOrderRow_SIDE() + " ");
        System.out.print(getTextOrderRow_Negative_QNT()+ " ");
        System.out.print(getTextOrderRow_SYMBOL()+ " ");
        System.out.print(getTextOrderRow_TYPE()+ " ");
        System.out.print(getTextOrderRow_TIME_FORCE()+ " ");

    }


    public void getTextOrderRowHistoryForex(){
        System.out.print(getTextOrderRow_SIDE() + " ");
        System.out.print(getTextOrderRow_FOREXSYMBOL() + " ");
        System.out.print(getTextOrderRow_TYPE()+ " ");
    }

    public String getTextSymbol(){
        String text;
        text = driver.findElement(symbol).getText();
        return text;
    }

    public String getTextOrderType() {
        String text;
        text = driver.findElement(orderType).getText();
        return text;
    }

    public String getTextTimeInForce(){
        String text;
        text = driver.findElement(timeInForce).getText();
        return text;
    }

    public String getTextSide(){

        String[] text = driver.findElement(side).getText().split(" ");
        return text[0];
    }

    public String getTextQNT(){

        String text = driver.findElement(quantity).getAttribute("value");
        return text;
    }

    public String[] getDescription(){

        String[] description = (getTextSide() + " " + getTextQNT() + " " + getTextSymbol() + " " + getTextOrderType() + " " + getTextTimeInForce()).split(" ");
        return description;
    }
    public String[] getForexDescription(){

        String[] descriptionForex = (getTextSide() + " " + getTextSymbol() + " " + getTextOrderType()).split(" ");
        return descriptionForex;
    }




    public void pressFilledTab(){
        driver.findElement(filledTab).click();
    }

}
