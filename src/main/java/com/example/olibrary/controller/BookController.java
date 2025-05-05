package com.example.olibrary.controller;

import com.example.olibrary.exceptions.NotFoundException;
import com.example.olibrary.model.Book;
import com.example.olibrary.service.BookService;
import com.example.olibrary.dto.book.BookCreateRequest;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBookById(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
    }
    @PostMapping("/create")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "404", description = "Пользователь не найден",
            content = @Content(schema = @Schema(implementation =NotFoundException.class)))
    public Book createBook(@RequestBody @Validated BookCreateRequest request) {
        return bookService.createBook(request.makeBook());
    }
}
