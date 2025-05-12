package com.example.olibrary.controller;

import com.example.olibrary.model.Book;
import com.example.olibrary.service.BookService;
import com.example.olibrary.dto.book.BookCreateRequest;
import com.example.olibrary.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthUtils authUtils;
    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable Long bookId) {
        authUtils.isAllowed("GET /books/{bookId}");
        return bookService.getBookById(bookId);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBookById(@PathVariable Long bookId) {
        authUtils.isAllowed("DELETE /books/{bookId}");
        bookService.deleteBookById(bookId);
    }
    @PostMapping("/create")
    public Book createBook(@RequestBody @Validated BookCreateRequest request) {
        authUtils.isAllowed("POST /books/create");
        return bookService.createBook(request.makeBook());
    }
}
