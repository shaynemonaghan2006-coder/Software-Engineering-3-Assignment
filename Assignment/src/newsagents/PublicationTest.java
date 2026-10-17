
package newsagent;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PublicationTest {

    // Create / Read test cases

    // TC01
    // Test Objective: Verify Publication ID is retrieved correctly
    // Input(s): Publication ID = 1
    // Expected Output(s): Publication ID returned as 1

    @Test
    void validPublicationId_TC01() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);

        assertEquals(1, publication.getPublicationId());
    }

    // TC02
    // Test Objective: Verify Publication Name is retrieved correctly
    // Input(s): Publication Name = "Daily News"
    // Expected Output(s): Publication Name returned as "Daily News"

    @Test
    void validPublicationName_TC02() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);

        assertEquals("Daily News", publication.getPublicationName());
    }

    // TC03
    // Test Objective: Verify Publication Price is retrieved correctly
    // Input(s): Price = 2.50
    // Expected Output(s): Price returned as 2.50

    @Test
    void validPublicationPrice_TC03() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);

        assertEquals(2.50, publication.getPrice(), 0.001);
    }

    // TC04
    // Test Objective: Verify Publication Frequency is retrieved correctly
    // Input(s): Frequency = "Daily"
    // Expected Output(s): Frequency returned as "Daily"

    @Test
    void validPublicationFrequency_TC04() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);

        assertEquals("Daily", publication.getFrequency());
    }

    // TC05
    // Test Objective: Verify Publication Type is retrieved correctly
    // Input(s): Type = "Newspaper"
    // Expected Output(s): Type returned as "Newspaper"

    @Test
    void validPublicationType_TC05() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);

        assertEquals("Newspaper", publication.getType());
    }

    // TC06
    // Test Objective: Verify Stock Quantity is retrieved correctly
    // Input(s): Stock Quantity = 100
    // Expected Output(s): Stock Quantity returned as 100

    @Test
    void validStockQuantity_TC06() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);

        assertEquals(100, publication.getStockQuantity());
    }

    // Update test cases

    // TC07
    // Test Objective: Verify Publication Name can be updated
    // Input(s): New Publication Name = "Sunday Magazine"
    // Expected Output(s): Publication Name updated successfully

    @Test
    void updatePublicationName_TC07() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);

        publication.setPublicationName("Sunday Magazine");

        assertEquals("Sunday Magazine", publication.getPublicationName());
    }

    // TC08
    // Test Objective: Verify Publication Price can be updated
    // Input(s): New Price = 3.00
    // Expected Output(s): Publication Price updated to 3.00

    @Test
    void updatePublicationPrice_TC08() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);

        publication.setPrice(3.00);

        assertEquals(3.00, publication.getPrice(), 0.001);
    }

    // TC09
    // Test Objective: Verify Stock Quantity can be updated
    // Input(s): New Stock Quantity = 50
    // Expected Output(s): Stock Quantity updated to 50

    @Test
    void updateStockQuantity_TC09() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);

        publication.setStockQuantity(50);

        assertEquals(50, publication.getStockQuantity());
    }

    // Read test case

    // TC10
    // Test Objective: Verify Publication details are returned as a string
    // Input(s): Publication ID = 1, Name = "Daily News",
    // Type = "Newspaper", Stock Quantity = 100
    // Expected Output(s): String contains publication name, type and stock

    @Test
    void readPublicationInfo_TC10() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);

        String details = publication.toString();

        assertTrue(details.contains("Daily News"));
        assertTrue(details.contains("Newspaper"));
        assertTrue(details.contains("100"));
    }
}
