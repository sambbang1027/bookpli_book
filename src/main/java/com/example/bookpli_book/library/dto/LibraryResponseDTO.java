package com.example.bookpli_book.library.dto;

import com.example.bookpli_book.entity.Book;
import com.example.bookpli_book.entity.Library;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class LibraryResponseDTO {
    private Long libraryId;
    private Long userId;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;

    private String isbn13;
    private String title;
    private String author;
    private String cover;
    private Integer startindex;

    // DTO -> Entity
    public Library toEntity() {
        return Library.builder()
                .userId(this.userId)
                .status(this.status)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .isbn13(this.isbn13)
                .build();
    }

    //Entity -> DTO
    public static LibraryResponseDTO fromEntity(Library library, Book book) {
        return LibraryResponseDTO.builder()
                .libraryId(library.getLibraryId())
                .userId(library.getUserId())
                .status(library.getStatus())
                .startDate(library.getStartDate())
                .endDate(library.getEndDate())
                .isbn13(book.getIsbn13())
                .title(book.getTitle())
                .author(book.getAuthor())
                .cover(book.getCover())
                .startindex(book.getStartindex())
                .build();
    }
}
