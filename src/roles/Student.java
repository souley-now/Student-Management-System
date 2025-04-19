package roles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import courses.Course; // Import the Course class
import files.FileInfoReader; // Import the FileInfoReader class

public class Student extends User {
    private String courses;
    private List<String> enrolledCourses = new ArrayList<>();
    private Map<String, String> grades = new HashMap<>();
    private FileInfoReader fileInfoReader; // Add a reference to FileInfoReader

    public Student(String id, String name, String username, String password, String courses, FileInfoReader fileInfoReader) {
        super(id, name, username, password);
        this.courses = courses;
        this.fileInfoReader = fileInfoReader; // Initialize the FileInfoReader instance
    }

    public String getCourses() {
        return courses;
    }

    public boolean addCourse(String courseId) {
        if (enrolledCourses.contains(courseId)) {
            return false; // Already enrolled
        }
        // Check for conflicts
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

    public boolean dropCourse(String courseId) {
        return enrolledCourses.remove(courseId);
    }

    public List<String> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses);
    }

    public Map<String, String> getGrades() {
        return new HashMap<>(grades);
    }
}
