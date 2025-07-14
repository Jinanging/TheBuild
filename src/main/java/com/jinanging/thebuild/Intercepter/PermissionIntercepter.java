package com.jinanging.thebuild.Intercepter;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionIntercepter implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(
					HttpServletRequest request
					,HttpServletResponse response
					, Object Handler) throws IOException {
		
		//로그인이 안된경우 리스트 페이지, 글 작성 페이지 , 상세 페이지를 접근하지 못하게 한.
		// 그 경우 로그인 페이지로 다시 요청하게 한다.
		
		HttpSession session = request.getSession();
		
		String uri = request.getRequestURI();
		
		Long userId = (Long)session.getAttribute("userId");
		
		
		// post/list-view
		if(userId == null) {
			//로그인 안된 경우
			// /post로 시작하는 url path
			if(uri.startsWith("/post")) {
				
				// 요청이 더 진행 안되도록
				
				response.sendRedirect("/user/login-view");
				return false;
				
				
			}
			
			// 그 경우 로그인 페이지로 다시 요청하게 한다.
		}
		else {
			// 로그인이 된 경우 
			if(uri.startsWith("/user")) {
				
				// 요청이 더 진행 안되도록
				
				response.sendRedirect("/post/list-view");
				return false;
				
				
			}
			
			
		}
		
		return true;
		
		
		
	}

}
