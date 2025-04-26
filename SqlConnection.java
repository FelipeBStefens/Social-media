package socialmedia;
import java.sql.*;

public class SqlConnection {
    
    private static Connection connection;

    public static void openConnection() {

        try {
            Class.forName(Constants.DRIVER_CLASS);
            connection = DriverManager.getConnection(
                Constants.ADDRESS, Constants.USER, Constants.PASSWORD);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {

        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
