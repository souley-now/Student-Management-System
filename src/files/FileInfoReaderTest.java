package files;
import courses.Course;
import org.junit.jupiter.api.Test;
import roles.Admin; // Corrected import
import roles.Professor; // Corrected import
import roles.Student; // Corrected import

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class FileInfoReaderTest {

    @Test
    public void testLoadAllData() {
        FileInfoReader fileInfoReader = new FileInfoReader();
        fileInfoReader.loadAllData();

        assertFalse(fileInfoReader.getCourses().isEmpty());
        assertFalse(fileInfoReader.getAdmins().isEmpty());
        assertFalse(fileInfoReader.getStudents().isEmpty());
        assertFalse(fileInfoReader.getProfessors().isEmpty());
    }

    @Test
    public void testGetCourseById() {
        FileInfoReader fileInfoReader = new FileInfoReader();
        fileInfoReader.loadAllData();

        Course course = fileInfoReader.getCourseById("CIT591");
        assertNotNull(course);
        assertEquals("CIT591", course.getCourseCode());
    }

    @Test
    public void testGetStudentByUsername() {
        FileInfoReader fileInfoReader = new FileInfoReader();
        fileInfoReader.loadAllData();

        Student student = fileInfoReader.getStudentByUsername("student1");
        assertNotNull(student);
        assertEquals("student1", student.getUsername());
    }

    @Test
    public void testGetProfessorByUsername() {
        FileInfoReader fileInfoReader = new FileInfoReader();
        fileInfoReader.loadAllData();

        Professor professor = fileInfoReader.getProfessorByUsername("prof1");
        assertNotNull(professor);
        assertEquals("prof1", professor.getUsername());
    }

    @Test
    public void testGetAdminByUsername() {
        FileInfoReader fileInfoReader = new FileInfoReader();
        fileInfoReader.loadAllData();

        Admin admin = fileInfoReader.getAdminByUsername("admin1");
        assertNotNull(admin);
        assertEquals("admin1", admin.getUsername());
    }
}
