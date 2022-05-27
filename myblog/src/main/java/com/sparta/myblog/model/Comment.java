package com.sparta.myblog.model;

import com.sparta.myblog.domain.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public Comment(String username, String comments, Long postId) {
        this.username = username;
        this.comments = comments;
        this.postId = postId;
    }
}