package com.example.olibrary.service;

import com.example.olibrary.exceptions.NotFoundException;
import com.example.olibrary.model.Author;
import com.example.olibrary.model.Book;
import com.example.olibrary.model.Genre;
import com.example.olibrary.repository.AuthorRepository;
import com.example.olibrary.repository.BookRepository;
import com.example.olibrary.repository.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    public Book getBookById(Long id) {
        log.info("Trying to find book by id={}", id);
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new NotFoundException("Book not found");
        }
        return book.get();
    }

    public ArrayList<Book> findBookByName(String name) {
        log.info("Trying to find book by name like '{}'", name);
        return bookRepository.findBookByName(name);
    }
    public ArrayList<Book> findBookByGenreName(String name) {
        log.info("Trying to find book by genre name like '{}'", name);
        return new ArrayList<>(bookRepository.findBookByGenreName(name));
    }
    public ArrayList<Book> findBookByAuthorInfo(String info) {
        log.info("Trying to find book by author info like '{}'", info);
        return new ArrayList<>(bookRepository.findBookByAuthorInfo(info));
    }
    public Book saveBook(Book book) {
        log.info("Trying to save book='{}'", book);
        return bookRepository.save(book);
    }
    public Book createBook(Book book) {
        log.info("Trying to createBook book='{}'", book);
        List<Author> rightAuthorList = new ArrayList<>();
        for(Author a : book.getAuthors()) {
            Optional<Author> aut = authorRepository.findById(a.getId());
            if (aut.isEmpty()) {
                throw new NotFoundException("Author not found");
            }
            rightAuthorList.add(aut.get());
        }
        List<Genre> rightGenreList = new ArrayList<>();
        for(Genre g : book.getGenres()) {
            Optional<Genre> gen = genreRepository.findById(g.getId());
            if (gen.isEmpty()) {
                throw new NotFoundException("Genre not found");
            }
            rightGenreList.add(gen.get());
        }
        book.setAuthors(rightAuthorList);
        book.setGenres(rightGenreList);
        Book savedBook = bookRepository.save(book);
        return bookRepository.findById(savedBook.getId()).get();
    }
    public void deleteBookById(Long id) {
        log.info("Trying to delete book by id={}", id);
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new NotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }
}
