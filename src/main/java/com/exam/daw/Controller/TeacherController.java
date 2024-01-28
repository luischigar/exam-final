package com.exam.daw.Controller;

import com.exam.daw.domain.dto.TeacherDto;
import com.exam.daw.exeption.ResourceNotFoundException;
import com.exam.daw.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/teachers")
@CrossOrigin(origins = "*")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable(value = "id") Long idTeacher) {
        return new ResponseEntity<>(teacherService.getTeacherByIdTeacher(idTeacher), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeacherDto> saveTeacher(@RequestBody @Valid TeacherDto teacherDto){
        return new ResponseEntity<>(teacherService.saveTeacher(teacherDto),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacherById(@PathVariable(value = "id") Long id) {
        teacherService.deleteTeacherByIdTeacher(id);
    }
}
