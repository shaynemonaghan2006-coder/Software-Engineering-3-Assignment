package newsagents;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DeliveryDocketTest {
	
	// Create cases

	// TC01
	// Test Objective: Verify Docket ID is accepted when its length is at least 1 character
	// Input(s): Docket ID = 0
	// Expected Output(s): Docket ID accepted

	@Test
	void validDocketIdMin_TC01() {
	    DeliveryDocket docket = new DeliveryDocket(1, 101, "16/10/2026", "Delivered");
	    assertTrue(String.valueOf(docket.getDocketId()).length() > 0);
	}

	// TC02
	// Test Objective: Verify Docket ID is accepted when its length does not exceed 30 characters
	// Input(s): Docket ID = 1
	// Expected Output(s): Docket ID accepted

	@Test
	void validDocketIdMax_TC02() {
	    DeliveryDocket docket = new DeliveryDocket(1, 101, "16/10/2026", "Delivered");
	    assertFalse(String.valueOf(docket.getDocketId()).length() > 30);
	}

	// TC03
	// Test Objective: Verify Order ID is accepted when its length is at least 1 character
	// Input(s): Order ID = 101
	// Expected Output(s): Order ID accepted

	@Test
	void validOrderIdMin_TC03() {
	    DeliveryDocket docket = new DeliveryDocket(1, 101, "16/10/2026", "Delivered");
	    assertTrue(String.valueOf(docket.getOrderId()).length() > 0);
	}

	// TC04
	// Test Objective: Verify Order ID is accepted when its length does not exceed 30 characters
	// Input(s): Order ID = 101
	// Expected Output(s): Order ID accepted

	@Test
	void validOrderIdMax_TC04() {
	    DeliveryDocket docket = new DeliveryDocket(1, 101, "16/10/2026", "Delivered");
	    assertFalse(String.valueOf(docket.getOrderId()).length() > 30);
	}

    // TC04 - Verify delivery status
	// TC05
	// Test Objective: Verify Delivery Status is accepted when the status is Delivered
	// Input(s): Delivery Status = "Delivered"
	// Expected Output(s): Delivery status accepted

	@Test
	void validDeliveryStatus_TC05() {
	    DeliveryDocket docket = new DeliveryDocket(1, 101, "16/10/2026", "Delivered");
	    assertTrue(docket.getDeliveryStatus().equals("Delivered"));
	}

	// TC06
	// Test Objective: Verify Delivery Status is rejected when the status is Unknown
	// Input(s): Delivery Status = "Unknown"
	// Expected Output(s): Delivery status is not Delivered

	@Test
	void invalidDeliveryStatus_TC06() {
		DeliveryDocket docket = new DeliveryDocket(1, 101, "16/10/2026", "Unknown");
		assertFalse(docket.getDeliveryStatus().equals("Delivered"));
	}
	
	// TC07
	// Test Objective: Verify Delivery Date is accepted when a valid date is provided
	// Input(s): Delivery Date = "16/10/2026"
	// Expected Output(s): Delivery date accepted

	@Test
	void validDeliveryDate_TC07() {
	    DeliveryDocket docket = new DeliveryDocket(1, 101, "16/10/2026", "Delivered");
	    assertTrue(docket.getDeliveryDate().equals("16/10/2026"));
	}

	// TC08
	// Test Objective: Verify Delivery Date is not accepted when it does not match the expected date
	// Input(s): Delivery Date = "invalid"
	// Expected Output(s): Delivery date does not match the expected date

	@Test
	void invalidDeliveryDate_TC08() {
	    DeliveryDocket docket = new DeliveryDocket(1, 101, "invalid", "Delivered");
	    assertFalse(docket.getDeliveryDate().equals("17/10/2026"));
	}
	
	//Update tests cases
	
	// TC09
	// Test Objective: Verify Docket ID can be updated
	// Input(s): Docket ID = 2
	// Expected Output(s): Docket ID updated to 2

	@Test
	void updateDocketId_TC09() {
	    DeliveryDocket docket = new DeliveryDocket(1, 101, "16/10/2026", "Delivered");
	    docket.setDocketId(2);

	    assertEquals(2, docket.getDocketId());
	}

	// TC10
	// Test Objective: Verify Order ID can be updated
	// Input(s): Order ID = 102
	// Expected Output(s): Order ID updated to 102

	@Test
	void updateOrderId_TC10() {
	    DeliveryDocket docket = new DeliveryDocket(1, 101, "16/10/2026", "Delivered");
	    docket.setOrderId(102);

	    assertEquals(102, docket.getOrderId());
	}

	// TC11
	// Test Objective: Verify Delivery Date can be updated
	// Input(s): Delivery Date = "17/10/2026"
	// Expected Output(s): Delivery Date updated to "17/10/2026"

	@Test
	void updateDeliveryDate_TC11() {
	    DeliveryDocket docket = new DeliveryDocket(1, 101, "16/10/2026", "Delivered");
	    docket.setDeliveryDate("17/10/2026");

	    assertEquals("17/10/2026", docket.getDeliveryDate());
	}

	// TC12
	// Test Objective: Verify Delivery Status can be updated
	// Input(s): Delivery Status = "Delivered"
	// Expected Output(s): Delivery Status updated to "Delivered"

	@Test
	void updateDeliveryStatus_TC12() {
	    DeliveryDocket docket = new DeliveryDocket(1, 101, "16/10/2026", "Delivered");
	    docket.setDeliveryStatus("Not Delivered");

	    assertEquals("Delivered", docket.getDeliveryStatus());
	}
	
	// Read test case
	
	// TC15
	// Test Objective: Verify a delivery docket can be read successfully
	// Input(s): Docket ID = 1
	// Expected Output(s): Delivery docket details retrieved successfully

	@Test
	void readDocketInfo_TC13() {
	    DeliveryDocket docket = new DeliveryDocket(1, 101, "16/10/2026", "Pending");

	    assertEquals(1, docket.getDocketId());
	    assertEquals(101, docket.getOrderId());
	    assertEquals("16/10/2026", docket.getDeliveryDate());
	    assertEquals("Pending", docket.getDeliveryStatus());
	}
 
}