package com.jinanging.thebuild.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {
	
	
	@GetMapping("/list")
	public String chatListView() {
		
		
		return "chat/list";
		
	}

}
