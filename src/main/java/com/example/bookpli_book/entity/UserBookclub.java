package com.example.bookpli_book.entity;

import com.rabbitmq.client.AMQP;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBookclub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userclub_id")
    private Long userClubId;

    private Long userId;

    @Column(name = "bookclub_id", insertable = false, updatable = false)
    private Long bookClubId;


    public UserBookclub (Long userClubId, Long userId, Long bookClubId){
        this.userClubId = userClubId;
        this.userId = userId;
        this.bookClubId = bookClubId;
    }

}
