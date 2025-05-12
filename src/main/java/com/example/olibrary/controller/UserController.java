package com.example.olibrary.controller;

import com.example.olibrary.model.User;
import com.example.olibrary.service.UserService;
import com.example.olibrary.dto.user.UserCreateRequest;
import com.example.olibrary.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthUtils authUtils;
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        authUtils.isAllowed("GET /users/{userId}");
        return userService.loadUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        authUtils.isAllowed("DELETE /users/{userId}");
        userService.deleteUserById(userId);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody @Validated UserCreateRequest request) {
        authUtils.isAllowed("POST /users/create");
        return userService.createUser(request.makeUser());
    }
}
