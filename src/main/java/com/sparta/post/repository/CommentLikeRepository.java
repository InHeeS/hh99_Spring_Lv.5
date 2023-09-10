package com.sparta.post.repository;

import com.sparta.post.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findById(Long commentId);

    Optional<CommentLike> findByUserId(Long userId);
}
