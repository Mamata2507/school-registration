package ar.com.metadata.schoolregistration.unit.repository;

import ar.com.metadata.schoolregistration.entity.Course;
import ar.com.metadata.schoolregistration.entity.Student;
import ar.com.metadata.schoolregistration.repository.StudentRepository;
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
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;
    private Student studentTest;
    private static final Long ID_STUDENT = 1L;
    private static final String NAME_STUDENT = "Rommel Torrez Test";
    private static final String DESCRIPTION_STUDENT = "Student for Unit Test";
    private static final String DOCUMENT_STUDENT = "95785421";

    @Before
    public void setUp() {
        Set<Course> enrolledCourses = new HashSet<>();
        studentTest = new Student(ID_STUDENT, NAME_STUDENT, DESCRIPTION_STUDENT, DOCUMENT_STUDENT, enrolledCourses);

    }

    @Test
    @Order(1)
    @Rollback(false)
    public void saveStudentAndFindById() {
        studentTest = studentRepository.save(studentTest);
        Optional<Student> courseFound = studentRepository.findById(studentTest.getStudentId());
        Student studentEntity = courseFound.get();
        assertThat(studentEntity).isEqualTo((studentTest));
    }

    @Test
    @Order(2)
    public void getListStudents() {
        List<Student> students = studentRepository.findAll();
        assertThat(students).size().isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void updateStudentName() {
        Student studentFound = studentRepository.findStudentByStudentName(NAME_STUDENT);
        studentFound.setStudentName("Claudio Torrez");
        studentRepository.save(studentFound);

        Student updatedStudent = studentRepository.findStudentByStudentName("Claudio Torrez");

        assertThat(updatedStudent.getStudentName()).isEqualTo("Claudio Torrez");
    }

    @Test
    @Order(4)
    @Rollback(false)
    public void deleteStudent() {
        Student studentFound = studentRepository.findStudentByStudentName(NAME_STUDENT);

        studentRepository.deleteById(studentFound.getStudentId());

        Student deletedStudent = studentRepository.findStudentByStudentName(NAME_STUDENT);

        assertThat(deletedStudent).isNull();

    }

}
