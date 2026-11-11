

import java.sql.*;
import java.util.ArrayList;

public class DeliveryPersonDAO {

// CREATE
	private static final String INSERT_CUSTOMER = "INSERT INTO customer "
			+ "(firstName, surname, address, phoneNumber) " + "VALUES (?, ?, ?, ?)";

// READ ONE
	private static final String SELECT_BY_ID = "SELECT * FROM customer WHERE customerId = ?";

// READ ALL
	private static final String SELECT_ALL = "SELECT * FROM customer";

// UPDATE
	private static final String UPDATE_CUSTOMER = "UPDATE customer "
			+ "SET firstName=?, surname=?, address=?, phoneNumber=? " + "WHERE customerId=?";

// DELETE
	private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE customerId=?";

	/**
	 * CREATE CUSTOMER
	 */
	public boolean addCustomer(DeliveryPerson customer) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(INSERT_CUSTOMER)) {
			int rowsInserted = 0;
			if (customer.createCustomerAccount(customer.getFirstName(), customer.getSurname(), customer.getAddress(),
					customer.getPhoneNumber())) {
				stmt.setString(1, customer.getFirstName());
				stmt.setString(2, customer.getSurname());
				stmt.setString(3, customer.getAddress());
				stmt.setString(4, customer.getPhoneNumber());

				rowsInserted = stmt.executeUpdate();
			}

			return rowsInserted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * FIND CUSTOMER BY ID
	 */
	public DeliveryPerson findCustomerById(int customerId) {

		DeliveryPerson customer = null;

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {

			stmt.setInt(1, customerId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				customer = new DeliveryPerson();

				customer.setCustomerId(rs.getInt("customerId"));

				customer.setFirstName(rs.getString("firstName"));

				customer.setSurname(rs.getString("surname"));

				customer.setAddress(rs.getString("address"));

				customer.setPhoneNumber(rs.getString("phoneNumber"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customer;
	}

	/**
	 * GET ALL CUSTOMERS
	 */
	public ArrayList<DeliveryPerson> getAllCustomers() {

		ArrayList<DeliveryPerson> customers = new ArrayList<>();

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);

				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {

				DeliveryPerson customer = new DeliveryPerson();

				customer.setCustomerId(rs.getInt("customerId"));

				customer.setFirstName(rs.getString("firstName"));

				customer.setSurname(rs.getString("surname"));

				customer.setAddress(rs.getString("address"));

				customer.setPhoneNumber(rs.getString("phoneNumber"));

				customers.add(customer);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customers;
	}

	/**
	 * UPDATE CUSTOMER
	 */
	public boolean updateCustomer(DeliveryPerson customer) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(UPDATE_CUSTOMER)) {
			int rowsUpdated = 0;
			if (customer.createCustomerAccount(customer.getFirstName(), customer.getSurname(), customer.getAddress(),
					customer.getPhoneNumber())) {
				stmt.setString(1, customer.getFirstName());
				stmt.setString(2, customer.getSurname());
				stmt.setString(3, customer.getAddress());
				stmt.setString(4, customer.getPhoneNumber());
				stmt.setInt(5, customer.getCustomerId());

				rowsUpdated = stmt.executeUpdate();
			}
			return rowsUpdated > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * DELETE CUSTOMER
	 */
	public boolean deleteCustomer(int customerId) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(DELETE_CUSTOMER)) {

			stmt.setInt(1, customerId);

			int rowsDeleted = stmt.executeUpdate();

			return rowsDeleted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}