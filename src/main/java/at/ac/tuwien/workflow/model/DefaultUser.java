package at.ac.tuwien.workflow.model;

/**
 * TODO: provide JavaDoc
 */
public class DefaultUser {
    private String name;
    private String password;
    private String email;
    private boolean admin;

    public DefaultUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public DefaultUser(String name, String password, boolean admin) {
        this.name = name;
        this.password = password;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
