package com.example.olibrary.controller;

import com.example.olibrary.model.Genre;
import com.example.olibrary.service.GenreService;
import com.example.olibrary.dto.genre.GenreCreateRequest;
import com.example.olibrary.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthUtils authUtils;
    @GetMapping("/{genreId}")
    public Genre getGenreById(@PathVariable Long genreId) {
        authUtils.isAllowed("GET /genres/{genreId}");
        return genreService.getGenreById(genreId);
    }

    @DeleteMapping("/{genreId}")
    public void deleteGenreById(@PathVariable Long genreId) {
        authUtils.isAllowed("DELETE /genres/{genreId}");
        genreService.deleteGenreById(genreId);
    }

    @PostMapping("/create")
    public Genre createGenre(@RequestBody @Validated GenreCreateRequest request) {
        authUtils.isAllowed("POST /genres/create");
        return genreService.saveGenre(request.makeGenre());
    }
}
