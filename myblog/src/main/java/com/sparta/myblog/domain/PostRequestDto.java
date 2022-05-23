package com.sparta.myblog.domain;


import lombok.Getter;
import lombok.ToString;

@Getter
public class PostRequestDto {

    private String title;
    private String username;
    private String contents;
    private int password;
}
