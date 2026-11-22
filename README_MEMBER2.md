# Member 2 - FASTag, Wallet, Toll and Transaction Management

## Added Java files (13)
- `model`: Fastag, Wallet, TollTransaction
- `dao`: FastagDAO, WalletDAO, TransactionDAO
- `service`: FastagService, WalletService, TollService, TransactionService
- `collections`: TollQueueManager, LaneManager
- `threading`: TollVehicleTask

## Project placement
All Member 2 Java files are placed under `src/main/java` in the same package folders as Member 1.

## Member 3 dependency
The DAO classes require Member 3 to provide:
`util.DBConnection` with:
`public static java.sql.Connection getConnection() throws java.sql.SQLException`

The ZIP supplied by Member 1 does not yet include `util/DBConnection.java`; the complete project will need Member 3's implementation.

## Expected MySQL tables
The Member 1 README defines `users` and `vehicles`. Member 2 additionally expects these tables:

```sql
USE fastag_db;

CREATE TABLE IF NOT EXISTS fastags (
    fastag_id INT PRIMARY KEY,
    tag_number VARCHAR(30) NOT NULL UNIQUE,
    vehicle_id INT NOT NULL,
    issue_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_fastag_vehicle FOREIGN KEY (vehicle_id)
        REFERENCES vehicles(vehicle_id)
);

CREATE TABLE IF NOT EXISTS wallets (
    wallet_id INT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    balance DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_wallet_user FOREIGN KEY (user_id)
        REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS transactions (
    transaction_id INT PRIMARY KEY,
    fastag_id INT NOT NULL,
    vehicle_id INT NOT NULL,
    toll_plaza VARCHAR(100) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    transaction_date DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_transaction_fastag FOREIGN KEY (fastag_id)
        REFERENCES fastags(fastag_id),
    CONSTRAINT fk_transaction_vehicle FOREIGN KEY (vehicle_id)
        REFERENCES vehicles(vehicle_id)
);
```

## Integration notes
- Keep the package declarations as they are (`model`, `dao`, `service`, `collections`, `threading`).
- FASTag IDs, wallet IDs and transaction IDs are supplied by the calling code in this version.
- Sample toll amounts in `TollService`: Bike Rs. 50, Car Rs. 100, Truck Rs. 250. These are demonstration values.
- `TollService.processTollPayment()` currently performs FASTag validation and wallet deduction. The calling flow should save a transaction using `TransactionService` after a successful payment.
- This is an academic demo. For real payment systems, use database transactions and stronger security controls.
