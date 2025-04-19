package courses;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CourseTest {

    @Test
    public void testAddStudent() {
        Course course = new Course("CIT591", "Introduction to Software Development", "Dr. Smith", "MW", "10:00", "11:30", 2);
        assertTrue(course.addStudent("student1"));
        assertTrue(course.addStudent("student2"));
        assertFalse(course.addStudent("student3")); // Exceeds capacity
    }

    @Test
    public void testRemoveStudent() {
        Course course = new Course("CIT591", "Introduction to Software Development", "Dr. Smith", "MW", "10:00", "11:30", 2);
        course.addStudent("student1");
        assertTrue(course.removeStudent("student1"));
        assertFalse(course.removeStudent("student2")); // Not enrolled
    }

    @Test
    public void testHasTimeConflict() {
        Course course1 = new Course("CIT591", "Intro to Software", "Dr. Smith", "MW", "10:00", "11:30", 2);
        Course course2 = new Course("CIT592", "Data Structures", "Dr. Brown", "MW", "11:00", "12:30", 2);
        Course course3 = new Course("CIT593", "Algorithms", "Dr. White", "TR", "10:00", "11:30", 2);

        assertTrue(course1.hasTimeConflict(course2)); // Overlapping time
        assertFalse(course1.hasTimeConflict(course3)); // Different days
    }

    @Test
    public void testGetEnrolledStudents() {
        Course course = new Course("CIT591", "Introduction to Software Development", "Dr. Smith", "MW", "10:00", "11:30", 2);
        course.addStudent("student1");
        course.addStudent("student2");
        List<String> enrolledStudents = course.getEnrolledStudents();
        assertEquals(2, enrolledStudents.size());
        assertTrue(enrolledStudents.contains("student1"));
        assertTrue(enrolledStudents.contains("student2"));
    }
}
