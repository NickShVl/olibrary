package com.example.olibrary.controller;

import com.example.olibrary.model.Author;
import com.example.olibrary.service.AuthorService;
import com.example.olibrary.dto.author.AuthorCreateRequest;
import com.example.olibrary.utils.AuthUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthUtils authUtils;

    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable Long authorId) {
        authUtils.isAllowed("GET /authors/{authorId}");
        return authorService.getAuthorById(authorId);
    }

    @DeleteMapping("/{authorId}")
    public void deleteAuthorById(@PathVariable Long authorId, HttpServletRequest method) {
        authUtils.isAllowed("DELETE /authors/{authorId}");
        authorService.deleteAuthorById(authorId);
    }

    @PostMapping("/create")
    public Author createAuthor(@RequestBody @Validated AuthorCreateRequest request, HttpServletRequest method) {
        authUtils.isAllowed("POST /authors/create");
        return authorService.saveAuthor(request.makeAuthor());
    }
}
