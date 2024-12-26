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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private Long userId;
    private String isbn13;
    private String reviewContent;
    private Integer rating;


    @Builder
    public Review(Long reviewId, Long userId, String isbn13, String reviewContent, Integer rating, String userNickname, String profilePath) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.isbn13 = isbn13;
        this.reviewContent = reviewContent;
        this.rating = rating;

    }
}