package com.example.olibrary.service;

import com.example.olibrary.model.Book;
import com.example.olibrary.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Slf4j
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public Book getBookById(Long id) {
        log.info("Trying to find book by id={}", id);
        return bookRepository.findById(id).orElse(null);
    }

    public ArrayList<Book> findBookByName(String name) {
        log.info("Trying to find book by name like '{}'", name);
        return bookRepository.findBookByName(name);
    }
    public ArrayList<Book> findBookByGenreName(String name) {
        log.info("Trying to find book by genre name like '{}'", name);
        return bookRepository.findBookByGenreName(name);
    }
    public ArrayList<Book> findBookByAuthorInfo(String info) {
        log.info("Trying to find book by author info like '{}'", info);
        return bookRepository.findBookByAuthorInfo(info);
    }
    public Book saveBook(Book book) {
        log.info("Trying to save book='{}'", book);
        return bookRepository.save(book);
    }
    public void deleteBookById(Long id) {
        log.info("Trying to delete book by id={}", id);
        bookRepository.deleteById(id);
    }
}
