package com.example.bookpli_book.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBookclub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userclub_id")
    private Long userClubId;

    private Long userId;

    @Column(name = "bookclub_id", insertable = false, updatable = false)
    private Long bookClubId;
}
