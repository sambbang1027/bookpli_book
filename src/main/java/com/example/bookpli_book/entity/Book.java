package com.example.bookpli_book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id
    private String isbn13;

    private String title;
    private String author;
    private String description;
    private LocalDate pubdate;

    private String publisher;
    private String cover;
    private Integer startindex;
    private String genre;

}
