package com.example.olibrary.service;

import com.example.olibrary.exceptions.NotFoundException;
import com.example.olibrary.model.Author;
import com.example.olibrary.repository.AuthorRepository;
import com.example.olibrary.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private UserUtils userUtils;

    public Author getAuthorById(Long id) {
        log.info("Trying to find author by id={}", id);
        Optional<Author> author = authorRepository.findById(id);
        if(author.isEmpty()) throw new NotFoundException("Author not found");
        return author.get();
    }
    public ArrayList<Author> findAuthorsBySomeString(String info) {
        log.info("Trying to find author by info like '{}'", info);
        return new ArrayList<>(authorRepository.findAuthorsBySomeInformation(info));
    }

    public Author saveAuthor(Author author) {
        log.info("Trying to save author='{}'", author);
        return authorRepository.save(author);
    }

    public void deleteAuthorById(Long id) {
        log.info("Trying to delete author by id={}", id);
        Optional<Author> author = authorRepository.findById(id);
        if(author.isEmpty()) {
            throw new NotFoundException("Author not found");
        }
        authorRepository.deleteById(id);
    }

}
