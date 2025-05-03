package com.example.olibrary.service;

import com.example.olibrary.model.Genre;
import com.example.olibrary.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }
    public ArrayList<Genre> findGenreByName(String name) {
        return genreRepository.findGenreByName(name);
    }

    public Genre saveGenre(Genre author) {
        return genreRepository.save(author);
    }

    public void deleteGenreById(Long id) {
        genreRepository.deleteById(id);
    }
}
