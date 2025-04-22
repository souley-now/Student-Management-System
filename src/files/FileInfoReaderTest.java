import courses.Course;
import org.junit.jupiter.api.Test;
import roles.Admin; 
import roles.Professor; 
import roles.Student;
import files.FileInfoReader;

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

        Student student = fileInfoReader.getStudentByUsername("testStudent01");
        assertNotNull(student);
        assertEquals("testStudent01", student.getUsername());
    }

    @Test
    public void testGetProfessorByUsername() {
        FileInfoReader fileInfoReader = new FileInfoReader();
        fileInfoReader.loadAllData();

        Professor professor = fileInfoReader.getProfessorByUsername("Greenberg");
        assertNotNull(professor);
        assertEquals("Greenberg", professor.getUsername());
    }

    @Test
    public void testGetAdminByUsername() {
        FileInfoReader fileInfoReader = new FileInfoReader();
        fileInfoReader.loadAllData();

        Admin admin = fileInfoReader.getAdminByUsername("admin01");
        assertNotNull(admin);
        assertEquals("admin01", admin.getUsername());
    }
}
