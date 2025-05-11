package com.example.olibrary.dto.auth;

import com.example.olibrary.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@Schema(description = "Login")
public class RegisterRequest {
    @NotBlank
    @Schema(description = "Логин", example = "username")
    private String username;
    @NotBlank
    @Schema(description = "Пароль", example = "password")
    private String password;
    @NotBlank
    @Email
    @Schema(description = "e-mail", example = "example@example.org")
    private String email;
    @NotBlank
    @Schema(description = "Дата рождения", example = "1868-03-16")
    private Date birthDate;
    public RegisterRequest(
            String username,
            String email,
            String password,
            Date birthDate
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthDate = (Date)birthDate.clone();
    }
    public User makeUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEMail(email);
        user.setBirthDate(birthDate);
        user.setAccountCreated(new Date());
        return user;
    }
}
