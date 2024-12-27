package com.example.bookpli_book.mypage.repository;

import com.example.bookpli_book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySpotifyId(String spotifyUserId);

    Optional<User> findByUserNickname (String nickName);

    List<User> findByUserId(Long userId);
}
