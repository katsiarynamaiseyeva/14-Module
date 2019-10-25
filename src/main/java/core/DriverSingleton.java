package core;

import config.BrowserConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import reporting.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverSingleton {

  private static CustomWebDriver driver;

  public static CustomWebDriver getDriver() {
    if (Objects.isNull(driver)) {
      driver = initDriver();
    }
    return driver;
  }

  private static CustomWebDriver initDriver() {

    CustomWebDriver webDriver = null;
    DesiredCapabilities capabilities;
    String seleniumServerUrl = BrowserConfig.getSeleniumServerUrl();

    switch (BrowserConfig.getBrowserType()) {
      case CHROME:
        WebDriverManager.chromedriver().setup();
        capabilities = DesiredCapabilities.chrome();
        capabilities.setPlatform(Platform.WINDOWS);
        try {
          webDriver =
              new CustomWebDriver(new RemoteWebDriver(new URL(seleniumServerUrl), capabilities));
        } catch (MalformedURLException e) {
          Log.debug("Can't connect to remote WebDriver with capabilities " + capabilities);
        }
        break;
      case FIREFOX:
        WebDriverManager.firefoxdriver().setup();
        capabilities = DesiredCapabilities.firefox();
        capabilities.setPlatform(Platform.WINDOWS);
        try {
          webDriver =
              new CustomWebDriver(new RemoteWebDriver(new URL(seleniumServerUrl), capabilities));
        } catch (MalformedURLException e) {
          Log.debug("Can't connect to remote WebDriver with capabilities " + capabilities);
        }
        break;
    }
    webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    webDriver.manage().window().maximize();
    return webDriver;
  }
}
