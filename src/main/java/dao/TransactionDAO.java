package dao;

import model.TollTransaction;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    public boolean addTransaction(TollTransaction transaction) {
        String sql = "INSERT INTO transactions (transaction_id, fastag_id, vehicle_id, toll_plaza, amount, transaction_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, transaction.getTransactionId());
            ps.setInt(2, transaction.getFastagId());
            ps.setInt(3, transaction.getVehicleId());
            ps.setString(4, transaction.getTollPlaza());
            ps.setDouble(5, transaction.getAmount());
            ps.setTimestamp(6, Timestamp.valueOf(transaction.getTransactionDate()));
            ps.setString(7, transaction.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
            return false;
        }
    }

    public TollTransaction getTransactionById(int transactionId) {
        String sql = "SELECT * FROM transactions WHERE transaction_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, transactionId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapTransaction(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching transaction: " + e.getMessage());
        }
        return null;
    }

    public List<TollTransaction> getAllTransactions() {
        List<TollTransaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY transaction_date DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) transactions.add(mapTransaction(rs));
        } catch (SQLException e) {
            System.out.println("Error fetching transactions: " + e.getMessage());
        }
        return transactions;
    }

    public List<TollTransaction> getTransactionsByVehicle(int vehicleId) {
        List<TollTransaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE vehicle_id = ? ORDER BY transaction_date DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) transactions.add(mapTransaction(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching vehicle transactions: " + e.getMessage());
        }
        return transactions;
    }

    private TollTransaction mapTransaction(ResultSet rs) throws SQLException {
        return new TollTransaction(rs.getInt("transaction_id"), rs.getInt("fastag_id"),
            rs.getInt("vehicle_id"), rs.getString("toll_plaza"), rs.getDouble("amount"),
            rs.getTimestamp("transaction_date").toLocalDateTime(), rs.getString("status"));
    }
}
