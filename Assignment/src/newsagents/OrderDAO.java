package newsagents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAO {

	private static final String INSERT_ORDER = "INSERT INTO order_item "
			+ "(orderDate, productName,quantity, price, customerId) " + "VALUES (?, ?, ?, ?, ?)";
	
	private static final String SELECT_BY_ID = "SELECT * FROM order_item where orderId = ?";
	
	private static final String SELECT_ALL = "SELECT * FROM order_item";
	
	private static final String UPDATE_ORDER = "UPDATE order_item "
			+ "SET orderDate=?, productName=?, quantity=?,price=?,customerId=? WHERE orderId=?";
	
	private static final String DELETE_ORDER = "DELETE FROM order_item WHERE orderId=?";
	
	
	/**
	 * *Create Order
	 */
	public boolean addOrder(Order order) {

		try (Connection conn = DatabaseConnection.getConnection();

			PreparedStatement stmt = conn.prepareStatement(INSERT_ORDER)) {
			int rowsInserted = 0;
			if(order.createOrder(order.getOrderDate(),order.getProductName(),order.getQuantity(),order.getPrice(),order.getCustId())) {
				stmt.setString(1, order.getOrderDate() );

				stmt.setString(2, order.getProductName());

				stmt.setInt(3, order.getQuantity());

				stmt.setString(4, order.getPrice());

				stmt.setInt(5, order.getCustId());
				
				rowsInserted = stmt.executeUpdate();
			}
			
			return rowsInserted > 0;
			

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Find order by id
	 */
	public Order findOrderById(int orderId) {
		Order order = null;
		
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)){
			stmt.setInt(1, orderId);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				order = new Order();
				order.setOrderId(rs.getInt("orderId"));
				order.setOrderDate(rs.getString("orderDate"));
				order.setProductName(rs.getString("productName"));
				order.setQuantity(rs.getInt("quantity"));
				order.setPrice(rs.getString("price"));
				order.setCustId(rs.getInt("customerId"));
			}
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
				
	}
	
	/**
	 * Get all orders
	 */
	public ArrayList<Order> getAllOrders() {
		ArrayList<Order> orders = new ArrayList<>();
		
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
			
			
			ResultSet rs = stmt.executeQuery()){
			
			while(rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("orderId"));
				order.setOrderDate(rs.getString("orderDate"));
				order.setProductName(rs.getString("productName"));
				order.setQuantity(rs.getInt("quantity"));
				order.setPrice(rs.getString("price"));
				order.setCustId(rs.getInt("customerId"));
				
				orders.add(order);
			}
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
				
	}
	
	/**
	 * Update Order
	 */
	public boolean updateOrder(Order order) {

		try (Connection conn = DatabaseConnection.getConnection();

			PreparedStatement stmt = conn.prepareStatement(UPDATE_ORDER)) {
			
			int rowsUpdated = 0;
			if(order.createOrder(order.getOrderDate(),order.getProductName(),order.getQuantity(),order.getPrice(),order.getCustId())) {
				stmt.setString(1, order.getOrderDate() );

				stmt.setString(2, order.getProductName());

				stmt.setInt(3, order.getQuantity());

				stmt.setString(4, order.getPrice());

				stmt.setInt(5, order.getCustId());
				
				
				stmt.setInt(6,  order.getOrderId());
				rowsUpdated = stmt.executeUpdate();
			}
			
			return rowsUpdated > 0;
			

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Delete order
	 */
	public boolean deleteOrder(int orderId) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(DELETE_ORDER)) {

			stmt.setInt(1, orderId);

			int rowsDeleted = stmt.executeUpdate();

			return rowsDeleted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
