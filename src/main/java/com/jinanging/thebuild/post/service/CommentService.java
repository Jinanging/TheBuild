package com.jinanging.thebuild.post.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinanging.thebuild.post.domain.Comment;
import com.jinanging.thebuild.post.domain.Post;
import com.jinanging.thebuild.post.dto.CommentDto;
import com.jinanging.thebuild.post.repository.CommentRepository;
import com.jinanging.thebuild.user.domain.User;
import com.jinanging.thebuild.user.service.UserService;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    // 댓글 조회
    public List<CommentDto> getCommentDtosByPostId(Long postId) {
        // 댓글 조회
        List<Comment> comments = commentRepository.findByPostIdOrderByCreatedAtAsc(postId);

        // 댓글에서 유저 ID만 추출
        List<Long> userIds = new ArrayList<>();
        for (Comment comment : comments) {
            if (!userIds.contains(comment.getUserId())) {
                userIds.add(comment.getUserId());
            }
        }

        // 유저 정보 조회
        List<User> users = userService.getUsersByIds(userIds);

        // 유저 정보를 Map으로 변환 (userId -> 닉네임)
        Map<Long, String> userMap = new HashMap<>();
        for (User user : users) {
            userMap.put(user.getId(), user.getNickName());
        }

        // 댓글을 CommentDto로 변환
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDto dto = new CommentDto();
            dto.setId(comment.getId());
            dto.setUserId(comment.getUserId());
            dto.setNickname(userMap.get(comment.getUserId())); // 유저 닉네임 매핑
            dto.setContent(comment.getContent());
            dto.setCreatedAt(comment.getCreatedAt());
            commentDtos.add(dto);
        }

        return commentDtos;
    }

    // 댓글 추가
    @Transactional
    public boolean addComment(Long postId, Long userId, String content) {
        // 댓글 엔티티 생성
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        // Post 객체 조회 (id로 찾기)
        Post post = commentRepository.findPostById(postId); // id로 조회
        if (post != null) {
            comment.setPost(post);  // 댓글에 Post 객체 설정
            commentRepository.save(comment);
            return true;
        } else {
            return false;  // 포스트가 존재하지 않으면 false 리턴
        }
    }


}
