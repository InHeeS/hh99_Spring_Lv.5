package com.sparta.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "postlike")
@NoArgsConstructor
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "postid", nullable = false)
    private Long postId;

    @Column(name = "userid", nullable = false)
    private Long userId;

    @Column(name = "postlike", nullable = false)
    private Boolean postLike = true;


    public PostLike(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }
}
