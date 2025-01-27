package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager extends BaseConnectionManager{
    @Override
    public Connection getConnection() {
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
        } catch (SQLException ex) {
            System.out.println("Failed to create the database connection.");
        }
        return con;
    }
}
