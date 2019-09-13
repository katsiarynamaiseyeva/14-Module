package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverContainer {

  private static WebDriver driver;

  public static WebDriver getDriver() {
    if (Objects.isNull(driver)) {
      driver = initDriver();
    }
    return driver;
  }

  private static WebDriver initDriver() {
    WebDriver driver = null;
    try {
      driver =
          new RemoteWebDriver(
              new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.chrome());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    return driver;
  }
}
