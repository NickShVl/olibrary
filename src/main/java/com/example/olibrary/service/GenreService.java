package com.example.olibrary.service;

import com.example.olibrary.model.Genre;
import com.example.olibrary.repository.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Slf4j
@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public Genre getGenreById(Long id) {
        log.info("Trying to find genre by id={}", id);
        return genreRepository.findById(id).orElse(null);
    }
    public ArrayList<Genre> findGenreByName(String name) {
        log.info("Trying to find genre by name like '{}'", name);
        return genreRepository.findGenreByName(name);
    }

    public Genre saveGenre(Genre genre) {
        log.info("Trying to save genre='{}'", genre);
        return genreRepository.save(genre);
    }

    public void deleteGenreById(Long id) {
        log.info("Trying to delete by id={}", id);
        genreRepository.deleteById(id);
    }
}
