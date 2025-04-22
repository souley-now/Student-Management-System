import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import courses.Course;
import files.FileInfoReader;

/**
 * Represents a student in the student management system.
 */
public class Student extends User {
    private String courses; // Courses associated with the student
    private List<String> enrolledCourses = new ArrayList<>(); // List of enrolled course IDs
    private Map<String, String> grades = new HashMap<>(); // Map of course IDs to grades
    private FileInfoReader fileInfoReader; // Reference to FileInfoReader for course data

    /**
     * Constructs a new Student.
     *
     * @param id             The ID of the student.
     * @param name           The name of the student.
     * @param username       The username of the student.
     * @param password       The password of the student.
     * @param courses        The courses associated with the student.
     * @param fileInfoReader The FileInfoReader instance for accessing course data.
     */
    public Student(String id, String name, String username, String password, String courses, FileInfoReader fileInfoReader) {
        super(id, name, username, password);
        this.courses = courses;
        this.fileInfoReader = fileInfoReader;
    }

    /**
     * Gets the courses associated with the student.
     *
     * @return The courses as a string.
     */
    public String getCourses() {
        return courses;
    }

    /**
     * Adds a course to the student's enrolled courses if there are no conflicts.
     *
     * @param courseId The ID of the course to add.
     * @return True if the course was added, false otherwise.
     */
    public boolean addCourse(String courseId) {
        if (enrolledCourses.contains(courseId)) {
            return false; // Already enrolled
        }
        // Check for conflicts with existing courses
        for (String enrolledCourseId : enrolledCourses) {
            Course enrolledCourse = fileInfoReader.getCourseById(enrolledCourseId);
            Course newCourse = fileInfoReader.getCourseById(courseId);
            if (enrolledCourse != null && newCourse != null && enrolledCourse.hasTimeConflict(newCourse)) {
                return false; // Time conflict
            }
        }
        enrolledCourses.add(courseId);
        return true;
    }

    /**
     * Drops a course from the student's enrolled courses.
     *
     * @param courseId The ID of the course to drop.
     * @return True if the course was dropped, false otherwise.
     */
    public boolean dropCourse(String courseId) {
        return enrolledCourses.remove(courseId);
    }

    /**
     * Gets the list of enrolled courses.
     *
     * @return A list of enrolled course IDs.
     */
    public List<String> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses);
    }

    /**
     * Gets the grades for the student's courses.
     *
     * @return A map of course IDs to grades.
     */
    public Map<String, String> getGrades() {
        return new HashMap<>(grades);
    }
}
