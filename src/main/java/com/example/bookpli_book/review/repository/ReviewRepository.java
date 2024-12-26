package com.example.bookpli_book.review.repository;

import com.example.bookpli_book.entity.Review;
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
    List<Review> findByIsbn13(@Param("isbn13") String isbn13);
}
