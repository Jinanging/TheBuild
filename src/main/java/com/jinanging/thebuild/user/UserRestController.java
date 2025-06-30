package com.jinanging.thebuild.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jinanging.thebuild.user.domain.User;
import com.jinanging.thebuild.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



// API 구성을 위한 컨트롤러 
@RequestMapping("/user")
@RestController  // @COntroller + @ResponseBody
public class UserRestController {
	
	private final UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/login")
	public Map<String,String> login(
			@RequestParam String loginId
			, @RequestParam String password
			, HttpServletRequest request) {
		
		User user = userService.getUser(loginId, password);
		
		Map<String,String> resultMap = new HashMap<>();
		
		if(user != null) {
			resultMap.put("result", "success");
			
			//세션을 관라히나는 객체
			// 요청한 대상 클라이언트의 세션 
			HttpSession session = request.getSession();
			
			//로그인이 되었다.
			// 사용자 정보를 저장
			// 세션은 모든 요청에서 접근하고 사용할 수 있다. 
			// 세션에 userId 라는 키에 값이 저장되어 있으면 로그인된 상태다
			session.setAttribute("userId", user.getId());
			
			session.setAttribute("userName", user.getNickName());
			
			
		}
		else {
			
			resultMap.put("result", "fail");
			
		}
		
		return resultMap;
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
