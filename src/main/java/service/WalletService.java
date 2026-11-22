package service;

import dao.WalletDAO;
import model.Wallet;

public class WalletService {
    private final WalletDAO walletDAO;

    public WalletService() {
        this.walletDAO = new WalletDAO();
    }

    public boolean createWallet(int walletId, int userId) {
        if (walletId <= 0 || userId <= 0) return false;
        return walletDAO.createWallet(new Wallet(walletId, userId, 0.0, true));
    }

    public double checkBalance(int userId) {
        Wallet wallet = walletDAO.getWalletByUserId(userId);
        return wallet == null ? -1 : wallet.getBalance();
    }

    public boolean rechargeWallet(int walletId, double amount) {
        return walletId > 0 && amount > 0 && walletDAO.rechargeWallet(walletId, amount);
    }

    public boolean deductToll(int walletId, double amount) {
        return walletId > 0 && amount > 0 && walletDAO.deductBalance(walletId, amount);
    }

    public Wallet getWalletDetails(int userId) {
        return walletDAO.getWalletByUserId(userId);
    }
}
