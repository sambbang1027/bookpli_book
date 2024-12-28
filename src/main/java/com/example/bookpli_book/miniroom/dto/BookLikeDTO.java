package com.example.bookpli_book.miniroom.dto;
//
//import com.example.bookpli_book.entity.Book;
//import com.example.bookpli_book.entity.BookLike;
//import com.example.bookpli_book.entity.Library;
//import lombok.*;
//
//@Getter
//@NoArgsConstructor
//@ToString
//@Builder
//@AllArgsConstructor
//public class BookLikeDTO {
//    private Long book_like_id;
//    private Long user_id;
//    private String isbn13;
//
//    // DTO -> Entity
//    public BookLike toEntity(BookLike bookLike) {
//        return BookLike.builder()
//                .bookLikeId(this.book_like_id)
//                .userId(this.user_id)
//                .isbn13(this.isbn13)
//                .build();
//    }
//
//    // Entity -> DTO
//    public static BookLikeDTO fromEntity(BookLike bookLike) {
//        return BookLikeDTO.builder()
//                .book_like_id(bookLike.getBookLikeId())
//                .user_id(bookLike.getUserId())
//                .isbn13(bookLike.getIsbn13())
//                .build();
//    }
//}
