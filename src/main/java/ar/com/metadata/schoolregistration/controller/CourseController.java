package ar.com.metadata.schoolregistration.controller;

import ar.com.metadata.schoolregistration.entity.Course;
import ar.com.metadata.schoolregistration.entity.Student;
import ar.com.metadata.schoolregistration.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping("/courses")
    public ResponseEntity<Course> saveCourse(@Valid @RequestBody Course course) {
        Course courseSaved = courseService.saveCourse(course);
        return new ResponseEntity<Course>(courseSaved, HttpStatus.OK);
    }

    @GetMapping("/courses")
    public ResponseEntity<List> getAllCourse() {
        return new ResponseEntity<List>(courseService.getAllCourse(), HttpStatus.OK);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable("id") Long courseId) {
        Course courseUpdated = courseService.updateCourse(course, courseId);
        return new ResponseEntity<Course>(courseUpdated, HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") Long courseId) {
        Course courseFound = courseService.getCourseById(courseId);
        return new ResponseEntity<Course>(courseFound, HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    public String deleteCourseById(@PathVariable("id") Long courseId) {
        courseService.deleteCourseById(courseId);
        return "Course with ID: " + courseId + " deleted successfully";
    }

    // Register to courses
    @PutMapping("/courses/{courseId}/students/{studentId}")
    public ResponseEntity<Student> registerStudentToCourse(@PathVariable("courseId") Long courseId, @PathVariable("studentId") Long studentId) {
        Student courseUpdated = courseService.registerStudentToCourse(courseId, studentId);
        return new ResponseEntity<Student>(courseUpdated, HttpStatus.OK);
    }

    //Filter all students with a specific course
    @GetMapping("/courses/{id}/students")
    public ResponseEntity<List> getAllStudentsByCourseId(@PathVariable("id") Long courseId) {
        return new ResponseEntity<List>(courseService.getAllStudentsByCourseId(courseId), HttpStatus.OK);
    }

    //Filter all courses without any students
    @GetMapping("/courses/nostudents")
    public ResponseEntity<List> getAllCoursesWithOutStudents() {
        return new ResponseEntity<List>(courseService.getAllCoursesWithOutStudents(), HttpStatus.OK);
    }
}
