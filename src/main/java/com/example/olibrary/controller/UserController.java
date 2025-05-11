package com.example.olibrary.controller;

import com.example.olibrary.model.User;
import com.example.olibrary.service.UserService;
import com.example.olibrary.dto.user.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.loadUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody @Validated UserCreateRequest request) {
        return userService.createUser(request.makeUser());
    }
}
