package com.sparta.myblog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.myblog.domain.*;
import com.sparta.myblog.model.Post;
import com.sparta.myblog.repository.PostRepository;
import com.sparta.myblog.security.UserDetailsImpl;
import com.sparta.myblog.service.CommentService;
import com.sparta.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    private final PostRepository postRepository;

    private final PostService postService;

    private final CommentService commentService;
    @Autowired
    public PostController(PostRepository postRepository, PostService postService, CommentService commentService){
        this.postRepository = postRepository;
        this.postService = postService;
        this.commentService = commentService;
    }


    @GetMapping("/api/postsRead")
    public List<PostListRequestDto> getPosts(){
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostListRequestDto> Parts = new ArrayList<>();
        for(Post post : posts){
            PostListRequestDto part = new PostListRequestDto();
            part.setTitle(post.getTitle());
            part.setUsername(post.getUsername());
            part.setCreatedAt(post.getCreatedAt());
            Parts.add(part);
        }
        return Parts;
    }


    @PostMapping("/api/postsWrite")
    public String createdPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails ) throws Exception {
        String warn = "로그인을 해주세요.";
        if(userDetails.getUser()!=null) {
            Post post = new Post(requestDto);
            postRepository.save(post);
            warn = "생성 성공";
            return warn;
        }
        return warn;
    }


    @GetMapping("/api/postsRead/{id}")
    public JSONArray getPost(@PathVariable Long id){
        JSONArray jsonArray = new JSONArray();
        Post post =  postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        PostOneRequestDto one = new PostOneRequestDto();
        one.setTitle(post.getTitle());
        one.setUsername(post.getUsername());
        one.setContents(post.getContents());
        one.setCreatedAt(post.getCreatedAt());
        jsonArray.put(one);
        jsonArray.put(commentService.getComments(id));
        return jsonArray;
    }


    @PutMapping("/api/postsUpdate/{id}")
    public String updatePost (@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception{
        String warn = "로그인을 해주세요.";
        if(userDetails.getUser()!=null) {
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );
            if(post.getPassword() == requestDto.getPassword()){
                postService.update(id, requestDto);
            }
            warn = "업데이트 완료";
            return warn;
        }
        return warn;
    }

    @DeleteMapping("/api/postsDelete/{id}")
    public String deleteMemo(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        String warn = "로그인을 해주세요.";
        if(userDetails.getUser()!=null) {
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );
            if(post.getPassword() == requestDto.getPassword()){
                postRepository.deleteById(id);
            }
            warn = "삭제 완료";
            return warn;
        }
        return warn;
    }

}
