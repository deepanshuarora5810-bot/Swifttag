package service;

import dao.FastagDAO;
import model.Fastag;

import java.time.LocalDate;
import java.util.UUID;

public class FastagService {
    private final FastagDAO fastagDAO;

    public FastagService() {
        this.fastagDAO = new FastagDAO();
    }

    public boolean issueFastag(int fastagId, int vehicleId) {
        if (fastagId <= 0 || vehicleId <= 0) {
            System.out.println("Invalid FASTag ID or Vehicle ID.");
            return false;
        }
        Fastag fastag = new Fastag(fastagId, generateTagNumber(),
            vehicleId, LocalDate.now(), "ACTIVE");
        boolean saved = fastagDAO.addFastag(fastag);
        if (saved) System.out.println("FASTag issued: " + fastag.getTagNumber());
        else System.out.println("FASTag issuance failed.");
        return saved;
    }

    private String generateTagNumber() {
        return "FASTAG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public Fastag searchFastag(int fastagId) {
        if (fastagId <= 0) return null;
        return fastagDAO.getFastagById(fastagId);
    }

    public boolean blockFastag(int fastagId) {
        return changeStatus(fastagId, "BLOCKED");
    }

    public boolean activateFastag(int fastagId) {
        return changeStatus(fastagId, "ACTIVE");
    }

    private boolean changeStatus(int fastagId, String status) {
        if (fastagId <= 0) return false;
        return fastagDAO.updateStatus(fastagId, status);
    }

    public boolean deleteFastag(int fastagId) {
        return fastagId > 0 && fastagDAO.deleteFastag(fastagId);
    }
}
