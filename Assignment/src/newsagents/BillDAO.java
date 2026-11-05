package newagents;

import java.sql.*;
import java.util.ArrayList;

public class BillDAO {

	private static final String INSERT_BILL = "INSERT INTO bill "
			+ "(receiptId, orderReference, driverId, paymentDate) "
			+ "VALUES (?, ?, ?, ?)";

	private static final String SELECT_BY_ID =
			"SELECT * FROM bill WHERE receiptId = ?";

	private static final String SELECT_ALL =
			"SELECT * FROM bill";

	private static final String UPDATE_BILL = "UPDATE bill "
			+ "SET orderReference=?, driverId=?, paymentDate=? "
			+ "WHERE receiptId=?";

	private static final String DELETE_BILL =
			"DELETE FROM bill WHERE receiptId=?";

	//CREATE BILL
	public boolean addBill(Bill bill) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(INSERT_BILL)) {

			stmt.setInt(1, bill.getReceiptId());
			stmt.setString(2, bill.getOrderReference());
			stmt.setInt(3, bill.getDriverId());
			stmt.setString(4, bill.getPaymentDate());

			int rowsInserted = stmt.executeUpdate();

			return rowsInserted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	//FIND BILL BY RECEIPT ID
	public Bill findBillById(int receiptId) {

		Bill bill = null;

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {

			stmt.setInt(1, receiptId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				bill = new Bill(
						rs.getInt("receiptId"),
						rs.getString("orderReference"),
						rs.getInt("driverId"),
						rs.getString("paymentDate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bill;
	}

	//GET ALL BILLS
	public ArrayList<Bill> getAllBills() {

		ArrayList<Bill> bills = new ArrayList<>();

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);

				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Bill bill = new Bill(
						rs.getInt("receiptId"),
						rs.getString("orderReference"),
						rs.getInt("driverId"),
						rs.getString("paymentDate"));

				bills.add(bill);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bills;
	}

	//UPDATE BILL
	public boolean updateBill(Bill bill) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(UPDATE_BILL)) {

			stmt.setString(1, bill.getOrderReference());
			stmt.setInt(2, bill.getDriverId());
			stmt.setString(3, bill.getPaymentDate());
			stmt.setInt(4, bill.getReceiptId());

			int rowsUpdated = stmt.executeUpdate();

			return rowsUpdated > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//DELETE BILL
	public boolean deleteBill(int receiptId) {

		try (Connection conn = DatabaseConnection.getConnection();

				PreparedStatement stmt = conn.prepareStatement(DELETE_BILL)) {

			stmt.setInt(1, receiptId);

			int rowsDeleted = stmt.executeUpdate();

			return rowsDeleted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
