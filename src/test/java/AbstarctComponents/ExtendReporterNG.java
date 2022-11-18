package AbstarctComponents;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ExtendReporterNG {

    WebDriver driver;

    public ExtendReporterNG(WebDriver driver){
        this.driver = driver;
    }
    public static ExtentReports getReportObject(){

        String path = System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("TOS-Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");
        reporter.getTestRunnerLogs();
        reporter.getTestList();
        reporter.getRunDuration();
        reporter.getSystemAttributeContext();
        ExtentReports extent = new ExtentReports();

        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Vasil Mutafchiev");
        return extent;


    }


}
