package roles;

import java.util.ArrayList;
import java.util.List;

public class Professor extends User {
    private List<String> coursesTaught;

    public Professor(String id, String name, String username, String password) {
        super(id, name, username, password);
        this.coursesTaught = new ArrayList<>();
    }

    public boolean isTeachingCourse(String courseId) {
        return coursesTaught.contains(courseId);
    }

    public List<String> getCoursesTaught() {
        return coursesTaught;
    }
}

