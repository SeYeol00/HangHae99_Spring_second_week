package com.sparta.myblog.model;

import com.sparta.myblog.domain.CommentRequestDto;
import com.sparta.myblog.domain.CommentUpdateDto;
import com.sparta.myblog.domain.PostRequestDto;
import com.sparta.myblog.domain.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Setter
@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comments;

    @Column(nullable = false)
    private Long postId;

    public Comment(CommentRequestDto commentRequestDto, Long postId) {
        this.username = commentRequestDto.getUsername();
        this.comments = commentRequestDto.getComments();
        this.postId = postId;
    }

    public void update(CommentUpdateDto commentUpdateDto){
        this.comments = commentUpdateDto.getComments();
    }
}