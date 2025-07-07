package com.jinanging.thebuild.post.dto;

import java.util.List;

public class PostDto {
    private Long postId;
    private Long userId;
    private String nickname;
    private String title;
    private String contents;
    private List<String> imagePaths;
    private long likeCount;
    private boolean isLiked; // 좋아요 여부를 나타내는 필드 추가

    private List<CommentDto> comments;

    public PostDto() {}

    public PostDto(Long postId, Long userId, String nickname, String title, String contents,
                   List<String> imagePaths, long likeCount, boolean isLiked, List<CommentDto> comments) {
        this.postId = postId;
        this.userId = userId;
        this.nickname = nickname;
        this.title = title;
        this.contents = contents;
        this.imagePaths = imagePaths;
        this.likeCount = likeCount;
        this.isLiked = isLiked; // 좋아요 여부 설정
        this.comments = comments;
    }

    // --- Getter & Setter ---
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isLiked() {
        return isLiked; // getter 메서드
    }

    public void setLiked(boolean isLiked) {
        this.isLiked = isLiked; // setter 메서드
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }
}
