package com.example.bookpli_book.mypage.service;

import com.example.bookpli_book.common.exception.BaseException;
import com.example.bookpli_book.common.response.BaseResponseStatus;
import com.example.bookpli_book.entity.User;
import com.example.bookpli_book.mypage.dto.UserDTO;
import com.example.bookpli_book.mypage.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class MypageService {
    private final UserRepository userRepository;

    public MypageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserProfile(Long userId) {
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
        return UserDTO.fromEntity(user);
    }

    @Transactional
    public void patchNickName(Map<String, Object> request) {
        Long userId = ((Number) request.get("userId")).longValue(); // 안전한 타입 변환
        String nickName = (String) request.get("userNickname");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));

        user.updateNickName(nickName);
    }

    public UserDTO duplicateCheckNickname(String nickName) {
        Optional<User> user = userRepository.findByUserNickname(nickName);
        return user.map(UserDTO::fromEntity).orElse(null);
    }

    public Optional<User> findBySpotifyId(String spotifyId) {
        Optional<User> user = userRepository.findBySpotifyId(spotifyId);
        return user;
    }
}
