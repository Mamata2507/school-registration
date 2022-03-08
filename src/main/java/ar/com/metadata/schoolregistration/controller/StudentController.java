package ar.com.metadata.schoolregistration.controller;

import ar.com.metadata.schoolregistration.entity.Student;
import ar.com.metadata.schoolregistration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {
        Student studentSaved = studentService.saveStudent(student);
        return new ResponseEntity<Student>(studentSaved, HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<List> getAllStudent() {
        return new ResponseEntity<List>(studentService.getAllStudent(), HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long studentId) {
        Student studentFound = studentService.getStudentById(studentId);
        return new ResponseEntity<Student>(studentFound, HttpStatus.OK);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") Long studentId) {
        Student studentUpdated = studentService.updateStudent(student, studentId);
        return new ResponseEntity<Student>(studentUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long studentId) {
        studentService.deleteStudentById(studentId);
        return "Student with ID: " + studentId + " deleted successfully";
    }

    //Filter all courses for a specific student
    @GetMapping("/students/{id}/courses")
    public ResponseEntity<List> getAllCoursesByStudentId(@PathVariable("id") Long studentId) {
        return new ResponseEntity<List>(studentService.getAllCoursesByStudentId(studentId), HttpStatus.OK);
    }

    //Filter all students without any courses
    @GetMapping("/students/nocourses")
    public ResponseEntity<List> getAllStudentsWithOutCourses() {
        return new ResponseEntity<List>(studentService.getAllStudentsWithOutCourses(), HttpStatus.OK);
    }
}
