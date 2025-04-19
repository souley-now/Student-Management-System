package roles;

import files.FileInfoReader;
import org.junit.jupiter.api.Test; // Ensure this import works after adding JUnit to the project

import static org.junit.jupiter.api.Assertions.*; // Ensure this import works after adding JUnit to the project

import java.util.List;

public class RoleTests {

    @Test
    public void testStudentAddAndDropCourse() {
        FileInfoReader fileInfoReader = new FileInfoReader();
        fileInfoReader.loadAllData();
        Student student = new Student("1", "John Doe", "student1", "password", "", fileInfoReader);

        assertTrue(student.addCourse("CIT591"));
        assertFalse(student.addCourse("CIT591")); // Already enrolled
        assertTrue(student.dropCourse("CIT591"));
        assertFalse(student.dropCourse("CIT591")); // Already dropped
    }

    @Test
    public void testStudentGetEnrolledCourses() {
        FileInfoReader fileInfoReader = new FileInfoReader();
        fileInfoReader.loadAllData();
        Student student = new Student("1", "John Doe", "student1", "password", "", fileInfoReader);

        student.addCourse("CIT591");
        List<String> enrolledCourses = student.getEnrolledCourses();
        assertEquals(1, enrolledCourses.size());
        assertTrue(enrolledCourses.contains("CIT591"));
    }

    @Test
    public void testProfessorIsTeachingCourse() {
        Professor professor = new Professor("1", "Dr. Smith", "prof1", "password");
        professor.getCoursesTaught().add("CIT591");

        assertTrue(professor.isTeachingCourse("CIT591"));
        assertFalse(professor.isTeachingCourse("CIT592"));
    }

    @Test
    public void testAdminFunctionality() {
        Admin admin = new Admin("1", "Admin User", "admin1", "password");
        assertEquals("Admin User", admin.getName());
        assertEquals("admin1", admin.getUsername());
    }
}
