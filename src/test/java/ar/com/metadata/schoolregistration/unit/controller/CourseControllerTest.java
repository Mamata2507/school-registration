package ar.com.metadata.schoolregistration.unit.controller;

import ar.com.metadata.schoolregistration.controller.CourseController;
import ar.com.metadata.schoolregistration.entity.Course;
import ar.com.metadata.schoolregistration.entity.Student;
import ar.com.metadata.schoolregistration.service.CourseService;
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
@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    CourseService courseService;

    @Test
    public void getAllCourseTest() throws Exception {
        Set<Student> enrolled = new HashSet<>();
        Course course1 = new Course(1l, "Math 1", "Course Math 1", enrolled);
        Course course2 = new Course(2l, "Math 2", "Course Math 2", enrolled);
        Course course3 = new Course(3l, "Math 3", "Course Math 3", enrolled);
        List<Course> courses = new ArrayList<>(Arrays.asList(course1, course2, course3));

        when(courseService.getAllCourse()).thenReturn(courses);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/courses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].courseName", is("Math 3")));
    }

}
