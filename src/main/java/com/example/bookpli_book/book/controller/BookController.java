package com.example.bookpli_book.book.controller;

import com.example.bookpli_book.book.dto.BookDTO;
import com.example.bookpli_book.book.service.BookService;
import com.example.bookpli_book.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    @GetMapping("{isbn13}")
    public BaseResponse<BookDTO> getBookDetail(@PathVariable String isbn13){
        BookDTO response = bookService.getBookDetail(isbn13);
        return new BaseResponse<>(response);
    }
}
