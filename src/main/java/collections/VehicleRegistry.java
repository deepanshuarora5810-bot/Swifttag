
package collections;

import java.util.HashMap;
import java.util.Map;
import model.Vehicle;

public class VehicleRegistry {

    // Vehicle number is the key and Vehicle object is the value
    private HashMap<String, Vehicle> vehicles = new HashMap<>();

    // Add a new vehicle
    public void addVehicle(Vehicle vehicle) {
        String vehicleNumber = vehicle.getVehicleNumber();

        if (vehicles.containsKey(vehicleNumber)) {
            System.out.println("Vehicle already registered!");
            return;
        }

        vehicles.put(vehicleNumber, vehicle);
        System.out.println("Vehicle registered successfully!");
    }

    // Find a vehicle using its number
    public Vehicle findVehicle(String vehicleNumber) {
        return vehicles.get(vehicleNumber);
    }

    // Check whether a vehicle exists
    public boolean vehicleExists(String vehicleNumber) {
        return vehicles.containsKey(vehicleNumber);
    }

    // Remove a vehicle
    public void removeVehicle(String vehicleNumber) {
        if (vehicles.containsKey(vehicleNumber)) {
            vehicles.remove(vehicleNumber);
            System.out.println("Vehicle removed successfully!");
        } else {
            System.out.println("Vehicle not found!");
        }
    }

    // Display all registered vehicles
    public void displayVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered!");
            return;
        }

        for (Map.Entry<String, Vehicle> entry : vehicles.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
