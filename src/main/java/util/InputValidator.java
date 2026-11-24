
package util;

public class InputValidator {

    public static boolean isValidVehicleNumber(String vehicleNumber) {
        return vehicleNumber != null &&
               vehicleNumber.matches("[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}");
    }

    public static boolean isValidTagId(String tagId) {
        return tagId != null &&
               tagId.matches("TAG[0-9]+");
    }

    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}
