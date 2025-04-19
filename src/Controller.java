import files.FileInfoReader;
import roles.Admin;
import roles.Professor;
import roles.Student;
import roles.User;

import java.util.Scanner;

public class Controller {
    private static FileInfoReader fileInfoReader = new FileInfoReader();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Student Management System!");

        // Load data from files
        System.out.println("Loading data...");
        fileInfoReader.loadAllData();
        System.out.println("Data loaded successfully!");

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Professor");
            System.out.println("3. Login as Admin");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    loginAsStudent(scanner);
                    break;
                case 2:
                    loginAsProfessor(scanner);
                    break;
                case 3:
                    loginAsAdmin(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void loginAsStudent(Scanner scanner) {
        System.out.print("Enter Student Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Student student = fileInfoReader.getStudentByUsername(username);
        if (student != null && student.getPassword().equals(password)) {
            System.out.println("Login successful! Welcome, " + student.getName());

            while (true) {
                System.out.println("\nStudent Menu:");
                System.out.println("1. View all courses");
                System.out.println("2. Add courses to your list");
                System.out.println("3. Drop courses from your list");
                System.out.println("4. View enrolled courses");
                System.out.println("5. View grades");
                System.out.println("6. Return to previous menu");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Available Courses:");
                        fileInfoReader.getAllCourses().forEach(System.out::println);
                        break;
                    case 2:
                        System.out.println("Enter course ID to add (or 'q' to cancel): ");
                        String courseIdToAdd = scanner.nextLine();
                        if (!courseIdToAdd.equalsIgnoreCase("q")) {
                            if (fileInfoReader.isValidCourse(courseIdToAdd)) {
                                if (student.addCourse(courseIdToAdd)) {
                                    System.out.println("Course added successfully.");
                                } else {
                                    System.out.println("Failed to add course. It may already be in your list or there is a conflict.");
                                }
                            } else {
                                System.out.println("Invalid course ID.");
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Enter course ID to drop (or 'q' to cancel): ");
                        String courseIdToDrop = scanner.nextLine();
                        if (!courseIdToDrop.equalsIgnoreCase("q")) {
                            if (student.dropCourse(courseIdToDrop)) {
                                System.out.println("Course dropped successfully.");
                            } else {
                                System.out.println("Failed to drop course. It may not be in your list.");
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Enrolled Courses:");
                        student.getEnrolledCourses().forEach(System.out::println);
                        break;
                    case 5:
                        System.out.println("Grades:");
                        student.getGrades().forEach((course, grade) -> 
                            System.out.println(course + ": " + grade));
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void loginAsProfessor(Scanner scanner) {
        System.out.print("Enter Professor Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Professor professor = fileInfoReader.getProfessorByUsername(username);
        if (professor != null && professor.getPassword().equals(password)) {
            System.out.println("Login successful! Welcome, " + professor.getName());

            while (true) {
                System.out.println("\nProfessor Menu:");
                System.out.println("1. View given courses");
                System.out.println("2. View students in a given course");
                System.out.println("3. Return to previous menu");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Courses you are teaching:");
                        professor.getCoursesTaught().forEach(System.out::println);
                        break;
                    case 2:
                        System.out.print("Enter course ID to view students: ");
                        String courseId = scanner.nextLine();
                        if (fileInfoReader.isValidCourse(courseId) && professor.isTeachingCourse(courseId)) {
                            System.out.println("Students in " + courseId + ":");
                            fileInfoReader.getStudentsInCourse(courseId).forEach(System.out::println);
                        } else {
                            System.out.println("Invalid course ID or you are not teaching this course.");
                        }
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void loginAsAdmin(Scanner scanner) {
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Admin admin = fileInfoReader.getAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            System.out.println("Login successful! Welcome, " + admin.getName());

            while (true) {
                System.out.println("\nAdmin Menu:");
                System.out.println("1. View all courses");
                System.out.println("2. Add new courses");
                System.out.println("3. Remove courses");
                System.out.println("4. Add new professors");
                System.out.println("5. Remove professors");
                System.out.println("6. Add new students");
                System.out.println("7. Remove students");
                System.out.println("8. Return to previous menu");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("All Courses:");
                        fileInfoReader.getAllCourses().forEach(System.out::println);
                        break;
                    case 2:
                        System.out.print("Enter course details (name, ID, start time, end time, days, capacity, instructor ID): ");
                        String courseDetails = scanner.nextLine();
                        if (fileInfoReader.addCourse(courseDetails)) {
                            System.out.println("Course added successfully.");
                        } else {
                            System.out.println("Failed to add course. Check for conflicts or invalid data.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter course ID to remove: ");
                        String courseIdToRemove = scanner.nextLine();
                        if (fileInfoReader.removeCourse(courseIdToRemove)) {
                            System.out.println("Course removed successfully.");
                        } else {
                            System.out.println("Failed to remove course. It may not exist.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter professor details (name, username, password): ");
                        String professorDetails = scanner.nextLine();
                        if (fileInfoReader.addProfessor(professorDetails)) {
                            System.out.println("Professor added successfully.");
                        } else {
                            System.out.println("Failed to add professor. Username may already exist.");
                        }
                        break;
                    case 5:
                        System.out.print("Enter professor username to remove: ");
                        String professorUsername = scanner.nextLine();
                        if (fileInfoReader.removeProfessor(professorUsername)) {
                            System.out.println("Professor removed successfully.");
                        } else {
                            System.out.println("Failed to remove professor. Username may not exist.");
                        }
                        break;
                    case 6:
                        System.out.print("Enter student details (name, username, password, ID): ");
                        String studentDetails = scanner.nextLine();
                        if (fileInfoReader.addStudent(studentDetails)) {
                            System.out.println("Student added successfully.");
                        } else {
                            System.out.println("Failed to add student. Username may already exist.");
                        }
                        break;
                    case 7:
                        System.out.print("Enter student username to remove: ");
                        String studentUsername = scanner.nextLine();
                        if (fileInfoReader.removeStudent(studentUsername)) {
                            System.out.println("Student removed successfully.");
                        } else {
                            System.out.println("Failed to remove student. Username may not exist.");
                        }
                        break;
                    case 8:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}