package com.example.olibrary.dto.session;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "Create session")
@AllArgsConstructor
public class CreateSessionRequest {
    @NotBlank
    @Schema(description = "userId", example = "1")
    private Long userId;
    @Schema(description = "bookId", example = "1")
    private Long bookId;
}
