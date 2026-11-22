package service;

import dao.TransactionDAO;
import model.TollTransaction;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionService {
    private final TransactionDAO transactionDAO;

    public TransactionService() {
        this.transactionDAO = new TransactionDAO();
    }

    public boolean saveTransaction(int transactionId, int fastagId, int vehicleId,
                                   String tollPlaza, double amount, String status) {
        if (transactionId <= 0 || fastagId <= 0 || vehicleId <= 0 ||
            tollPlaza == null || tollPlaza.trim().isEmpty() || amount <= 0) {
            System.out.println("Invalid transaction details.");
            return false;
        }
        TollTransaction transaction = new TollTransaction(transactionId, fastagId,
            vehicleId, tollPlaza.trim(), amount, LocalDateTime.now(), status);
        return transactionDAO.addTransaction(transaction);
    }

    public TollTransaction getTransaction(int transactionId) {
        return transactionDAO.getTransactionById(transactionId);
    }

    public void displayAllTransactions() {
        List<TollTransaction> transactions = transactionDAO.getAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        for (TollTransaction transaction : transactions) System.out.println(transaction);
    }

    public void displayVehicleTransactions(int vehicleId) {
        List<TollTransaction> transactions = transactionDAO.getTransactionsByVehicle(vehicleId);
        if (transactions.isEmpty()) {
            System.out.println("No transactions found for this vehicle.");
            return;
        }
        for (TollTransaction transaction : transactions) System.out.println(transaction);
    }

    public double calculateTotalCollection() {
        double total = 0;
        for (TollTransaction transaction : transactionDAO.getAllTransactions()) {
            if ("SUCCESS".equalsIgnoreCase(transaction.getStatus())) {
                total += transaction.getAmount();
            }
        }
        return total;
    }
}
