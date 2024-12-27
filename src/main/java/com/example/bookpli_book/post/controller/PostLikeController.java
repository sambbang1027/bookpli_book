package com.example.bookpli_book.post.controller;

import com.example.bookpli_book.common.response.BaseResponse;
import com.example.bookpli_book.post.dto.PostLikeDTO;
import com.example.bookpli_book.post.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/postlike")
@RestController
public class PostLikeController {

    private final PostLikeService postLikeService;

    // 게시글에 대한 좋아요 수 조회
    @GetMapping("/{postId}")
    public BaseResponse<Integer>likeCount (@PathVariable Long postId){
        try {
            int likes = postLikeService.likeCount(postId);
            log.info("좋아요 수를 출력합니다.");
            return new BaseResponse<>(likes);
        } catch (Exception e) {
            log.error("컨트롤 : 에러 발생");
            e.printStackTrace();
            return new BaseResponse<>(0);
        }
    }

        // 좋아요 && 좋아요 취소
    @RequestMapping(value = "/mylike", method = RequestMethod.POST)
    public BaseResponse<Boolean> mylike (@RequestBody PostLikeDTO postLikeDTO){
        try {
            Boolean result = postLikeService.postLike(postLikeDTO);
            log.info("좋아요!!");
            return new BaseResponse<>(result);
        } catch (Exception e) {
            log.error("컨트롤 : 오류 발생");
            e.printStackTrace();
            return new BaseResponse<>();
        }
    }

    // 어떤 게시글에 유저가 좋아요 했는지 체크
    @GetMapping("/checkingLike")
    public BaseResponse<Boolean>checking(@RequestParam Long postId, @RequestParam Long userId){
        System.out.println("postId : "+postId + "userID : "+userId);
        boolean response =  postLikeService.checking(postId,userId);
        System.out.println("체킹 값 "+response);
        return new BaseResponse<>(response);
    }
}
