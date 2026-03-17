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

    public void save(User user){
        repo.save(user);
    }

    public User getUserById(Long id){
        return repo.findById(id).orElse(null);
    }

    public void deleteUser(Long id){
        repo.deleteById(id);
    }
}