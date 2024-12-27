package com.example.bookpli_book.post.repository;

import com.example.bookpli_book.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p ,u.userNickname, u.profilePath FROM Post p JOIN User u ON p.userId = u.userId" +
            " WHERE p.bookClubId = :bookclubId " +
            " ORDER BY p.postDate DESC ")
    List<Object[]> findByBookClubId(Long bookclubId);  //특정 북클럽 전체 게시글 조회

    @Query("SELECT p, u.userNickname, u.profilePath " +
            "FROM Post p JOIN User u ON p.userId = u.userId " +
            "WHERE p.userId = :userId AND p.bookClubId = :bookclubId " +
            "ORDER BY p.postDate DESC ")
    List<Object[]> findByUserIdAndBookClubId(Long userId, Long bookclubId); //유저의 해당 북클럽 작성 글 전체 조회

    @Query("SELECT p, u.userNickname, u.profilePath " +
            "FROM Post p JOIN User u ON p.userId = u.userId " +
            "WHERE p.postId = :postId ")
    List<Object[]> findByPost(Long postId);      // 댓글 전체 조회에 불러올 각각의 게시글

}

