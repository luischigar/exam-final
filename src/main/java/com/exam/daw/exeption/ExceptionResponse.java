package com.exam.daw.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private LocalDate timestamp;
    private String message;
    private String details;
}