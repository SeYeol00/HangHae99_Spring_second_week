package com.sparta.myblog.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String email;
    private String password_confirm;
}

