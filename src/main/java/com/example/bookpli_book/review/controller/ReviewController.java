package com.example.bookpli_book.review.controller;


import com.example.bookpli_book.common.response.BaseResponse;
import com.example.bookpli_book.common.response.BaseResponseStatus;
import com.example.bookpli_book.review.dto.ReviewDTO;
import com.example.bookpli_book.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 해당 유저가 작성한 리뷰 전체 조회
    @GetMapping("/myreview/{userId}")
    public BaseResponse<List<ReviewDTO>> getListByUserId (@PathVariable Long userId ){
        List<ReviewDTO> mylist = reviewService.readAllByIsbn(userId);
        System.out.println(mylist);

        return new BaseResponse<>(mylist);
    }
}
