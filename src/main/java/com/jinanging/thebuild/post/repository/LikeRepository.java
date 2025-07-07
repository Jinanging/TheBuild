package com.jinanging.thebuild.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinanging.thebuild.post.domain.Like;

import jakarta.transaction.Transactional;

public interface LikeRepository extends JpaRepository<Like, Long>{
	
	// 좋아요 눌렀다 껐다~
	boolean existsByPostIdAndUserId(Long postId, Long userId);
    
    // 카운트
    long countByPostId(Long postId);
    
    @Transactional
    void deleteByPostIdAndUserId(Long postId, Long userId);

}
