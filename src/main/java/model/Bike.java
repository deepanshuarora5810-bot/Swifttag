package model;

public class Bike extends Vehicle {
    private static final double TOLL_RATE = 50.0;

    public Bike(int userId, String vehicleNumber) {
        super(userId, vehicleNumber);
    }

    public Bike(int vehicleId, int userId, String vehicleNumber) {
        super(vehicleId, userId, vehicleNumber);
    }

    @Override
    public String getVehicleType() { return "BIKE"; }

    @Override
    public double calculateToll() { return TOLL_RATE; }
}
