package com.example.bookpli_book.miniroom.controller;

import com.example.bookpli_book.book.repository.BookRepository;
import com.example.bookpli_book.library.repository.LibraryRepository;
import com.example.bookpli_book.miniroom.service.MiniRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/bookservice")
public class ReadingGoalController {

    @Autowired
    private BookRepository bookrep;

    @Autowired
    private LibraryRepository libraryrep;

    @Autowired
    private MiniRoomService service;


    //독서목표 설정 status 변경
//    @PutMapping("/goal/change/{isbn13}")
//    public ResponseEntity<String> bookGoal(@PathVariable String isbn13, @RequestParam Long userId,
//                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
//
//            int update= libraryrep.setReadGoal(isbn13, userId, startDate, endDate);
//
//            if(update > 0){
//                return ResponseEntity.ok("독서목표가 설정되었습니다.");
//            }else{
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("독서 목표 설정 실패");
//            }
//    }

    //library 등록
//    @PutMapping("/goal/register/{isbn13}")
//    public ResponseEntity<String> bookGoal(@PathVariable String isbn13,
//                                           @RequestBody LibraryDTO libraryDTO) {
//        try {
//             service.saveGoal(libraryDTO, isbn13);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("도서를 찾을 수 없습니다: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알 수 없는 오류가 발생했습니다.");
//        }
//        return ResponseEntity.ok("도서 저장성공");
//    }

    //독서목표 설정 status 변경
    @PutMapping("/goal/register/{isbn13}")
    public ResponseEntity<String> bookGoal(@PathVariable String isbn13,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){

        System.out.println(startDate + " >>>>>>>>>>> " + endDate);
        int update= libraryrep.setReadGoal(isbn13,startDate, endDate);


        if(update > 0){
            return ResponseEntity.ok("독서목표가 설정되었습니다.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("독서 목표 설정 실패");
        }
    }

    //독서 목표 해제
    @DeleteMapping("/goal/{isbn13}")
    public ResponseEntity<String> dropReading(@PathVariable String isbn13,
                                              @RequestParam String status) {
        int stopRead = libraryrep.changeStatus(isbn13, status);

        if (stopRead > 0) {
            return ResponseEntity.ok("독서목표가 해제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("독서 목표 해제 실패");
        }
    }

    //독서 기간 수정
    @PutMapping("/goal/reset/{isbn13}")
    public ResponseEntity<Integer> resetDate(@PathVariable String isbn13,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        int setPeriod = libraryrep.updateDate(isbn13,startDate,endDate);
        return ResponseEntity.ok(setPeriod);
    }

}
