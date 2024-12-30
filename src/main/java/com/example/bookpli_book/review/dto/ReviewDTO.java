package com.example.bookpli_book.review.dto;

import com.example.bookpli_book.entity.Review;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class ReviewDTO {
    private Long reviewId;
    private Long userId;
    private String isbn13;
    private String reviewContent;
    private Integer rating;

    private String title;
    private String cover;

    private String userNickname;
    private String profilePath;

    public ReviewDTO(Long reviewId, Long userId, String isbn13,
                     String reviewContent, Integer rating,String userNickname, String profilePath){
        this.reviewId = reviewId;
        this.userId = userId;
        this.isbn13 = isbn13;
        this.reviewContent = reviewContent;
        this.rating = rating;
        this.userNickname = userNickname;
        this.profilePath = profilePath;
    }

    // dto -> entity
    public Review toEntity(){
        return Review.builder()
                .reviewId(this.reviewId)
                .isbn13(this.isbn13)
                .userId(this.userId)
                .reviewContent(this.reviewContent)
                .rating(this.rating)
                .build();
    }

    // 엔티티를 DTO로 변환하는 메서드
    public static ReviewDTO fromEntity(Review review) {
        return ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .userId(review.getUserId())
                .isbn13(review.getIsbn13())
                .reviewContent(review.getReviewContent())
                .rating(review.getRating())
                .build();
    }

}
