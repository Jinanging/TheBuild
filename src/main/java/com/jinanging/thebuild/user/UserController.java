package com.jinanging.thebuild.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// view controller
@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/join-view")
	public String joinInput() {
		return "user/join";
	}
	
	@GetMapping("/login-view")
	public String loginInput() {
		return "user/login";
	}
	
	@GetMapping("/profile-view")
	public String profile() {
		return "user/profile";
	}
	


}