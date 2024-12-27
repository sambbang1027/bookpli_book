package com.example.bookpli_book.book.feignClient;

import com.example.bookpli_book.book.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "book-service", url = "http://localhost:9001")
public interface BookFeignClient {

    @GetMapping("/book/review")
    List<BookDTO> getBookByisbn(@RequestParam("isbns") List<String>isbns);

}
