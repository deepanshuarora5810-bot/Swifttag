package service;

import dao.FastagDAO;
import dao.WalletDAO;
import model.Fastag;

public class TollService {
    private final FastagDAO fastagDAO;
    private final WalletDAO walletDAO;

    public TollService() {
        this.fastagDAO = new FastagDAO();
        this.walletDAO = new WalletDAO();
    }

    public boolean processTollPayment(int fastagId, int walletId, double tollAmount) {
        if (tollAmount <= 0) {
            System.out.println("Invalid toll amount.");
            return false;
        }

        Fastag fastag = fastagDAO.getFastagById(fastagId);
        if (fastag == null) {
            System.out.println("FASTag not found.");
            return false;
        }
        if (!"ACTIVE".equalsIgnoreCase(fastag.getStatus())) {
            System.out.println("FASTag is blocked or inactive.");
            return false;
        }

        boolean paid = walletDAO.deductBalance(walletId, tollAmount);
        if (paid) {
            System.out.println("Toll payment successful. Vehicle " +
                fastag.getVehicleId() + " may pass. Amount: Rs. " + tollAmount);
        } else {
            System.out.println("Payment failed: insufficient balance or inactive wallet.");
        }
        return paid;
    }

    public double calculateToll(String vehicleType) {
        if (vehicleType == null) return 0;
        switch (vehicleType.trim().toUpperCase()) {
            case "BIKE": return 50.0;
            case "CAR": return 100.0;
            case "TRUCK": return 250.0;
            default:
                System.out.println("Unknown vehicle type.");
                return 0;
        }
    }
}
