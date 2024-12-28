package com.example.bookpli_book.library.dto;

import com.example.bookpli_book.book.dto.BookDTO;
import com.example.bookpli_book.entity.Book;
import com.example.bookpli_book.entity.BookLike;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class BookLikeDTO {
    private Long bookLikeId;
    private Long userId;
    private BookDTO bookDTO;


    // Entity -> DTO
    public static BookLikeDTO fromEntity(BookLike bookLike, Book book) {
        return BookLikeDTO.builder()
                .bookLikeId(bookLike.getBookLikeId())
                .userId(bookLike.getUserId())
                .bookDTO(BookDTO.fromEntity(book))
                .build();
    }
}
