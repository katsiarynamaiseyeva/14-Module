package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import reporting.MyLogger;

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
    MyLogger.info("Get url: " + s);
    driver.get(s);
  }

  @Override
  public String getCurrentUrl() {
    MyLogger.info("Get current url");
    return driver.getCurrentUrl();
  }

  @Override
  public String getTitle() {
    String title = driver.getTitle();
    MyLogger.info("Got title: " + title);
    return title;
  }

  @Override
  public List<WebElement> findElements(By by) {
    List<WebElement> webElementList = driver.findElements(by);
    MyLogger.info("Got " + webElementList.size() + " elements");
    return webElementList;
  }

  @Override
  public WebElement findElement(By by) {
    MyLogger.info("Find element: " + by);
    return driver.findElement(by);
  }

  @Override
  public String getPageSource() {
    MyLogger.info("Get page source");
    return driver.getPageSource();
  }

  @Override
  public void close() {
    MyLogger.info("Driver closes");
    driver.close();
  }

  @Override
  public void quit() {
    MyLogger.info("Driver quits");
    driver.quit();
  }

  @Override
  public Set<String> getWindowHandles() {
    MyLogger.info("Get window handles");
    return driver.getWindowHandles();
  }

  @Override
  public String getWindowHandle() {
    MyLogger.info("Get window handle");
    return driver.getWindowHandle();
  }

  @Override
  public TargetLocator switchTo() {
    MyLogger.info("Switch to");
    return driver.switchTo();
  }

  @Override
  public Navigation navigate() {
    MyLogger.info("Navigate");
    return driver.navigate();
  }

  @Override
  public Options manage() {
    return driver.manage();
  }

  @Override
  public Object executeScript(String s, Object... objects) {
    MyLogger.info("Execute script");
    return ((JavascriptExecutor) driver).executeScript(s, objects);
  }

  @Override
  public Object executeAsyncScript(String s, Object... objects) {
    MyLogger.info("Execute async script");
    return ((JavascriptExecutor) driver).executeAsyncScript(s, objects);
  }

  @Override
  public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
    return ((TakesScreenshot) driver).getScreenshotAs(outputType);
  }
}
