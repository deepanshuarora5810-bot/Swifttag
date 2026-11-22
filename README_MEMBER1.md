# Member 1 - Customer & Vehicle Management

## Included Java files
- model: User, Admin, Vehicle, Car, Bike, Truck
- dao: UserDAO, VehicleDAO
- service: AuthService, UserService, VehicleService
- ui: CustomerMenu
- exception: InvalidFastagException

## Required integration dependency
The DAO classes expect Member 3 to provide:
`util.DBConnection` with a public static method:
`public static Connection getConnection() throws SQLException`

## MySQL tables expected by this code
```sql
CREATE DATABASE IF NOT EXISTS fastag_db;
USE fastag_db;

CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    phone VARCHAR(10) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS vehicles (
    vehicle_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    vehicle_number VARCHAR(20) NOT NULL UNIQUE,
    vehicle_type VARCHAR(10) NOT NULL,
    CONSTRAINT fk_vehicle_user FOREIGN KEY (user_id)
        REFERENCES users(user_id)
);
```

## Integration notes
- MainMenu should create one Scanner and pass it to `new CustomerMenu(scanner).show()`.
- Member 2's FastagService can use VehicleDAO to retrieve vehicles if needed.
- Member 3 must implement `util.DBConnection.getConnection()`.
- Add all files under `src/main/java` in the matching package folders.

## Important security note
This is an academic demonstration. Passwords are shown in plain text to keep the JDBC example simple. A real application must hash passwords with a suitable password-hashing algorithm and must not store plain-text passwords.
