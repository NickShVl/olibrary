package com.example.olibrary.service;

import com.example.olibrary.model.Regal;
import com.example.olibrary.model.User;
import com.example.olibrary.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private  UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUserCRUD() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setEMail("testemail");
        user.setPassword("password");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setMiddleName("MiddleName");
        user.setAccountCreated(new Date(1984, 01, 01));
        user.setBirthDate(new Date(1985, 01, 01));
        user.setRegals(new ArrayList<>(Arrays.asList(new Regal(), new Regal())));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.createUser(user);
        User test = userService.loadUserById(1L);
        assertEquals(1L, test.getId());
        assertEquals("username", test.getUsername());
        assertEquals("testemail", test.getEMail());
        assertEquals(passwordEncoder.encode("password"), test.getPassword());
        assertEquals("FirstName", test.getFirstName());
        assertEquals("LastName", test.getLastName());
        assertEquals("MiddleName", test.getMiddleName());
        assertEquals(new Date(1984, 01, 01), test.getAccountCreated());
        assertEquals(new Date(1985, 01, 01), test.getBirthDate());
        assertEquals(2, test.getRegals().size());
    }
    @Test
    public void testUserFindByUsername() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setEMail("testemail");
        user.setPassword("password");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setMiddleName("MiddleName");
        user.setAccountCreated(new Date(1984, 01, 01));
        user.setBirthDate(new Date(1985, 01, 01));
        user.setRegals(new ArrayList<>(Arrays.asList(new Regal(), new Regal())));

        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        userService.createUser(user);
        User test = userService.loadUserByUsername("username");
        assertEquals(1L, test.getId());
        assertEquals("username", test.getUsername());
        assertEquals("testemail", test.getEMail());
        assertEquals(passwordEncoder.encode("password"), test.getPassword());
        assertEquals("FirstName", test.getFirstName());
        assertEquals("LastName", test.getLastName());
        assertEquals("MiddleName", test.getMiddleName());
        assertEquals(new Date(1984, 01, 01), test.getAccountCreated());
        assertEquals(new Date(1985, 01, 01), test.getBirthDate());
        assertEquals(2, test.getRegals().size());
    }
}
