package com.sparta.myblog.service;

import com.sparta.myblog.domain.CommentRequestDto;
import com.sparta.myblog.domain.CommentUpdateDto;
import com.sparta.myblog.model.Comment;
import com.sparta.myblog.repository.CommentRepository;
import com.sparta.myblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;


    @Autowired
    public CommentService(CommentRepository commentRepository){
    this.commentRepository = commentRepository;
    }

    public Comment createComment(CommentRequestDto commentRequestDto,Long postId){
     Comment comment = new Comment(commentRequestDto,postId);
     commentRepository.save(comment);

     return comment;
    }

    public Comment updateComment(String username, Long Id, CommentUpdateDto commentUpdateDto){
        Comment comment = commentRepository.findById(Id).orElseThrow(()->new NullPointerException("해당 아이디가 존재하지 않습니다."));
        if(username.equals(comment.getUsername())){
            comment.update(commentUpdateDto);
            commentRepository.save(comment);
            return comment;
        }else{
            throw new IllegalArgumentException("작성자와 사용자가 같지 않습니다.");
        }
    }

    public List<Comment> getComments(Long postId){
        return commentRepository.findAllByPostId(postId);
    }

    public Comment deleteComment(String username, Long Id){
        Comment comment = commentRepository.findById(Id).orElseThrow(()->new NullPointerException("해당 아이디가 존재하지 않습니다."));
        if(username.equals(comment.getUsername())){
            commentRepository.deleteById(Id);
        }else{
            throw new IllegalArgumentException("작성자와 사용자가 같지 않습니다.");
        }
        return comment;
    }

}
