package com.sparta.myblog.service;

import com.sparta.myblog.domain.SignupRequestDto;
import com.sparta.myblog.model.User;
import com.sparta.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        System.out.println(requestDto);
        if(!Pattern.matches("^[a-zA-Z0-9]{3,}$",username)){
            System.out.println("아이디는 3자리 이상의 영문 대소문자 및 숫자를 사용해주세요.");
            return "아이디는 3자리 이상의 영문 대소문자 및 숫자를 사용해주세요.";
        }System.out.println(requestDto);
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            System.out.println("중복된 닉네임입니다.");
            return "중복된 닉네임입니다.";
        }System.out.println(requestDto);
        if(!Objects.equals(requestDto.getPassword(), requestDto.getPassword_confirm())){
            System.out.println("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
        }System.out.println(requestDto);
        if(requestDto.getUsername().contains(requestDto.getPassword())){
            System.out.println("닉네임과 같은 값이 비밀번호에 포함되어있습니다. ");
            return"닉네임과 같은 값이 비밀번호에 포함되어 있습니다.";
        }System.out.println(requestDto);
        if(!(requestDto.getPassword().length() >=4)){
            System.out.println("비밀번호가 4자 미만입니다.");
            return"비밀번호가 4자 미만입니다.";
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();


        User user = new User(username, password, email);
        userRepository.save(user);
        return "성공입니다.";
    }




}
