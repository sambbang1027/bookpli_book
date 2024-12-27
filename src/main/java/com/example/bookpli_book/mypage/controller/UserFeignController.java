package com.example.bookpli_book.mypage.controller;

import com.example.bookpli_book.common.response.BaseResponse;
import com.example.bookpli_book.mypage.dto.UserDTO;
import com.example.bookpli_book.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserFeignController {

    private final MypageService mypageService;

    @GetMapping("/review")
    public BaseResponse<List<UserDTO>> getUserInfoForReview(List<Long> userIds){
        List<UserDTO> userDTOS = userIds.stream()
                .map(user -> mypageService.getUserProfile(user))
                .collect(Collectors.toList());
        return new BaseResponse<>(userDTOS);
    }
}
