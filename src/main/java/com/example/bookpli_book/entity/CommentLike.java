package com.example.bookpli_book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentLikeId;
    private Long commentId;
    private Long userId;

    @Builder
    public CommentLike(Long commentLikeId, Long commentId, Long userId){
        this.commentLikeId = commentLikeId;
        this.commentId = commentId;
        this.userId = userId;
    }
}
