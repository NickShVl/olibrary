package com.example.olibrary.service;

import com.example.olibrary.exceptions.ConflictException;
import com.example.olibrary.exceptions.NotFoundException;
import com.example.olibrary.model.Book;
import com.example.olibrary.model.Regal;
import com.example.olibrary.repository.BookRepository;
import com.example.olibrary.repository.RegalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class RegalService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RegalRepository regalRepository;

    public Regal getRegalById(Long id) {
        log.info("Trying to find regal by id={}", id);
        Optional<Regal> regal = regalRepository.findById(id);
        if (regal.isEmpty()) {
            throw new NotFoundException("Regal not found");
        }
        return regal.get();
    }

    public Regal saveRegal(Regal regal) {
        log.info("Trying to save regal='{}'", regal);
        return regalRepository.save(regal);
    }

    public void deleteRegalById(Long id) {
        log.info("Trying to delete regal by id={}", id);
        Optional<Regal> regal = regalRepository.findById(id);
        if (regal.isEmpty()) {
            throw new NotFoundException("Regal not found");
        }
        regalRepository.deleteById(id);
    }

    public ArrayList<Regal> findRegalByName(String name) {
        log.info("Trying to find regal by name like '{}'", name);
        return  new ArrayList<>(regalRepository.findRegalByName(name));
    }

    public ArrayList<Book> findBookOnRegalByBookName(Long id, String name){
        log.info("Trying to find book on regal={} by book like '{}'", id, name);
        return  new ArrayList<>(regalRepository.findBookOnRegalByBookName(id, name));
    }

    public ArrayList<Book> findBookOnRegalByGenreName(Long id, String name) {
        log.info("Trying to find book on regal={} by genre like '{}'", id, name);
        return  new ArrayList<>(regalRepository.findBookOnRegalByGenreName(id, name));
    }

    public ArrayList<Book> findBookOnRegalByAuthorInfo(Long id, String name) {
        log.info("Trying to find book on regal={} by author like '{}'", id, name);
        return  new ArrayList<>(regalRepository.findBookOnRegalByAuthorInfo(id, name));
    }

    public Regal addBookToRegal(Long bookId, Long regalId) {
        Optional<Regal> optionalRegal = regalRepository.findById(regalId);
        if (optionalRegal.isEmpty()) {
            throw new NotFoundException("Regal not found");
        }
        Regal regal = optionalRegal.get();
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            throw new NotFoundException("Book not found");
        }
        Book book = optionalBook.get();
        List<Book> newBookList = regal.getBooks();
        if (newBookList.contains(book)) {
            throw new ConflictException("Book is already on this regal");
        }
        newBookList.add(book);
        regal.setBooks(newBookList);
        saveRegal(regal);
        return regal;
    }

    public Regal deleteBookFromRegal(Long bookId, Long regalId) {
        Optional<Regal> optionalRegal = regalRepository.findById(regalId);
        if (optionalRegal.isEmpty()) {
            throw new NotFoundException("Regal not found");
        }
        Regal regal = optionalRegal.get();
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            throw new NotFoundException("Book not found");
        }
        Book book = optionalBook.get();
        List<Book> newBookList = regal.getBooks();
        if (!newBookList.contains(book)) {
            throw new NotFoundException("Book is not on regal");
        }
        newBookList.remove(book);
        regal.setBooks(newBookList);
        regalRepository.save(regal);
        return regal;
    }
}
