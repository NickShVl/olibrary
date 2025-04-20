package com.example.olibrary.service;

import com.example.olibrary.model.Author;
import com.example.olibrary.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private  AuthorService authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthor() {
        Author author = new Author();
        author.setId(1L);
        author.setAlias("Alias");
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        author.setMiddleName("MiddleName");
        author.setBirthDate(new Date(1950,01,01));
        author.setDeathDate(new Date(3000,01,01));
        author.setCentury(20);

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        authorService.saveAuthor(author);

        Author saved = authorService.getAuthorById(1L);
        assertEquals(1L, saved.getId());
        assertEquals("Alias", saved.getAlias());
        assertEquals("FirstName", saved.getFirstName());
        assertEquals("LastName", saved.getLastName());
        assertEquals("MiddleName", saved.getMiddleName());
        assertEquals(new Date(1950,01,01), saved.getBirthDate());
        assertEquals(new Date(3000,01,01), saved.getDeathDate());
        assertEquals(20, saved.getCentury());
        verify(authorRepository, times(1)).findById(1L);

    }
}
