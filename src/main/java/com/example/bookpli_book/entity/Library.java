package com.example.bookpli_book.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libraryId;

    private Long userId;
    private String isbn13;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;


    @Builder
    public Library(Long libraryId, Long userId, String isbn13, String status, LocalDate startDate, LocalDate endDate) {
        this.libraryId = libraryId;
        this.userId = userId;
        this.isbn13 = isbn13;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
