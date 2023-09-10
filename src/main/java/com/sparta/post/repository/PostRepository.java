package com.sparta.post.repository;

import com.sparta.post.dto.PageRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.dto.PostResponseListDto;
import com.sparta.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAt();


    List<Post> findByFolderNumber(Long id);
}
