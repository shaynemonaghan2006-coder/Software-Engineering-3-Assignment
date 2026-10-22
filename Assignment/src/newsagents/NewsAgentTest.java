package newsagents;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NewsAgentTest {

    private NewsAgent agent;

    @BeforeEach
    void setUp() {
        agent = new NewsAgent(
                1, "John Smith", "Main Street",
                "0871234567", "john@example.com");
    }

    // TC01
    // Test Objective: Verify news agent ID is retrieved correctly
    // Input: News Agent ID = 1
    // Expected Output: 1
    @Test
    void validNewsAgentId_TC01() {
        assertEquals(1, agent.getNewsAgentId());
    }

    // TC02
    // Test Objective: Verify news agent name is retrieved correctly
    // Input: John Smith
    // Expected Output: John Smith
    @Test
    void validNewsAgentName_TC02() {
        assertEquals("John Smith", agent.getName());
    }

    // TC03
    // Test Objective: Verify news agent address is retrieved correctly
    // Input: Main Street
    // Expected Output: Main Street
    @Test
    void validNewsAgentAddress_TC03() {
        assertEquals("Main Street", agent.getAddress());
    }

    // TC04
    // Test Objective: Verify news agent phone number is retrieved correctly
    // Input: 0871234567
    // Expected Output: 0871234567
    @Test
    void validPhoneNumber_TC04() {
        assertEquals("0871234567", agent.getPhoneNumber());
    }

    // TC05
    // Test Objective: Verify news agent email is retrieved correctly
    // Input: john@example.com
    // Expected Output: john@example.com
    @Test
    void validEmail_TC05() {
        assertEquals("john@example.com", agent.getEmail());
    }

    // TC06
    // Test Objective: Verify news agent name can be updated
    // Input: Mary Jones
    // Expected Output: Mary Jones
    @Test
    void updateNewsAgentName_TC06() {
        agent.setName("Mary Jones");
        assertEquals("Mary Jones", agent.getName());
    }

    // TC07
    // Test Objective: Verify news agent address can be updated
    // Input: High Street
    // Expected Output: High Street
    @Test
    void updateNewsAgentAddress_TC07() {
        agent.setAddress("High Street");
        assertEquals("High Street", agent.getAddress());
    }

    // TC08
    // Test Objective: Verify news agent phone number can be updated
    // Input: 0861234567
    // Expected Output: 0861234567
    @Test
    void updatePhoneNumber_TC08() {
        agent.setPhoneNumber("0861234567");
        assertEquals("0861234567", agent.getPhoneNumber());
    }

    // TC09
    // Test Objective: Verify news agent email can be updated
    // Input: mary@example.com
    // Expected Output: mary@example.com
    @Test
    void updateEmail_TC09() {
        agent.setEmail("mary@example.com");
        assertEquals("mary@example.com", agent.getEmail());
    }

    // TC10
    // Test Objective: Verify news agent details are displayed
    // Input: News agent details
    // Expected Output: Name, address, phone number and email appear in the result
    @Test
    void readNewsAgentInfo_TC10() {
        String details = agent.toString();

        assertTrue(details.contains("John Smith"));
        assertTrue(details.contains("Main Street"));
        assertTrue(details.contains("0871234567"));
        assertTrue(details.contains("john@example.com"));
    }

    // TC11
    // Test Objective: Verify a positive news agent ID is valid
    // Input: 1
    // Expected Output: true
    @Test
    void verifyValidNewsAgentId_TC11() {
        assertTrue(agent.verifyNewsAgentId(1));
    }

    // TC12
    // Test Objective: Verify zero news agent ID is invalid
    // Input: 0
    // Expected Output: false
    @Test
    void verifyInvalidNewsAgentId_TC12() {
        assertFalse(agent.verifyNewsAgentId(0));
    }

    // TC13
    // Test Objective: Verify blank news agent names are invalid
    // Input: Blank name
    // Expected Output: false
    @Test
    void verifyInvalidNewsAgentName_TC13() {
        assertFalse(agent.verifyName(" "));
    }

    // TC14
    // Test Objective: Verify blank news agent addresses are invalid
    // Input: Blank address
    // Expected Output: false
    @Test
    void verifyInvalidNewsAgentAddress_TC14() {
        assertFalse(agent.verifyAddress(""));
    }

    // TC15
    // Test Objective: Verify valid phone numbers are accepted
    // Input: 0871234567
    // Expected Output: true
    @Test
    void verifyValidPhoneNumber_TC15() {
        assertTrue(agent.verifyPhoneNumber("0871234567"));
    }

    // TC16
    // Test Objective: Verify phone numbers with fewer than 10 digits are invalid
    // Input: 12345
    // Expected Output: false
    @Test
    void verifyInvalidPhoneNumber_TC16() {
        assertFalse(agent.verifyPhoneNumber("12345"));
    }

    // TC17
    // Test Objective: Verify valid email addresses are accepted
    // Input: john@example.com
    // Expected Output: true
    @Test
    void verifyValidEmail_TC17() {
        assertTrue(agent.verifyEmail("john@example.com"));
    }

    // TC18
    // Test Objective: Verify invalid email addresses are rejected
    // Input: johnexample.com
    // Expected Output: false
    @Test
    void verifyInvalidEmail_TC18() {
        assertFalse(agent.verifyEmail("johnexample.com"));
    }

    // TC19
    // Test Objective: Verify blank news agent names are invalid
    // Input: Blank name
    // Expected Output: false
    @Test
    void verifyEmptyNewsAgentName_TC19() {
        assertFalse(agent.verifyName(""));
    }

    // TC20
    // Test Objective: Verify blank email addresses are invalid
    // Input: Blank email
    // Expected Output: false
    @Test
    void verifyInvalidBlankEmail_TC20() {
        assertFalse(agent.verifyEmail(""));
    }
}
