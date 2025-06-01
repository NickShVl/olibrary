package com.example.olibrary.dto.author;

import com.example.olibrary.model.Author;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
@Data
@Schema(description = "Create author")
public class AuthorCreateRequest {
    @Schema(description = "Псевдоним", example = "Максим Горький")
    private String alias;
    @Schema(description = "Имя", example = "Алексей")
    private String firstName;
    @Schema(description = "Фамилия", example = "Пешков")
    private String lastName;
    @Schema(description = "Отчество", example = "Максимович")
    private String middleName;
    @Schema(description = "Дата рождения", example = "1868-03-16")
    private Date birthDate;
    @Schema(description = "Дата смерти", example = "1936-06-18")
    private Date deathDate;
    @NotNull
    @Schema(description = "Век в котороый трудился писатель", example = "19")
    private int century;

    public AuthorCreateRequest(
            String alias,
            String firstName,
            String lastName,
            String middleName,
            Date birthDate,
            Date deathDate,
            int century) {
        this.alias = alias;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = (Date)birthDate.clone();
        this.deathDate = (Date)deathDate.clone();
        this.century = century;
    }

    public Author makeAuthor() {
        Author author = new Author();
        author.setAlias(alias);
        author.setCentury(century);
        author.setFirstName(firstName);
        author.setMiddleName(middleName);
        author.setLastName(lastName);
        author.setBirthDate(birthDate);
        author.setDeathDate(deathDate);
        return author;
    }
}
