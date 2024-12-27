package com.example.bookpli_book.bookclub.dto;


import com.example.bookpli_book.entity.UserBookclub;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class UserBookClubDTO {
    private Long userClubId;
    private Long userId;
    private Long bookClubId;
    private String isbn13;

    private String title;
    private String cover;

    private String author;
    private String description;

        // dto -> entity
    public UserBookclub toEntity (){
        return UserBookclub.builder()
                .userId(this.userId)
                .bookClubId(this.bookClubId)
                .build();
    }


            // entity -> dto
    public static UserBookClubDTO fromEntity(UserBookclub userBookclub, String isbn13,
                                             String title, String cover, String author, String description){
        return UserBookClubDTO.builder()
                .userClubId(userBookclub.getUserClubId())
                .userId(userBookclub.getUserId())
                .bookClubId(userBookclub.getBookClubId())
                .isbn13(isbn13)
                .title(title)
                .cover(cover)
                .author(author)
                .description(description)
                .build();
    }

}
