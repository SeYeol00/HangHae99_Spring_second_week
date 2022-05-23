package com.sparta.myblog.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostListRequestDto {

    private LocalDateTime createdAt;
    private String title;
    private String username;


}
