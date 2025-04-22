package roles;

/**
 * Represents an admin in the student management system.
 */
public class Admin extends User {

    /**
     * Constructs a new Admin.
     *
     * @param id       The ID of the admin.
     * @param name     The name of the admin.
     * @param username The username of the admin.
     * @param password The password of the admin.
     */
    public Admin(String id, String name, String username, String password) {
        super(id, name, username, password);
    }

    // Additional admin-specific methods can be added here
}
