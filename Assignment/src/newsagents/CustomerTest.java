package newsagents;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CustomerTest {

// TC01
// Test Objective: Verify first name is accepted when exactly 1 character long
// Input(s): First Name = "J"
// Expected Output(s): First name accepted
	@Test
	void validFirstNameMinBoundary_TC01() {
		Customer customer = new Customer();
		assertTrue(customer.verifyFirstName("J"));
	}

// TC02
// Test Objective: Verify first name is accepted when more than 1 character
// Input(s): First Name = "John"
// Expected Output(s): First name accepted
	@Test
	void validFirstName_TC02() {
		Customer customer = new Customer();
		assertTrue(customer.verifyFirstName("John"));
	}

// TC03
// Test Objective: Verify first name is rejected when blank
// Input(s): First Name = ""
// Expected Output(s): Error message: Invalid First Name
	@Test
	void invalidFirstNameBlank_TC03() {
		Customer customer = new Customer();
		assertFalse(customer.verifyFirstName(""));
	}

// TC04
// Test Objective: Verify surname is accepted when exactly 1 character long
// Input(s): Surname = "S"
// Expected Output(s): Surname accepted
	@Test
	void validSurnameMinBoundary_TC04() {
		Customer customer = new Customer();
		assertTrue(customer.verifySurname("S"));
	}

// TC05
// Test Objective: Verify surname is accepted when more than 1 character
// Input(s): Surname = "Smith"
// Expected Output(s): Surname accepted
	@Test
	void validSurname_TC05() {
		Customer customer = new Customer();
		assertTrue(customer.verifySurname("Smith"));
	}

// TC06
// Test Objective: Verify surname is rejected when blank
// Input(s): Surname = ""
// Expected Output(s): Error message: Invalid Surname
	@Test
	void invalidSurnameBlank_TC06() {
		Customer customer = new Customer();
		assertFalse(customer.verifySurname(""));
	}

// TC07
// Test Objective: Verify address is accepted when exactly 1 character long
// Input(s): Address = "A"
// Expected Output(s): Address accepted
	@Test
	void validAddressMinBoundary_TC07() {
		Customer customer = new Customer();
		assertTrue(customer.verifyAddress("A"));
	}

// TC08
// Test Objective: Verify address is accepted when more than 1 character
// Input(s): Address = "Main Street"
// Expected Output(s): Address accepted
	@Test
	void validAddress_TC08() {
		Customer customer = new Customer();
		assertTrue(customer.verifyAddress("Main Street"));
	}

// TC09
// Test Objective: Verify address is rejected when blank
// Input(s): Address = ""
// Expected Output(s): Error message: Invalid Address
	@Test
	void invalidAddressBlank_TC09() {
		Customer customer = new Customer();
		assertFalse(customer.verifyAddress(""));
	}

// TC10
// Test Objective: Verify phone number is accepted when exactly 10 digits
// Input(s): Phone Number = "0871234567"
// Expected Output(s): Phone number accepted
	@Test
	void validPhoneNumber_TC10() {
		Customer customer = new Customer();
		assertTrue(customer.verifyPhoneNumber("0871234567"));
	}

// TC11
// Test Objective: Verify phone number is rejected when fewer than 10 digits
// Input(s): Phone Number = "087123456"
// Expected Output(s): Error message: Invalid Phone Number
	@Test
	void invalidPhoneNumberTooShort_TC11() {
		Customer customer = new Customer();
		assertFalse(customer.verifyPhoneNumber("087123456"));
	}

// TC12
// Test Objective: Verify phone number is rejected when more than 10 digits
// Input(s): Phone Number = "08712345678"
// Expected Output(s): Error message: Invalid Phone Number
	@Test
	void invalidPhoneNumberTooLong_TC12() {
		Customer customer = new Customer();
		assertFalse(customer.verifyPhoneNumber("08712345678"));
	}

// TC13
// Test Objective: Verify phone number is rejected when containing alphabetic characters
// Input(s): Phone Number = "08712AB567"
// Expected Output(s): Error message: Invalid Phone Number
	@Test
	void invalidPhoneNumberAlphaCharacters_TC13() {
		Customer customer = new Customer();
		assertFalse(customer.verifyPhoneNumber("08712AB567"));
	}

// TC14
// Test Objective: Verify customer account is created when all details are valid
// Input(s): John, Smith, Main Street, 0871234567
// Expected Output(s): Customer account created successfully
	@Test
	void createValidCustomerAccount_TC14() {
		Customer customer = new Customer();

		assertTrue(customer.createCustomerAccount("John", "Smith", "Main Street", "0871234567"));
	}

// TC15
// Test Objective: Verify customer account creation fails with invalid first name
// Input(s): "", Smith, Main Street, 0871234567
// Expected Output(s): Customer account not created
	@Test
	void createAccountInvalidFirstName_TC15() {
		Customer customer = new Customer();
		assertFalse(customer.createCustomerAccount("", "Smith", "Main Street", "0871234567"));
	}

// TC16
// Test Objective: Verify customer account creation fails with invalid surname
// Input(s): John, "", Main Street, 0871234567
// Expected Output(s): Customer account not created
	@Test
	void createAccountInvalidSurname_TC16() {
		Customer customer = new Customer();
		assertFalse(customer.createCustomerAccount("John", "", "Main Street", "0871234567"));
	}

// TC17
// Test Objective: Verify customer account creation fails with invalid address
// Input(s): John, Smith, "", 0871234567
// Expected Output(s): Customer account not created
	@Test
	void createAccountInvalidAddress_TC17() {
		Customer customer = new Customer();
		assertFalse(customer.createCustomerAccount("John", "Smith", "", "0871234567"));
	}

// TC18
// Test Objective: Verify customer account creation fails with invalid phone number
// Input(s): John, Smith, Main Street, 087123456
// Expected Output(s): Customer account not created
	@Test
	void createAccountInvalidPhoneNumber_TC18() {
		Customer customer = new Customer();

		assertFalse(customer.createCustomerAccount("John", "Smith", "Main Street", "087123456"));
	}
}
