package newsagents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {

	private static final String INSERT_ORDER = "INSERT INTO order_item "
			+ "(orderDate, productName,quantity, price, customerId) " + "VALUES (?, ?, ?, ?, ?)";

	public boolean addOrder(Order order) {

		try (Connection conn = DatabaseConnection.getConnection();

			PreparedStatement stmt = conn.prepareStatement(INSERT_ORDER)) {
			
			if(order.createOrder(order.getOrderDate(),order.getProductName(),order.getQuantity(),order.getPrice(),order.getCustId())) {
				stmt.setString(1, order.getOrderDate() );

				stmt.setString(2, order.getProductName());

				stmt.setInt(3, order.getQuantity());

				stmt.setString(4, order.getPrice());

				stmt.setInt(5, order.getCustId());
				
			}
			int rowsInserted = stmt.executeUpdate();
			return rowsInserted > 0;
			

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
