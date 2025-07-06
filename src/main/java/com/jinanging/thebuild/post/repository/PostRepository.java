package com.jinanging.thebuild.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinanging.thebuild.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	public List<Post> findByUserIdOrderByIdDesc(long userId);
	
	

}
