package com.example.bookpli_book.user.feignClient;

import com.example.bookpli_book.user.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "authservice", url = "http://localhost:9003")
public interface UserFeignClient {

    @GetMapping("/user/review")
    public List<UserDTO> getInfoForReview(@RequestParam  List<Long> userIds,
                                          @RequestHeader("Authorization") String token);

}
