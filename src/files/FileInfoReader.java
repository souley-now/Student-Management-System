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
    private List<Course> courses = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Professor> professors = new ArrayList<>();

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
                students.add(new Student(parts[0], parts[1], parts[2], parts[3], parts[4]));
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
}
