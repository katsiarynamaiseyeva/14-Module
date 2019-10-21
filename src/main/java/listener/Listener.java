package listener;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import reporting.MyLogger;
import utils.Screenshoter;

public class Listener extends TestListenerAdapter {

  @Override
  public void onTestFailure(ITestResult tr) {
    MyLogger.error("Test " + tr.getName() + " is failed");
    Screenshoter.takeScreenshot();
  }

  @Override
  public void onTestSkipped(ITestResult tr) {
    MyLogger.info("Test " + tr.getName() + " is skipped");
  }

  @Override
  public void onTestSuccess(ITestResult tr) {
    MyLogger.info("Test " + tr.getName() + " is passed");
  }
}
