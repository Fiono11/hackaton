package server.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Cyrille on 13/07/17.
 */
public class ConnectionManager {
    Connection connection = null;

    private final String pathUrl = "jdbc:mysql://localhost:3306/logindatabase";
    private final String userSQL = "root";
    private final String passwordSQL = "";

    public Connection getConnection() {
        try {

            if (connection == null) {
                connection = DriverManager.getConnection(pathUrl, userSQL, passwordSQL);
            }

        } catch (SQLException e) {
            System.err.println("Failure to connect to database: " + e.getMessage());
        }
        return connection;
    }

    public void close() {
        try{

            if (connection != null){
                connection.close();
            }

        }catch (SQLException e){
            System.err.println("Failure to close database connection: " + e.getMessage());
        }
    }
}
