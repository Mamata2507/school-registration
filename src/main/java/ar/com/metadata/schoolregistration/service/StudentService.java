package ar.com.metadata.schoolregistration.service;

import ar.com.metadata.schoolregistration.entity.Course;
import ar.com.metadata.schoolregistration.entity.Student;
import ar.com.metadata.schoolregistration.exception.RecordNotFoundException;
import ar.com.metadata.schoolregistration.repository.CourseRepository;
import ar.com.metadata.schoolregistration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudent() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student updateStudent(Student student, Long studentId) {
        Student studentFound = studentRepository.findById(studentId)
                .orElseThrow(() -> new RecordNotFoundException("Student with ID: " + studentId + " not found to Update"));

        student.setStudentId(studentFound.getStudentId());
        return studentRepository.save(student);
    }

    public void deleteStudentById(Long studentId) {
        Student studentFound = studentRepository.findById(studentId)
                .orElseThrow(() -> new RecordNotFoundException("Student with ID: " + studentId + " not found to Delete"));
        studentRepository.deleteById(studentFound.getStudentId());
    }

    public Student getStudentById(Long studentId) {
        Student studentFound = studentRepository.findById(studentId)
                .orElseThrow(() -> new RecordNotFoundException("Student with ID: " + studentId + " not found"));
        return studentFound;
    }

    public List<Course> getAllCoursesByStudentId(Long studentId) {
        Student studentFound = studentRepository.findById(studentId)
                .orElseThrow(() -> new RecordNotFoundException("Course with ID: " + studentId + " not found to Filter all courses for a specific student"));
        return courseRepository.getAllCoursesByStudentId(studentFound.getStudentId());
    }

    public List<Student> getAllStudentsWithOutCourses() {
        return studentRepository.getAllCoursesWithOutStudents();
    }
}

