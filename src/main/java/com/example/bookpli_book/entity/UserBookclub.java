package com.example.bookpli_book.entity;

import com.rabbitmq.client.AMQP;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBookclub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userclub_id")
    private Long userClubId;

    private Long userId;

    @Column(name = "bookclub_id")
    private Long bookClubId;


    @Builder
    public UserBookclub ( Long userId, Long bookClubId){
        this.userId = userId;
        this.bookClubId = bookClubId;
    }

}
