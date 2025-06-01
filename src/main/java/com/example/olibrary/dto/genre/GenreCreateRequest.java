package com.example.olibrary.dto.genre;

import com.example.olibrary.model.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Create genre")
public class GenreCreateRequest {
    @NotBlank
    @Schema(description = "Название жанра", example = "Фентези")
    private String name;
    @Schema(description = "Описание", example = "Магия, драконы, сокровища и приключения")
    private String description;

    public GenreCreateRequest(
            String name,
            String description
    ) {
        this.name = name;
        this.description = description;
    }

    public Genre makeGenre() {
        Genre genre = new Genre();
        genre.setName(name);
        genre.setDescription(description);
        return genre;
    }
}
