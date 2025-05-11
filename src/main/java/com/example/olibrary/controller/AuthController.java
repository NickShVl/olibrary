package com.example.olibrary.controller;
import com.example.olibrary.dto.auth.AuthRequest;
import com.example.olibrary.dto.auth.RegisterRequest;
import com.example.olibrary.model.User;
import com.example.olibrary.service.UserService;
import com.example.olibrary.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public String create(@RequestBody AuthRequest authRequest) {
        var authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword());

        authenticationManager.authenticate(authentication);
        User user = userService.loadUserByUsername(authRequest.getUsername());

        var token = jwtUtils.generateToken(user);
        return token;
    }

    @PostMapping("/register")
    public User create(@RequestBody RegisterRequest registerRequest) {
        return userService.createUser(registerRequest.makeUser());
    }

}
