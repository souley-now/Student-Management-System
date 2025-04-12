package roles;

public class Student extends User {
    private String courses;

    public Student(String id, String name, String username, String password, String courses) {
        super(id, name, username, password);
        this.courses = courses;
    }

    public String getCourses() {
        return courses;
    }
}
