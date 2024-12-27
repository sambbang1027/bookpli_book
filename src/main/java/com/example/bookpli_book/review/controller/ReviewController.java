package com.example.bookpli_book.review.controller;


import com.example.bookpli_book.common.response.BaseResponse;
import com.example.bookpli_book.common.response.BaseResponseStatus;
import com.example.bookpli_book.review.dto.ReviewDTO;
import com.example.bookpli_book.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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

    // 해당 도서의 리뷰 전체 조회
    @GetMapping("/book/{isbn13}")
    public BaseResponse<List<ReviewDTO>> getListByisbn (@PathVariable String isbn13){
        List<ReviewDTO> review = reviewService.readAllByUser(isbn13);
        System.out.println("컨트롤러 : 도서 리뷰 전체 출력 : "+ review);

        return new BaseResponse<>(review);
    }


    // 리뷰 수정
    @PutMapping("/update")
    public BaseResponse<ReviewDTO> updateReview(@RequestBody ReviewDTO review) {
        log.info("우선 도착은 했니 ..?");
        try {
            ReviewDTO updatedReview = reviewService.update(review);
            log.info("리뷰 업데이트 성공");

            return new BaseResponse<>(updatedReview);
        } catch (Exception e) {
            log.error(e.getMessage());

            return new BaseResponse<>(false, e.getMessage(),500, null);
        }
    }

    //리뷰 등록
    @PostMapping("/post")
    public BaseResponse<Void> newReview (@RequestBody ReviewDTO reviewDTO){

        try{
            System.out.println("컨트롤러 : 책번호 -> "+reviewDTO.getIsbn13());
            reviewService.save(reviewDTO);
            log.info("리뷰 등록 성공");

            return new BaseResponse<>();
        }catch (Exception e){
            log.error(e.getMessage());

            return new BaseResponse<>(false, e.getMessage(), 500,null);
        }
    }

    //리뷰 삭제
    @DeleteMapping("/delete/{reviewId}")
    public BaseResponse<Boolean> removeReview (@PathVariable Long reviewId) {

        System.out.println("컨트롤러 : 삭제할 리뷰 -> " + reviewId);
        boolean result= reviewService.remove(reviewId);

        return new BaseResponse<>(result);
    }

}
