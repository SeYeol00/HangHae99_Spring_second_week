package com.sparta.myblog.mockobject;

import com.sparta.myblog.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockUserRepository {

    private List<User> users = new ArrayList<>();

    private Long userId = 1L;

    public User save(User user){
       user.setId(userId);
       ++userId;
       users.add(user);
       return user;
    }
    public Optional<User> findByUsername(String username){
        Optional<User> userList = Optional.empty();
        for(User user:users){
            if(user.getUsername().equals(username)){
                userList = Optional.of(user);
            }
        }
        return userList;
    }

    public List<User> findAll() {
        return users;
    }
}
