package model;

public class Truck extends Vehicle {
    private static final double TOLL_RATE = 250.0;

    public Truck(int userId, String vehicleNumber) {
        super(userId, vehicleNumber);
    }

    public Truck(int vehicleId, int userId, String vehicleNumber) {
        super(vehicleId, userId, vehicleNumber);
    }

    @Override
    public String getVehicleType() { return "TRUCK"; }

    @Override
    public double calculateToll() { return TOLL_RATE; }
}
