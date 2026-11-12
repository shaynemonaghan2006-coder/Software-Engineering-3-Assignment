package newsagents;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DeliveryPersonTestJunit {

// TC01
// Test Objective: Verify first name is accepted when exactly 1 character long
// Input(s): First Name = "J"
// Expected Output(s): First name accepted
	@Test
	void validFirstNameMinBoundary_TC01() {
		DeliveryPerson dp = new DeliveryPerson();
		assertTrue(dp.verifyFirstName("J"));
	}

// TC02
// Test Objective: Verify first name is accepted when more than 1 character
// Input(s): First Name = "John"
// Expected Output(s): First name accepted
	@Test
	void validFirstName_TC02() {
		DeliveryPerson dp = new DeliveryPerson();
		assertTrue(dp.verifyFirstName("John"));
	}

// TC03
// Test Objective: Verify first name is rejected when blank
// Input(s): First Name = ""
// Expected Output(s): Error message: Invalid First Name
	@Test
	void invalidFirstNameBlank_TC03() {
		DeliveryPerson dp = new DeliveryPerson();
		assertFalse(dp.verifyFirstName(""));
	}

// TC04
// Test Objective: Verify surname is accepted when exactly 1 character long
// Input(s): Surname = "S"
// Expected Output(s): Surname accepted
	@Test
	void validSurnameMinBoundary_TC04() {
		DeliveryPerson dp = new DeliveryPerson();
		assertTrue(dp.verifySurname("S"));
	}

// TC05
// Test Objective: Verify surname is accepted when more than 1 character
// Input(s): Surname = "Smith"
// Expected Output(s): Surname accepted
	@Test
	void validSurname_TC05() {
		DeliveryPerson dp = new DeliveryPerson();
		assertTrue(dp.verifySurname("Smith"));
	}

// TC06
// Test Objective: Verify surname is rejected when blank
// Input(s): Surname = ""
// Expected Output(s): Error message: Invalid Surname
	@Test
	void invalidSurnameBlank_TC06() {
		DeliveryPerson dp = new DeliveryPerson();
		assertFalse(dp.verifySurname(""));
	}

// TC07
// Test Objective: Verify address is accepted when exactly 1 character long
// Input(s): Address = "A"
// Expected Output(s): Address accepted
	@Test
	void validAddressMinBoundary_TC07() {
		DeliveryPerson dp = new DeliveryPerson();
		assertTrue(dp.verifyAddress("A"));
	}

// TC08
// Test Objective: Verify address is accepted when more than 1 character
// Input(s): Address = "Main Street"
// Expected Output(s): Address accepted
	@Test
	void validAddress_TC08() {
		DeliveryPerson dp = new DeliveryPerson();
		assertTrue(dp.verifyAddress("Main Street"));
	}

// TC09
// Test Objective: Verify address is rejected when blank
// Input(s): Address = ""
// Expected Output(s): Error message: Invalid Address
	@Test
	void invalidAddressBlank_TC09() {
		DeliveryPerson dp = new DeliveryPerson();
		assertFalse(dp.verifyAddress(""));
	}

// TC10
// Test Objective: Verify phone number is accepted when exactly 10 digits
// Input(s): Phone Number = "0871234567"
// Expected Output(s): Phone number accepted
	@Test
	void validPhoneNumber_TC10() {
		DeliveryPerson dp = new DeliveryPerson();
		assertTrue(dp.verifyPhoneNumber("0871234567"));
	}

// TC11
// Test Objective: Verify phone number is rejected when fewer than 10 digits
// Input(s): Phone Number = "087123456"
// Expected Output(s): Error message: Invalid Phone Number
	@Test
	void invalidPhoneNumberTooShort_TC11() {
		DeliveryPerson dp = new DeliveryPerson();
		assertFalse(dp.verifyPhoneNumber("087123456"));
	}

// TC12
// Test Objective: Verify phone number is rejected when more than 10 digits
// Input(s): Phone Number = "08712345678"
// Expected Output(s): Error message: Invalid Phone Number
	@Test
	void invalidPhoneNumberTooLong_TC12() {
		DeliveryPerson dp = new DeliveryPerson();
		assertFalse(dp.verifyPhoneNumber("08712345678"));
	}

// TC13
// Test Objective: Verify phone number is rejected when containing alphabetic characters
// Input(s): Phone Number = "08712AB567"
// Expected Output(s): Error message: Invalid Phone Number
	@Test
	void invalidPhoneNumberAlphaCharacters_TC13() {
		DeliveryPerson dp = new DeliveryPerson();
		assertFalse(dp.verifyPhoneNumber("08712AB567"));
	}

// TC14
// Test Objective: Verify DeliveryPerson account is created when all details are valid
// Input(s): John, Smith, Main Street, 0871234567
// Expected Output(s): Customer account created successfully
	@Test
	void createValidDeliveryPersonAccount_TC14() {
		DeliveryPerson dp = new DeliveryPerson();

		assertTrue(dp.createDeliveryPersonAccount("John", "Smith", "Main Street", "0871234567"));
	}

// TC15
// Test Objective: Verify DeliveryPerson account creation fails with invalid first name
// Input(s): "", Smith, Main Street, 0871234567
// Expected Output(s): Customer account not created
	@Test
	void createAccountInvalidFirstName_TC15() {
		DeliveryPerson dp = new DeliveryPerson();
		assertFalse(dp.createDeliveryPersonAccount("", "Smith", "Main Street", "0871234567"));
	}

// TC16
// Test Objective: Verify DeliveryPerson account creation fails with invalid surname
// Input(s): John, "", Main Street, 0871234567
// Expected Output(s): Customer account not created
	@Test
	void createAccountInvalidSurname_TC16() {
		DeliveryPerson dp = new DeliveryPerson();
		assertFalse(dp.createDeliveryPersonAccount("John", "", "Main Street", "0871234567"));
	}

// TC17
// Test Objective: Verify DeliveryPerson account creation fails with invalid address
// Input(s): John, Smith, "", 0871234567
// Expected Output(s): Customer account not created
	@Test
	void createAccountInvalidAddress_TC17() {
		DeliveryPerson dp = new DeliveryPerson();
		assertFalse(dp.createDeliveryPersonAccount("John", "Smith", "", "0871234567"));
	}

// TC18
// Test Objective: Verify DeliveryPerson account creation fails with invalid phone number
// Input(s): John, Smith, Main Street, 087123456
// Expected Output(s): Customer account not created
	@Test
	void createAccountInvalidPhoneNumber_TC18() {
		DeliveryPerson dp = new DeliveryPerson();

		assertFalse(dp.createDeliveryPersonAccount("John", "Smith", "Main Street", "087123456"));
	}
}

