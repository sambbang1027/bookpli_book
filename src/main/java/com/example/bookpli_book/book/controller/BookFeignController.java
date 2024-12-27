package com.example.bookpli_book.book.controller;

import com.example.bookpli_book.book.dto.BookDTO;
import com.example.bookpli_book.book.service.BookService;
import com.example.bookpli_book.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookFeignController {

    private final BookService bookService;

    @GetMapping("/review")
    public BaseResponse<List<BookDTO>> getBookByisbn(@RequestParam List<String>isbns){
        List<BookDTO> list = isbns.stream()
                .map(isbn ->bookService.getBookDetail(isbn))
                .collect(Collectors.toList());
        return new BaseResponse<>(list);
    }

}
