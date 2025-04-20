package com.example.olibrary.service;

import com.example.olibrary.model.Regal;
import com.example.olibrary.model.User;
import com.example.olibrary.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private  UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUser() {
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

        userService.saveUser(user);
        User test = userService.getUserById(1L);
        assertEquals(1L, test.getId());
        assertEquals("username", test.getUsername());
        assertEquals("testemail", test.getEMail());
        assertEquals("password", test.getPassword());
        assertEquals("FirstName", test.getFirstName());
        assertEquals("LastName", test.getLastName());
        assertEquals("MiddleName", test.getMiddleName());
        assertEquals(new Date(1984, 01, 01), test.getAccountCreated());
        assertEquals(new Date(1985, 01, 01), test.getBirthDate());
        assertEquals(2, test.getRegals().size());
        verify(userRepository, times(1)).findById(1L);
    }
}
