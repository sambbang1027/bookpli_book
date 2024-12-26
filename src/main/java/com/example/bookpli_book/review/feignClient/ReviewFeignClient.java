package com.example.bookpli_book.review.feignClient;

import com.example.bookpli_book.book.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "book-service", url = "http://localhost:9000")
public interface ReviewFeignClient {

    @GetMapping("/book/review")
    List<BookDTO> getBookByisbn(@RequestParam("isbns") List<String>isbns);
}
