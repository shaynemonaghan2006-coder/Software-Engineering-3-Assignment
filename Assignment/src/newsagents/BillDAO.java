
package newsagents;

import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BillDAO {

    private final String url = "jdbc:mysql://localhost:3306/newsagents_db";
    private final String username = "root";
    private final String password = "";

    private final DateTimeFormatter format =
        DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Connect to database
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Add bill
    public boolean addBill(Bill bill) {

        String sql = "INSERT INTO bill (Payment_Status, Payment_Date, Total_Amount) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bill.getPaymentStatus());

            LocalDate date = LocalDate.parse(bill.getPaymentDate(), format);
            ps.setDate(2, Date.valueOf(date));

            ps.setDouble(3, bill.getTotalAmount());

            return ps.executeUpdate() > 0;

        } catch (SQLException | java.time.format.DateTimeParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Find bill by ID
    public Bill findBillById(int billId) {

        String sql = "SELECT * FROM bill WHERE Bill_ID = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, billId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Bill(
                    rs.getInt("Bill_ID"),
                    rs.getString("Payment_Status"),
                    rs.getDate("Payment_Date").toLocalDate().format(format),
                    rs.getDouble("Total_Amount")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Get all bills
    public ArrayList<Bill> getAllBills() {

        ArrayList<Bill> bills = new ArrayList<>();

        String sql = "SELECT * FROM bill";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                bills.add(new Bill(
                    rs.getInt("Bill_ID"),
                    rs.getString("Payment_Status"),
                    rs.getDate("Payment_Date").toLocalDate().format(format),
                    rs.getDouble("Total_Amount")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }

    // Update bill
    public boolean updateBill(Bill bill) {

        String sql = "UPDATE bill SET Payment_Status = ?, Payment_Date = ?, Total_Amount = ? WHERE Bill_ID = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bill.getPaymentStatus());

            LocalDate date = LocalDate.parse(bill.getPaymentDate(), format);
            ps.setDate(2, Date.valueOf(date));

            ps.setDouble(3, bill.getTotalAmount());
            ps.setInt(4, bill.getBillId());

            return ps.executeUpdate() > 0;

        } catch (SQLException | java.time.format.DateTimeParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete bill
    public boolean deleteBill(int billId) {

        String sql = "DELETE FROM bill WHERE Bill_ID = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, billId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
