package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // ── Change these to match your MySQL setup ──────────────────────────────
    private static final String URL      = "jdbc:mysql://localhost:3306/bibliotheque"
                                         + "?useSSL=false&serverTimezone=UTC";
    private static final String USER     = "root";
    private static final String PASSWORD = "1234";           // put your password here
    // ────────────────────────────────────────────────────────────────────────

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("MySQL JDBC driver not found: " + e.getMessage());
        }
    }

    /**
     * Returns a fresh Connection.
     * Callers are responsible for closing it (best used in try-with-resources).
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private DatabaseConnection() {}
}
