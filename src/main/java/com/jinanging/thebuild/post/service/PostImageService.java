package com.jinanging.thebuild.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinanging.thebuild.post.domain.PostImage;
import com.jinanging.thebuild.post.repository.PostImageRepository;

@Service
public class PostImageService {
	
	private final PostImageRepository postImageRepository;

    
    public PostImageService(PostImageRepository postImageRepository) {
        this.postImageRepository = postImageRepository;
    }
    	
    public List<PostImage> getPostImageList(long userId){
		
		List<PostImage> postImageList = postImageRepository.selectPostImageByUserId(userId);
		
		return postImageList;
		
	};
    
    
    public PostImage getPostImage(long id) {
		Optional<PostImage> optionalPost = postImageRepository.findById(id);
		
		if(optionalPost.isPresent()) {
			return optionalPost.get();
			
		}
		else {
			return null;
				
		}
		
	}
	
  
}

