package com.example.bookpli_book.comment.service;

import com.example.bookpli_book.comment.dto.CommentLikeDTO;
import com.example.bookpli_book.comment.repository.CommentLikeRepository;
import com.example.bookpli_book.entity.CommentLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;

    // 좋아요 수 조회
    public int likeCount (Long commentId) {

        int counting = commentLikeRepository.countByCommentId(commentId);

        return counting;
    }

    // 좋아요 && 취소 기능
    @Transactional
    public boolean commentLike (CommentLikeDTO commentLikeDTO) {

        Long userId = commentLikeDTO.getUserId();
        Long commentId = commentLikeDTO.getCommentId();

        Optional<CommentLike> counting = commentLikeRepository.findByUserIdAndCommentId(userId, commentId);
        if (counting.isPresent()) {
            commentLikeRepository.delete(counting.get());
            return false;
        } else {
            CommentLike newLike = commentLikeDTO.toEntity();
            commentLikeRepository.save(newLike);
            return true;
        }
    }

    // 해당 댓글에 좋아요 되어있는지 체크
    public boolean checkingLike ( Long commentId, Long userId) {
        boolean response = false;
        if (userId != null && commentId != null) {
            response = commentLikeRepository.existsByCommentIdAndUserId(commentId, userId);
        }
        return response;
    }
}
