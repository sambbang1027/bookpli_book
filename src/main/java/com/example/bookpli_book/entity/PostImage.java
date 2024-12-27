package com.example.bookpli_book.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "postimage")
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private Long postId;
    private String imageUrl;

    @Builder
    public PostImage(Long imageId, Long postId, String imageUrl){
        this.imageId = imageId;
        this.postId = postId;
        this.imageUrl = imageUrl;
    }
}
