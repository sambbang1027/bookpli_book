package com.example.bookpli_book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private Long userId;
    private Long postId;
    private String commentContent;

    @CreationTimestamp
    private LocalDate commentDate;

    @Builder
    public Comment(Long commentId, Long userId, Long postId,
                   String commentContent, LocalDate commentDate){
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
    }
}
