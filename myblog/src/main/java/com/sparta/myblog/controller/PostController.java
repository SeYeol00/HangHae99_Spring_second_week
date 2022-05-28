package com.sparta.myblog.controller;

import com.sparta.myblog.domain.*;
import com.sparta.myblog.model.Post;
import com.sparta.myblog.repository.PostRepository;
import com.sparta.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    private final PostRepository postRepository;

    private final PostService postService;
    @Autowired
    public PostController(PostRepository postRepository, PostService postService){
        this.postRepository = postRepository;
        this.postService = postService;
    }


    @GetMapping("/api/postsRead")
    public List<PostListRequestDto> getPosts(){
        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc();
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
    public Post createdPost(@RequestBody PostRequestDto requestDto){
        Post post = new Post(requestDto);
        System.out.println(post.toString());
        return postRepository.save(post);
    }


    @GetMapping("/api/postsRead/{id}")
    public PostOneRequestDto getPost(@PathVariable Long id){
        Post post =  postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        PostOneRequestDto one = new PostOneRequestDto();
        one.setTitle(post.getTitle());
        one.setUsername(post.getUsername());
        one.setContents(post.getContents());
        one.setCreatedAt(post.getCreatedAt());
        return one;
    }


    @PutMapping("/api/postsUpdate/{id}")
    public Long updatePost (@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(post.getPassword() == requestDto.getPassword()){
            postService.update(id, requestDto);
        }
        return id;
    }

    @DeleteMapping("/api/postsDelete/{id}")
    public Long deleteMemo(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(post.getPassword() == requestDto.getPassword()){
            postRepository.deleteById(id);
        }
        return id;
    }

}
