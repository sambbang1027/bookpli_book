package com.example.bookpli_book.comment.repository;

import com.example.bookpli_book.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c, u.userNickname, u.profilePath " +
            "FROM Comment c " +
            "JOIN User u ON c.userId = u.userId " +
            "WHERE c.postId = :postId " +
            "ORDER BY c.commentDate DESC")
    List<Object[]>findByPost(@Param("postId") Long postId);        // 해당 게시글 댓글 조회

    @Query("SELECT c, u.userNickname, u.profilePath, p.bookClubId " +
            "FROM Comment c " +
            "JOIN User u ON c.userId = u.userId " +
            "JOIN Post p ON c.postId = p.postId " +
            "WHERE c.userId = :userId AND p.bookClubId = :bookClubId " +
            "ORDER BY c.commentDate DESC")
    List<Object[]>findByUserId(@Param("userId") Long userId , @Param("bookClubId") Long bookClubId); // 유저가 작성한 북클럽 글 조회



    List<Comment>findByPostId(Long postId);  // 게시글 삭제를 위한 정보 불러오기
    void deleteAllByPostId(Long postId);  // 게시글 삭제시 삭제
}
