package ar.com.metadata.schoolregistration.unit.controller;

import ar.com.metadata.schoolregistration.controller.StudentController;
import ar.com.metadata.schoolregistration.entity.Course;
import ar.com.metadata.schoolregistration.entity.Student;
import ar.com.metadata.schoolregistration.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    StudentService studentService;

    @Test
    public void getAllCourseTest() throws Exception {
        Set<Course> enrolledCourses = new HashSet<>();
        Student student1 = new Student(1l, "Rommel 1", "Street 1", "12345678", enrolledCourses);
        Student student2 = new Student(2l, "Rommel 2", "Street 2", "12345678", enrolledCourses);
        Student student3 = new Student(3l, "Rommel 3", "Street 3", "12345678", enrolledCourses);
        List<Student> students = new ArrayList<>(Arrays.asList(student1, student2, student3));

        when(studentService.getAllStudent()).thenReturn(students);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].studentName", is("Rommel 3")));
    }

}
