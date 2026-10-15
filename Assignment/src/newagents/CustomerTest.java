package newagents;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    // TC01
    // Verify customer ID is stored correctly
    // Customer ID = 1
    // Expected Output: Customer ID accepted

    @Test
    void validCustomerId_TC01() {
        Customer customer = new Customer(1, "John Smith",
                "Main Street", "0871234567");

        assertEquals(1, customer.getCustomerId());
    }

    // TC02
    // Verify customer name is stored correctly
    // Customer Name = "John Smith"
    // Expected Output: Customer name accepted

    @Test
    void validCustomerName_TC02() {
        Customer customer = new Customer(1, "John Smith",
                "Main Street", "0871234567");

        assertEquals("John Smith", customer.getName());
    }

    // TC03
    // Verify customer address is stored correctly
    // Customer Address = "Main Street"
    // Expected Output: Customer address accepted

    @Test
    void validCustomerAddress_TC03() {
        Customer customer = new Customer(1, "John Smith",
                "Main Street", "0871234567");

        assertEquals("Main Street", customer.getAddress());
    }

    // TC04
    // Verify customer phone number is stored correctly
    // Phone Number = "0871234567"
    // Expected Output: Phone number accepted

    @Test
    void validCustomerPhoneNumber_TC04() {
        Customer customer = new Customer(1, "John Smith",
                "Main Street", "0871234567");

        assertEquals("0871234567", customer.getPhoneNumber());
    }

    // TC05
    // Verify customer ID can be updated
    // Customer ID = 2
    // Expected Output: Customer ID updated successfully

    @Test
    void updateCustomerId_TC05() {
        Customer customer = new Customer(1, "John Smith",
                "Main Street", "0871234567");

        customer.setCustomerId(2);

        assertEquals(2, customer.getCustomerId());
    }

    // TC06
    // Verify customer name can be updated
    // Customer Name = "Jane Smith"
    // Expected Output: Customer name updated successfully

    @Test
    void updateCustomerName_TC06() {
        Customer customer = new Customer(1, "John Smith",
                "Main Street", "0871234567");

        customer.setName("Jane Smith");

        assertEquals("Jane Smith", customer.getName());
    }

    // TC07
    // Verify customer address can be updated
    // Customer Address = "High Street"
    // Expected Output: Customer address updated successfully

    @Test
    void updateCustomerAddress_TC07() {
        Customer customer = new Customer(1, "John Smith",
                "Main Street", "0871234567");

        customer.setAddress("High Street");

        assertEquals("High Street", customer.getAddress());
    }

    // TC08
    // Verify customer phone number can be updated
    // Phone Number = "0869876543"
    // Expected Output: Phone number updated successfully

    @Test
    void updateCustomerPhoneNumber_TC08() {
        Customer customer = new Customer(1, "John Smith",
                "Main Street", "0871234567");

        customer.setPhoneNumber("0869876543");

        assertEquals("0869876543", customer.getPhoneNumber());
    }

    // TC09
    // Verify customer details are returned as a string
    // Customer Name = "John Smith"
    // Expected Output: Customer details included in string

    @Test
    void customerDetailsToString_TC09() {
        Customer customer = new Customer(1, "John Smith",
                "Main Street", "0871234567");

        String details = customer.toString();

        assertTrue(details.contains("John Smith"));
        assertTrue(details.contains("Main Street"));
        assertTrue(details.contains("0871234567"));
    }
}
