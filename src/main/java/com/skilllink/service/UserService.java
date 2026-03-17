package com.skilllink.service;

import com.skilllink.model.User;
import com.skilllink.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo){
        this.repo = repo;
    }

    public List<User> getAllUsers(){
        return repo.findAll();
    }

    public void saveUser(User user){
        repo.save(user);
    }
}