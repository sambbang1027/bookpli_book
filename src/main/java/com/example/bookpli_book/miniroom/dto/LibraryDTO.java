package com.example.bookpli_book.miniroom.dto;

import com.example.bookpli_book.entity.Book;
import com.example.bookpli_book.entity.Library;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class LibraryDTO {
    @JsonProperty("userId")
    private Long user_id;
    private String isbn13;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;


    // DTO -> Entity
    public Library toEntity() {
        return Library.builder()
                .userId(this.user_id)
                .isbn13(this.isbn13)
                .status(this.status)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .build();
    }

    // Entity -> DTO
    public static LibraryDTO fromEntity(Library library) {
        return LibraryDTO.builder()
                .user_id(library.getUserId())
                .isbn13(library.getIsbn13())
                .status(library.getStatus())
                .startDate(library.getStartDate())
                .endDate(library.getEndDate())
                .build();
    }
}
