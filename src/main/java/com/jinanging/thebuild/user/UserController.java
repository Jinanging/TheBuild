package com.jinanging.thebuild.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinanging.thebuild.post.domain.PostImage;
import com.jinanging.thebuild.post.service.PostImageService;
import com.jinanging.thebuild.post.service.PostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

// view controller
@Controller
@RequestMapping("/user")
public class UserController {
	
	private final PostService postService;
	private final PostImageService postImageService;

	public UserController(PostService postService, PostImageService postImageService) {
	    this.postService = postService;
	    this.postImageService = postImageService;
	}
	

	
	@GetMapping("/join-view")
	public String joinInput() {
		return "user/join";
	}
	
	@GetMapping("/login-view")
	public String loginInput() {
		return "user/login";
	}
	
	@GetMapping("/profile-view")
	public String profile(HttpSession session, Model model) {
	    Long userId = (Long) session.getAttribute("userId");
	    if (userId == null) {
	        return "redirect:/user/login-view";
	    }

	    // 작성자가 올린 사진 리스트 가져오기
	    List<PostImage> userImages = postImageService.getPostImageList(userId);

	    // 모델에 담아서 뷰로 전달
	    model.addAttribute("userImages", userImages);

	    return "user/profile";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		// 세션에 저장한 사용자 정보 삭제
		HttpSession session = request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		
		return "redirect:/user/login-view";
		
	}
	


}