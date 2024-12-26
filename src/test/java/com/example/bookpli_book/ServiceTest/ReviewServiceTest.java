package com.example.bookpli_book.ServiceTest;

import com.example.bookpli_book.book.dto.BookDTO;
import com.example.bookpli_book.entity.Review;
import com.example.bookpli_book.review.dto.ReviewDTO;
import com.example.bookpli_book.review.feignClient.ReviewFeignClient;
import com.example.bookpli_book.review.repository.ReviewRepository;
import com.example.bookpli_book.review.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewFeignClient reviewFeignClient;

    @InjectMocks
    private ReviewService reviewService;

    private List<Review> reviews;
    private List<BookDTO> books;

    @BeforeEach
    void pre(){
        System.out.println("!@@@!#@#@!#!@!#@#!!!@!!!!!!!!!!!!!!!!");
    }

    @BeforeEach
    public void setup(){
        reviews = Arrays.asList(
                Review.builder()
                        .reviewId(1L)
                        .isbn13("9781234567890")
                        .userId(1L)
                        .reviewContent("how sweet")
                        .rating(5)
                        .build(),

                Review.builder()
                        .reviewId(2L)
                        .isbn13("9780987654321")
                        .userId(1L)
                        .reviewContent("good story")
                        .rating(5)
                        .build()
        );

        books = Arrays.asList(
                BookDTO.builder()
                        .isbn13("9781234567890")
                        .title("Book1")
                        .cover("cover1.png")
                        .author("author1")
                        .description("description1")
                        .pubdate(LocalDate.of(2024,12,26))
                        .publisher("publisher1")
                        .genre("genre1")
                        .build(),

                BookDTO.builder()
                        .isbn13("9780987654321")
                        .title("Book2")
                        .cover("cover2.png")
                        .author("author2")
                        .description("description2")
                        .pubdate(LocalDate.of(2024,12,25))
                        .publisher("publisher2")
                        .genre("genre2")
                        .build()
        );

        when(reviewRepository.findByUserId(1L)).thenReturn(reviews);
        when(reviewFeignClient.getBookByisbn(anyList())).thenReturn(books);
    }

    @Test
    void testAll(){
        List<ReviewDTO> result = reviewService.readAllByIsbn(1L);

        verify(reviewRepository, times(1)).findByUserId(1L);
        verify(reviewFeignClient,times(1)).getBookByisbn(anyList());

        result.stream().forEach(System.out::println);
    }

}
