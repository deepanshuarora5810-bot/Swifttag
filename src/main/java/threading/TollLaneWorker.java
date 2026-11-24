
package threading;

import exception.InsufficientBalanceException;

public class TollLaneWorker extends Thread {

    private String laneName;
    private String tagId;
    private double tollAmount;
    private SharedWallet wallet;

    public TollLaneWorker(String laneName, String tagId,
                          double tollAmount, SharedWallet wallet) {

        this.laneName = laneName;
        this.tagId = tagId;
        this.tollAmount = tollAmount;
        this.wallet = wallet;
    }

    @Override
    public void run() {

        System.out.println(laneName + " processing vehicle...");

        try {
            wallet.deductBalance(tagId, tollAmount);
            System.out.println(laneName + ": Transaction successful!");
        } catch (InsufficientBalanceException e) {
            System.out.println(laneName + ": " + e.getMessage());
        }
    }
}
