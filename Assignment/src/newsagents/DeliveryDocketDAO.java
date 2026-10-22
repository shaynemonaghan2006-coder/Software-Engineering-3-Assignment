package newagents;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeliveryDocketDAO {

    private static final String INSERT_DOCKET =
            "INSERT INTO delivery_docket " +
            "(orderId, deliveryDate, deliveryStatus) " +
            "VALUES (?, ?, ?)";

    public boolean addDeliveryDocket(DeliveryDocket docket) {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(INSERT_DOCKET)) {

            stmt.setInt(
                1,
                docket.getOrderId());

            stmt.setString(
                2,
                docket.getDeliveryDate());

            stmt.setString(
                3,
                docket.getDeliveryStatus());

            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
