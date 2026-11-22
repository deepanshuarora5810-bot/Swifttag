package model;

public class User {
    private int userId;
    private String name;
    private String email;
    private String phone;
    private String password;

    public User(int userId, String name, String email, String phone, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public User(String name, String email, String phone, String password) {
        this(0, name, email, phone, password);
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name +
               ", Email: " + email + ", Phone: " + phone;
    }
}
