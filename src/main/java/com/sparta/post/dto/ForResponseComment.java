package com.sparta.post.dto;

import com.sparta.post.entity.Comment;
import com.sparta.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;

@Getter
public class ForResponseComment {

    private Long id;

    private String content;

    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public ForResponseComment(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUsername();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }


}
