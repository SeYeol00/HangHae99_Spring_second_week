package com.sparta.myblog.service;

import com.sparta.myblog.repository.CommentRepository;
import com.sparta.myblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private final CommentRepository commentRepository;

    public CommentService(){

    }

}
