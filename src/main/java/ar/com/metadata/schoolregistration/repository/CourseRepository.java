package ar.com.metadata.schoolregistration.repository;

import ar.com.metadata.schoolregistration.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c JOIN c.enrolled r WHERE r.studentId= :studentId")
    public List<Course> getAllCoursesByStudentId(Long studentId);

    @Query("SELECT c FROM Course c LEFT JOIN c.enrolled r WHERE r.studentId IS NULL")
    public List<Course> getAllCoursesWithOutStudents();

    Course findCourseByCourseName(String nameCourse);

}

