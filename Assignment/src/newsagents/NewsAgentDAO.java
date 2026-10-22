package newsagents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewsAgentDAO {

    private static final String INSERT_NEWS_AGENT =
            "INSERT INTO news_agent "
            + "(name, address, phoneNumber, email) "
            + "VALUES (?, ?, ?, ?)";

    private static final String SELECT_BY_ID =
            "SELECT * FROM news_agent WHERE newsAgentId = ?";

    private static final String SELECT_ALL =
            "SELECT * FROM news_agent";

    private static final String UPDATE_NEWS_AGENT =
            "UPDATE news_agent SET name=?, address=?, "
            + "phoneNumber=?, email=? WHERE newsAgentId=?";

    private static final String DELETE_NEWS_AGENT =
            "DELETE FROM news_agent WHERE newsAgentId=?";

    //Creates news agent
    public boolean addNewsAgent(NewsAgent agent) {

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(INSERT_NEWS_AGENT)) {

            int rowsInserted = 0;

            if (agent.verifyName(agent.getName())
                    && agent.verifyAddress(agent.getAddress())
                    && agent.verifyPhoneNumber(agent.getPhoneNumber())
                    && agent.verifyEmail(agent.getEmail())) {

                stmt.setString(1, agent.getName());
                stmt.setString(2, agent.getAddress());
                stmt.setString(3, agent.getPhoneNumber());
                stmt.setString(4, agent.getEmail());

                rowsInserted = stmt.executeUpdate();
            }

            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Finds news agent by ID
    public NewsAgent findNewsAgentById(int newsAgentId) {

        NewsAgent agent = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(SELECT_BY_ID)) {

            stmt.setInt(1, newsAgentId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    agent = new NewsAgent(
                            rs.getInt("newsAgentId"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("phoneNumber"),
                            rs.getString("email")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agent;
    }

   //Gets all news agent
    public ArrayList<NewsAgent> getAllNewsAgents() {

        ArrayList<NewsAgent> agents = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                NewsAgent agent = new NewsAgent(
                        rs.getInt("newsAgentId"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phoneNumber"),
                        rs.getString("email")
                );

                agents.add(agent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agents;
    }

    //Updates a news agent
    public boolean updateNewsAgent(NewsAgent agent) {

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(UPDATE_NEWS_AGENT)) {

            int rowsUpdated = 0;

            if (agent.verifyNewsAgentId(agent.getNewsAgentId())
                    && agent.verifyName(agent.getName())
                    && agent.verifyAddress(agent.getAddress())
                    && agent.verifyPhoneNumber(agent.getPhoneNumber())
                    && agent.verifyEmail(agent.getEmail())) {

                stmt.setString(1, agent.getName());
                stmt.setString(2, agent.getAddress());
                stmt.setString(3, agent.getPhoneNumber());
                stmt.setString(4, agent.getEmail());
                stmt.setInt(5, agent.getNewsAgentId());

                rowsUpdated = stmt.executeUpdate();
            }

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Deletes a news agent
    public boolean deleteNewsAgent(int newsAgentId) {

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(DELETE_NEWS_AGENT)) {

            stmt.setInt(1, newsAgentId);

            int rowsDeleted = stmt.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
