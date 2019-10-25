package test;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import core.DriverSingleton;
import element.ConfirmationPopup;
import listener.Listener;
import model.EmployeeModel;
import model.UserFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.DashboardPage;
import page.LoginPage;
import reporting.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Listeners({ReportPortalTestNGListener.class, Listener.class})
public class PunchOutTest {

  @Test(description = "Punch out test")
  public void punchOutTest() {
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
    EmployeeModel user =
        UserFactory.createUser(
            property.getProperty("kronos.username"), property.getProperty("kronos.password"));
    DashboardPage dashboardPage =
        loginPage.login(
            property.getProperty("kronos.username"), property.getProperty("kronos.password"));
    ConfirmationPopup confirmationPopup = dashboardPage.punchOut();
    confirmationPopup.clickOkClockButton();
  }

  @AfterClass(description = "Close browser")
  public void closeBrowser() {
    DriverSingleton.getDriver().quit();
    Log.log("DriverSingleton is closed");
  }
}
