package dao;

import model.Wallet;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletDAO {
    public boolean createWallet(Wallet wallet) {
        String sql = "INSERT INTO wallets (wallet_id, user_id, balance, active) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, wallet.getWalletId());
            ps.setInt(2, wallet.getUserId());
            ps.setDouble(3, wallet.getBalance());
            ps.setBoolean(4, wallet.isActive());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error creating wallet: " + e.getMessage());
            return false;
        }
    }

    public Wallet getWalletByUserId(int userId) {
        String sql = "SELECT * FROM wallets WHERE user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Wallet(rs.getInt("wallet_id"), rs.getInt("user_id"),
                        rs.getDouble("balance"), rs.getBoolean("active"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching wallet: " + e.getMessage());
        }
        return null;
    }

    public boolean updateBalance(int walletId, double newBalance) {
        String sql = "UPDATE wallets SET balance = ? WHERE wallet_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setInt(2, walletId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating balance: " + e.getMessage());
            return false;
        }
    }

    public boolean rechargeWallet(int walletId, double amount) {
        if (amount <= 0) return false;
        String sql = "UPDATE wallets SET balance = balance + ? WHERE wallet_id = ? AND active = TRUE";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, walletId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error recharging wallet: " + e.getMessage());
            return false;
        }
    }

    public boolean deductBalance(int walletId, double amount) {
        if (amount <= 0) return false;
        String sql = "UPDATE wallets SET balance = balance - ? WHERE wallet_id = ? AND balance >= ? AND active = TRUE";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, walletId);
            ps.setDouble(3, amount);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deducting balance: " + e.getMessage());
            return false;
        }
    }
}
