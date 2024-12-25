package com.example.bookpli_book.review.service;

import com.example.bookpli_book.review.dto.ReviewDTO;
import com.example.bookpli_book.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // 해당 유저가 작성한 리뷰 전체 조회


}
