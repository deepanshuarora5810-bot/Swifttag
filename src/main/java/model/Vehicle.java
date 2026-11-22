package model;

public abstract class Vehicle {
    private int vehicleId;
    private int userId;
    private String vehicleNumber;

    protected Vehicle(int vehicleId, int userId, String vehicleNumber) {
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.vehicleNumber = vehicleNumber;
    }

    protected Vehicle(int userId, String vehicleNumber) {
        this(0, userId, vehicleNumber);
    }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    public int getUserId() { return userId; }
    public String getVehicleNumber() { return vehicleNumber; }

    public abstract String getVehicleType();
    public abstract double calculateToll();

    @Override
    public String toString() {
        return "Vehicle ID: " + vehicleId + ", Number: " + vehicleNumber +
               ", Type: " + getVehicleType();
    }
}
