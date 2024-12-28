package com.example.bookpli_book.bookclub.service;

import com.example.bookpli_book.book.repository.BookRepository;
import com.example.bookpli_book.book.service.BookApiService;
import com.example.bookpli_book.bookclub.dto.BookClubDTO;
import com.example.bookpli_book.bookclub.repository.BookClubRepository;
import com.example.bookpli_book.entity.Book;
import com.example.bookpli_book.entity.Bookclub;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookClubService {

    private final BookClubRepository bookClubRepository;
    private final BookRepository bookRepository;

    private final BookApiService bookApiService;

    // 북클럽 생성
    @Transactional
    public void createBookClub(String isbn13) {
        try {

            //해당 도서가 있는지 체크
            bookApiService.searchBook(isbn13);
            Optional<Book> checkBook = bookRepository.findById(isbn13);

            if (checkBook.isEmpty()) {
                throw new NoSuchElementException("해당 도서를 찾을 수 없습니다");
            }

            // BookClub 존재 여부 확인
            BookClubDTO existingClub = bookClubRepository.findBookClubByIsbn(isbn13);
            if (existingClub != null) {
                System.out.println("이미 존재하는 북클럽입니다.");
                return;
            }
            // BookClub 생성
            Book book = checkBook.get();
            Bookclub bookClub = Bookclub.builder()
                    .isbn13(book.getIsbn13())
                    .build();

            bookClubRepository.save(bookClub);
            System.out.println("새로운 북클럽 생성!");

        } catch (Exception e) {
            System.out.println("북클럽 생성 실패!!!!");
            e.printStackTrace();
        }
    }


        //  특정 북클럽 찾기
        public BookClubDTO findBookClub (String isbn13){

            //해당 북클럽이 존재하는지 확인
            BookClubDTO getClub = bookClubRepository.findBookClubByIsbn(isbn13);
            return getClub;
        }


        // 북클럽 리스트 찾기
        public List<BookClubDTO> findBookClubList (String key){

            List<Object[]> list = bookClubRepository.findBookClubByName(key);
            if (list.isEmpty()) {
                throw new NoSuchElementException("검색어에 해당하는 도서가 없습니다");
            }

            List<BookClubDTO> bookclubs = list.stream()
                    .map(row -> BookClubDTO.builder()
                            .bookClubId(((Bookclub) row[0]).getBookClubId())
                            .isbn13(((Bookclub) row[0]).getIsbn13())
                            .title((String) row[1])
                            .author((String) row[4])
                            .cover((String) row[2])
                            .description((String) row[3])
                            .build())
                    .collect(Collectors.toList());
            return bookclubs;
        }
    }
