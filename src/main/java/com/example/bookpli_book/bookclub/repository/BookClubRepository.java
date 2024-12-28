package com.example.bookpli_book.bookclub.repository;

import com.example.bookpli_book.bookclub.dto.BookClubDTO;
import com.example.bookpli_book.entity.Bookclub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookClubRepository extends JpaRepository<Bookclub, Long> {

    // 북적북적 검색창
    @Query("SELECT bc, b.title, b.cover, b.description, b.author " +
            "FROM Bookclub bc " +
            "JOIN Book b ON bc.isbn13 = b.isbn13 " +
            "WHERE b.title LIKE CONCAT('%', :key, '%') ")
    List<Object[]>findBookClubByName(@Param("key") String key);

    //북클럽 찾기
    @Query("SELECT new com.example.bookpli_book.bookclub.dto.BookClubDTO(bc, b.title, b.author, b.description, b.cover) " +
            "FROM Bookclub bc " +
            "JOIN Book b ON bc.isbn13 = b.isbn13 " +
            "WHERE b.isbn13 = :isbn13")
    BookClubDTO findBookClubByIsbn(@Param("isbn13") String isbn13);
}
