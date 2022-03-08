package ar.com.metadata.schoolregistration.repository;

import ar.com.metadata.schoolregistration.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s JOIN s.enrolledCourses r WHERE r.courseId = :courseId")
    public List<Student> getAllStudentsByCourseId(Long courseId);

    @Query("SELECT s FROM Student s LEFT JOIN s.enrolledCourses r WHERE r.courseId IS NULL")
    public List<Student> getAllCoursesWithOutStudents();

    Student findStudentByStudentName(String nameStudent);
}

