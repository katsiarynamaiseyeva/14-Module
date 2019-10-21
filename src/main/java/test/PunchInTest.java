package test;

import core.DriverSingleton;
import element.ConfirmationPopup;
import model.UserFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import page.DashboardPage;
import page.LoginPage;
import reporting.MyLogger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PunchInTest {

  @Test(description = "Punch in test")
  public void punchInTest() {
    LoginPage loginPage = new LoginPage();
    loginPage.open();
    Properties property = new Properties();
    FileInputStream fis = null;
    try {
      fis = new FileInputStream("src/main/resources/credentials.properties");
      property.load(fis);
    } catch (IOException e) {
      e.printStackTrace();
    }

    UserFactory.createUser(
        property.getProperty("kronos.username"), property.getProperty("kronos.password"));
    DashboardPage dashboardPage =
        loginPage.login(
            property.getProperty("kronos.username"), property.getProperty("kronos.password"));
    ConfirmationPopup confirmationPopup = dashboardPage.punchIn();
    confirmationPopup.clickOkClockButton();
  }

  @AfterClass(description = "Close browser")
  public void closeBrowser() {
    DriverSingleton.getDriver().quit();
    MyLogger.log("DriverSingleton is closed");
  }
}
