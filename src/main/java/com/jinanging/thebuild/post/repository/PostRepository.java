package com.jinanging.thebuild.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jinanging.thebuild.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	public List<Post> findByUserIdOrderByIdDesc(long userId);
	
	
	@Query("""
	        SELECT DISTINCT p FROM Post p
	        LEFT JOIN FETCH p.images
	        ORDER BY p.createdAt DESC
	    """)
	    List<Post> findAllPostsWithImages();
	
	

}
