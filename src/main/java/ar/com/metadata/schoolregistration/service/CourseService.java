package ar.com.metadata.schoolregistration.service;

import ar.com.metadata.schoolregistration.entity.Course;
import ar.com.metadata.schoolregistration.entity.Student;
import ar.com.metadata.schoolregistration.exception.MaximumReachedException;
import ar.com.metadata.schoolregistration.exception.RecordNotFoundException;
import ar.com.metadata.schoolregistration.repository.CourseRepository;
import ar.com.metadata.schoolregistration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Value("${app.validation.students.maximum}")
    private int studentsMaximum;
    @Value("${app.validation.courses.maximum}")
    private int coursesMaximum;

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourse() {
        return (List<Course>) courseRepository.findAll();
    }

    public Course updateCourse(Course course, Long courseId) {
        Course courseFound = courseRepository.findById(courseId)
                .orElseThrow(() -> new RecordNotFoundException("Course with ID: " + courseId + " not found to Update"));

        course.setCourseId(courseFound.getCourseId());
        return courseRepository.save(course);
    }

    public Course getCourseById(Long courseId) {
        Course courseFound = courseRepository.findById(courseId)
                .orElseThrow(() -> new RecordNotFoundException("Course with ID: " + courseId + " not found"));
        return courseFound;
    }

    public void deleteCourseById(Long courseId) {
        Course courseFound = courseRepository.findById(courseId)
                .orElseThrow(() -> new RecordNotFoundException("Course with ID: " + courseId + " not found to Delete"));
        courseRepository.deleteById(courseFound.getCourseId());
    }


    public Student registerStudentToCourse(Long courseId, Long studentId) {

        Student studentFound = studentRepository.findById(studentId)
                .orElseThrow(() -> new RecordNotFoundException("Student with ID: " + studentId + " not found to Enrolled"));
        Course courseFound = courseRepository.findById(courseId)
                .orElseThrow(() -> new RecordNotFoundException("Course with ID: " + courseId + " not found to Enrolled"));

        handleMaximumReached(studentFound, courseFound);

        Set<Course> courses = studentFound.getEnrolledCourses();
        courses.add(courseFound);
        studentFound.setEnrolledCourses(courses);
        return studentRepository.save(studentFound);
    }

    private void handleMaximumReached(Student studentFound, Course courseFound) {
        if (studentFound.getEnrolledCourses().size() >= coursesMaximum) {
            throw new MaximumReachedException("Student with ID: " + studentFound.getStudentId() + " reached maximum. A student can register to " + coursesMaximum + " course maximum");
        }
        if (courseFound.getEnrolled().size() >= studentsMaximum) {
            throw new MaximumReachedException("Course with ID: " + courseFound.getCourseId() + " reached maximum. A course has " + studentsMaximum + " students maximum");
        }
    }

    public List<Student> getAllStudentsByCourseId(Long courseId) {
        Course courseFound = courseRepository.findById(courseId)
                .orElseThrow(() -> new RecordNotFoundException("Course with ID: " + courseId + " not found to Filter all students with a specific course"));
        return studentRepository.getAllStudentsByCourseId(courseFound.getCourseId());
    }

    public List<Course> getAllCoursesWithOutStudents() {
        return courseRepository.getAllCoursesWithOutStudents();
    }
}

