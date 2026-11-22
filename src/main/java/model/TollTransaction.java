package model;

import java.time.LocalDateTime;

public class TollTransaction {
    private int transactionId;
    private int fastagId;
    private int vehicleId;
    private String tollPlaza;
    private double amount;
    private LocalDateTime transactionDate;
    private String status;

    public TollTransaction() {}

    public TollTransaction(int transactionId, int fastagId, int vehicleId,
                           String tollPlaza, double amount,
                           LocalDateTime transactionDate, String status) {
        this.transactionId = transactionId;
        this.fastagId = fastagId;
        this.vehicleId = vehicleId;
        this.tollPlaza = tollPlaza;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.status = status;
    }

    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }
    public int getFastagId() { return fastagId; }
    public void setFastagId(int fastagId) { this.fastagId = fastagId; }
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    public String getTollPlaza() { return tollPlaza; }
    public void setTollPlaza(String tollPlaza) { this.tollPlaza = tollPlaza; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId + ", FASTag ID: " + fastagId +
               ", Vehicle ID: " + vehicleId + ", Toll Plaza: " + tollPlaza +
               ", Amount: Rs. " + amount + ", Date: " + transactionDate +
               ", Status: " + status;
    }
}
