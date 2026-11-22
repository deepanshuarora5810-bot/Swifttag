package service;

import dao.UserDAO;
import model.User;

import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserService {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10}$");

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public boolean registerUser(String name, String email, String phone, String password)
            throws SQLException {
        validateUser(name, email, phone, password);

        if (userDAO.findByEmail(email.trim()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        User user = new User(name.trim(), email.trim(), phone.trim(), password);
        return userDAO.register(user);
    }

    public Optional<User> findUserByEmail(String email) throws SQLException {
        return userDAO.findByEmail(email.trim());
    }

    private void validateUser(String name, String email, String phone, String password) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (email == null || !EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new IllegalArgumentException("Enter a valid email address.");
        }
        if (phone == null || !PHONE_PATTERN.matcher(phone.trim()).matches()) {
            throw new IllegalArgumentException("Phone number must contain 10 digits.");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must contain at least 6 characters.");
        }
    }
}
