package com.example.olibrary.service;

import com.example.olibrary.model.Author;
import com.example.olibrary.model.Book;
import com.example.olibrary.model.Genre;
import com.example.olibrary.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private  BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBook() {
        Book book = new Book();
        book.setId(1L);
        book.setName("TestName");
        book.setDescription("Это определённо книга");
        book.setAuthorsId(new ArrayList<>(Arrays.asList(new Author(), new Author())));
        book.setGenresId(new ArrayList<>(Arrays.asList(new Genre(), new Genre())));
        book.setBookPart("Это тестовая первая страница");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.saveBook(book);

        Book saved = bookService.getBookById(1L);
        assertEquals(1L, saved.getId());
        assertEquals("TestName", saved.getName());
        assertEquals("Это определённо книга", saved.getDescription());
        assertEquals(2, saved.getAuthorsId().size());
        assertEquals(2, saved.getGenresId().size());
        assertEquals("Это тестовая первая страница", saved.getBookPart());
        verify(bookRepository, times(1)).findById(1L);
    }
}
