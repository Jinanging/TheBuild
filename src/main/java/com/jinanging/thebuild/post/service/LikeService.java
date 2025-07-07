package com.jinanging.thebuild.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinanging.thebuild.post.domain.Like;
import com.jinanging.thebuild.post.dto.PostDto;
import com.jinanging.thebuild.post.repository.LikeRepository;

@Service
public class LikeService {
    
    private final LikeRepository likeRepository;
    
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    /**
     * 게시글들의 좋아요 수와 좋아요 상태를 가져오는 메서드
     */
    public Map<Long, PostDto> getLikeCountsAndStatusByPostIds(List<Long> postIds, Long userId) {
        Map<Long, PostDto> result = new HashMap<>();
        for (Long postId : postIds) {
            // 좋아요 수
            long likeCount = countLikes(postId);
            // 좋아요 상태 (현재 사용자가 좋아요를 눌렀는지 여부)
            boolean isLiked = likeRepository.existsByPostIdAndUserId(postId, userId);
            // PostDto에 좋아요 수와 상태를 설정
            PostDto postDto = new PostDto();
            postDto.setPostId(postId);
            postDto.setLikeCount(likeCount);
            postDto.setLiked(isLiked); // isLiked 값 설정
            result.put(postId, postDto);
        }
        return result;
    }

    /**
     * 좋아요 토글 (좋아요/취소)
     */
    @Transactional
    public boolean toggleLike(Long postId, Long userId) {
        boolean liked = likeRepository.existsByPostIdAndUserId(postId, userId);
        if (liked) {
            likeRepository.deleteByPostIdAndUserId(postId, userId);
            return false; // 좋아요 취소됨
        } else {
            Like like = new Like();
            like.setPostId(postId);
            like.setUserId(userId);
            likeRepository.save(like);
            return true; // 좋아요 됨
        }
    }
    
    /**
     * 특정 게시글의 좋아요 수를 계산하는 메서드
     */
    public long countLikes(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}
