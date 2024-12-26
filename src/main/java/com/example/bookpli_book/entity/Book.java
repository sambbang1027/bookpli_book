package com.example.bookpli_book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
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


    @Builder
    public Book(String isbn13, String title, String author, String description, LocalDate pubdate, String publisher, String cover, Integer startindex, String genre) {
        this.isbn13 = isbn13;
        this.title = title;
        this.author = author;
        this.description = description;
        this.pubdate = pubdate;
        this.publisher = publisher;
        this.cover = cover;
        this.startindex = startindex;
        this.genre = genre;
    }
}
