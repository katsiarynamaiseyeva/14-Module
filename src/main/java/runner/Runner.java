package runner;

import config.BrowserConfig;
import config.BrowserType;
import listener.Listener;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import page.DirectUrl;
import reporting.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Runner {

  public static void main(String[] args) {
    String browserType = args[1];
    String env = args[1];
    String xmlSuiteName = "src/main/resources/testng_regression.xml";

    Properties property = new Properties();
    FileInputStream fis;
    try {
      fis = new FileInputStream("src/main/resources/env.properties");
      property.load(fis);
    } catch (IOException e) {
      e.printStackTrace();
    }

    DirectUrl.setURL(property.getProperty(env));

    BrowserConfig.setBrowserType(BrowserType.fromString(browserType));

    TestNG testNG = new TestNG(false);
    XmlSuite suite = new XmlSuite();
    suite.setName("TestNG xml suite");

    List<String> files = new ArrayList<>();
    files.add(xmlSuiteName);

    suite.setSuiteFiles(files);

    List<XmlSuite> suites = new ArrayList<>();
    suites.add(suite);

    testNG.setXmlSuites(suites);

    testNG.addListener(new Listener());
    Log.info(
        "Run with parameters: browser "
            + browserType
            + ", selenium server url "
            + BrowserConfig.getSeleniumServerUrl()
            + " in suite "
            + xmlSuiteName);
    testNG.run();
  }
}
