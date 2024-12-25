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


}
