package com.jinanging.thebuild.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinanging.thebuild.post.dto.PostDto;
import com.jinanging.thebuild.post.service.LikeService;
import com.jinanging.thebuild.post.service.PostDtoService;
import com.jinanging.thebuild.post.service.PostImageService;
import com.jinanging.thebuild.post.service.PostService;
import com.jinanging.thebuild.user.service.UserService;

@RequestMapping("/post")
@Controller
public class PostController {
	
	private final PostImageService postImageService;
	private final PostService postService;
	private final UserService userService; 
	private final LikeService likeService;
	private final PostDtoService postDtoService;

	public PostController(PostImageService postImageService, PostService postService,
	                      UserService userService, LikeService likeService,
	                      PostDtoService postDtoService) {
	    this.postImageService = postImageService;
	    this.postService = postService;
	    this.userService = userService;
	    this.likeService = likeService;
	    this.postDtoService = postDtoService;
	}

	@GetMapping("/list-view")
	public String postList(Model model) {
	    List<PostDto> postDtos = postDtoService.getAllPostDtos();
	    model.addAttribute("posts", postDtos);
	    return "post/list";
	}
    
    
	
	@GetMapping("/create-view")
	public String postCreate() {
		return "post/create";
	}
	
	
	
	
	@GetMapping("/detail-view")
	public String postDetail() {
		return "post/detail";
	}
	

}
