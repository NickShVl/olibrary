package com.example.olibrary.dto.book;

import com.example.olibrary.model.Author;
import com.example.olibrary.model.Book;
import com.example.olibrary.model.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Data
@Schema(description = "Create book")
public class BookCreateRequest {
    @NotBlank
    @Schema(description = "Название книги", example = "Преступление и наказание")
    private String name;
    @Schema(description = "Описание", example = "Магия, драконы, сокровища и приключения")
    private String description;

    @Schema(description = "id авторов", example = "[1,2,3,4,5]")
    private ArrayList<Long> authorsId;

    @Schema(description = "id жанров", example = "[1,2,3,4,5]")
    private ArrayList<Long> genresId;

    @Schema(description = "Часть книги", example = "Тварь я дрожащая, или право имею")
    private String bookPart;
    public BookCreateRequest(
            String name,
            String description,
            ArrayList<Long> authorsId,
            ArrayList<Long> genresId,
            String bookPart
    ) {
        this.name = name;
        this.description = description;
        this.authorsId = new ArrayList<>(authorsId);
        this.genresId = new ArrayList<>(genresId);
        this.bookPart = bookPart;
    }

    public Book makeBook() {
        Book book = new Book();
        book.setName(name);
        book.setDescription(description);
        book.setBookPart(bookPart);
        List<Author> authors = new ArrayList<>();
        for(Long authorId : authorsId) {
            Author a = new Author();
            a.setId(authorId);
            authors.add(a);
        }
        authors = new ArrayList<>(new TreeSet<>(authors));
        book.setAuthors(authors);
        List<Genre> genres = new ArrayList<>();
        for(Long genreId : genresId) {
            Genre a = new Genre();
            a.setId(genreId);
            genres.add(a);
        }
        genres = new ArrayList<>(new TreeSet<>(genres));
        book.setGenres(genres);
        return book;
    }
}
