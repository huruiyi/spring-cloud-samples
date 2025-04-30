package vip.fairy.readingservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import vip.fairy.readingservice.service.BookService;

@RestController
public class ReadingController {

  @Autowired
  private BookService bookService;

  @RequestMapping("/to-read")
  public Mono<String> toRead() {
    return bookService.readingList();
  }

}
