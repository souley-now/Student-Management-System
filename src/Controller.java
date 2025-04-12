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
            // Add student-specific operations here
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
            // Add professor-specific operations here
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
            // Add admin-specific operations here
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}