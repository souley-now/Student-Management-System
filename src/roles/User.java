package roles;

/**
 * Represents a generic user in the student management system.
 * This class is extended by specific user types such as Student, Professor, and Admin.
 */
public abstract class User {
    private String id;
    private String name;
    private String username;
    private String password;

    /**
     * Constructs a new User.
     *
     * @param id       The ID of the user.
     * @param name     The name of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    // Getters and setters for user properties
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
