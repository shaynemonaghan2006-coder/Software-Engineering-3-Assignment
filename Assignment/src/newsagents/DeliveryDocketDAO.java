
package newsagents;

import java.sql.*;
import java.util.ArrayList;

public class DeliveryDocketDAO {

	private static final String INSERT_DOCKET = "INSERT INTO delivery_docket "
			+ "(orderId, deliveryDate, deliveryStatus) "
			+ "VALUES (?, ?, ?)";

	private static final String SELECT_BY_ID =
			"SELECT * FROM delivery_docket WHERE docketId = ?";

	private static final String SELECT_ALL =
			"SELECT * FROM delivery_docket";

	private static final String UPDATE_DOCKET = "UPDATE delivery_docket "
			+ "SET orderId=?, deliveryDate=?, deliveryStatus=? "
			+ "WHERE docketId=?";

	private static final String DELETE_DOCKET =
			"DELETE FROM delivery_docket WHERE docketId=?";

	//CREATE DELIVERY DOCKET
	public boolean addDeliveryDocket(DeliveryDocket docket) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(INSERT_DOCKET)) {

			stmt.setInt(1, docket.getOrderId());
			stmt.setString(2, docket.getDeliveryDate());
			stmt.setString(3, docket.getDeliveryStatus());

			int rowsInserted = stmt.executeUpdate();

			return rowsInserted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	//FIND DELIVERY DOCKET BY ID
	public DeliveryDocket findDeliveryDocketById(int docketId) {

		DeliveryDocket docket = null;

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {

			stmt.setInt(1, docketId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				docket = new DeliveryDocket(
						rs.getInt("orderId"),
						rs.getString("deliveryDate"),
						rs.getString("deliveryStatus"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return docket;
	}

	//GET ALL DELIVERY DOCKETS
	public ArrayList<DeliveryDocket> getAllDeliveryDockets() {

		ArrayList<DeliveryDocket> dockets = new ArrayList<>();

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);

				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				DeliveryDocket docket = new DeliveryDocket(
						rs.getInt("orderId"),
						rs.getString("deliveryDate"),
						rs.getString("deliveryStatus"));

				dockets.add(docket);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dockets;
	}

	//UPDATE DELIVERY DOCKET
	public boolean updateDeliveryDocket(DeliveryDocket docket) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(UPDATE_DOCKET)) {

			stmt.setInt(1, docket.getOrderId());
			stmt.setString(2, docket.getDeliveryDate());
			stmt.setString(3, docket.getDeliveryStatus());
			stmt.setInt(4, docket.getDocketId());

			int rowsUpdated = stmt.executeUpdate();

			return rowsUpdated > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	//DELETE DELIVERY DOCKET
	public boolean deleteDeliveryDocket(int docketId) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(DELETE_DOCKET)) {

			stmt.setInt(1, docketId);

			int rowsDeleted = stmt.executeUpdate();

			return rowsDeleted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
