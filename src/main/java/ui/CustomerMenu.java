package ui;

import model.User;
import model.Vehicle;
import service.AuthService;
import service.UserService;
import service.VehicleService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CustomerMenu {
    private final Scanner scanner;
    private final UserService userService;
    private final AuthService authService;
    private final VehicleService vehicleService;

    public CustomerMenu(Scanner scanner) {
        this.scanner = scanner;
        this.userService = new UserService();
        this.authService = new AuthService();
        this.vehicleService = new VehicleService();
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== CUSTOMER MENU =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Back");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> register();
                    case "2" -> login();
                    case "0" -> running = false;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Input error: " + e.getMessage());
            }
        }
    }

    private void register() throws SQLException {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("10-digit phone: ");
        String phone = scanner.nextLine();
        System.out.print("Password (minimum 6 characters): ");
        String password = scanner.nextLine();

        if (userService.registerUser(name, email, phone, password)) {
            System.out.println("Registration successful. You can now log in.");
        } else {
            System.out.println("Registration could not be completed.");
        }
    }

    private void login() throws SQLException {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Optional<User> user = authService.login(email, password);
        if (user.isEmpty()) {
            System.out.println("Invalid email or password.");
            return;
        }

        System.out.println("Welcome, " + user.get().getName() + "!");
        customerActions(user.get());
    }

    private void customerActions(User user) throws SQLException {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n===== CUSTOMER ACCOUNT =====");
            System.out.println("1. Register a vehicle");
            System.out.println("2. View my vehicles");
            System.out.println("0. Logout");
            System.out.print("Choose option: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> addVehicle(user);
                case "2" -> viewVehicles(user);
                case "0" -> loggedIn = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addVehicle(User user) throws SQLException {
        System.out.print("Vehicle number (e.g. PB10AB1234): ");
        String number = scanner.nextLine();
        System.out.print("Vehicle type (CAR/BIKE/TRUCK): ");
        String type = scanner.nextLine();

        if (vehicleService.registerVehicle(user.getUserId(), number, type)) {
            System.out.println("Vehicle registered successfully.");
        } else {
            System.out.println("Vehicle registration failed.");
        }
    }

    private void viewVehicles(User user) throws SQLException {
        List<Vehicle> vehicles = vehicleService.getUserVehicles(user.getUserId());
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered.");
            return;
        }

        System.out.println("\n--- Your Vehicles ---");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
            System.out.println("Demo toll rate: Rs. " + vehicle.calculateToll());
        }
    }
}
