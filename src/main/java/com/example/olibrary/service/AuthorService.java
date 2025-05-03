package com.example.olibrary.service;

import com.example.olibrary.model.Author;
import com.example.olibrary.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Slf4j
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author getAuthorById(Long id) {
        log.info("Trying to find author by id={}", id);
        return authorRepository.findById(id).orElse(null);
    }
    public ArrayList<Author> findAuthorsBySomeString(String info) {
        log.info("Trying to find author by info like '{}'", info);
        return authorRepository.findAuthorsBySomeInformation(info);
    }

    public Author saveAuthor(Author author) {
        log.info("Trying to save author='{}'", author);
        return authorRepository.save(author);
    }

    public void deleteAuthorById(Long id) {
        log.info("Trying to delete author by id={}", id);
        authorRepository.deleteById(id);
    }

}
