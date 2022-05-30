package com.sparta.myblog.controller;


import com.sparta.myblog.domain.CommentRequestDto;
import com.sparta.myblog.domain.CommentUpdateDto;
import com.sparta.myblog.domain.PostRequestDto;
import com.sparta.myblog.model.Comment;
import com.sparta.myblog.model.Post;
import com.sparta.myblog.security.UserDetailsImpl;
import com.sparta.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/api/postsRead/{id}")
    public List<Comment> getComments(@PathVariable Long id ){//포스트 아이디
        return commentService.getComments(id);
    }

    @PostMapping("/api/postsRead/{id}")
    public Comment createComment(@PathVariable Long id,
                                 @RequestBody CommentRequestDto commentRequestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails, Model model) throws Exception {
        if(userDetails.getUsername()==null) {
        String warn = "로그인을 해주세요.";
        model.addAttribute(warn);
        throw new Exception("로그인을 해주세요.");
        }
        String username = userDetails.getUser().getUsername();
        commentRequestDto.setUsername(username);
        return commentService.createComment(commentRequestDto,id);

    }
    @PutMapping("/api/postsRead/{id}")
    public Comment updateProduct(@PathVariable Long id, @RequestBody CommentUpdateDto commentUpdateDto, @AuthenticationPrincipal UserDetailsImpl userDetails, Model model) throws Exception {
        if(userDetails.getUsername()==null) {
            String warn = "로그인을 해주세요.";
            model.addAttribute(warn);
            throw new Exception("로그인을 해주세요.");
        }
        String username = userDetails.getUser().getUsername();

        // 응답 보내기 (업데이트된 상품 id)
        return commentService.updateComment(username,id,commentUpdateDto);
    }

    @DeleteMapping("/api/postRead/{id}")
    public Comment deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, Model model) throws Exception {
        if(userDetails.getUsername()==null) {
            String warn = "로그인을 해주세요.";
            model.addAttribute(warn);
            throw new Exception("로그인을 해주세요.");
        }
        String username = userDetails.getUser().getUsername();

        // 응답 보내기 (업데이트된 상품 id)
        return commentService.deleteComment(username,id);
    }
}
