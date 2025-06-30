package com.jinanging.thebuild.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {
	
	@GetMapping("/create-view")
	public String postCreate() {
		return "post/create";
	}
	
	@GetMapping("/list-view")
	public String listView() {
		return "post/list";
	}
	

}
