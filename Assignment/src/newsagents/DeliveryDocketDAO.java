
package newsagents;

import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeliveryDocketDAO {

    private final String url = "jdbc:mysql://localhost:3306/newsagents_db";
    private final String username = "root";
    private final String password = "";

    private final DateTimeFormatter format =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Connect to database
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    private static final String INSERT_DOCKET =
            "INSERT INTO delivery_docket (deliveryDate, deliveryStatus) VALUES (?, ?)";

    private static final String SELECT_BY_ID =
            "SELECT * FROM delivery_docket WHERE docketId = ?";

    private static final String SELECT_ALL =
            "SELECT * FROM delivery_docket";

    private static final String UPDATE_DOCKET =
            "UPDATE delivery_docket SET deliveryDate=?, deliveryStatus=? WHERE docketId=?";

    private static final String DELETE_DOCKET =
            "DELETE FROM delivery_docket WHERE docketId=?";

    // VALIDATE DELIVERY STATUS
    private boolean isValidDeliveryStatus(String deliveryStatus) {
        return deliveryStatus != null &&
                (deliveryStatus.equalsIgnoreCase("Delivered") ||
                 deliveryStatus.equalsIgnoreCase("InTransit"));
    }

    // CREATE DELIVERY DOCKET
    public boolean addDeliveryDocket(DeliveryDocket docket) {

        if (!isValidDeliveryStatus(docket.getDeliveryStatus())) {
            System.out.println("Invalid delivery status. Enter Delivered or InTransit.");
            return false;
        }

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_DOCKET)) {

            LocalDate date = LocalDate.parse(docket.getDeliveryDate(), format);

            stmt.setDate(1, Date.valueOf(date));
            stmt.setString(2, docket.getDeliveryStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException | java.time.format.DateTimeParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    // FIND DELIVERY DOCKET BY ID
    public DeliveryDocket findDeliveryDocketById(int docketId) {

        DeliveryDocket docket = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {

            stmt.setInt(1, docketId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                docket = new DeliveryDocket(
                        rs.getDate("deliveryDate").toLocalDate().format(format),
                        rs.getString("deliveryStatus"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return docket;
    }

    // GET ALL DELIVERY DOCKETS
    public ArrayList<DeliveryDocket> getAllDeliveryDockets() {

        ArrayList<DeliveryDocket> dockets = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                DeliveryDocket docket = new DeliveryDocket(
                        rs.getDate("deliveryDate").toLocalDate().format(format),
                        rs.getString("deliveryStatus"));

                dockets.add(docket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dockets;
    }

    // UPDATE DELIVERY DOCKET
    public boolean updateDeliveryDocket(DeliveryDocket docket, int docketId) {

        if (!isValidDeliveryStatus(docket.getDeliveryStatus())) {
            System.out.println("Invalid delivery status. Enter Delivered or InTransit.");
            return false;
        }

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_DOCKET)) {

            LocalDate date = LocalDate.parse(docket.getDeliveryDate(), format);

            stmt.setDate(1, Date.valueOf(date));
            stmt.setString(2, docket.getDeliveryStatus());
            stmt.setInt(3, docketId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException | java.time.format.DateTimeParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE DELIVERY DOCKET
    public boolean deleteDeliveryDocket(int docketId) {

        try (Connection conn = getConnection();
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
