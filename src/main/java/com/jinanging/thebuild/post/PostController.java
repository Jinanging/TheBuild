package com.jinanging.thebuild.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinanging.thebuild.post.domain.Post;
import com.jinanging.thebuild.post.service.PostImageService;
import com.jinanging.thebuild.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {
	
	private final PostImageService postImageService;
	private final PostService postService;

	public PostController(PostImageService postImageService, PostService postService) {
		this.postImageService = postImageService;
		this.postService = postService;
	}
	
	@GetMapping("/create-view")
	public String postCreate() {
		return "post/create";
	}
	
	
	
	@GetMapping("/list-view")
	public String postList() {
		return "post/list";
	}
	
	@GetMapping("/detail-view")
	public String postDetail() {
		return "post/detail";
	}
	

}
