package dao;

import model.Fastag;
import util.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FastagDAO {
    public boolean addFastag(Fastag fastag) {
        String sql = "INSERT INTO fastags (fastag_id, tag_number, vehicle_id, issue_date, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, fastag.getFastagId());
            ps.setString(2, fastag.getTagNumber());
            ps.setInt(3, fastag.getVehicleId());
            ps.setDate(4, Date.valueOf(fastag.getIssueDate()));
            ps.setString(5, fastag.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding FASTag: " + e.getMessage());
            return false;
        }
    }

    public Fastag getFastagById(int fastagId) {
        String sql = "SELECT * FROM fastags WHERE fastag_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, fastagId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Fastag(rs.getInt("fastag_id"), rs.getString("tag_number"),
                        rs.getInt("vehicle_id"), rs.getDate("issue_date").toLocalDate(),
                        rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching FASTag: " + e.getMessage());
        }
        return null;
    }

    public boolean updateStatus(int fastagId, String status) {
        String sql = "UPDATE fastags SET status = ? WHERE fastag_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, fastagId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating FASTag: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteFastag(int fastagId) {
        String sql = "DELETE FROM fastags WHERE fastag_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, fastagId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting FASTag: " + e.getMessage());
            return false;
        }
    }
}
