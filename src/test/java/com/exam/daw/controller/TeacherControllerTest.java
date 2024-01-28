package com.exam.daw.controller;

import com.exam.daw.ExamApplication;
import com.exam.daw.domain.Teacher;
import com.exam.daw.domain.dto.TeacherDto;
import com.exam.daw.domain.enums.SpecialtyEnum;
import com.exam.daw.repository.TeacherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ExamApplication.class
)
@AutoConfigureMockMvc
class TeacherControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private TeacherRepository teacherRepository;
    private static final Long ID = 1L;
    private final ObjectMapper mapperJson = new ObjectMapper();

    private Teacher teacher;
    private TeacherDto teacherDto;

    @BeforeEach
    void setUp(){
        teacher = new Teacher(1L,"Luis", SpecialtyEnum.ART, LocalDate.now());
        teacherDto = new TeacherDto(1L, null, SpecialtyEnum.ART,null);
    }

    @Test
    void getTeacherById() throws Exception {

        Mockito.when(teacherRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(teacher));
        mvc.perform(MockMvcRequestBuilders.get("/teachers/"+ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Luis")))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Mockito.when(teacherRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.ofNullable(null));
        mvc.perform(MockMvcRequestBuilders.get("/teachers/"+ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isExpectationFailed())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Teacher not found")));
    }

    @Test
    void saveTeacher() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapperJson.writeValueAsString(teacherDto)))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }
}
