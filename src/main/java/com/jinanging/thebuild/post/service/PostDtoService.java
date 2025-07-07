package com.jinanging.thebuild.post.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jinanging.thebuild.post.domain.Post;
import com.jinanging.thebuild.post.domain.PostImage;
import com.jinanging.thebuild.post.dto.CommentDto;
import com.jinanging.thebuild.post.dto.PostDto;
import com.jinanging.thebuild.post.repository.PostRepository;
import com.jinanging.thebuild.user.repository.UserRepository;
import com.jinanging.thebuild.user.domain.User;

@Service
public class PostDtoService {

    private final PostRepository postRepository;
    private final LikeService likeService;
    private final CommentService commentService;
    private final UserRepository userRepository;  // UserRepository 추가

    public PostDtoService(PostRepository postRepository, LikeService likeService, 
                          CommentService commentService, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.likeService = likeService;
        this.commentService = commentService;
        this.userRepository = userRepository;
    }

    public List<PostDto> getAllPostDtos() {
        List<Post> posts = postRepository.findAllPostsWithImages();
        List<PostDto> dtoList = new ArrayList<>();

        // 유저 ID 리스트 모으기
        List<Long> userIds = new ArrayList<>();
        for (Post post : posts) {
            if (!userIds.contains(post.getUserId())) {
                userIds.add(post.getUserId());
            }
        }

        // 유저 정보를 한 번에 조회
        List<User> users = userRepository.selectUsersByIds(userIds);

        // 유저 정보를 Map에 저장 (userId -> nickname)
        Map<Long, String> userNicknameMap = new HashMap<>();
        for (User user : users) {
            userNicknameMap.put(user.getId(), user.getNickName());
        }

        // 게시글 정보 설정
        for (Post post : posts) {
            PostDto dto = new PostDto();

            dto.setPostId(post.getId());
            dto.setUserId(post.getUserId());

            // 닉네임 조회
            dto.setNickname(userNicknameMap.get(post.getUserId())); 
            dto.setTitle(post.getTitle());
            dto.setContents(post.getContents());

            // 이미지 경로 리스트 만들기
            List<String> imagePaths = new ArrayList<>();
            for (PostImage pi : post.getImages()) {
                imagePaths.add(pi.getImagePath());
            }
            dto.setImagePaths(imagePaths);

            // 좋아요 개수
            long likeCount = likeService.countLikes(post.getId());
            dto.setLikeCount(likeCount);

            // 댓글 리스트 CommentDto 변환
            List<CommentDto> comments = commentService.getCommentDtosByPostId(post.getId());
            dto.setComments(comments);

            dtoList.add(dto);
        }

        return dtoList;
    }
}
