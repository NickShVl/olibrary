package com.example.olibrary.dto.regal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "Add book to regal")
@AllArgsConstructor
public class RegalAddBookRequest {
    @Schema(description = "id книги", example = "1")
    private Long bookId;
    @Schema(description = "id полки", example = "1")
    private Long regalId;
}
