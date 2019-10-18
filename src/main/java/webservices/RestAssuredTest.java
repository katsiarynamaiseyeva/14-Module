package webservices;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import model.user.User;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RestAssuredTest {

  @BeforeTest
  public void initTest() {
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
  }

  @Test
  public void checkStatusCode() {
    Response response = RestAssured.when().get("/users").andReturn();
    Assert.assertEquals(response.getStatusCode(), 200);
  }

  @Test
  public void checkResponseHeader() {
    Response reponse = RestAssured.when().get("/users").andReturn();

    String rpContentTypeHeader = reponse.getHeader("Content-Type");
    Assert.assertEquals(rpContentTypeHeader, "application/json; charset=utf-8");
  }

  @Test
  public void checkResponseBody() {
    Response reponse = RestAssured.when().get("/users").andReturn();

    ResponseBody<?> responseBody = reponse.getBody();
    User[] users = responseBody.as(User[].class);
    Assert.assertEquals(users.length, 10);
  }
}
