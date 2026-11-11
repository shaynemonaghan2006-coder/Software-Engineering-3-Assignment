package newsagents;

import java.sql.*;
import java.util.ArrayList;

public class DeliveryAreaDAO {

// CREATE
	private static final String INSERT_DELIVERY_AREA = "INSERT INTO delivery_area "
			+ "(name, region, driverId) " + "VALUES (?, ?, ?)";

// READ ONE
	private static final String SELECT_BY_ID = "SELECT * FROM delivery_area WHERE deliveryAreaId = ?";

// READ ALL
	private static final String SELECT_ALL = "SELECT * FROM delivery_area";

// UPDATE
	private static final String UPDATE_DELIVERY_AREA = "UPDATE delivery_area "
			+ "SET name=?, region=?, driverId=?" + "WHERE deliveryAreaId=?";

// DELETE
	private static final String DELETE_DELIVERY_AREA = "DELETE FROM delivery_area WHERE deliveryAreaId=?";

	/**
	 * CREATE Delivery Area
	 */
	public boolean addDeliveryArea(DeliveryArea area) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(INSERT_DELIVERY_AREA)) {
			int rowsInserted = 0;
			if (area.createDeliveryArea(area.getName(), area.getRegion(), area.getDriverId())) {
				stmt.setString(1, area.getName());
				stmt.setString(2, area.getRegion());
				stmt.setInt(3, area.getDriverId());
				

				rowsInserted = stmt.executeUpdate();
			}

			return rowsInserted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * FIND Delivery Area BY ID
	 */
	public DeliveryArea findDeliveryAreaById(int deliveryAreaId) {

		DeliveryArea area = null;

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {

			stmt.setInt(1, deliveryAreaId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				area = new DeliveryArea();

				area.setDeliveryAreaId(rs.getInt("deliveryAreaId"));

				area.setName(rs.getString("name"));

				area.setRegion(rs.getString("region"));

				area.setDriverId(rs.getInt("driverId"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return area;
	}

	/**
	 * GET ALL Delivery Areas
	 */
	public ArrayList<DeliveryArea> getAllAreas() {

		ArrayList<DeliveryArea> areas = new ArrayList<>();

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);

				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {

				DeliveryArea area = new DeliveryArea();

				area.setDeliveryAreaId(rs.getInt("deliveryAreaId"));

				area.setName(rs.getString("name"));

				area.setRegion(rs.getString("region"));

				area.setDriverId(rs.getInt("driverId"));

				areas.add(area);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return areas;
	}

	/**
	 * UPDATE DELIVERY AREA
	 */
	public boolean updateArea(DeliveryArea area) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(UPDATE_DELIVERY_AREA)) {
			int rowsUpdated = 0;
			if (area.createDeliveryArea(area.getName(), area.getRegion(), area.getDriverId())) {
				stmt.setString(1, area.getName());
				stmt.setString(2, area.getRegion());
				stmt.setInt(3, area.getDriverId());
				stmt.setInt(4, area.getDeliveryAreaId());

				rowsUpdated = stmt.executeUpdate();
			}
			return rowsUpdated > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * DELETE DELIVERY AREA
	 */
	public boolean deleteArea(int deliveryAreaId) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(DELETE_DELIVERY_AREA)) {

			stmt.setInt(1, deliveryAreaId);

			int rowsDeleted = stmt.executeUpdate();

			return rowsDeleted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
