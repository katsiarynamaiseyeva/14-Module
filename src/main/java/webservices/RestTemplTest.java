package webservices;

import model.user.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestTemplTest {

  @Test
  public void checkStatusCode() {
    RestTemplate restTeample = new RestTemplate();
    ResponseEntity<User[]> response =
        restTeample.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);
    int actStatusCode = response.getStatusCode().value();
    Assert.assertEquals(actStatusCode, 200);
  }

  @Test
  public void checkResponseHeader() {
    RestTemplate restTeample = new RestTemplate();
    ResponseEntity<User[]> response =
        restTeample.getForEntity("http://jsonplaceholder.typicode.com/users", User[].class);
    HttpHeaders headers = response.getHeaders();

    String actContentTypeValue = headers.getContentType().toString();
    Assert.assertEquals(actContentTypeValue, "application/json;charset=utf-8");
  }

  @Test()
  public void checkResponseBody() {
    RestTemplate restTeample = new RestTemplate();
    ResponseEntity<User[]> response =
        restTeample.getForEntity("http://jsonplaceholder.typicode.com/users", User[].class);
    User[] actPosts = response.getBody();

    Assert.assertEquals(actPosts.length, 10);
  }
}
