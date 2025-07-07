package com.jinanging.thebuild.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jinanging.thebuild.post.dto.CommentDto;
import com.jinanging.thebuild.post.service.CommentService;
import com.jinanging.thebuild.post.service.LikeService;
import com.jinanging.thebuild.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {

    private final PostService postService;
    private final LikeService likeService;
    private final CommentService commentService;

    public PostRestController(PostService postService, LikeService likeService, CommentService commentService) {
        this.postService = postService;
        this.likeService = likeService;
        this.commentService = commentService;
    }

    // 좋아요 토글
    @PostMapping("/like")
    @ResponseBody
    public Map<String, String> likePost(@RequestParam long postId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        Map<String, String> resultMap = new HashMap<>();

        if (userId == null) {
            resultMap.put("status", "error");
            resultMap.put("message", "로그인이 필요합니다.");
            return resultMap;
        }

        boolean liked = likeService.toggleLike(postId, userId);

        if (liked) {
            resultMap.put("status", "success");
            resultMap.put("message", "좋아요가 추가되었습니다.");
        } else {
            resultMap.put("status", "success");
            resultMap.put("message", "좋아요가 취소되었습니다.");
        }

        long likeCount = likeService.countLikes(postId);
        resultMap.put("likeCount", String.valueOf(likeCount));

        return resultMap;
    }

    // 게시글 생성
    @PostMapping("/create")
    public Map<String, String> createPost(
            @RequestParam String title,
            @RequestParam String contents,
            @RequestParam(value = "images", required = false) MultipartFile[] images,
            HttpSession session) {

        long userId = (Long) session.getAttribute("userId");
        Map<String, String> resultMap = new HashMap<>();

        try {
            postService.addPost(userId, title, contents, images);
            resultMap.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "fail");
        }

        return resultMap;
    }

    // 댓글 추가 (userId는 세션에서 가져옴)
    @PostMapping("/comment")
    @ResponseBody
    public Map<String, Object> addComment(
            @RequestParam Long postId,
            @RequestParam String content,
            HttpSession session) {

        Map<String, Object> resultMap = new HashMap<>();

        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            resultMap.put("result", "fail");
            resultMap.put("message", "로그인이 필요합니다.");
            return resultMap;
        }

        try {
            boolean success = commentService.addComment(postId, userId, content);
            if (success) {
                List<CommentDto> commentDtos = commentService.getCommentDtosByPostId(postId);
                resultMap.put("result", "success");
                resultMap.put("comments", commentDtos);
            } else {
                resultMap.put("result", "fail");
                resultMap.put("message", "댓글 추가 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "fail");
            resultMap.put("message", "서버 오류");
        }

        return resultMap;
    }

    // 댓글 목록 조회
    @PostMapping("/commentList")
    @ResponseBody
    public Map<String, Object> getCommentsByPostId(@RequestParam Long postId) {
        Map<String, Object> resultMap = new HashMap<>();

        try {
            List<CommentDto> commentDtos = commentService.getCommentDtosByPostId(postId);
            resultMap.put("result", "success");
            resultMap.put("comments", commentDtos);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "fail");
            resultMap.put("message", "댓글 조회 실패");
        }

        return resultMap;
    }
}
