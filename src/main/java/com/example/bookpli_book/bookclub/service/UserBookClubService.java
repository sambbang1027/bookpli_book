package com.example.bookpli_book.bookclub.service;

import com.example.bookpli_book.book.dto.BookDTO;
import com.example.bookpli_book.book.service.BookApiService;
import com.example.bookpli_book.bookclub.repository.UserBookClubRepository;
import com.example.bookpli_book.book.repository.BookRepository;
import com.example.bookpli_book.bookclub.dto.BookClubDTO;
import com.example.bookpli_book.bookclub.dto.UserBookClubDTO;
import com.example.bookpli_book.entity.Book;
import com.example.bookpli_book.entity.Bookclub;
import com.example.bookpli_book.entity.UserBookclub;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserBookClubService {

    private final UserBookClubRepository userBookClubRepository;
    private final BookClubService bookClubService;
    private final BookApiService bookApiService;
    private final BookRepository bookRepository;


            // 유저가 추가한 북클럽 조회
    public List<UserBookClubDTO> readMyBookClub(Long userId) {
        List<Object[]> list = userBookClubRepository.myBookClub(userId);

        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        // isbn 추출
        List<String> isbn13List = list.stream()
                .map(objects -> (String) objects[1])  // 두 번째 요소가 isbn13
                .collect(Collectors.toList());

        // isbn13으로 책 정보를 한 번에 가져오기
        List<Object[]> books = bookRepository.findBookDetails(isbn13List);  // 한 번에 조회

        // isbn13을 키로, BookDTO를 값으로 가지는 맵 생성
        Map<String, BookDTO> bookInfoMap = books.stream()
                .collect(Collectors.toMap(
                        book -> (String) book[0],
                        book-> BookDTO.builder()
                                .isbn13((String)book[0])
                                .title((String) book[1])
                                .cover((String) book[2])
                                .description((String) book[3])
                                .author((String) book[4])
                                .build()
                ));

        // UserBookclubDTO 리스트 생성
        List<UserBookClubDTO> myClubs = list.stream()
                .map(objects -> {
                    UserBookclub userBookclub = (UserBookclub) objects[0];
                    String isbn13 = (String) objects[1];

                    // isbn13에 해당하는 BookDTO를 Map에서 가져오기
                    BookDTO bookDTO = bookInfoMap.get(isbn13);

                    // BookDTO가 존재하지 않으면 null 반환
                    if (bookDTO == null) {
                        return null;
                    }

                    // UserBookClubDTO로 변환
                    return UserBookClubDTO.builder()
                            .userClubId(userBookclub.getUserClubId())
                            .userId(userBookclub.getUserId())
                            .bookClubId(userBookclub.getBookClubId())
                            .isbn13(isbn13)
                            .title(bookDTO.getTitle())
                            .cover(bookDTO.getCover())
                            .description(bookDTO.getDescription())
                            .author(bookDTO.getAuthor())
                            .build();
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return myClubs;
    }


                //  나의 북클럽 추가
    @Transactional
    public boolean addBookClub (Long userId , String isbn13){
        // 1. 북클럽을 이미 추가 했는지 확인
        boolean isAlreadyMem = userBookClubRepository.existsByUserIdAndIsbn13(userId,isbn13);

        if(isAlreadyMem){
            System.out.println("이미 추가한 북클럽입니다");
            return false;
        }

        // 2. db에 도서 존재 여부 확인
        boolean checkBook = bookRepository.existsById(isbn13);

        if(!checkBook){ // 없으면 생성
            bookApiService.searchBook(isbn13);
        }

        // 3. 북클럽 존재 여부 확인
        BookClubDTO bookClubDTO = bookClubService.findBookClub(isbn13);

        if(bookClubDTO == null) { //없으면 생성
            bookClubService.createBookClub(isbn13);
            bookClubDTO = bookClubService.findBookClub(isbn13);
            System.out.println("북클럽 생성 "+ bookClubDTO);
        }
        Bookclub bookClub = bookClubDTO.toEntity();

        // 4. 유저의 북클럽 추가
        try {
            System.out.println("유저의 북클럽 리스트에 추가하겠습니다");
            System.out.println(bookClub.getBookClubId()+ "check bookclubid");
            UserBookclub addClub = UserBookclub.builder()
                    .userId(userId)
                    .bookClubId(bookClub.getBookClubId())
                    .build();

            if(addClub != null) {
                userBookClubRepository.save(addClub);
                System.out.println("북클럽을 추가하였습니다.");
            }
            return true;
        }catch (Exception e){
            System.out.println("서비스단 : 나의 북클럽에 등록 중 오류 발생");
            e.printStackTrace();
            return false;
        }
    }


    // 북클럽 삭제
    @Transactional
    public void deleteClub(Long userClubId){
        try {
            System.out.println("삭제할 id : " + userClubId);
            userBookClubRepository.deleteByUserClubId(userClubId);
        }catch (Exception e){
            System.out.println("서비스단 : 북클럽 삭제 중 오류 발생");
            e.printStackTrace();
        }
    }
}
