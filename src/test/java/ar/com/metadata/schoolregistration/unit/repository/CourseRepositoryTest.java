package ar.com.metadata.schoolregistration.unit.repository;

import ar.com.metadata.schoolregistration.entity.Course;
import ar.com.metadata.schoolregistration.entity.Student;
import ar.com.metadata.schoolregistration.repository.CourseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;
    private Course mathematics;
    private static final Long ID_COURSE = 1L;
    private static final String NAME_COURSE = "Mathematics Test";
    private static final String DESCRIPTION_COURSE = "Course for Unit Test";

    @Before
    public void setUp() {
        Set<Student> enrolled = new HashSet<>();
        mathematics = new Course(ID_COURSE, NAME_COURSE, DESCRIPTION_COURSE, enrolled);

    }

    @Test
    @Order(1)
    @Rollback(false)
    public void saveCourseAndFindById() {
        mathematics = courseRepository.save(mathematics);
        Optional<Course> courseFound = courseRepository.findById(mathematics.getCourseId());
        Course courseEntity = courseFound.get();
        assertThat(courseEntity).isEqualTo((mathematics));
    }

    @Test
    @Order(2)
    public void getListCourses() {
        List<Course> courses = courseRepository.findAll();
        assertThat(courses).size().isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void updateCourseName() {
        Course courseFound = courseRepository.findCourseByCourseName(NAME_COURSE);
        courseFound.setCourseName("Mathematics 4");
        courseRepository.save(courseFound);

        Course updatedCourse = courseRepository.findCourseByCourseName("Mathematics 4");

        assertThat(updatedCourse.getCourseName()).isEqualTo("Mathematics 4");
    }

    @Test
    @Order(4)
    @Rollback(false)
    public void deleteCourse() {
        Course courseFound = courseRepository.findCourseByCourseName(NAME_COURSE);

        courseRepository.deleteById(courseFound.getCourseId());

        Course deletedCourse = courseRepository.findCourseByCourseName(NAME_COURSE);

        assertThat(deletedCourse).isNull();

    }

}
