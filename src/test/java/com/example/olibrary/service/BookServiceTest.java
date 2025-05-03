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
import java.util.List;
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
    public void testBookCRUD() {
        Book book = new Book();
        book.setId(1L);
        book.setName("TestName");
        book.setDescription("Это определённо книга");
        book.setAuthors(new ArrayList<>(Arrays.asList(new Author(), new Author())));
        book.setGenres(new ArrayList<>(Arrays.asList(new Genre(), new Genre())));
        book.setBookPart("Это тестовая первая страница");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.saveBook(book);

        Book saved = bookService.getBookById(1L);
        assertEquals(1L, saved.getId());
        assertEquals("TestName", saved.getName());
        assertEquals("Это определённо книга", saved.getDescription());
        assertEquals(2, saved.getAuthors().size());
        assertEquals(2, saved.getGenres().size());
        assertEquals("Это тестовая первая страница", saved.getBookPart());
    }
    @Test
    public void testBookFindByName() {
        Author author = new Author();
        author.setId(1L);
        author.setCentury(12);
        author.setAlias("testAuthor");

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("testGenre");

        Book book = new Book();
        book.setId(1L);
        book.setName("TestName");
        book.setDescription("Это определённо книга");
        book.setAuthors(new ArrayList<>(Arrays.asList(author)));
        book.setGenres(new ArrayList<>(Arrays.asList(genre)));
        book.setBookPart("Это тестовая первая страница");

        when(bookRepository.findBookByName("tNa")).thenReturn(new ArrayList<>(List.of(book)));
        when(bookRepository.findBookByGenreName("tG")).thenReturn(new ArrayList<>(List.of(book)));
        when(bookRepository.findBookByAuthorInfo("tA")).thenReturn(new ArrayList<>(List.of(book)));

        bookService.saveBook(book);

        List<Book> saved = bookService.findBookByName("tNa");
        assertEquals(1, saved.size());
        assertEquals(1L, saved.get(0).getId());
    }
    @Test
    public void testBookFindByGenreName() {
        Author author = new Author();
        author.setId(1L);
        author.setCentury(12);
        author.setAlias("testAuthor");

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("testGenre");

        Book book = new Book();
        book.setId(1L);
        book.setName("TestName");
        book.setDescription("Это определённо книга");
        book.setAuthors(new ArrayList<>(Arrays.asList(author)));
        book.setGenres(new ArrayList<>(Arrays.asList(genre)));
        book.setBookPart("Это тестовая первая страница");

        when(bookRepository.findBookByGenreName("tG")).thenReturn(new ArrayList<>(List.of(book)));

        bookService.saveBook(book);

        List<Book> saved = bookService.findBookByGenreName("tG");
        assertEquals(1, saved.size());
        assertEquals(1L, saved.get(0).getGenres().get(0).getId());
    }
    @Test
    public void testBookFindByAuthorInfo() {
        Author author = new Author();
        author.setId(1L);
        author.setCentury(12);
        author.setAlias("testAuthor");

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("testGenre");

        Book book = new Book();
        book.setId(1L);
        book.setName("TestName");
        book.setDescription("Это определённо книга");
        book.setAuthors(new ArrayList<>(Arrays.asList(author)));
        book.setGenres(new ArrayList<>(Arrays.asList(genre)));
        book.setBookPart("Это тестовая первая страница");

        when(bookRepository.findBookByAuthorInfo("tA")).thenReturn(new ArrayList<>(List.of(book)));

        bookService.saveBook(book);

        List<Book> saved = bookService.findBookByAuthorInfo("tA");
        assertEquals(1, saved.size());
        assertEquals(1L, saved.get(0).getAuthors().get(0).getId());
    }

}
