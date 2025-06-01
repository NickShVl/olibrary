package com.example.olibrary.service;

import com.example.olibrary.model.Author;
import com.example.olibrary.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthorCRUD() {
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
    }
    @Test
    public void testAuthorFindByInfo() {
        Author author1 = new Author();
        author1.setId(1L);
        author1.setAlias("Alias");
        author1.setCentury(1);

        Author author2 = new Author();
        author2.setId(2L);
        author2.setFirstName("FirstName");
        author2.setCentury(2);

        Author author3 = new Author();
        author3.setId(3L);
        author3.setLastName("LastName");
        author3.setCentury(3);

        Author author4 = new Author();
        author4.setId(4L);
        author4.setMiddleName("MiddleName");
        author4.setCentury(4);

        when(authorRepository.findAuthorsBySomeInformation("lias"))
                .thenReturn(new ArrayList<>(List.of(author1)));
        when(authorRepository.findAuthorsBySomeInformation("First"))
                .thenReturn(new ArrayList<>(List.of(author2)));
        when(authorRepository.findAuthorsBySomeInformation("Name"))
                .thenReturn(new ArrayList<>(List.of(author2, author3, author4)));
        when(authorRepository.findAuthorsBySomeInformation("a"))
                .thenReturn(new ArrayList<>(List.of(author1, author2, author3, author4)));
        authorService.saveAuthor(author1);
        authorService.saveAuthor(author2);
        authorService.saveAuthor(author3);
        authorService.saveAuthor(author4);

        List<Author> saved1 = authorService.findAuthorsBySomeString("lias");
        List<Author> saved2 = authorService.findAuthorsBySomeString("First");
        List<Author> saved3 = authorService.findAuthorsBySomeString("Name");
        List<Author> saved4 = authorService.findAuthorsBySomeString("a");
        assertEquals(1, saved1.size());
        assertEquals(1L, saved1.get(0).getId());
        assertEquals(1, saved2.size());
        assertEquals(2L, saved2.get(0).getId());
        assertEquals(3, saved3.size());
        assertEquals(4, saved4.size());
    }

}
