package com.jinanging.thebuild.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jinanging.thebuild.post.domain.Comment;
import com.jinanging.thebuild.post.domain.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    
    List<Comment> findByPostIdOrderByCreatedAtAsc(Long postId);
    
    @Query("SELECT p FROM Post p WHERE p.id = :postId")  // pk인 id로 조회
    Post findPostById(@Param("postId") Long postId);

}
