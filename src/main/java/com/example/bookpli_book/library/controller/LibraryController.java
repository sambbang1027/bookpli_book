package com.example.bookpli_book.library.controller;

import com.example.bookpli_book.common.response.BaseResponse;
import com.example.bookpli_book.library.dto.BookLikeDTO;
import com.example.bookpli_book.library.dto.LibraryResponseDTO;
import com.example.bookpli_book.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookservice")
public class LibraryController {
    private final LibraryService libraryService;

    /* 담기 기능 */
    @GetMapping("/library/{userId}")
    public BaseResponse<List<LibraryResponseDTO>> getMyLibrary(@PathVariable Long userId){
        List<LibraryResponseDTO> response = libraryService.getMyLibrary(userId);
        return new BaseResponse<>(response);
    }

    @GetMapping("/library/{userId}/{isbn13}")
    public BaseResponse<LibraryResponseDTO> getMyOneLibrary(@PathVariable Long userId, @PathVariable String isbn13){
        LibraryResponseDTO response = libraryService.getMyOneLibrary(userId, isbn13);
        return new BaseResponse<>(response);
    }

    @PostMapping("/library/{userId}/{isbn13}")
    public BaseResponse<LibraryResponseDTO> addWishlist(@PathVariable String isbn13, @PathVariable Long userId){
        LibraryResponseDTO response = libraryService.addWishlist(userId, isbn13);
        return new BaseResponse<>(response);
    }

    @DeleteMapping("/library/{userId}/{libraryId}")
    public BaseResponse<Void> deleteMyLibrary(@PathVariable Long libraryId, @PathVariable Long userId){
        libraryService.deleteMyLibrary(userId, libraryId);
        return new BaseResponse<>();
    }

    /* 좋아요 기능 */
    @GetMapping("/library/book/{isbn13}/{userId}")
    public BaseResponse<Long> getBookLike(@PathVariable String isbn13, @PathVariable Long userId){
        Long response = libraryService.getBookLike(userId, isbn13);
        return new BaseResponse<>(response);
    }

    @GetMapping("/library/book-like/{userId}")
    public BaseResponse<List<BookLikeDTO>> getBookLikesByUserId(@PathVariable Long userId){
        List<BookLikeDTO> response = libraryService.getBookLikesByUserId(userId);
        return new BaseResponse<>(response);
    }

    @PostMapping("/library/book-like/{userId}/{isbn13}")
    public BaseResponse<Long> addBookLike(@PathVariable String isbn13, @PathVariable Long userId){
        Long response = libraryService.addBookLike(userId, isbn13);
        return new BaseResponse<>(response);
    }

    @DeleteMapping("/library/book-like/{bookLikeId}")
    public BaseResponse<Void> deleteBookLike(@PathVariable Long bookLikeId){
        libraryService.deleteBookLike(bookLikeId);
        return new BaseResponse<>();
    }
}
