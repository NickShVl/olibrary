package com.example.olibrary.controller;

import com.example.olibrary.exceptions.NotFoundException;
import com.example.olibrary.model.Author;
import com.example.olibrary.service.AuthorService;
import com.example.olibrary.dto.author.AuthorCreateRequest;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    @DeleteMapping("/{authorId}")
    public void deleteAuthorById(@PathVariable Long authorId) {
        authorService.deleteAuthorById(authorId);
    }

    @PostMapping("/create")
    public Author createAuthor(@RequestBody @Validated AuthorCreateRequest request) {
        return authorService.saveAuthor(request.makeAuthor());
    }
}
