package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import reporting.Log;

import java.util.List;
import java.util.Set;

public class CustomWebDriver implements WebDriver, JavascriptExecutor, TakesScreenshot {

  private WebDriver driver;
  private Actions actions;

  public CustomWebDriver(WebDriver driver) {
    this.driver = driver;
    actions = new Actions(driver);
  }

  @Override
  public void get(String s) {
    Log.info("Get url: " + s);
    driver.get(s);
  }

  @Override
  public String getCurrentUrl() {
    Log.info("Get current url");
    return driver.getCurrentUrl();
  }

  @Override
  public String getTitle() {
    String title = driver.getTitle();
    Log.info("Got title: " + title);
    return title;
  }

  @Override
  public List<WebElement> findElements(By by) {
    List<WebElement> webElementList = driver.findElements(by);
    Log.info("Got " + webElementList.size() + " elements");
    return webElementList;
  }

  @Override
  public WebElement findElement(By by) {
    Log.info("Find element: " + by);
    return driver.findElement(by);
  }

  @Override
  public String getPageSource() {
    Log.info("Get page source");
    return driver.getPageSource();
  }

  @Override
  public void close() {
    Log.info("Driver closes");
    driver.close();
  }

  @Override
  public void quit() {
    Log.info("Driver quits");
    driver.quit();
  }

  @Override
  public Set<String> getWindowHandles() {
    Log.info("Get window handles");
    return driver.getWindowHandles();
  }

  @Override
  public String getWindowHandle() {
    Log.info("Get window handle");
    return driver.getWindowHandle();
  }

  @Override
  public TargetLocator switchTo() {
    Log.info("Switch to");
    return driver.switchTo();
  }

  @Override
  public Navigation navigate() {
    Log.info("Navigate");
    return driver.navigate();
  }

  @Override
  public Options manage() {
    return driver.manage();
  }

  @Override
  public Object executeScript(String s, Object... objects) {
    Log.info("Execute script");
    return ((JavascriptExecutor) driver).executeScript(s, objects);
  }

  @Override
  public Object executeAsyncScript(String s, Object... objects) {
    Log.info("Execute async script");
    return ((JavascriptExecutor) driver).executeAsyncScript(s, objects);
  }

  @Override
  public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
    return ((TakesScreenshot) driver).getScreenshotAs(outputType);
  }
}
