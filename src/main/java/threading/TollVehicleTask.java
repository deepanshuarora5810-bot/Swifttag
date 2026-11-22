package threading;

import service.TollService;

public class TollVehicleTask implements Runnable {
    private final int vehicleId;
    private final int fastagId;
    private final int walletId;
    private final double tollAmount;
    private final TollService tollService;

    public TollVehicleTask(int vehicleId, int fastagId, int walletId, double tollAmount) {
        this.vehicleId = vehicleId;
        this.fastagId = fastagId;
        this.walletId = walletId;
        this.tollAmount = tollAmount;
        this.tollService = new TollService();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +
            " started processing Vehicle " + vehicleId);
        try {
            Thread.sleep(1000);
            boolean successful = tollService.processTollPayment(fastagId, walletId, tollAmount);
            System.out.println(successful
                ? "Vehicle " + vehicleId + " crossed the toll successfully."
                : "Vehicle " + vehicleId + " could not cross the toll.");
        } catch (InterruptedException e) {
            System.out.println("Vehicle processing interrupted: " + vehicleId);
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() +
                " finished processing Vehicle " + vehicleId);
        }
    }
}
