package com.example.bookpli_book.review.repository;

import com.example.bookpli_book.entity.Review;
import com.example.bookpli_book.review.dto.ReviewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 유저 리뷰 조회
    List<Review> findByUserId(@Param("userId") long userId);

    //도서 리뷰 조회
    @Query("SELECT new com.example.bookpli_book.review.dto.ReviewDTO( " +
            "r.reviewId, r.userId, r.isbn13, r.reviewContent, r.rating, " +
            "u.userNickname, u.profilePath) " +
            "FROM Review r " +
            "JOIN User u ON r.userId = u.userId " +
            "JOIN Book b ON r.isbn13 = b.isbn13 " +
            "WHERE b.isbn13 = :isbn13")
    List<ReviewDTO> findByIsbn13(@Param("isbn13") String isbn13);
}
