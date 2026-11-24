package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Central JDBC connection factory. Configure using JVM properties or environment variables:
 * db.url / DB_URL, db.user / DB_USER, db.password / DB_PASSWORD.
 * A JDBC driver for the selected database must be on the runtime classpath.
 */
public final class DBConnection {
    private DBConnection() { }

    public static Connection getConnection() throws SQLException {
        String url = setting("db.url", "DB_URL");
        String user = setting("db.user", "DB_USER");
        String password = setting("db.password", "DB_PASSWORD");
        if (url == null || url.isBlank()) {
            throw new SQLException("Database URL is not configured. Set DB_URL or -Ddb.url.");
        }
        return DriverManager.getConnection(url, user == null ? "" : user, password == null ? "" : password);
    }

    private static String setting(String property, String environment) {
        String value = System.getProperty(property);
        return value != null && !value.isBlank() ? value : System.getenv(environment);
    }
}
