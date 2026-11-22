package model;

public class Wallet {
    private int walletId;
    private int userId;
    private double balance;
    private boolean active;

    public Wallet() {}

    public Wallet(int walletId, int userId, double balance, boolean active) {
        this.walletId = walletId;
        this.userId = userId;
        this.balance = balance;
        this.active = active;
    }

    public int getWalletId() { return walletId; }
    public void setWalletId(int walletId) { this.walletId = walletId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public void addBalance(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive.");
        balance += amount;
    }

    public boolean deductBalance(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive.");
        if (!active || balance < amount) return false;
        balance -= amount;
        return true;
    }

    @Override
    public String toString() {
        return "Wallet ID: " + walletId + ", User ID: " + userId +
               ", Balance: Rs. " + balance + ", Active: " + active;
    }
}
