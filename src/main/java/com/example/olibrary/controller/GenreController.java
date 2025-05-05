package com.example.olibrary.controller;

import com.example.olibrary.model.Genre;
import com.example.olibrary.service.GenreService;
import com.example.olibrary.dto.genre.GenreCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;
    @GetMapping("/{genreId}")
    public Genre getGenreById(@PathVariable Long genreId) {
        return genreService.getGenreById(genreId);
    }

    @DeleteMapping("/{genreId}")
    public void deleteGenreById(@PathVariable Long genreId) {
        genreService.deleteGenreById(genreId);
    }

    @PostMapping("/create")
    public Genre createGenre(@RequestBody @Validated GenreCreateRequest request) {
        return genreService.saveGenre(request.makeGenre());
    }
}
