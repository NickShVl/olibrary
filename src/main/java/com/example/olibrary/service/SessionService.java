package com.example.olibrary.service;

import com.example.olibrary.components.MinioComponent;
import com.example.olibrary.exceptions.ConflictException;
import com.example.olibrary.exceptions.NotFoundException;
import com.example.olibrary.model.Book;
import com.example.olibrary.model.Regal;
import com.example.olibrary.model.Session;
import com.example.olibrary.model.User;
import com.example.olibrary.repository.BookRepository;
import com.example.olibrary.repository.SessionRepository;
import com.example.olibrary.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MinioComponent minioComponent;

    public Session getSessionById(Long id) {
        log.info("Trying to find regal by id={}", id);
        Optional<Session> session = sessionRepository.findById(id);
        if (session.isEmpty()) {
            throw new NotFoundException("Regal not found");
        }
        return session.get();
    }

    public Session createSession(Long userId, Long bookId) {
        log.info("Trying to save session:userId='{}', bookId='{}'", userId, bookId);
        Session session = new Session();
        session.setStopPosition(0);
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        Optional<Book> book = bookRepository.findById(userId);
        if (book.isEmpty()) {
            throw new NotFoundException("Book not found");
        }
        session.setOwner(user.get());
        session.setLinkedBook(book.get());
        session = sessionRepository.save(session);
        return session;
    }

    public void deleteSessionById(Long id) {
        log.info("Trying to delete session by id={}", id);
        Optional<Session> session = sessionRepository.findById(id);
        if (session.isEmpty()) {
            throw new NotFoundException("Session not found");
        }
        sessionRepository.deleteById(id);
    }

    public int setNextPart(Long id) {
        Session session = getSessionById(id);
        session.setStopPosition(session.getStopPosition() + 1);
        sessionRepository.save(session);
        return session.getStopPosition();
    }

    public int setPreviousPart(Long id) {
        Session session = getSessionById(id);
        Integer nextPos = session.getStopPosition() - 1;
        if (nextPos < 0) {
            throw new ConflictException("Page not exist");
        }
        session.setStopPosition(nextPos);
        sessionRepository.save(session);
        return session.getStopPosition();
    }

    public String getBook(Long id) {
        Session session = getSessionById(id);
        Book book = session.getLinkedBook();
        return minioComponent.getObject(book.getBookFileName());
    }
}
