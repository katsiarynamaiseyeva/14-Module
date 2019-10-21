package test;

import core.DriverSingleton;
import element.NotesPopup;
import element.Popover;
import model.EmployeeModel;
import model.UserFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import page.HamburgerMenu;
import page.LoginPage;
import page.OtherFormsOpenTabPage;
import page.OtherFormsPage;
import reporting.MyLogger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AddNoteTest {

  @Test(description = "Add note test")
  public void AddNoteTest() {
    LoginPage loginPage = new LoginPage();
    loginPage.open();
    Properties property = new Properties();
    FileInputStream fis;
    try {
      fis = new FileInputStream("src/main/resources/credentials.properties");
      property.load(fis);
    } catch (IOException e) {
      e.printStackTrace();
    }

    EmployeeModel user =
        UserFactory.createUser(
            property.getProperty("kronos.username"), property.getProperty("kronos.password"));
    loginPage.login(user.getUsername(), user.getPassword());
    new HamburgerMenu().navigateToForms();
    new OtherFormsPage().navigateToOpenTab();

    int numberOfNotesBefore = new OtherFormsOpenTabPage().countNumberOfNotes();

    Popover popover = new OtherFormsOpenTabPage().clickOnThreeDots();
    NotesPopup comment = popover.openCommentPopup();
    comment.addComment("comment text");

    OtherFormsOpenTabPage otherFormsOpenTabPage2 = new OtherFormsOpenTabPage();

    otherFormsOpenTabPage2.waitForNoteCounter2(numberOfNotesBefore + 1);

    int numberOfNotesAfter = otherFormsOpenTabPage2.countNumberOfNotes();

    Assert.assertEquals(numberOfNotesAfter, numberOfNotesBefore + 1, "Note is not added");
  }

  @AfterClass(description = "Close browser")
  public void closeBrowser() {
    DriverSingleton.getDriver().quit();
    MyLogger.log("DriverSingleton is closed");
  }
}
