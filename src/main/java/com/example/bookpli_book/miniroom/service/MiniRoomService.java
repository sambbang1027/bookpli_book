package com.example.bookpli_book.miniroom.service;

import com.example.bookpli_book.book.repository.BookRepository;
import com.example.bookpli_book.library.repository.BookLikeRepository;
import com.example.bookpli_book.library.repository.LibraryRepository;
import com.example.bookpli_book.miniroom.dto.BookResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class MiniRoomService {

    private final String TTB_KEY = "ttbsumini0911820002";
    private final String BASE_URL = "http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx";

    @Autowired
    private BookRepository bookrep;

    @Autowired
    private LibraryRepository librep;

    @Autowired
    private BookLikeRepository blrep;

    //미니룸 - 도서리스트 조회
    public List<BookResponseDTO> getBookList(Long userId, String status) {
        if ("reading".equalsIgnoreCase(status)) {
            return bookrep.readBookList(userId);
        } else if ("wished".equalsIgnoreCase(status)) {
            return bookrep.addBookList(userId);
        } else if("completed".equalsIgnoreCase(status)) {
            return bookrep.finishBookList(userId);
        } else{
            throw new IllegalArgumentException("Invalid status: " + status);
        }
    }

    //도서 저장
//    public void saveGoal(LibraryDTO libraryDTO, String isbn13) {
//        // 도서 검색
//        Book book = searchBook(isbn13);
//        if (book == null) {
//            throw new IllegalArgumentException("해당 ISBN의 도서를 찾을 수 없습니다.");
//        }
//
//        Library newLibrary = libraryDTO.toEntity(book); // DTO를 엔티티로 변환
//        librep.save(newLibrary);                             // 새 데이터 저장
//
//        }

    //도서완독
    public int clearRead(String isbn13, String status){
        return librep.completeBook(isbn13, status);
    }

    //도서 실패
    public int failReading(String isbn13){
        return librep.changeFail(isbn13);
    }
}
