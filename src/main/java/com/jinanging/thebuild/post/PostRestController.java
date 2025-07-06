package com.jinanging.thebuild.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jinanging.thebuild.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public Map<String,String> createPost(
            @RequestParam String title,
            @RequestParam String contents,
            @RequestParam(value="images", required=false) MultipartFile[] images,
            HttpSession session) {

        long userId = (Long) session.getAttribute("userId");
        Map<String,String> resultMap = new HashMap<>();

        try {
            postService.addPost(userId, title, contents, images);
            resultMap.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "fail");
        }

        return resultMap;
    }
}
