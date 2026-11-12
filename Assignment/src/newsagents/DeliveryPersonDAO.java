package newsagents;

import java.sql.*;
import java.util.ArrayList;

public class DeliveryPersonDAO {

// CREATE
	private static final String INSERT_DP = "INSERT INTO delivery_person "
			+ "(firstName, surname, address, phoneNumber) " + "VALUES (?, ?, ?, ?)";

// READ ONE
	private static final String SELECT_BY_ID = "SELECT * FROM delivery_person WHERE deliveryPersonId = ?";

// READ ALL
	private static final String SELECT_ALL = "SELECT * FROM delivery_person";

// UPDATE
	private static final String UPDATE_DP = "UPDATE delivery_person "
			+ "SET firstName=?, surname=?, address=?, phoneNumber=? " + "WHERE deliveryPersonId=?";

// DELETE
	private static final String DELETE_DP = "DELETE FROM delivery_person WHERE deliveryPersonId=?";

	/**
	 * CREATE CUSTOMER
	 */
	public boolean addDeliveryPerson(DeliveryPerson dp) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(INSERT_DP)) {
			int rowsInserted = 0;
			if (dp.createDeliveryPersonAccount(dp.getFirstName(), dp.getSurname(), dp.getAddress(),
					dp.getPhoneNumber())) {
				stmt.setString(1, dp.getFirstName());
				stmt.setString(2, dp.getSurname());
				stmt.setString(3, dp.getAddress());
				stmt.setString(4, dp.getPhoneNumber());

				rowsInserted = stmt.executeUpdate();
			}

			return rowsInserted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * FIND DeliveryPerson BY ID
	 */
	public DeliveryPerson findDeliveryPersonById(int deliveryPersonId) {

		DeliveryPerson dp = null;

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {

			stmt.setInt(1, deliveryPersonId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				dp = new DeliveryPerson();

				dp.setDeliveryPersonId(rs.getInt("deliveryPersonId"));

				dp.setFirstName(rs.getString("firstName"));

				dp.setSurname(rs.getString("surname"));

				dp.setAddress(rs.getString("address"));

				dp.setPhoneNumber(rs.getString("phoneNumber"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dp;
	}

	/**
	 * GET ALL Delivery Persons
	 */
	public ArrayList<DeliveryPerson> getAllDeliveryPerson() {

		ArrayList<DeliveryPerson> dps = new ArrayList<>();

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);

				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {

				DeliveryPerson dp = new DeliveryPerson();

				dp.setDeliveryPersonId(rs.getInt("deliveryPersonId"));

				dp.setFirstName(rs.getString("firstName"));

				dp.setSurname(rs.getString("surname"));

				dp.setAddress(rs.getString("address"));

				dp.setPhoneNumber(rs.getString("phoneNumber"));

				dps.add(dp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dps;
	}

	/**
	 * UPDATE DELIVERY PERSON
	 */
	public boolean updateDeliveryPerson(DeliveryPerson dp) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(UPDATE_DP)) {
			int rowsUpdated = 0;
			if (dp.createDeliveryPersonAccount(dp.getFirstName(), dp.getSurname(), dp.getAddress(),
					dp.getPhoneNumber())) {
				stmt.setString(1, dp.getFirstName());
				stmt.setString(2, dp.getSurname());
				stmt.setString(3, dp.getAddress());
				stmt.setString(4, dp.getPhoneNumber());
				stmt.setInt(5, dp.getDeliveryPersonId());

				rowsUpdated = stmt.executeUpdate();
			}
			return rowsUpdated > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * DELETE DELIVERY PERSON
	 */
	public boolean deleteDeliveryPerson(int deliveryPersonId) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(DELETE_DP)) {

			stmt.setInt(1, deliveryPersonId);

			int rowsDeleted = stmt.executeUpdate();

			return rowsDeleted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}