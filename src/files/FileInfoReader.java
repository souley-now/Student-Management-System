package files;

import roles.Admin;
import roles.Professor;
import roles.Student;
import courses.Course;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileInfoReader {
    private final List<Course> courses = new ArrayList<>();
    private final List<Admin> admins = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();
    private final List<Professor> professors = new ArrayList<>();

    public void loadAllData() {
        loadProfessors();
        loadCourses();
        loadAdmins();
        loadStudents();
    }

    private void loadCourses() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/courseInfo.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                courses.add(new Course(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5],
                        Integer.parseInt(parts[6])));
            }
        } catch (IOException e) {
            System.err.println("Error reading courseInfo.txt: " + e.getMessage());
        }
    }

    private void loadAdmins() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/adminInfo.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("; ");
                admins.add(new Admin(parts[0], parts[1], parts[2], parts[3]));
            }
        } catch (IOException e) {
            System.err.println("Error reading adminInfo.txt: " + e.getMessage());
        }
    }

    private void loadStudents() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/studentInfo.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("; ");
                students.add(new Student(parts[0], parts[1], parts[2], parts[3], parts[4], this)); // Pass 'this' as FileInfoReader instance
            }
        } catch (IOException e) {
            System.err.println("Error reading studentInfo.txt: " + e.getMessage());
        }
    }

    private void loadProfessors() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/profInfo.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("; ");
                professors.add(new Professor(parts[0], parts[1], parts[2], parts[3]));
            }
        } catch (IOException e) {
            System.err.println("Error reading profInfo.txt: " + e.getMessage());
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public List<String> getAllCourses() {
        List<String> courseDetails = new ArrayList<>();
        for (Course course : courses) {
            courseDetails.add(course.getCourseCode() + " - " + course.getCourseName());
        }
        return courseDetails;
    }

    public boolean isValidCourse(String courseId) {
        return courses.stream().anyMatch(course -> course.getCourseCode().equals(courseId));
    }

    public boolean addCourse(String courseDetails) {
        try {
            String[] parts = courseDetails.split(", ");
            Course newCourse = new Course(parts[1], parts[0], parts[6], parts[4], parts[2], parts[3], Integer.parseInt(parts[5]));
            if (isValidCourse(newCourse.getCourseCode())) {
                return false; // Course already exists
            }
            courses.add(newCourse);
            return true;
        } catch (Exception e) {
            return false; // Invalid input format
        }
    }

    public boolean removeCourse(String courseId) {
        return courses.removeIf(course -> course.getCourseCode().equals(courseId));
    }

    public boolean addProfessor(String professorDetails) {
        try {
            String[] parts = professorDetails.split(", ");
            if (professors.stream().anyMatch(prof -> prof.getUsername().equals(parts[1]))) {
                return false; // Username already exists
            }
            professors.add(new Professor(parts[0], parts[1], parts[2], ""));
            return true;
        } catch (Exception e) {
            return false; // Invalid input format
        }
    }

    public boolean removeProfessor(String professorUsername) {
        return professors.removeIf(prof -> prof.getUsername().equals(professorUsername));
    }

    public boolean addStudent(String studentDetails) {
        try {
            String[] parts = studentDetails.split(", ");
            if (students.stream().anyMatch(stu -> stu.getUsername().equals(parts[1]))) {
                return false; // Username already exists
            }
            students.add(new Student(parts[0], parts[1], parts[2], parts[3], parts[4], this)); // Pass 'this' as FileInfoReader instance
            return true;
        } catch (Exception e) {
            return false; // Invalid input format
        }
    }

    public boolean removeStudent(String studentUsername) {
        return students.removeIf(stu -> stu.getUsername().equals(studentUsername));
    }

    public List<String> getStudentsInCourse(String courseId) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseId)) {
                return course.getEnrolledStudents();
            }
        }
        return new ArrayList<>(); // Return empty list if course not found
    }

    public Course getCourseById(String courseId) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseId)) {
                return course;
            }
        }
        return null; // Return null if course not found
    }

    public Student getStudentByUsername(String username) {
        for (Student student : students) {
            if (student.getUsername().equals(username)) {
                return student;
            }
        }
        return null; // Return null if student not found
    }

    public Professor getProfessorByUsername(String username) {
        for (Professor professor : professors) {
            if (professor.getUsername().equals(username)) {
                return professor;
            }
        }
        return null; // Return null if professor not found
    }

    public Admin getAdminByUsername(String username) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null; // Return null if admin not found
    }
}
