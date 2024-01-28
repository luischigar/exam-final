package com.exam.daw.service;

import com.exam.daw.domain.dto.TeacherDto;
import com.exam.daw.exeption.ResourceNotFoundException;

public interface TeacherService {
    TeacherDto getTeacherByIdTeacher(Long idTeacher);
    TeacherDto saveTeacher(TeacherDto teacherDto);

    void deleteTeacherByIdTeacher(Long idTeacher);
}
