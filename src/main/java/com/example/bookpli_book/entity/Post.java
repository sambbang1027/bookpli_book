package com.example.bookpli_book.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private Long userId;

    @Column(name = "bookclub_id")
    private Long bookClubId;
    private String postContent;

    @CreationTimestamp
    private LocalDate postDate;

    @Builder
    public Post (Long postId, Long userId, Long bookClubId,
                 String postContent, LocalDate postDate){
        this.postId = postId;
        this.userId = userId;
        this.bookClubId = bookClubId;
        this.postContent = postContent;
        this.postDate = postDate;
    }
}
