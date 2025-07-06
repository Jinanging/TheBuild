package com.jinanging.thebuild.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jinanging.thebuild.common.FileManager;
import com.jinanging.thebuild.post.domain.Post;
import com.jinanging.thebuild.post.domain.PostImage;
import com.jinanging.thebuild.post.repository.PostImageRepository;
import com.jinanging.thebuild.post.repository.PostRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;

    public PostService(PostRepository postRepository, PostImageRepository postImageRepository) {
        this.postRepository = postRepository;
        this.postImageRepository = postImageRepository;
    }
    
   
    
    public List<Post> getPostList(){
		
		List<Post> postList = postRepository.findAll();
		
		return postList;
		
	};
    
    public Post getPost(long id) {
		Optional<Post> optionalPost = postRepository.findById(id);
		
		if(optionalPost.isPresent()) {
			return optionalPost.get();
			
		}
		else {
			return null;
				
		}
    }
    
    
    
    
    
    public void addPost(long userId, String title, String contents, MultipartFile[] images) {
        Post post = Post.builder()
                .userId(userId)
                .title(title)
                .contents(contents)
                .build();

        postRepository.save(post);

        if (images != null) {
            for (MultipartFile image : images) {
                if (image != null && !image.isEmpty()) {
                    String imagePath = FileManager.saveFile(userId, image);
                    if (imagePath != null) {
                        PostImage postImage = PostImage.builder()
                                .imagePath(imagePath)
                                .build();
                        postImage.setPost(post); // 연관관계 주입
                        postImageRepository.save(postImage);
                    }
                }
            }
        }
    }
}
