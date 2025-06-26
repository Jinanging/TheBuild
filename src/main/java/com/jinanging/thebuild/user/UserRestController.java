package com.jinanging.thebuild.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jinanging.thebuild.user.service.UserService;



// API 구성을 위한 컨트롤러 
@RequestMapping("/user")
@RestController  // @COntroller + @ResponseBody
public class UserRestController {
	
	private final UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/join")
	public Map<String,String> join(
			@RequestParam String loginId
			,@RequestParam String password
			,@RequestParam String nickName
			,@RequestParam String email
			,@RequestParam String introduce){
		
		Map<String,String> resultMap = new HashMap<>();
		
		if(userService.addUser(loginId, password, nickName, email, introduce)) {
			
			resultMap.put("result", "success");
			
			
		} else {
			resultMap.put("result", "fail");
			
		}
		
		
		
		
		return resultMap;
	}
	
	@GetMapping("/duplicate-id")
	public Map<String,Boolean> isDuplicateId(@RequestParam String loginId) {
		
		Map<String,Boolean> resultMap = new HashMap<>();
		
		if(userService.isDuplicateId(loginId)) {
			
			resultMap.put("isDuplicate", true);
			
		}
		else {
			resultMap.put("isDuplicate", false);
			
		}
		
		return resultMap;
		
		
		
	}
	

}
