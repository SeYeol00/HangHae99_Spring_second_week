package com.sparta.myblog.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PostOneRequestDto {


    private LocalDateTime createdAt;
    private String title;
    private String username;
    private String contents;
}
