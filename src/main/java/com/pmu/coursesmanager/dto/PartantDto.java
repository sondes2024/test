package com.pmu.coursesmanager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PartantDto {

    @NotNull
    @Min(1)
    private Integer numero;
    @NotBlank
    private String nom;

}
