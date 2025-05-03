package com.example.olibrary.service;

import com.example.olibrary.model.Book;
import com.example.olibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public ArrayList<Book> findBookByName(String name) {
        return bookRepository.findBookByName(name);
    }
    public ArrayList<Book> findBookByGenreName(String name) {
        return bookRepository.findBookByGenreName(name);
    }
    public ArrayList<Book> findBookByAuthorInfo(String info) {
        return bookRepository.findBookByAuthorInfo(info);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
