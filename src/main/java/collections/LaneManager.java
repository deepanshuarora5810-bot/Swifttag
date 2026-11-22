package collections;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LaneManager {
    private final Map<Integer, Queue<Integer>> lanes = new HashMap<>();

    public LaneManager() {
        for (int lane = 1; lane <= 3; lane++) lanes.put(lane, new LinkedList<>());
    }

    public void addVehicleToLane(int laneNumber, int vehicleId) {
        Queue<Integer> queue = lanes.get(laneNumber);
        if (queue == null || vehicleId <= 0) {
            System.out.println("Invalid lane number or vehicle ID.");
            return;
        }
        queue.offer(vehicleId);
        System.out.println("Vehicle " + vehicleId + " added to Lane " + laneNumber);
    }

    public Integer processVehicle(int laneNumber) {
        Queue<Integer> queue = lanes.get(laneNumber);
        if (queue == null) {
            System.out.println("Invalid lane number.");
            return null;
        }
        return queue.poll();
    }

    public void displayLanes() {
        for (Map.Entry<Integer, Queue<Integer>> entry : lanes.entrySet()) {
            System.out.println("Lane " + entry.getKey() + " : " + entry.getValue());
        }
    }

    public int getWaitingCount(int laneNumber) {
        Queue<Integer> queue = lanes.get(laneNumber);
        return queue == null ? 0 : queue.size();
    }

    public int getShortestLane() {
        int shortestLane = -1;
        int minimumVehicles = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Queue<Integer>> entry : lanes.entrySet()) {
            if (entry.getValue().size() < minimumVehicles) {
                minimumVehicles = entry.getValue().size();
                shortestLane = entry.getKey();
            }
        }
        return shortestLane;
    }

    public void displayLaneStatus() {
        for (Integer lane : lanes.keySet()) {
            System.out.println("Lane " + lane + " has " + lanes.get(lane).size() + " waiting vehicles.");
        }
    }
}
