
package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReceiptGenerator {

    public static void generateReceipt(String transactionId,
                                       String vehicleNumber,
                                       String tagId,
                                       double amount,
                                       double remainingBalance) {

        File folder = new File("receipts");

        if (!folder.exists()) {
            folder.mkdir();
        }

        String fileName = "receipts/" + transactionId + ".txt";

        try {
            FileWriter writer = new FileWriter(fileName);

            writer.write("========== FASTag Toll Receipt ==========\n");
            writer.write("Transaction ID: " + transactionId + "\n");
            writer.write("Vehicle Number: " + vehicleNumber + "\n");
            writer.write("FASTag ID: " + tagId + "\n");
            writer.write("Toll Amount: Rs. " + amount + "\n");
            writer.write("Remaining Balance: Rs. " + remainingBalance + "\n");
            writer.write("=========================================\n");

            writer.close();

            System.out.println("Receipt generated successfully!");

        } catch (IOException e) {
            System.out.println("Error generating receipt: " + e.getMessage());
        }
    }
}
