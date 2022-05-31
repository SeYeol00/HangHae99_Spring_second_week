package com.sparta.myblog.mockobject;

import com.sparta.myblog.domain.SignupRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockUserServiceTest  {

    public PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("유저네임 정상 케이스")
    void signUp_username(){


        SignupRequestDto signupRequestDto = new SignupRequestDto(
                 "seyeal00",
                 "1111",
                "dmot@naver.com",
                "1111"
        );




        MockUserRepository mockUserRepository = new MockUserRepository();
        MockUserService mockUserService = new MockUserService(mockUserRepository,passwordEncoder);
        String result = mockUserService.registerUser(signupRequestDto);

        assertEquals("성공입니다.",result);

    }
}
