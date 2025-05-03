package com.example.olibrary.service;

import com.example.olibrary.model.Author;
import com.example.olibrary.model.Book;
import com.example.olibrary.model.Genre;
import com.example.olibrary.model.Regal;
import com.example.olibrary.repository.RegalRepository;
import org.junit.jupiter.api.AfterAll;
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

public class RegalServiceTest {
    @Mock
    private RegalRepository regalRepository;

    @InjectMocks
    private  RegalService regalService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegalCRUD() {
        Regal regal = new Regal();
        regal.setId(1L);
        regal.setName("Name");
        regal.setDescription("Description");
        regal.setBooks(new ArrayList<>(Arrays.asList(new Book(), new Book())));

        when(regalRepository.findById(1L)).thenReturn(Optional.of(regal));

        regalService.saveRegal(regal);
        Regal test = regalService.getRegalById(1L);
        assertEquals(1L, test.getId());
        assertEquals("Name", test.getName());
        assertEquals("Description", test.getDescription());
        assertEquals(2, test.getBooks().size());
    }

    @Test
    public void testRegalFindByName() {
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

        Regal regal = new Regal();
        regal.setId(1L);
        regal.setName("Name");
        regal.setDescription("Description");
        regal.setBooks(new ArrayList<>(Arrays.asList(book)));

        when(regalRepository.findRegalByName("ame")).thenReturn(new ArrayList<>(List.of(regal)));

        regalService.saveRegal(regal);

        ArrayList<Regal> test = regalService.findRegalByName("ame");
        assertEquals(1, test.size());
        assertEquals(1L, test.get(0).getId());
    }

    @Test
    public void testRegalFindByBookName() {
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

        Regal regal = new Regal();
        regal.setId(1L);
        regal.setName("Name");
        regal.setDescription("Description");
        regal.setBooks(new ArrayList<>(Arrays.asList(book)));

        when(regalRepository.findBookOnRegalByBookName(1L,"ame")).thenReturn(new ArrayList<>(List.of(book)));

        regalService.saveRegal(regal);

        ArrayList<Book> test = regalService.findBookOnRegalByBookName(1L, "ame");
        assertEquals(1, test.size());
        assertEquals(1L, test.get(0).getId());
    }

    @Test
    public void testRegalFindByGenre() {
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

        Regal regal = new Regal();
        regal.setId(1L);
        regal.setName("Name");
        regal.setDescription("Description");
        regal.setBooks(new ArrayList<>(Arrays.asList(book)));

        when(regalRepository.findBookOnRegalByGenreName(1L,"estG")).thenReturn(new ArrayList<>(List.of(book)));

        regalService.saveRegal(regal);

        ArrayList<Book> test = regalService.findBookOnRegalByGenreName(1L, "estG");
        assertEquals(1, test.size());
        assertEquals(1L, test.get(0).getId());
    }

    @Test
    public void testRegalFindByAuthor() {
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

        Regal regal = new Regal();
        regal.setId(1L);
        regal.setName("Name");
        regal.setDescription("Description");
        regal.setBooks(new ArrayList<>(Arrays.asList(book)));

        when(regalRepository.findBookOnRegalByAuthorInfo(1L,"estA")).thenReturn(new ArrayList<>(List.of(book)));

        regalService.saveRegal(regal);

        ArrayList<Book> test = regalService.findBookOnRegalByAuthorInfo(1L, "estA");
        assertEquals(1, test.size());
        assertEquals(1L, test.get(0).getId());
    }
}
