package AbstarctComponents;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class Retry implements IRetryAnalyzer {
    private int count = 0;
    private static int maxTry = 3;
    @Override
    public boolean retry(ITestResult iTestResult) {
                           //Check if test not succeed
            if (count < maxTry)
            {                            //Check if maxtry count is reached
                count++;                                     //Increase the maxTry count by 1
                return true;  //Mark test as failed

            }
            return false;
    }
}