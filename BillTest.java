package newagents;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BillTest {
	
	// Create cases

	// TC01
	// Test Objective: Verify Bill ID is accepted when its length is at least 1 character
	// Input(s): Bill ID = 1
	// Expected Output(s): Bill ID accepted

	@Test
	void validBillIdMin_TC01() {
	    Bill bill = new Bill(1, "ORD-101", "Paid", "16/10/2026", 150.50);
	    assertTrue(String.valueOf(bill.getBillId()).length() > 0);
	}

	// TC02
	// Test Objective: Verify Bill ID is accepted when its length does not exceed 30 characters
	// Input(s): Bill ID = 1
	// Expected Output(s): Bill ID accepted

	@Test
	void validBillIdMax_TC02() {
	    Bill bill = new Bill(1, "ORD-101", "Paid", "16/10/2026", 150.50);
	    assertFalse(String.valueOf(bill.getBillId()).length() > 30);
	}

	// TC03
	// Test Objective: Verify Order Reference is accepted when its length is at least 1 character
	// Input(s): Order Reference = "ORD-101"
	// Expected Output(s): Order Reference accepted

	@Test
	void validOrderRefMin_TC03() {
	    Bill bill = new Bill(1, "ORD-101", "Paid", "16/10/2026", 150.50);
	    assertTrue(bill.getOrderReference().length() > 0);
	}

	// TC04
	// Test Objective: Verify Order Reference is accepted when its length does not exceed 50 characters
	// Input(s): Order Reference = "ORD-101"
	// Expected Output(s): Order Reference accepted

	@Test
	void validOrderRefMax_TC04() {
	    Bill bill = new Bill(1, "ORD-101", "Paid", "16/10/2026", 150.50);
	    assertFalse(bill.getOrderReference().length() > 50);
	}

	// TC05
	// Test Objective: Verify Payment Status is accepted when the status is Paid
	// Input(s): Payment Status = "Paid"
	// Expected Output(s): Payment status accepted

	@Test
	void validPaymentStatus_TC05() {
	    Bill bill = new Bill(1, "ORD-101", "Paid", "16/10/2026", 150.50);
	    assertTrue(bill.getPaymentStatus().equals("Paid"));
	}

	// TC06
	// Test Objective: Verify Payment Status is rejected when the status is Unpaid
	// Input(s): Payment Status = "Unpaid"
	// Expected Output(s): Payment status is not Paid

	@Test
	void invalidPaymentStatus_TC06() {
		Bill bill = new Bill(1, "ORD-101", "Unpaid", "16/10/2026", 150.50);
		assertFalse(bill.getPaymentStatus().equals("Paid"));
	}
	
	// TC07
	// Test Objective: Verify Payment Date is accepted when a valid date is provided
	// Input(s): Payment Date = "16/10/2026"
	// Expected Output(s): Payment date accepted

	@Test
	void validPaymentDate_TC07() {
	    Bill bill = new Bill(1, "ORD-101", "Paid", "16/10/2026", 150.50);
	    assertTrue(bill.getPaymentDate().equals("16/10/2026"));
	}

	// TC08
	// Test Objective: Verify Total Amount is accepted when a valid decimal is provided
	// Input(s): Total Amount = 150.50
	// Expected Output(s): Total Amount matches

	@Test
	void validTotalAmount_TC08() {
	    Bill bill = new Bill(1, "ORD-101", "Paid", "16/10/2026", 150.50);
	    assertEquals(150.50, bill.getTotalAmount());
	}
	
	//Update tests cases
	
	// TC09
	// Test Objective: Verify Bill ID can be updated
	// Input(s): Bill ID = 2
	// Expected Output(s): Bill ID updated to 2

	@Test
	void updateBillId_TC09() {
	    Bill bill = new Bill(1, "ORD-101", "Paid", "16/10/2026", 150.50);
	    bill.setBillId(2);

	    assertEquals(2, bill.getBillId());
	}

	// TC10
	// Test Objective: Verify Order Reference can be updated
	// Input(s): Order Reference = "ORD-102"
	// Expected Output(s): Order Reference updated to "ORD-102"

	@Test
	void updateOrderRef_TC10() {
	    Bill bill = new Bill(1, "ORD-101", "Paid", "16/10/2026", 150.50);
	    bill.setOrderReference("ORD-102");

	    assertEquals("ORD-102", bill.getOrderReference());
	}

	// TC11
	// Test Objective: Verify Payment Date can be updated
	// Input(s): Payment Date = "17/10/2026"
	// Expected Output(s): Payment Date updated to "17/10/2026"

	@Test
	void updatePaymentDate_TC11() {
	    Bill bill = new Bill(1, "ORD-101", "Paid", "16/10/2026", 150.50);
	    bill.setPaymentDate("17/10/2026");

	    assertEquals("17/10/2026", bill.getPaymentDate());
	}

	// TC12
	// Test Objective: Verify Payment Status can be updated
	// Input(s): Payment Status = "Refunded"
	// Expected Output(s): Payment Status updated to "Refunded"

	@Test
	void updatePaymentStatus_TC12() {
	    Bill bill = new Bill(1, "ORD-101", "Paid", "16/10/2026", 150.50);
	    bill.setPaymentStatus("Refunded");

	    assertEquals("Refunded", bill.getPaymentStatus());
	}
	
	// TC13
	// Test Objective: Verify Total Amount can be updated
	// Input(s): Total Amount = 200.00
	// Expected Output(s): Total Amount updated to 200.00

	@Test
	void updateTotalAmount_TC13() {
	    Bill bill = new Bill(1, "ORD-101", "Paid", "16/10/2026", 150.50);
	    bill.setTotalAmount(200.00);

	    assertEquals(200.00, bill.getTotalAmount());
	}
	
	// Read test case
	
	// TC14
	// Test Objective: Verify a bill can be read successfully
	// Input(s): Bill ID = 1
	// Expected Output(s): Bill details retrieved successfully

	@Test
	void readBillInfo_TC14() {
	    Bill bill = new Bill(1, "ORD-101", "Pending", "16/10/2026", 150.50);

	    assertEquals(1, bill.getBillId());
	    assertEquals("ORD-101", bill.getOrderReference());
	    assertEquals("Pending", bill.getPaymentStatus());
	    assertEquals("16/10/2026", bill.getPaymentDate());
	    assertEquals(150.50, bill.getTotalAmount());
	}
 
}