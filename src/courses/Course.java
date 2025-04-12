package courses;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private final String courseCode;
    private final String courseName;
    private final String professorName;
    private final String days;
    private final String startTime;
    private final String endTime;
    private final int capacity;
    private final List<String> enrolledStudents;

    public Course(String courseCode, String courseName, String professorName, String days, String startTime, String endTime, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.professorName = professorName;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public boolean hasTimeConflict(Course other) {
        // Implement logic to check for time conflicts
        return false;
    }

    public boolean addStudent(String studentId) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(studentId);
            return true;
        }
        return false;
    }

    public boolean removeStudent(String studentId) {
        return enrolledStudents.remove(studentId);
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public String getDays() {
        return days;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }
}
