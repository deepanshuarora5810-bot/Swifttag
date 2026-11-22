USE fastag_db;

CREATE TABLE IF NOT EXISTS fastags (
    fastag_id INT PRIMARY KEY,
    tag_number VARCHAR(30) NOT NULL UNIQUE,
    vehicle_id INT NOT NULL,
    issue_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_fastag_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);

CREATE TABLE IF NOT EXISTS wallets (
    wallet_id INT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    balance DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_wallet_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS transactions (
    transaction_id INT PRIMARY KEY,
    fastag_id INT NOT NULL,
    vehicle_id INT NOT NULL,
    toll_plaza VARCHAR(100) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    transaction_date DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_transaction_fastag FOREIGN KEY (fastag_id) REFERENCES fastags(fastag_id),
    CONSTRAINT fk_transaction_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);
