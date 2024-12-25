package com.example.bookpli_book.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
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
}
