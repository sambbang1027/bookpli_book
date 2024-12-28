package com.example.bookpli_book.miniroom.controller;

import com.example.bookpli_book.miniroom.dto.BookResponseDTO;
import com.example.bookpli_book.miniroom.service.MiniRoomService;
import com.example.bookpli_book.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookservice")
public class MiniroomController {

    @Autowired
    private MiniRoomService miniRoomService;

    //담은 도서 리스트
    @GetMapping("/miniroom/user/{userId}/book")
    public ResponseEntity<List<BookResponseDTO>> getBookList(@PathVariable Long userId,
                                                             @RequestParam(required = false, defaultValue = "wished") String status){
        List<BookResponseDTO> bookList= miniRoomService.getBookList(userId, status);
        return ResponseEntity.ok(bookList);
    }

    //완독도서 처리
    @PutMapping("/miniroom/clear/{isbn13}")
    public ResponseEntity<Integer> compReading(@PathVariable String isbn13, @RequestParam("status") String status){
        int updateStatus= miniRoomService.clearRead(isbn13,status);
        return ResponseEntity.ok(updateStatus);
    }

    //도서 실패처리
    @PutMapping("/miniroom/fail/{isbn13}")
    public ResponseEntity<Integer> changeToFail(@PathVariable String isbn13){
        int failRead= miniRoomService.failReading(isbn13);
        return ResponseEntity.ok(failRead);
    }

}
