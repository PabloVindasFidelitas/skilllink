package com.skilllink.repository;

import com.skilllink.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findBySkillContainingIgnoreCase(String skill);
}