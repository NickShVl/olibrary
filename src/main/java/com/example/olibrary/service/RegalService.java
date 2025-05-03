package com.example.olibrary.service;

import com.example.olibrary.model.Book;
import com.example.olibrary.model.Regal;
import com.example.olibrary.repository.RegalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RegalService {
    @Autowired
    private RegalRepository regalRepository;

    public Regal getRegalById(Long id) {
        return regalRepository.findById(id).orElse(null);
    }

    public Regal saveRegal(Regal regal) {
        return regalRepository.save(regal);
    }

    public void deleteRegalById(Long id) {
        regalRepository.deleteById(id);
    }

    ArrayList<Regal> findRegalByName(String name) {
        return regalRepository.findRegalByName(name);
    }

    ArrayList<Book> findBookOnRegalByBookName(Long id, String name){
        return regalRepository.findBookOnRegalByBookName(id, name);
    }

    ArrayList<Book> findBookOnRegalByGenreName(Long id, String name) {
        return regalRepository.findBookOnRegalByGenreName(id, name);
    }

    ArrayList<Book> findBookOnRegalByAuthorInfo(Long id, String name) {
        return regalRepository.findBookOnRegalByAuthorInfo(id, name);
    }
}
