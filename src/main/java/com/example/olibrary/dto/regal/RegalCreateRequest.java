package com.example.olibrary.dto.regal;

import com.example.olibrary.model.Regal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Create regal")
public class RegalCreateRequest {
    @NotBlank
    @Schema(description = "Название полки", example = "Фентези")
    private String name;
    @Schema(description = "Описание", example = "Магия, драконы, сокровища и приключения")
    private String description;

    public RegalCreateRequest(
            String name,
            String description
    ) {
        this.name = name;
        this.description = description;
    }

    public Regal makeRegal() {
        Regal regal = new Regal();
        regal.setName(name);
        regal.setDescription(description);
        return regal;
    }
}
