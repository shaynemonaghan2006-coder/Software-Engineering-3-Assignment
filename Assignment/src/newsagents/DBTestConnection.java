
package newsagents;

import java.sql.Connection;
import java.sql.SQLException;

public class DBTestConnection {

    public static void main(String[] args) {
        try  {
        	Connection conn = DatabaseConnection.getConnection();
            System.out.println("Database connected successfully!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
        
    }
}
