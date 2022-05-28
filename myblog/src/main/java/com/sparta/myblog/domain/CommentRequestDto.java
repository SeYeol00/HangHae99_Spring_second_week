package com.sparta.myblog.domain;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentRequestDto {

    private String username;
    private String comments;


}
