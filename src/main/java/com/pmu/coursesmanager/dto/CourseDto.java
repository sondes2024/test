package com.pmu.coursesmanager.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.pmu.coursesmanager.validation.MinSizeConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CourseDto {
    private Long id;
    @NotNull
    private Integer numero;
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    @NotEmpty
    private String nom;
    @NotEmpty
    @MinSizeConstraint
    private List<PartantDto> partants;
}
