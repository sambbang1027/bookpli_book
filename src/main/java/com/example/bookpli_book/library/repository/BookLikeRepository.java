package com.example.bookpli_book.library.repository;

import com.example.bookpli_book.entity.BookLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookLikeRepository extends JpaRepository<BookLike, Long> {

    List<BookLike> findAllByUserId(Long userId);

    Optional<BookLike> findByUserIdAndIsbn13(Long userId, String isbn13);

    boolean existsByUserIdAndIsbn13(Long userId, String isbn13);
}
