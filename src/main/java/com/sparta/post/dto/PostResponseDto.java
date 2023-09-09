package com.sparta.post.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sparta.post.entity.Comment;
import com.sparta.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
public class PostResponseDto{


    private Long id;
    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
  //  private String comments;
    private List<ForResponseComment> comments = new ArrayList<>();
//    private List<CommentResponseDto> commentResponseDto = new ArrayList<>();

    public PostResponseDto(Post post) {
        Comparator<ForResponseComment> comparator = new Comparator<ForResponseComment>() {
            @Override
            public int compare(ForResponseComment o1, ForResponseComment o2) {
                if(o1.getCreatedAt().isAfter(o2.getCreatedAt())) return 1;
                else return -1;
                // return o1 - o2 ;
            }
        };

        this.id = post.getId();
        this.title = post.getTitle();
        this.username = post.getUsername();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        StringBuilder sb = new StringBuilder();
        List<Comment> commentlist = new ArrayList<>(post.getComments());

        for(Comment comment : post.getComments()){
            ForResponseComment cm = new ForResponseComment(comment);
            comments.add(cm);
        }
        Collections.sort(comments, comparator);
    }
}
