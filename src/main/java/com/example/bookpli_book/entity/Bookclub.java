package com.example.bookpli_book.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookclub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookclub_id")
    private Long bookClubId;

    private String isbn13;

    public Bookclub(Long bookClubId, String isbn13){
         this.bookClubId = bookClubId;
         this.isbn13 = isbn13;

    }
}
