package com.example.bookpli_book.post.service;

import com.example.bookpli_book.entity.PostLike;
import com.example.bookpli_book.post.dto.PostLikeDTO;
import com.example.bookpli_book.post.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;

    // 좋아요 수 조회
    public int likeCount (Long postId) {

            int counting = postLikeRepository.countByPostId(postId);

            return counting;

    }

    // 좋아요 && 취소 기능
    public boolean postLike (PostLikeDTO postLikeDTO) {

        Long userId = postLikeDTO.getUserId();
        Long postId = postLikeDTO.getPostId();

        Optional<PostLike> counting = postLikeRepository.findByUserIdAndPostId(userId, postId);
        if (counting.isPresent()) {
            postLikeRepository.delete(counting.get());
            return false;
        } else {
            PostLike newLike = postLikeDTO.toEntity();
            postLikeRepository.save(newLike);
            return true;
        }
    }

    public boolean checking(Long postId, Long userId){
        boolean response = false;
        if(postId!=null && userId !=null){
            response = postLikeRepository.existsByPostIdAndUserId(postId, userId);
        }
        return  response;
    }
}
