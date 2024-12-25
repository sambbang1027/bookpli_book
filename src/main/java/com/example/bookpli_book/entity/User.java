package com.example.bookpli_book.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String spotifyId;

    private String displayName;
    private String userNickname;

    @Column(unique = true)
    private String email;

    private String profilePath;

    private String refreshToken;

    private String role;
}
