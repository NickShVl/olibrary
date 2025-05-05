package com.example.olibrary.dto.user;

import com.example.olibrary.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
@Data
@Schema(description = "Create user")
public class UserCreateRequest {
    @NotBlank
    @Schema(description = "Псевдоним", example = "Максим Горький")
    private String username;
    @Email
    @Schema(description = "e-mail", example = "example@example.ru")
    private String email;
    @NotBlank
    @Schema(description = "Пароль", example = "password")
    private String password;
    @Schema(description = "Имя", example = "Алексей")
    private String firstName;
    @Schema(description = "Фамилия", example = "Пешков")
    private String lastName;
    @Schema(description = "Отчество", example = "Максимович")
    private String middleName;
    @Schema(description = "Дата рождения", example = "1868-03-16")
    private Date birthDate;
    @Schema(description = "Дата смерти", example = "1936-06-18")
    private Date accountCreated;
    public UserCreateRequest(
            String username,
            String email,
            String password,
            String firstName,
            String lastName,
            String middleName,
            Date birthDate,
            Date accountCreated
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.accountCreated = (Date)accountCreated.clone();
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = (Date)birthDate.clone();
    }

    public User makeUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEMail(email);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setBirthDate(birthDate);
        user.setAccountCreated(accountCreated);
        return user;
    }
}
