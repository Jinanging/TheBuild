package com.jinanging.thebuild.post.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "postimage")
@Entity
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이미지 경로(파일명 또는 URL)
    private String imagePath;

    // Post와 다대일 관계
    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    // 연관관계 설정용 Setter
    public void setPost(Post post) {
        this.post = post;
    }
}
