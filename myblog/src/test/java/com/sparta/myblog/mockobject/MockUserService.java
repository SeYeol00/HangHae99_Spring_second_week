package com.sparta.myblog.mockobject;

import com.sparta.myblog.domain.SignupRequestDto;
import com.sparta.myblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public class MockUserService {


    private final MockUserRepository mockUserRepository;


    public MockUserService(MockUserRepository mockUserRepository){
        this.mockUserRepository =mockUserRepository;
    }
    public String registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        System.out.println(requestDto);
        if(!Pattern.matches("^[a-zA-Z0-9]{3,}$",username)){
            System.out.println("아이디는 3자리 이상의 영문 대소문자 및 숫자를 사용해주세요.");
            return "아이디는 3자리 이상의 영문 대소문자 및 숫자를 사용해주세요.";
        }System.out.println(1);
        Optional<User> found = mockUserRepository.findByUsername(username);
        if (found.isPresent()) {
            System.out.println("중복된 닉네임입니다.");
            return ("중복된 닉네임입니다.");
        }System.out.println(2);
        if(!Objects.equals(requestDto.getPassword(), requestDto.getPassword_confirm())){
            System.out.println("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return ("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }System.out.println(3);
        if(requestDto.getUsername().contains(requestDto.getPassword())){
            System.out.println("닉네임과 같은 값이 비밀번호에 포함되어있습니다. ");
            return("닉네임과 같은 값이 비밀번호에 포함되어 있습니다.");
        }System.out.println(4);
        if(!(requestDto.getPassword().length() >=4)){
            System.out.println("비밀번호가 4자 미만입니다.");
            return("비밀번호가 4자 미만입니다.");
        }

        // 패스워드 암호화
        String password = requestDto.getPassword();
        String email = requestDto.getEmail();


        User user = new User(username, password, email);
        mockUserRepository.save(user);
        return "성공입니다.";
    }


}
