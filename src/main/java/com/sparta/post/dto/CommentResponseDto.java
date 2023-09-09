package com.sparta.post.dto;

import com.sparta.post.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
public class CommentResponseDto {

    private Long id;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment saveComment) {
        this.id = saveComment.getId();
        this.content = saveComment.getContent();
        this.username = saveComment.getUsername();
        this.createdAt = saveComment.getCreatedAt();
        this.modifiedAt = saveComment.getModifiedAt();
    }
}
