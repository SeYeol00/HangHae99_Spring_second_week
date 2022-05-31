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

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    /*@GetMapping("/api/commentsRead/{id}")
    public List<Comment> getComments(@PathVariable Long id ){//포스트 아이디
        return commentService.getComments(id);
    }*/

    @ResponseBody
    @PostMapping("/api/commentsCreate/{id}")
    public String createComment(@PathVariable Long id,
                                 @RequestBody CommentRequestDto commentRequestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        String warn = "로그인을 해주세요.";
        if(userDetails.getUsername()!=null) {
            String username = userDetails.getUser().getUsername();
            commentRequestDto.setUsername(username);
            warn = commentService.createComment(commentRequestDto,id);;
        }
        return warn;
    }

    @ResponseBody
    @PutMapping("/api/commentsUpdate/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody CommentUpdateDto commentUpdateDto, @AuthenticationPrincipal UserDetailsImpl userDetails, Model model) throws Exception {
        String warn = "로그인을 해주세요.";
        if(userDetails.getUsername().equals(commentUpdateDto.getUsername())) {
            String username = userDetails.getUser().getUsername();
            // 응답 보내기 (업데이트된 상품 id)
             commentService.updateComment(username,id,commentUpdateDto);
             warn = "업데이트 성공";
        }return warn;

    }

    @ResponseBody
    @DeleteMapping("/api/commentsDelete/{id}")
    public String deleteComment(@PathVariable Long id,@RequestBody CommentUpdateDto commentUpdateDto,@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) throws Exception {
        String warn = "로그인을 해주세요.";
        if(userDetails.getUsername().equals(commentUpdateDto.getUsername())) {
            String username = userDetails.getUser().getUsername();
            // 응답 보내기 (업데이트된 상품 id)
            commentService.deleteComment(username,id);
            warn = "삭제 성공";
        }
        return warn;
    }
}
