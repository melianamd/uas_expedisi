package connection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseConnectionManager {
    protected static String url = "jdbc:mysql://localhost:3306/expediksi";
    protected static String driverName = "com.mysql.cj.jdbc.Driver";
    protected static String username = "root";
    protected static String password = "";
    protected Connection con;

    // Metode untuk mendapatkan koneksi
    public abstract Connection getConnection();

    // Metode untuk menutup koneksi
    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                System.out.println("Failed to close the connection.");
            }
        }
    }
}
