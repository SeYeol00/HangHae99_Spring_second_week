package com.sparta.myblog.mockobject;

import com.sparta.myblog.domain.SignupRequestDto;
import com.sparta.myblog.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockUserServiceTest {



    @Test
    @DisplayName("유저 회원가입 정상 케이스")
    void signUp_username(){


        SignupRequestDto signupRequestDto = new SignupRequestDto(
                 "seyeal00",
                 "1111",
                "dmot@naver.com",
                "1111"
        );




        MockUserRepository mockUserRepository = new MockUserRepository();
        MockUserService mockUserService = new MockUserService(mockUserRepository);
        String result = mockUserService.registerUser(signupRequestDto);

        assertEquals("성공입니다.",result);

    }

    @Test
    @DisplayName("유저네임 비정상 케이스 1")
    void signUp_username_wrong_1(){


        SignupRequestDto signupRequestDto = new SignupRequestDto(
                "se",
                "1111",
                "dmot@naver.com",
                "1111"
        );




        MockUserRepository mockUserRepository = new MockUserRepository();
        MockUserService mockUserService = new MockUserService(mockUserRepository);
        String result = mockUserService.registerUser(signupRequestDto);

        assertEquals("아이디는 3자리 이상의 영문 대소문자 및 숫자를 사용해주세요.",result);

    }
    @Test
    @DisplayName("유저네임 비정상 케이스 2")
    void signUp_username_wrong_2(){


        SignupRequestDto signupRequestDto = new SignupRequestDto(
                "박세열",
                "1111",
                "dmot@naver.com",
                "1111"
        );




        MockUserRepository mockUserRepository = new MockUserRepository();
        MockUserService mockUserService = new MockUserService(mockUserRepository);
        String result = mockUserService.registerUser(signupRequestDto);

        assertEquals("아이디는 3자리 이상의 영문 대소문자 및 숫자를 사용해주세요.",result);

    }
    @Test
    @DisplayName("유저비밀번호 비정상 케이스 1")
    void signUp_password_wrong_1(){


        SignupRequestDto signupRequestDto = new SignupRequestDto(
                "seyeal00",
                "1",
                "dmot@naver.com",
                "1"
        );




        MockUserRepository mockUserRepository = new MockUserRepository();
        MockUserService mockUserService = new MockUserService(mockUserRepository);
        String result = mockUserService.registerUser(signupRequestDto);

        assertEquals("비밀번호가 4자 미만입니다.",result);

    }

    @Test
    @DisplayName("유저비밀번호 비정상 케이스 2")
    void signUp_password_wrong_2(){


        SignupRequestDto signupRequestDto = new SignupRequestDto(
                "0126",
                "0126",
                "dmot@naver.com",
                "0126"
        );




        MockUserRepository mockUserRepository = new MockUserRepository();
        MockUserService mockUserService = new MockUserService(mockUserRepository);
        String result = mockUserService.registerUser(signupRequestDto);

        assertEquals("닉네임과 같은 값이 비밀번호에 포함되어 있습니다.",result);

    }
    @Test
    @DisplayName("유저비밀번호 매칭 비정상 케이스 1")
    void signUp_password_matchWrong_1(){


        SignupRequestDto signupRequestDto = new SignupRequestDto(
                "seyeal00",
                "1111",
                "dmot@naver.com",
                "1"
        );




        MockUserRepository mockUserRepository = new MockUserRepository();
        MockUserService mockUserService = new MockUserService(mockUserRepository);
        String result = mockUserService.registerUser(signupRequestDto);

        assertEquals("비밀번호와 비밀번호 확인이 일치하지 않습니다.",result);

    }
    @Test
    @DisplayName("유저비밀번호 매칭 비정상 케이스 2")
    void signUp_password_matchWrong_2(){


        SignupRequestDto signupRequestDto = new SignupRequestDto(
                "seyeal00",
                "1",
                "dmot@naver.com",
                "11111"
        );




        MockUserRepository mockUserRepository = new MockUserRepository();
        MockUserService mockUserService = new MockUserService(mockUserRepository);
        String result = mockUserService.registerUser(signupRequestDto);

        assertEquals("비밀번호와 비밀번호 확인이 일치하지 않습니다.",result);

    }
    @Test
    @DisplayName("유저 닉네임 데이터베이스 존재 케이스_1")
    void signUp_username_containWrong_1(){


        SignupRequestDto signupRequestDto = new SignupRequestDto(
                "seyeal00",
                "1111",
                "dmot@naver.com",
                "1111"
        );

        User exist = new User("seyeal00",
                                "2222",
                                "dmot@gmail.com");



        MockUserRepository mockUserRepository = new MockUserRepository();
        mockUserRepository.save(exist);
        MockUserService mockUserService = new MockUserService(mockUserRepository);
        String result = mockUserService.registerUser(signupRequestDto);

        assertEquals("중복된 닉네임입니다.",result);

    }
    @Test
    @DisplayName("유저 닉네임 데이터베이스 존재 케이스_2")
    void signUp_username_containWrong_2(){


        SignupRequestDto signupRequestDto = new SignupRequestDto(
                "parkSeYeol",
                "1111",
                "dmot@naver.com",
                "1111"
        );

        User exist = new User("parkSeYeol",
                "2222",
                "dmot@gmail.com");



        MockUserRepository mockUserRepository = new MockUserRepository();
        mockUserRepository.save(exist);
        MockUserService mockUserService = new MockUserService(mockUserRepository);
        String result = mockUserService.registerUser(signupRequestDto);

        assertEquals("중복된 닉네임입니다.",result);

    }

}
