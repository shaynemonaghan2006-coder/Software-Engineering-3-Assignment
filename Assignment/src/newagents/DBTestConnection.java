
package newagents;

import java.sql.Connection;
import java.sql.SQLException;

public class DBTestConnection {

    public static void main(String[] args) {
        try  {
        	DatabaseConnection.getConnection();
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}
