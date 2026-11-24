package collections;

import java.util.HashMap;
import java.util.Map;

public class FastagRegistry {

    // Stores FASTag ID and balance
    private HashMap<String, Double> tagBalances = new HashMap<>();

    // Stores vehicle number and FASTag ID
    private HashMap<String, String> vehicleTags = new HashMap<>();

    // Register a new FASTag
    public void registerFastag(String tagId, String vehicleNumber, double balance) {

        if (tagBalances.containsKey(tagId)) {
            System.out.println("FASTag already exists!");
            return;
        }

        if (vehicleTags.containsKey(vehicleNumber)) {
            System.out.println("Vehicle already has a FASTag!");
            return;
        }

        if (balance < 0) {
            System.out.println("Invalid balance!");
            return;
        }

        tagBalances.put(tagId, balance);
        vehicleTags.put(vehicleNumber, tagId);

        System.out.println("FASTag registered successfully!");
    }

    // Check whether FASTag exists
    public boolean tagExists(String tagId) {
        return tagBalances.containsKey(tagId);
    }

    // Get wallet balance
    public double getBalance(String tagId) {

        if (!tagBalances.containsKey(tagId)) {
            System.out.println("FASTag not found!");
            return -1;
        }

        return tagBalances.get(tagId);
    }

    // Find FASTag using vehicle number
    public String getTagByVehicle(String vehicleNumber) {
        return vehicleTags.get(vehicleNumber);
    }

    // Update wallet balance
    public void updateBalance(String tagId, double newBalance) {

        if (!tagBalances.containsKey(tagId)) {
            System.out.println("FASTag not found!");
            return;
        }

        if (newBalance < 0) {
            System.out.println("Invalid balance!");
            return;
        }

        tagBalances.put(tagId, newBalance);

        System.out.println("Balance updated successfully!");
    }

    // Remove FASTag
    public void removeFastag(String tagId) {

        if (!tagBalances.containsKey(tagId)) {
            System.out.println("FASTag not found!");
            return;
        }

        tagBalances.remove(tagId);

        vehicleTags.values().remove(tagId);

        System.out.println("FASTag removed successfully!");
    }

    // Display all FASTags
    public void displayFastags() {

        for (Map.Entry<String, Double> entry : tagBalances.entrySet()) {

            System.out.println(
                "FASTag ID: " + entry.getKey()
                + " | Balance: Rs. " + entry.getValue()
            );
        }
    }
}
