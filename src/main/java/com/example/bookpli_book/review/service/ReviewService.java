package com.example.bookpli_book.review.service;


import com.example.bookpli_book.book.dto.BookDTO;
import com.example.bookpli_book.common.response.BaseResponse;
import com.example.bookpli_book.entity.Review;
import com.example.bookpli_book.review.dto.ReviewDTO;
import com.example.bookpli_book.review.feignClient.ReviewFeignClient;
import com.example.bookpli_book.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewFeignClient reviewFeignClient;
    private final ReviewRepository reviewRepository;


    // 유저의 전체 리뷰
    public List<ReviewDTO> readAllByIsbn(Long userId){
        // 유저의 리뷰
        List<Review> reviews = reviewRepository.findByUserId(userId);

        if(reviews.isEmpty()){
            return Collections.emptyList();
        }

        // isbn 리스트
        List<String>isbns = reviews.stream().map(review ->
                        review.getIsbn13())
                        .collect(Collectors.toList());

        // 리뷰의 북 정보
        List<BookDTO>books = reviewFeignClient.getBookByisbn(isbns);

        // 책 정보를 isbn으로 Map 변환
        Map<String, BookDTO> bookmap = books.stream()
                .collect(Collectors.toMap(BookDTO::getIsbn13, book->book));

        List<ReviewDTO> reviewDTOS = reviews.stream()
                .map(review -> { // 리뷰에 맞는 책 찾기
                    BookDTO bookDTO = bookmap.get(review.getIsbn13()); // 책 매칭

                    return ReviewDTO.builder()
                            .isbn13(review.getIsbn13())
                            .reviewId(review.getReviewId())
                            .reviewContent(review.getReviewContent())
                            .rating(review.getRating())
                            .userId(review.getUserId())
                            .title(bookDTO.getTitle())
                            .cover(bookDTO.getCover())
                            .build();
                }).collect(Collectors.toList());

        return reviewDTOS;
    }
}
