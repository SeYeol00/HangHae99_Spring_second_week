package com.sparta.myblog.repository;

import com.sparta.myblog.model.Comment;
import com.sparta.myblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository {

    Optional<Comment> findByPostId(String postId);
}
