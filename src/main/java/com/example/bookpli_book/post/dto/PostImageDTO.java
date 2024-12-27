package com.example.bookpli_book.post.dto;

import com.example.bookpli_book.entity.PostImage;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
public class PostImageDTO {

    private Long imageId;
    private Long postId;
    private String imageUrl;


    //dto -> entity
    public PostImage toEntity(){
        return PostImage.builder()
                .imageId(this.imageId)
                .postId(this.postId)
                .imageUrl(this.getImageUrl())
                .build();
    }
}
