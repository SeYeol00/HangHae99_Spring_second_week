package com.sparta.myblog.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentRequestDto {

    private String username;
    private String comments;


}
