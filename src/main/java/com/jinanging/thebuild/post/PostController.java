package com.jinanging.thebuild.post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinanging.thebuild.post.domain.Post;
import com.jinanging.thebuild.post.service.PostImageService;
import com.jinanging.thebuild.post.service.PostService;
import com.jinanging.thebuild.user.domain.User;
import com.jinanging.thebuild.user.service.UserService;

@RequestMapping("/post")
@Controller
public class PostController {
	
	private final PostImageService postImageService;
    private final PostService postService;
    private final UserService userService;  // UserService 추가

    public PostController(PostImageService postImageService, PostService postService, UserService userService) {
        this.postImageService = postImageService;
        this.postService = postService;
        this.userService = userService;
    }
	
	@GetMapping("/create-view")
	public String postCreate() {
		return "post/create";
	}
	
	
	
	@GetMapping("/list-view")
	public String postList(Model model) {
	    List<Post> posts = postService.getAllPostsWithImages();
	    
	    // userId 추출
	    Set<Long> userIds = posts.stream()
	                             .map(Post::getUserId)
	                             .collect(Collectors.toSet());
	                             
	    // 닉네임 리스트 조회 (UserService 필요)
	    List<User> users = userService.getUsersByIds(new ArrayList<>(userIds));
	    
	    // Map<Long, String> userIdToNickName 생성
	    Map<Long, String> userIdToNickName = users.stream()
	                                              .collect(Collectors.toMap(User::getId, User::getNickName));
	                                              
	    model.addAttribute("posts", posts);
	    model.addAttribute("userIdToNickName", userIdToNickName);
	    return "post/list";
	}
	
	@GetMapping("/detail-view")
	public String postDetail() {
		return "post/detail";
	}
	

}
