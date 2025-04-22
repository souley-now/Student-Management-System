package courses;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a course in the student management system.
 */
public class Course {
    private final String courseCode;
    private final String courseName;
    private final String professorName;
    private final String days;
    private final String startTime;
    private final String endTime;
    private final int capacity;
    private final List<String> enrolledStudents;

    /**
     * Constructs a new Course.
     *
     * @param courseCode     The unique code of the course.
     * @param courseName     The name of the course.
     * @param professorName  The name of the professor teaching the course.
     * @param days           The days the course is held (e.g., "MW").
     * @param startTime      The start time of the course (e.g., "10:00").
     * @param endTime        The end time of the course (e.g., "11:30").
     * @param capacity       The maximum number of students allowed in the course.
     */
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

    /**
     * Checks if this course has a time conflict with another course.
     *
     * @param other The other course to compare with.
     * @return True if there is a time conflict, false otherwise.
     */
    public boolean hasTimeConflict(Course other) {
        if (!this.days.equals(other.days)) {
            return false; // Different days, no conflict
        }
        int thisStart = Integer.parseInt(this.startTime.replace(":", ""));
        int thisEnd = Integer.parseInt(this.endTime.replace(":", ""));
        int otherStart = Integer.parseInt(other.startTime.replace(":", ""));
        int otherEnd = Integer.parseInt(other.endTime.replace(":", ""));
        return (thisStart < otherEnd && otherStart < thisEnd); // Overlapping time ranges
    }

    /**
     * Adds a student to the course if there is capacity.
     *
     * @param studentId The ID of the student to add.
     * @return True if the student was added, false otherwise.
     */
    public boolean addStudent(String studentId) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(studentId);
            return true;
        }
        return false;
    }

    /**
     * Removes a student from the course.
     *
     * @param studentId The ID of the student to remove.
     * @return True if the student was removed, false otherwise.
     */
    public boolean removeStudent(String studentId) {
        return enrolledStudents.remove(studentId);
    }

    // Getters for course properties
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

    /**
     * Gets the list of enrolled students.
     *
     * @return A list of student IDs.
     */
    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }
}
