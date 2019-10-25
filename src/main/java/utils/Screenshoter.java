package utils;

import core.CustomWebDriver;
import core.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import reporting.Log;

import java.io.File;
import java.io.IOException;

public class Screenshoter {

  private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

  public static void takeScreenshot() {
    CustomWebDriver driver = DriverSingleton.getDriver();
    File screenshot = driver.getScreenshotAs(OutputType.FILE);
    byte[] screenshotBytes = driver.getScreenshotAs(OutputType.BYTES);
    try {
      String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime() + ".png";
      File copy = new File(screenshotName);
      FileUtils.copyFile(screenshot, copy);
      Log.info("Saved screenshot: " + screenshotName);
      ReportPortalUtils.submitImage(screenshotBytes, screenshotName);
      ReportPortalUtils.submitFile(screenshot, screenshotName);
    } catch (IOException e) {
      Log.info("Failed to make screenshot");
    }
  }
}
