package com.example.bookpli_book.ServiceTest;

import com.example.bookpli_book.book.dto.BookDTO;
import com.example.bookpli_book.book.feignClient.BookFeignClient;
import com.example.bookpli_book.entity.Review;
import com.example.bookpli_book.user.dto.UserDTO;
import com.example.bookpli_book.user.feignClient.UserFeignClient;
import com.example.bookpli_book.review.dto.ReviewDTO;
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
    private BookFeignClient bookFeignClient;

    @Mock
    private UserFeignClient userFeignClient;

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
        when(bookFeignClient.getBookByisbn(anyList())).thenReturn(books);
    }

    @Test
    void testAll(){
        List<ReviewDTO> result = reviewService.readAllByIsbn(1L);

        verify(reviewRepository, times(1)).findByUserId(1L);
        verify(bookFeignClient,times(1)).getBookByisbn(anyList());

        result.stream().forEach(System.out::println);
    }


    @Test
    void test(){
        reviews = Arrays.asList(
                Review.builder()
                        .reviewId(3L)
                        .isbn13("9780987654321")
                        .userId(2L)
                        .reviewContent("how sweet")
                        .rating(4)
                        .build(),

                Review.builder()
                        .reviewId(2L)
                        .isbn13("9780987654321")
                        .userId(1L)
                        .reviewContent("good story")
                        .rating(5)
                        .build()
        );
        List<UserDTO> users = Arrays.asList(
                UserDTO.builder()
                        .userId(1L)
                        .spotifyId("spotify123")
                        .email("user1@email.com")
                        .userNickname("바다")
                        .displayName("김땡땡")
                        .profilePath("user1.png")
                        .role("user")
                        .build(),

                UserDTO.builder()
                        .userId(2L)
                        .spotifyId("spotify456")
                        .email("user2@email.com")
                        .userNickname("땅")
                        .displayName("이지금")
                        .profilePath("user2.png")
                        .role("user")
                        .build()
        );


        when(reviewRepository.findByIsbn13("9780987654321")).thenReturn(reviews);
        when(userFeignClient.getInfoForReview(anyList())).thenReturn(users);

        List<ReviewDTO> result = reviewService.readAllByUser("9780987654321");

        verify(reviewRepository,times(1)).findByIsbn13("9780987654321");
        verify(userFeignClient,times(1)).getInfoForReview(anyList());

        result.stream().forEach(System.out::println);

    }
}
