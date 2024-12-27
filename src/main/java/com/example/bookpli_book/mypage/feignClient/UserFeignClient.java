package com.example.bookpli_book.mypage.feignClient;

import com.example.bookpli_book.common.response.BaseResponse;
import com.example.bookpli_book.mypage.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:9001")
public interface UserFeignClient {

    @GetMapping("/user/review")
    public List<UserDTO> getInfoForReview(@RequestParam  List<Long> userIds);

}
