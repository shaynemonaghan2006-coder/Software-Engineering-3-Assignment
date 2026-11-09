```java
package newsagents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PublicationDAO {

    private static final String INSERT_PUBLICATION =
            "INSERT INTO publication "
            + "(publicationName, price, frequency, type, stockQuantity) "
            + "VALUES (?, ?, ?, ?, ?)";

    private static final String SELECT_BY_ID =
            "SELECT * FROM publication WHERE publicationId = ?";

    private static final String SELECT_ALL =
            "SELECT * FROM publication";

    private static final String UPDATE_PUBLICATION =
            "UPDATE publication SET publicationName=?, price=?, "
            + "frequency=?, type=?, stockQuantity=? "
            + "WHERE publicationId=?";

    private static final String DELETE_PUBLICATION =
            "DELETE FROM publication WHERE publicationId=?";

    //Validates a Publication
    private boolean isValidPublication(Publication publication) {
        return publication != null
                && publication.verifyPublicationName(
                        publication.getPublicationName())
                && publication.verifyPrice(publication.getPrice())
                && publication.verifyFrequency(
                        publication.getFrequency())
                && publication.verifyType(publication.getType())
                && publication.verifyStockQuantity(
                        publication.getStockQuantity());
    }

    //Creates Publication
    public boolean addPublication(Publication publication) {

        if (!isValidPublication(publication)) {
            return false;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(INSERT_PUBLICATION)) {

            stmt.setString(1, publication.getPublicationName());
            stmt.setDouble(2, publication.getPrice());
            stmt.setString(3, publication.getFrequency());
            stmt.setString(4, publication.getType());
            stmt.setInt(5, publication.getStockQuantity());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Finds Publications by ID
    public Publication findPublicationById(int publicationId) {

        if (publicationId <= 0) {
            return null;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(SELECT_BY_ID)) {

            stmt.setInt(1, publicationId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new Publication(
                            rs.getInt("publicationId"),
                            rs.getString("publicationName"),
                            rs.getDouble("price"),
                            rs.getString("frequency"),
                            rs.getString("type"),
                            rs.getInt("stockQuantity")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Gets all Publications
    public ArrayList<Publication> getAllPublications() {

        ArrayList<Publication> publications = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Publication publication = new Publication(
                        rs.getInt("publicationId"),
                        rs.getString("publicationName"),
                        rs.getDouble("price"),
                        rs.getString("frequency"),
                        rs.getString("type"),
                        rs.getInt("stockQuantity")
                );

                publications.add(publication);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return publications;
    }

    //Update a Publication
    public boolean updatePublication(Publication publication) {

        if (!isValidPublication(publication)
                || !publication.verifyPublicationId(
                        publication.getPublicationId())) {
            return false;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(UPDATE_PUBLICATION)) {

            stmt.setString(1, publication.getPublicationName());
            stmt.setDouble(2, publication.getPrice());
            stmt.setString(3, publication.getFrequency());
            stmt.setString(4, publication.getType());
            stmt.setInt(5, publication.getStockQuantity());
            stmt.setInt(6, publication.getPublicationId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Delete a Publication
    public boolean deletePublication(int publicationId) {

        if (publicationId <= 0) {
            return false;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(DELETE_PUBLICATION)) {

            stmt.setInt(1, publicationId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
```
