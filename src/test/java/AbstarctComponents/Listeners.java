package AbstarctComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


public class Listeners implements ITestListener {

    ExtentReports extent = ExtendReporterNG.getReportObject();
    ExtentTest test;
    WebDriver driver;
    ThreadLocal<ExtentTest> extendTest = new ThreadLocal<ExtentTest>();

    public String getErrorScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot errorScreenshot = (TakesScreenshot) this.driver;
        File source = errorScreenshot.getScreenshotAs((OutputType.FILE));
        File file = new File(System.getProperty("user.dir") + "//Screenshot_Errors//" + testCaseName + ".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir") + "//Screenshot_Errors//" + testCaseName + ".png";
    }



    @Override
    public void onTestStart(ITestResult iTestResult) {
        test = extent.createTest(iTestResult.getMethod().getMethodName());
        extendTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        extendTest.get().log(Status.PASS,"Test Passed");
        extendTest.get().assignCategory(iTestResult.getTestClass().getName());
        extendTest.get().assignCategory(iTestResult.getInstanceName());
        extendTest.get().getExtent();
        extent.getStats();






    }


    @Override
    public void onTestFailure(ITestResult iTestResult) {
        test.log(Status.FAIL,"Test Fail");
        extendTest.get().fail(iTestResult.getThrowable());
        extendTest.get().assignCategory(iTestResult.getTestClass().getName());
        extendTest.get().assignCategory(iTestResult.getInstanceName());
        extendTest.get().getExtent();

        try {
            driver = (WebDriver) iTestResult.getTestClass().getRealClass().getField("driver").get(iTestResult.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        String filePath = null;

        try {
            filePath = getErrorScreenshot(iTestResult.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try {
            test.addScreenCaptureFromPath(filePath,iTestResult.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        test.log(Status.SKIP,"Test Skipped");
        extendTest.get().fail(iTestResult.getThrowable());
        extendTest.get().assignCategory(iTestResult.getTestClass().getName());
        extendTest.get().assignCategory(iTestResult.getInstanceName());
        extendTest.get().getExtent();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {extent.flush();
    }


}