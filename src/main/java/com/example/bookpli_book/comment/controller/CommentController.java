package com.example.bookpli_book.comment.controller;

import com.example.bookpli_book.comment.dto.CommentDTO;
import com.example.bookpli_book.comment.service.CommentService;
import com.example.bookpli_book.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping ("/bookservice")
@RestController
public class CommentController {

    private final CommentService commentService;

    // 게시글 댓글 조회
    @GetMapping("/comment/post/{postId}")
    public BaseResponse<List<CommentDTO>> readAllByPost(@PathVariable Long postId){
        List<CommentDTO> comments = commentService.readCommentByPost(postId);
        return new BaseResponse<>(comments);
    }

    // 유저 전체 댓글 조회
    @GetMapping("/comment/user")
    public BaseResponse<List<CommentDTO>> readAllByUser(@RequestParam Long userId, @RequestParam Long bookClubId){
        List<CommentDTO> comments = commentService.readCommentByUser(userId, bookClubId);
        return new BaseResponse<>(comments);
    }

    // 댓글 등록
    @PostMapping("/comment/insert")
    public BaseResponse<Boolean> registerComment (@RequestBody CommentDTO commentDTO){
        try {
          boolean response= commentService.insert(commentDTO);
            log.info("댓글 수정 성공");
            return new BaseResponse<>(response);
        }catch (Exception e){
            log.error("등록 중 에러 발생");
            e.printStackTrace();
            return new BaseResponse<>(false);
        }
    }

    // 댓글 수정
    @PutMapping("/comment/edit")
    public BaseResponse<Boolean> editComment (@RequestBody CommentDTO commentDTO){
        try{
            boolean response = commentService.update(commentDTO);
            log.info("댓글 수정 성공");
            return new BaseResponse<>(response);
        }catch (Exception e){
            log.error("등록 중 에러 발생");
            e.printStackTrace();
            return new BaseResponse<>(false);
        }
    }

    // 댓글 삭제
    @DeleteMapping("/comment/delete")
    public BaseResponse<Boolean> deleteComment(@RequestParam Long commentId){
        try{
            boolean response = commentService.delete(commentId);
            log.info("삭제 성공");
            return new BaseResponse<>(response);
        } catch (Exception e) {
            log.error("삭제 중 오류 발생");
            e.printStackTrace();
            return new BaseResponse<>(false);
        }
    }
}
