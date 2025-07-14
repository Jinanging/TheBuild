package com.jinanging.thebuild.post.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jinanging.thebuild.common.FileManager;
import com.jinanging.thebuild.post.domain.Post;
import com.jinanging.thebuild.post.domain.PostImage;
import com.jinanging.thebuild.post.dto.CommentDto;
import com.jinanging.thebuild.post.dto.PostDto;
import com.jinanging.thebuild.post.repository.PostRepository;
import com.jinanging.thebuild.user.domain.User;
import com.jinanging.thebuild.user.repository.UserRepository;

@Service
public class PostDtoService {

    private final PostRepository postRepository;
    private final LikeService likeService;
    private final CommentService commentService;
    private final UserRepository userRepository;

    public PostDtoService(PostRepository postRepository, LikeService likeService, 
                          CommentService commentService, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.likeService = likeService;
        this.commentService = commentService;
        this.userRepository = userRepository;
    }
    
    public boolean deletPost(long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        
        if(optionalPost.isPresent()) {
            Post post = optionalPost.get();
            
            
            for(PostImage image : post.getImages()) {
                FileManager.removeFile(image.getImagePath());
            }
            
            postRepository.delete(post);
            
            return true;
        }
        else {
            return false;
        }
    }

    
 

    /**
     * 로그인 사용자 ID 기준으로 좋아요 상태 포함 게시글 DTO 리스트 반환
     */
    public List<PostDto> getAllPostDtos(Long userId) {
        List<Post> posts = postRepository.findAllPostsWithImages();
        List<PostDto> dtoList = new ArrayList<>();

        List<Long> userIds = new ArrayList<>();
        List<Long> postIds = new ArrayList<>();
        for (Post post : posts) {
            if (!userIds.contains(post.getUserId())) {
                userIds.add(post.getUserId());
            }
            postIds.add(post.getId());
        }

        // 유저 닉네임 매핑
        List<User> users = userRepository.selectUsersByIds(userIds);
        Map<Long, String> userNicknameMap = new HashMap<>();
        for (User user : users) {
            userNicknameMap.put(user.getId(), user.getNickName());
        }

        // 좋아요 수와 상태 한번에 조회
        Map<Long, PostDto> likeInfoMap = likeService.getLikeCountsAndStatusByPostIds(postIds, userId);

        for (Post post : posts) {
            PostDto dto = new PostDto();

            dto.setPostId(post.getId());
            dto.setUserId(post.getUserId());
            dto.setNickname(userNicknameMap.get(post.getUserId()));
            dto.setTitle(post.getTitle());
            dto.setContents(post.getContents());

            List<String> imagePaths = new ArrayList<>();
            for (PostImage pi : post.getImages()) {
                imagePaths.add(pi.getImagePath());
            }
            dto.setImagePaths(imagePaths);

            PostDto likeDto = likeInfoMap.get(post.getId());
            if (likeDto != null) {
                dto.setLikeCount(likeDto.getLikeCount());
                dto.setLiked(likeDto.isLiked());
            } else {
                dto.setLikeCount(0);
                dto.setLiked(false);
            }

            List<CommentDto> comments = commentService.getCommentDtosByPostId(post.getId());
            dto.setComments(comments);

            dtoList.add(dto);
        }

        return dtoList;
    }
}
