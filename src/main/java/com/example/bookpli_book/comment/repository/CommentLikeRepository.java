package com.example.bookpli_book.comment.repository;

import com.example.bookpli_book.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    // counting
    @Query("SELECT COUNT(cl) FROM CommentLike cl WHERE cl.commentId = :commentId ")
    int countByCommentId(@Param("commentId") Long commentId);

    // update and delete
    Optional<CommentLike> findByUserIdAndCommentId(@Param("userId")Long userId, @Param("commentId")Long commentId);

    //checking user
    boolean existsByCommentIdAndUserId(Long commentId, Long userId);

    void deleteByCommentId(Long commentId);

    void deleteAllByCommentIdIn(List<Long> commentIds);  // 게시글 삭제 시 함께 삭제
}
