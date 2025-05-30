package vip.fairy.readingservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import vip.fairy.readingservice.service.BookService;


@RestClientTest(BookService.class)
public class BookServiceTests {

  @Autowired
  private BookService bookService;

  @Autowired
  private MockRestServiceServer server;

  @Test
  public void readingListTest() {
    this.server.expect(requestTo("http://localhost:8090/recommended"))
        .andRespond(withSuccess("books", MediaType.TEXT_PLAIN));
    assertThat(bookService.readingList()).isEqualTo("books");
  }

}
