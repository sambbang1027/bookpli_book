package com.example.bookpli_book.miniroom.controller;

import com.example.bookpli_book.book.repository.BookRepository;
import com.example.bookpli_book.common.exception.BaseException;
import com.example.bookpli_book.common.response.BaseResponseStatus;
import com.example.bookpli_book.entity.Book;
import com.example.bookpli_book.entity.Library;
import com.example.bookpli_book.library.repository.LibraryRepository;
import com.example.bookpli_book.miniroom.dto.LibraryDTO;
import com.example.bookpli_book.miniroom.service.MiniRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/bookservice")
public class ReadingGoalController {

    @Autowired
    private BookRepository bookrep;

    @Autowired
    private LibraryRepository libraryrep;

    @Autowired
    private MiniRoomService service;

    //library 등록

    @PutMapping("/register/{isbn13}")
    public ResponseEntity<String> bookGoal(@PathVariable String isbn13,
                                           @RequestBody LibraryDTO libraryDTO) {

        System.out.println("요청 데이터: " + libraryDTO);
        System.out.println("PathVariable isbn13: " + isbn13);

        // 요청 데이터 검증
        if (!isbn13.equals(libraryDTO.getIsbn13())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ISBN 번호가 일치하지 않습니다.");
        }

        try {
            // ISBN 존재 여부 확인
            Optional<Library> existingLibrary = libraryrep.findByUserIdAndIsbn13(libraryDTO.getUser_id(), isbn13);

            // 존재하지 않으면 새로 추가
            if (existingLibrary.isEmpty()) {
                // Book 엔티티 조회 또는 생성
                Book book = bookrep.findById(isbn13)
                        .orElseThrow(() -> new BaseException(BaseResponseStatus.BOOK_NOT_FOUND));

                // Library 엔티티 생성
                Library library = Library.builder()
                        .userId(libraryDTO.getUser_id())
                        .isbn13(book.getIsbn13())
                        .status("wished") // 기본 상태
                        .build();
                libraryrep.save(library);


            }

            // 독서 목표 업데이트 실행
            int update = libraryrep.setReadGoal(
                    isbn13,
                    libraryDTO.getStartDate(),
                    libraryDTO.getEndDate(),
                    libraryDTO.getStatus()
            );

            if (update > 0) {
                return ResponseEntity.ok("독서목표가 설정되었습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("독서 목표 설정 실패: 조건에 맞는 데이터가 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 에러 발생: " + e.getMessage());
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
