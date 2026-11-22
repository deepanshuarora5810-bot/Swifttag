package collections;

import java.util.LinkedList;
import java.util.Queue;

public class TollQueueManager {
    private final Queue<Integer> vehicleQueue = new LinkedList<>();

    public void addVehicle(int vehicleId) {
        if (vehicleId <= 0) {
            System.out.println("Invalid vehicle ID.");
            return;
        }
        vehicleQueue.offer(vehicleId);
        System.out.println("Vehicle " + vehicleId + " added to toll queue.");
    }

    public Integer processNextVehicle() {
        Integer vehicleId = vehicleQueue.poll();
        if (vehicleId == null) System.out.println("No vehicles waiting.");
        else System.out.println("Processing vehicle: " + vehicleId);
        return vehicleId;
    }

    public Integer peekNextVehicle() {
        return vehicleQueue.peek();
    }

    public void displayQueue() {
        if (vehicleQueue.isEmpty()) {
            System.out.println("No vehicles in queue.");
            return;
        }
        for (Integer vehicleId : vehicleQueue) System.out.println("Vehicle ID: " + vehicleId);
    }

    public boolean removeVehicle(int vehicleId) { return vehicleQueue.remove(vehicleId); }
    public int getQueueSize() { return vehicleQueue.size(); }
    public boolean isQueueEmpty() { return vehicleQueue.isEmpty(); }
    public void clearQueue() { vehicleQueue.clear(); }
}
