package com.example.olibrary.service;

import com.example.olibrary.model.User;
import com.example.olibrary.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        log.info("Trying to get user by id={}", id);
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUserName(String username) {
        log.info("Trying to get user by username='{}'", username);
        return userRepository.findByUsername(username).orElse(null);
    }

    public User saveUser(User user) {
        log.info("Trying to save user='{}'", user);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        log.info("Trying to delete user by id={}", id);
        userRepository.deleteById(id);
    }
}
