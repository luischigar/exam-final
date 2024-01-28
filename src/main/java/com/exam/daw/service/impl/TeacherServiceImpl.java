package com.exam.daw.service.impl;

import com.exam.daw.domain.Teacher;
import com.exam.daw.domain.dto.TeacherDto;
import com.exam.daw.exeption.ResourceNotFoundException;
import com.exam.daw.repository.TeacherRepository;
import com.exam.daw.service.TeacherService;
import com.exam.daw.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public TeacherDto getTeacherByIdTeacher(Long idTeacher) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(idTeacher);
        if (teacherOptional.isPresent()){
            return convertEntityToDto(teacherOptional.get());
        }else {
            throw new ResourceNotFoundException("Teacher not found");
        }
    }

    @Override
    public TeacherDto saveTeacher(TeacherDto teacherDto) {
        return convertEntityToDto(
                teacherRepository.save(
                        convertDtoToEntity(teacherDto)
                )
        );
    }

    @Override
    public void deleteTeacherByIdTeacher(Long idTeacher) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(idTeacher);
        if (teacherOptional.isPresent()){
            teacherRepository.delete(teacherOptional.get());
        }else {
            throw new ResourceNotFoundException("Teacher not found");
        }
    }

    private TeacherDto convertEntityToDto(Teacher entity) {
        return Mapper.modelMapper().map(entity, TeacherDto.class);
    }

    private Teacher convertDtoToEntity(TeacherDto dto) {
        return Mapper.modelMapper().map(dto, Teacher.class);
    }
}
