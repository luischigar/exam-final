package com.exam.daw.domain.dto;

import com.exam.daw.domain.enums.SpecialtyEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDto {

    private Long id;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "specialty is required")
    @Enumerated(EnumType.STRING)
    private SpecialtyEnum specialty;

    @NotNull(message = "dateAdmission is required")
    private LocalDate dateAdmission;
}
