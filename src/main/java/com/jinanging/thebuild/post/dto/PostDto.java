package com.jinanging.thebuild.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long postId;
    private Long userId;
    private String nickname;
    private String title;
    private String contents;
    private List<String> imagePaths;
    private long likeCount;
    private boolean isLiked; // 좋아요 여부
    private List<CommentDto> comments;
}
