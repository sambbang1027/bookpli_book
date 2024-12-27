package com.example.bookpli_book.mypage.controller;

import com.example.bookpli_book.common.response.BaseResponse;
import com.example.bookpli_book.mypage.dto.UserDTO;
import com.example.bookpli_book.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MypageController {
    private final MypageService mypageService;

    @GetMapping("{userId}")
    public BaseResponse<UserDTO> getUserProfile(@PathVariable Long userId){
        UserDTO response = mypageService.getUserProfile(userId);
        return new BaseResponse<>(response);
    }
    @PatchMapping
    public BaseResponse<Void> patchNickName(@RequestBody Map<String, Object> request){
        mypageService.patchNickName(request); 
        return new BaseResponse<>();
    }

    @GetMapping("/nickname/{nickName}")
    public BaseResponse<UserDTO> duplicateCheckNickname(@PathVariable String nickName){
        UserDTO response = mypageService.duplicateCheckNickname(nickName);
        return new BaseResponse<>(response);
    }






}
