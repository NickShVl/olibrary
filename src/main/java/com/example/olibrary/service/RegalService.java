package com.example.olibrary.service;

import com.example.olibrary.model.Book;
import com.example.olibrary.model.Regal;
import com.example.olibrary.repository.RegalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Slf4j
@Service
public class RegalService {
    @Autowired
    private RegalRepository regalRepository;

    public Regal getRegalById(Long id) {
        log.info("Trying to find regal by id={}", id);
        return regalRepository.findById(id).orElse(null);
    }

    public Regal saveRegal(Regal regal) {
        log.info("Trying to save regal='{}'", regal);
        return regalRepository.save(regal);
    }

    public void deleteRegalById(Long id) {
        log.info("Trying to delete regal by id={}", id);
        regalRepository.deleteById(id);
    }

    ArrayList<Regal> findRegalByName(String name) {
        log.info("Trying to find regal by name like '{}'", name);
        return regalRepository.findRegalByName(name);
    }

    ArrayList<Book> findBookOnRegalByBookName(Long id, String name){
        log.info("Trying to find book on regal={} by book like '{}'", id, name);
        return regalRepository.findBookOnRegalByBookName(id, name);
    }

    ArrayList<Book> findBookOnRegalByGenreName(Long id, String name) {
        log.info("Trying to find book on regal={} by genre like '{}'", id, name);
        return regalRepository.findBookOnRegalByGenreName(id, name);
    }

    ArrayList<Book> findBookOnRegalByAuthorInfo(Long id, String name) {
        log.info("Trying to find book on regal={} by author like '{}'", id, name);
        return regalRepository.findBookOnRegalByAuthorInfo(id, name);
    }
}
