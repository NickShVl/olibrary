package com.example.olibrary.service;

import com.example.olibrary.exceptions.NotFoundException;
import com.example.olibrary.model.Genre;
import com.example.olibrary.repository.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public Genre getGenreById(Long id) {
        log.info("Trying to find genre by id={}", id);
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isEmpty()) {
            throw new NotFoundException("Genre not found");
        }
        return genre.get();
    }
    public ArrayList<Genre> findGenreByName(String name) {
        log.info("Trying to find genre by name like '{}'", name);
        return  new ArrayList<>(genreRepository.findGenreByName(name));
    }

    public Genre saveGenre(Genre genre) {
        log.info("Trying to save genre='{}'", genre);
        return genreRepository.save(genre);
    }

    public void deleteGenreById(Long id) {
        log.info("Trying to delete by id={}", id);
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isEmpty()) {
            throw new NotFoundException("Genre not found");
        }
        genreRepository.deleteById(id);
    }
}
