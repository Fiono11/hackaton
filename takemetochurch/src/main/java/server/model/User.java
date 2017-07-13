package server.model;

/**
 * Created by Cyrille on 13/07/17.
 */
public class User {

    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User() {
    }

    public User(String username, String firstName, String lastName, String password,  String email) {
        this.username = username;
        this.lastName = lastName;
        this.password = password;
        this.firstName = firstName;
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
