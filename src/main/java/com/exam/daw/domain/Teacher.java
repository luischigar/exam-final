package com.exam.daw.domain;

import com.exam.daw.domain.enums.SpecialtyEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false,updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Column( name = "specialty",nullable = false)
    @Enumerated(EnumType.STRING)
    private SpecialtyEnum specialty;

    @Column(name = "date_admission",nullable = false)
    private LocalDate dateAdmission;
}
