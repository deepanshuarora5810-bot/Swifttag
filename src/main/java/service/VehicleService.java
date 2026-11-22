package service;

import dao.VehicleDAO;
import model.Bike;
import model.Car;
import model.Truck;
import model.Vehicle;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class VehicleService {
    private static final Pattern VEHICLE_NUMBER_PATTERN =
            Pattern.compile("^[A-Z]{2}[0-9]{1,2}[A-Z]{1,3}[0-9]{4}$");

    private final VehicleDAO vehicleDAO;

    public VehicleService() {
        this.vehicleDAO = new VehicleDAO();
    }

    public boolean registerVehicle(int userId, String vehicleNumber, String type)
            throws SQLException {
        if (userId <= 0) throw new IllegalArgumentException("Invalid user ID.");

        String normalizedNumber = vehicleNumber == null ? "" : vehicleNumber
                .replaceAll("\\s+", "").toUpperCase();

        if (!VEHICLE_NUMBER_PATTERN.matcher(normalizedNumber).matches()) {
            throw new IllegalArgumentException("Invalid vehicle number format. Example: PB10AB1234");
        }

        if (vehicleDAO.vehicleNumberExists(normalizedNumber)) {
            throw new IllegalArgumentException("This vehicle is already registered.");
        }

        Vehicle vehicle = createVehicle(userId, normalizedNumber, type);
        return vehicleDAO.addVehicle(vehicle);
    }

    public List<Vehicle> getUserVehicles(int userId) throws SQLException {
        return vehicleDAO.findByUserId(userId);
    }

    private Vehicle createVehicle(int userId, String number, String type) {
        if (type == null) throw new IllegalArgumentException("Select a vehicle type.");

        return switch (type.trim().toUpperCase()) {
            case "CAR" -> new Car(userId, number);
            case "BIKE" -> new Bike(userId, number);
            case "TRUCK" -> new Truck(userId, number);
            default -> throw new IllegalArgumentException("Vehicle type must be CAR, BIKE or TRUCK.");
        };
    }
}
