package com.example.olibrary.service;

import com.example.olibrary.enums.Role;
import com.example.olibrary.exceptions.NotFoundException;
import com.example.olibrary.model.User;
import com.example.olibrary.repository.UserRepository;
import com.example.olibrary.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService implements UserDetailsManager {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserUtils userUtils;

    public User loadUserById(Long id) {
        log.info("Trying to get user by id={}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("Regal not found");
        }
        return user.get();
    }
    @Override
    public User loadUserByUsername(String username) {
        log.info("Trying to load user by username='{}'", username);
        return userRepository.findByUsername(username).orElse(null);
    }

    public User createUser(User user) {
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
    @Override
    public void createUser(UserDetails userData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    @Override
    public void updateUser(UserDetails user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void deleteUser(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changePassword'");
    }

    @Override
    public boolean userExists(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'userExists'");
    }
}
