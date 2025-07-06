package com.jinanging.thebuild.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jinanging.thebuild.post.domain.Post;
import com.jinanging.thebuild.post.domain.PostImage;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
	
	
	 @Query("""
		        SELECT pi FROM PostImage pi
		        JOIN pi.post p
		        WHERE p.userId = :userId
		        ORDER BY pi.id DESC
		    """)
		List<PostImage> selectPostImageByUserId(@Param("userId") Long userId);

	

}
