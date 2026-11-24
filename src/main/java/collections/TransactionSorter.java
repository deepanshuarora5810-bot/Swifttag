
package collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

public class TransactionSorter {

    // Stores all transactions
    private ArrayList<String[]> transactions = new ArrayList<>();

    // Stores transactions sorted by transaction ID
    private TreeMap<String, String[]> sortedTransactions = new TreeMap<>();

    // Add a transaction
    public void addTransaction(String transactionId,
                               String vehicleNumber,
                               double amount) {

        String[] transaction = {
            transactionId,
            vehicleNumber,
            String.valueOf(amount)
        };

        transactions.add(transaction);
        sortedTransactions.put(transactionId, transaction);

        System.out.println("Transaction added successfully!");
    }

    // Display all transactions
    public void displayTransactions() {

        for (String[] t : transactions) {

            System.out.println(
                "ID: " + t[0]
                + " | Vehicle: " + t[1]
                + " | Amount: Rs. " + t[2]
            );
        }
    }

    // Sort transactions by amount in ascending order
    public void sortAscending() {

        transactions.sort(
            Comparator.comparingDouble(t -> Double.parseDouble(t[2]))
        );

        displayTransactions();
    }

    // Sort transactions by amount in descending order
    public void sortDescending() {

        transactions.sort(
            Comparator.comparingDouble(
                (String[] t) -> Double.parseDouble(t[2])
            ).reversed()
        );

        displayTransactions();
    }

    // Search transaction using ID
    public void searchTransaction(String transactionId) {

        if (sortedTransactions.containsKey(transactionId)) {

            String[] t = sortedTransactions.get(transactionId);

            System.out.println(
                "ID: " + t[0]
                + " | Vehicle: " + t[1]
                + " | Amount: Rs. " + t[2]
            );

        } else {

            System.out.println("Transaction not found!");
        }
    }
}
