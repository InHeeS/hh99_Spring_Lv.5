package com.sparta.post.dto;

import com.sparta.post.entity.Post;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Getter
public class PostResponseListDto {
    List<PostResponseDto> postList = new ArrayList<>();

    public PostResponseListDto(List<PostResponseDto> post){
        this.postList.addAll(post);
    }
}
