package model;

public class Car extends Vehicle {
    private static final double TOLL_RATE = 100.0;

    public Car(int userId, String vehicleNumber) {
        super(userId, vehicleNumber);
    }

    public Car(int vehicleId, int userId, String vehicleNumber) {
        super(vehicleId, userId, vehicleNumber);
    }

    @Override
    public String getVehicleType() { return "CAR"; }

    @Override
    public double calculateToll() { return TOLL_RATE; }
}
