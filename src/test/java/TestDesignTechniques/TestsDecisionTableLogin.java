package TestDesignTechniques;

import AbstarctComponents.Abstracts;
import AbstarctComponents.Browsers;
import AbstarctComponents.Retry;
import LocatorsAndMethods.TOSWebLoginElementsAndMethods;
import LocatorsAndMethods.TOSWebTradeElementsAndMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestsDecisionTableLogin {

    public WebDriver driver;

    @Parameters({"URL","user","password"})
    @Test(retryAnalyzer = Retry.class)
    public void loginHappyPath(String url, String user, String password) throws IOException, InterruptedException {

        Browsers browsers = new Browsers();
        driver = browsers.browserInitialization(url);
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        TOSWebTradeElementsAndMethods elementsTrading = new TOSWebTradeElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);

        elementsLogin.Login(user, password);
        abstracts.waitForElement(elementsLogin.Accept);
        elementsLogin.pressAcceptButton();


        abstracts.waitForElement(elementsTrading.activityTab);
        elementsLogin.takeSnapShot();
        driver.quit();

    }

    @Parameters({"URL","user_wrong","password"})
    @Test(retryAnalyzer = Retry.class)
    public void loginUserWrong(String url, String user_wrong, String password) throws IOException, InterruptedException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                + "//src//main//resources//GlobalData.properties");
        prop.load(fis);
        String browser = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");

        if (browser.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(browser.contains("headless")){
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));//full screen

        }
        else if (browser.contains("edge")) {
            EdgeOptions options = new EdgeOptions();
            WebDriverManager.edgedriver().setup();
            if(browser.contains("headless")){
                options.addArguments("headless");
            }
            driver = new EdgeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));//full screen

        }
        else if (browser.contains("firefox")) {

            FirefoxOptions options = new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();
            if(browser.contains("headless")){
                options.addArguments("headless");
            }
            driver = new FirefoxDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));//full screen
        }

        driver.get(url);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);

        elementsLogin.Login(user_wrong, password);
        elementsLogin.pressRememberID();
        abstracts.waitForElement(elementsLogin.Accept);
        elementsLogin.pressAcceptButton();

        Thread.sleep(1000);
        Assert.assertTrue(elementsLogin.loginFailValidation().toLowerCase().contains("log-in failed"));
        Assert.assertNotEquals( elementsLogin.getURL(),"https://trade.thinkorswim.com/");


    }

    @Parameters({"URL","user","password_wrong"})
    @Test(retryAnalyzer = Retry.class)
    public void loginPasswordWrong(String url,String user, String password_wrong) throws IOException, InterruptedException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                + "//src//main//resources//GlobalData.properties");
        prop.load(fis);
        String browser = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");

        if (browser.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(browser.contains("headless")){
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));//full screen

        }
        else if (browser.contains("edge")) {
            EdgeOptions options = new EdgeOptions();
            WebDriverManager.edgedriver().setup();
            if(browser.contains("headless")){
                options.addArguments("headless");
            }
            driver = new EdgeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));//full screen

        }
        else if (browser.contains("firefox")) {

            FirefoxOptions options = new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();
            if(browser.contains("headless")){
                options.addArguments("headless");
            }
            driver = new FirefoxDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));//full screen
        }

        driver.get(url);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);

        elementsLogin.Login(user, password_wrong);
        elementsLogin.pressRememberID();
        abstracts.waitForElement(elementsLogin.Accept);
        elementsLogin.pressAcceptButton();

        Thread.sleep(1000);
        Assert.assertTrue(elementsLogin.loginFailValidation().toLowerCase().contains("log-in failed"));
        Assert.assertNotEquals( elementsLogin.getURL(),"https://trade.thinkorswim.com/");


    }

    @Parameters({"URL","user_wrong","password_wrong"})
    @Test(retryAnalyzer = Retry.class)
    public void loginUserPasswordWrong(String url,String user_wrong, String password_wrong) throws IOException, InterruptedException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                + "//src//main//resources//GlobalData.properties");
        prop.load(fis);
        String browser = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");

        if (browser.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(browser.contains("headless")){
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));//full screen

        }
        else if (browser.contains("edge")) {
            EdgeOptions options = new EdgeOptions();
            WebDriverManager.edgedriver().setup();
            if(browser.contains("headless")){
                options.addArguments("headless");
            }
            driver = new EdgeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));//full screen

        }
        else if (browser.contains("firefox")) {

            FirefoxOptions options = new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();
            if(browser.contains("headless")){
                options.addArguments("headless");
            }
            driver = new FirefoxDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));//full screen
        }

        driver.get(url);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        TOSWebLoginElementsAndMethods elementsLogin = new TOSWebLoginElementsAndMethods(driver);
        Abstracts abstracts = new Abstracts(driver);

        elementsLogin.Login(user_wrong, password_wrong);
        elementsLogin.pressRememberID();
        abstracts.waitForElement(elementsLogin.Accept);
        elementsLogin.pressAcceptButton();

        Thread.sleep(1000);
        Assert.assertTrue(elementsLogin.loginFailValidation().toLowerCase().contains("log-in failed"));
        Assert.assertNotEquals( elementsLogin.getURL(),"https://trade.thinkorswim.com/");

    }

    @AfterMethod
    public void browserQuit(){
        driver.quit();
    }
}
