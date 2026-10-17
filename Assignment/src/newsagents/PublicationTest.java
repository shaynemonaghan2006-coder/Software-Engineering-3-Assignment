
package newsagent;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PublicationTest {

    // TC01
    // Test Objective: Verify publication ID is retrieved correctly
    // Input: Publication ID = 1
    // Expected Output: 1
    @Test
    void validPublicationId_TC01() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);
        assertEquals(1, publication.getPublicationId());
    }

    // TC02
    // Test Objective: Verify publication name is retrieved correctly
    // Input: Daily News
    // Expected Output: Daily News
    @Test
    void validPublicationName_TC02() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);
        assertEquals("Daily News", publication.getPublicationName());
    }

    // TC03
    // Test Objective: Verify publication price is retrieved correctly
    // Input: 2.50
    // Expected Output: 2.50
    @Test
    void validPublicationPrice_TC03() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);
        assertEquals(2.50, publication.getPrice(), 0.001);
    }

    // TC04
    // Test Objective: Verify publication frequency is retrieved correctly
    // Input: Daily
    // Expected Output: Daily
    @Test
    void validPublicationFrequency_TC04() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);
        assertEquals("Daily", publication.getFrequency());
    }

    // TC05
    // Test Objective: Verify publication type is retrieved correctly
    // Input: Newspaper
    // Expected Output: Newspaper
    @Test
    void validPublicationType_TC05() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);
        assertEquals("Newspaper", publication.getType());
    }

    // TC06
    // Test Objective: Verify stock quantity is retrieved correctly
    // Input: 100
    // Expected Output: 100
    @Test
    void validStockQuantity_TC06() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);
        assertEquals(100, publication.getStockQuantity());
    }

    // TC07
    // Test Objective: Verify publication name can be updated
    // Input: Sunday Magazine
    // Expected Output: Sunday Magazine
    @Test
    void updatePublicationName_TC07() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);
        publication.setPublicationName("Sunday Magazine");
        assertEquals("Sunday Magazine", publication.getPublicationName());
    }

    // TC08
    // Test Objective: Verify publication price can be updated
    // Input: 3.00
    // Expected Output: 3.00
    @Test
    void updatePublicationPrice_TC08() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);
        publication.setPrice(3.00);
        assertEquals(3.00, publication.getPrice(), 0.001);
    }

    // TC09
    // Test Objective: Verify stock quantity can be updated
    // Input: 50
    // Expected Output: 50
    @Test
    void updateStockQuantity_TC09() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);
        publication.setStockQuantity(50);
        assertEquals(50, publication.getStockQuantity());
    }

    // TC10
    // Test Objective: Verify publication details are displayed
    // Input: Publication details
    // Expected Output: Name, type and stock appear in the result
    @Test
    void readPublicationInfo_TC10() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);

        String details = publication.toString();

        assertTrue(details.contains("Daily News"));
        assertTrue(details.contains("Newspaper"));
        assertTrue(details.contains("100"));
    }

    // TC11
    // Test Objective: Verify a positive publication ID is valid
    // Input: 1
    // Expected Output: true
    @Test
    void verifyValidPublicationId_TC11() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);
        assertTrue(publication.verifyPublicationId(1));
    }

    // TC12
    // Test Objective: Verify zero publication ID is invalid
    // Input: 0
    // Expected Output: false
    @Test
    void verifyInvalidPublicationId_TC12() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);
        assertFalse(publication.verifyPublicationId(0));
    }

    // TC13
    // Test Objective: Verify blank publication names are invalid
    // Input: Blank name
    // Expected Output: false
    @Test
    void verifyInvalidPublicationName_TC13() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);
        assertFalse(publication.verifyPublicationName(" "));
    }

    // TC14
    // Test Objective: Verify negative prices are invalid
    // Input: -2.50
    // Expected Output: false
    @Test
    void verifyInvalidPrice_TC14() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);
        assertFalse(publication.verifyPrice(-2.50));
    }

    // TC15
    // Test Objective: Verify negative stock quantities are invalid
    // Input: -10
    // Expected Output: false
    @Test
    void verifyInvalidStockQuantity_TC15() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);
        assertFalse(publication.verifyStockQuantity(-10));
    }

    // TC16
    // Test Objective: Verify stock can be increased
    // Input: Add 20 copies to 100
    // Expected Output: Stock becomes 120
    @Test
    void addStock_TC16() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);

        assertTrue(publication.addStock(20));
        assertEquals(120, publication.getStockQuantity());
    }

    // TC17
    // Test Objective: Verify stock can be reduced
    // Input: Remove 30 copies from 100
    // Expected Output: Stock becomes 70
    @Test
    void removeStock_TC17() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);

        assertTrue(publication.removeStock(30));
        assertEquals(70, publication.getStockQuantity());
    }

    // TC18
    // Test Objective: Verify stock cannot be reduced below zero
    // Input: Remove 150 copies from 100
    // Expected Output: Operation fails and stock remains 100
    @Test
    void preventNegativeStock_TC18() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);

        assertFalse(publication.removeStock(150));
        assertEquals(100, publication.getStockQuantity());
    }

    // TC19
    // Test Objective: Verify zero stock additions are rejected
    // Input: Add 0 copies
    // Expected Output: Operation fails and stock remains 100
    @Test
    void rejectZeroStockAddition_TC19() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);

        assertFalse(publication.addStock(0));
        assertEquals(100, publication.getStockQuantity());
    }

    // TC20
    // Test Objective: Verify blank publication types are invalid
    // Input: Blank type
    // Expected Output: false
    @Test
    void verifyInvalidPublicationType_TC20() {
        Publication publication = new Publication(
                1, "Daily News", 2.50, "Daily", "Newspaper", 100);
        assertFalse(publication.verifyType(" "));
    }
}
