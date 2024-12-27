package com.example.bookpli_book.bookclub.repository;


import com.example.bookpli_book.entity.UserBookclub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBookClubRepository extends JpaRepository<UserBookclub, Long> {

    //유저가 등록한 북클럽 리스트 조회
    @Query("SELECT ubc, bc.isbn13 FROM UserBookclub ubc " +
            "JOIN Bookclub bc ON ubc.bookClubId = bc.bookClubId " +
            "WHERE ubc.userId = :userId")
    List<Object[]> myBookClub(@Param("userId") Long userId);

    @Query("SELECT COUNT(ub) > 0 FROM UserBookclub ub WHERE ub.userId = :userId AND ub.bookClubId IN " +
            "(SELECT bc.bookClubId FROM Bookclub bc WHERE bc.isbn13 = :isbn13)")
    boolean existsByUserIdAndIsbn13(@Param("userId") Long userId, @Param("isbn13") String isbn13);

    void deleteByUserClubId(Long userClubId);
}
