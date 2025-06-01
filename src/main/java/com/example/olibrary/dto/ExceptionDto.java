package com.example.olibrary.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "Exception")
@AllArgsConstructor
public class ExceptionDto {
    @Schema(description = "Описание ошибки", example = "Что-то определённо пошло не так")
    private String description;
}
