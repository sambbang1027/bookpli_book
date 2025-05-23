package com.example.bookpli_book.bookclub.dto;

import com.example.bookpli_book.entity.Book;
import com.example.bookpli_book.entity.Bookclub;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class BookClubDTO {
    private Long bookClubId;

    private String isbn13;
    private String title;
    private String author;
    private String cover;
    private String description;


    // JPQL에서 사용할 생성자
    public BookClubDTO(Bookclub bookClub, String title, String author, String description, String cover) {
        this.bookClubId = bookClub.getBookClubId();
        this.isbn13 = bookClub.getIsbn13();
        this.title = title;
        this.author = author;
        this.cover = cover;
        this.description = description;
    }

        // DTO -> Entity 변환
    public Bookclub toEntity(){
        return Bookclub.builder()
                .bookClubId(this.bookClubId)
                .isbn13(this.isbn13)
                .build();
    }

        // Entity -> DTO 변환
    public static BookClubDTO fromEntity(Bookclub bookClub,String title,
                                         String author, String cover, String description){
        return BookClubDTO.builder()
                .bookClubId(bookClub.getBookClubId())
                .isbn13(bookClub.getIsbn13())
                .title(title)
                .author(author)
                .cover(cover)
                .description(description)
                .build();
    }
}
