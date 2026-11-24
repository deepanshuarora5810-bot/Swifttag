
package threading;

import exception.InsufficientBalanceException;
import java.util.HashMap;

public class SharedWallet {

    private HashMap<String, Double> balances = new HashMap<>();

    public synchronized void addWallet(String tagId, double balance) {
        balances.put(tagId, balance);
        System.out.println("Wallet created successfully!");
    }

    public synchronized double getBalance(String tagId) {
        if (!balances.containsKey(tagId)) {
            return -1;
        }

        return balances.get(tagId);
    }

    public synchronized void deductBalance(String tagId, double amount)
            throws InsufficientBalanceException {

        if (!balances.containsKey(tagId)) {
            throw new InsufficientBalanceException("FASTag not found!");
        }

        double balance = balances.get(tagId);

        if (balance < amount) {
            throw new InsufficientBalanceException("Insufficient FASTag balance!");
        }

        balances.put(tagId, balance - amount);

        System.out.println("Toll amount deducted: Rs. " + amount);
        System.out.println("Remaining balance: Rs. " + balances.get(tagId));
    }
}
