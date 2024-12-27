package com.example.bookpli_book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookLikeId;
    private Long userId;
    private String isbn13;


    @Builder
    public BookLike(Long bookLikeId, Long userId, String isbn13) {
        this.bookLikeId = bookLikeId;
        this.userId = userId;
        this.isbn13 = isbn13;
    }
}
