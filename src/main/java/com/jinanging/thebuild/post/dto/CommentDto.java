package com.jinanging.thebuild.post.dto;

import java.time.LocalDateTime;

public class CommentDto {
    private Long id;
    private Long userId;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;

    public CommentDto() {}

    public CommentDto(Long id, Long userId, String nickname, String content, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
