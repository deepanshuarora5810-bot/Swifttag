package dao;

import model.Bike;
import model.Car;
import model.Truck;
import model.Vehicle;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    public boolean addVehicle(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO vehicles (user_id, vehicle_number, vehicle_type) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, vehicle.getUserId());
            ps.setString(2, vehicle.getVehicleNumber());
            ps.setString(3, vehicle.getVehicleType());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) vehicle.setVehicleId(keys.getInt(1));
                }
                return true;
            }
            return false;
        }
    }

    public List<Vehicle> findByUserId(int userId) throws SQLException {
        String sql = "SELECT vehicle_id, user_id, vehicle_number, vehicle_type FROM vehicles WHERE user_id = ?";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int vehicleId = rs.getInt("vehicle_id");
                    int ownerId = rs.getInt("user_id");
                    String number = rs.getString("vehicle_number");
                    String type = rs.getString("vehicle_type");

                    Vehicle vehicle = switch (type.toUpperCase()) {
                        case "CAR" -> new Car(vehicleId, ownerId, number);
                        case "BIKE" -> new Bike(vehicleId, ownerId, number);
                        case "TRUCK" -> new Truck(vehicleId, ownerId, number);
                        default -> null;
                    };
                    if (vehicle != null) vehicles.add(vehicle);
                }
            }
        }
        return vehicles;
    }

    public boolean vehicleNumberExists(String vehicleNumber) throws SQLException {
        String sql = "SELECT 1 FROM vehicles WHERE vehicle_number = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, vehicleNumber);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
