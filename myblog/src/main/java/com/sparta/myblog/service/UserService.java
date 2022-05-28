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
        if(!Pattern.matches("^[a-zA-Z0-9]{3,}$",username)){
            return "아이디는 3자리 이상의 영문 대소문자 및 숫자를 사용해주세요.";
        }
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            return ("중복된 닉네임입니다.");
        }
        if(!Objects.equals(requestDto.getPassword(), requestDto.getPassword_confirm())){
            return ("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
        if(requestDto.getPassword().contains(username)||!Pattern.matches("^{4,}$",requestDto.getPassword())){
            return("닉네임과 같은 값이 포함되거나 비밀번호가 4자 미만입니다.");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();


        User user = new User(username, password, email);
        userRepository.save(user);
        return "성공입니다.";
    }




}
