package com.example.olibrary.service;

import com.example.olibrary.exceptions.NotFoundException;
import com.example.olibrary.model.User;
import com.example.olibrary.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        log.info("Trying to get user by id={}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("Regal not found");
        }
        return user.get();
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
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("Regal not found");
        }
        userRepository.deleteById(id);
    }
}
