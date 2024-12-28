package com.example.bookpli_book.review.service;


import com.example.bookpli_book.book.dto.BookDTO;
import com.example.bookpli_book.book.feignClient.BookFeignClient;
import com.example.bookpli_book.entity.Review;
import com.example.bookpli_book.user.dto.UserDTO;
import com.example.bookpli_book.user.feignClient.UserFeignClient;
import com.example.bookpli_book.review.dto.ReviewDTO;
import com.example.bookpli_book.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ReviewService {

    private final BookFeignClient bookFeignClient;
    private final UserFeignClient userFeignClient;
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
        List<BookDTO>books = bookFeignClient.getBookByisbn(isbns);

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

                // 도서 전체 리뷰
    public List<ReviewDTO>readAllByUser(String isbn13){
        // 해당 도서에 대한 전체 리뷰
        List<Review> reviews = reviewRepository.findByIsbn13(isbn13);

        if(reviews.isEmpty()){
            return Collections.emptyList();
        }

        //userId 추출
        List<Long> userIds = reviews.stream()
                .map(review -> review.getUserId())
                .collect(Collectors.toList());

        // 유저 정보 가져오기
        List<UserDTO> users = userFeignClient.getInfoForReview(userIds);

        Map<Long, UserDTO> usermap = users.stream()
                .collect(Collectors.toMap(UserDTO::getUserId, user->user));

        // 유저정보 + 리뷰
        return reviews.stream()
                .map(review -> {
                    UserDTO userdto = usermap.get(review.getUserId());

                    return ReviewDTO.builder()
                            .reviewId(review.getReviewId())
                            .isbn13(review.getIsbn13())
                            .userId(review.getUserId())
                            .reviewContent(review.getReviewContent())
                            .rating(review.getRating())
                            .userNickname(userdto.getUserNickname())
                            .profilePath(userdto.getProfilePath())
                            .build();
                }).collect(Collectors.toList());
    }


    // 리뷰 수정
    @Transactional
    public ReviewDTO update(ReviewDTO reviewDTO) {
        try {
            Review old = reviewDTO.toEntity();
            reviewRepository.save(old);
            Review newOne = reviewRepository.findById(reviewDTO.getReviewId())
                    .orElseThrow(()-> new NoSuchElementException("찾는 데이터가 없습니다."));

            // 변경된 리뷰를 DTO로 변환하여 반환
            return ReviewDTO.fromEntity(newOne);
        }catch (Exception e){
            System.out.println("서비스단: 오류 발생");
            e.printStackTrace();
            return null;
        }
    }

    // 리뷰 등록
    @Transactional
    public void save(ReviewDTO reviewDTO){
        try{
            Review add = reviewDTO.toEntity();
            System.out.println("서비스단 : 엔티티 변환 성공");
            reviewRepository.save(add);
            System.out.println("서비스단 : 엔티티에 저장 성공");
        }catch (Exception e){
            System.out.println("리뷰 등록 중 오류가 발생하였습니다." +e.getMessage());
        }
    }

    // 리뷰 삭제
    public Boolean remove (Long reviewId){
        try {
            reviewRepository.deleteById(reviewId);

            Optional<Review> list = reviewRepository.findById(reviewId);

            return list.isEmpty(); // 리뷰가 존재하지 않으면 true 반환
        }catch (Exception e){
            System.out.println("서비스단 : 리뷰 삭제 중 오류 발생  reviewId -->"+ reviewId);
            e.printStackTrace();
            return  false;
        }
    }

}
