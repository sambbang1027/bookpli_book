package com.example.bookpli_book.comment.controller;

import com.example.bookpli_book.comment.dto.CommentLikeDTO;
import com.example.bookpli_book.comment.service.CommentLikeService;
import com.example.bookpli_book.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/bookservice")
@RestController
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    // 댓글에 대한 좋아요 수 조회
    @GetMapping("/commentlike/{commentId}")
    public BaseResponse<Integer> likeCount (@PathVariable Long commentId){
        try {
            int likes = commentLikeService.likeCount(commentId);
            log.info("좋아요 수를 출력합니다.");
            return new BaseResponse<>(likes);
        } catch (Exception e) {
            log.error("컨트롤 : 에러 발생");
            e.printStackTrace();
            return new BaseResponse<>(0);
        }
    }

    // 좋아요 && 좋아요 취소
    @RequestMapping(value = "/commentlike/mylike", method = RequestMethod.POST)
    public BaseResponse<Boolean> mylike (@RequestBody CommentLikeDTO CommentLikeDTO){
        try {
            Boolean result = commentLikeService.commentLike(CommentLikeDTO);
            log.info("좋아요!!");
            return new BaseResponse<>(result);
        } catch (Exception e) {
            log.error("컨트롤 : 오류 발생");
            e.printStackTrace();
            return new BaseResponse<>();
        }
    }

    @GetMapping("/commentlike/checking")
    public BaseResponse<Boolean> checking ( @RequestParam Long commentId,@RequestParam Long userId){
        try{
            Boolean response = commentLikeService.checkingLike(commentId,userId);
            log.info("유저가 좋아요를 했는지 안했는지 체크하겠습니다");
            System.out.println(response);
            return new BaseResponse<>(response);
        }catch (Exception e){
            log.error("컨트롤 : 오류발생");
            e.printStackTrace();
            return new BaseResponse<>(false);
        }
    }
}
