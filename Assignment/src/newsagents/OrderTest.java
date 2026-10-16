package newsagents;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class OrderTest {
//	TC01	
//	Verify product name is accepted when exactly 1 character long	
//	Product Name = "S"	
//	Product name accepted
	
	@Test
	void validProductNameMinBoundary_TC01() {
		Order order = new Order(); 
		assertTrue(order.verifyProductName("S"));
	}
	
//	TC02
//	Verify product name is accepted when more than 1 character
//	Product Name = "Sun"
//	Product name accepted
	
	@Test
	void validProductName_TC02() {
		Order order = new Order();
		assertTrue(order.verifyProductName("Sun"));
	}
	
//	TC03
//	Verify Product name is rejected when blank
//	Product Name = ""
//	Error message: Invalid Product Name
	
	@Test 
	void invalidProductNameBlank_TC03() {
		Order order = new Order();
		assertFalse(order.verifyProductName(""));
	}

//	TC04
//	Verify order date is accepted when it is in DDMMYYYY format
//	Order date = 12102026
//	Order date accepted
	@Test
	void validOrderDate_TC04() {
		Order order = new Order();
		assertTrue(order.verifyOrderDate("12102026"));
	}
	
//	TC27
//	Verify order date rejected if date isn't on the calendar
//	Order date = 32102026
//  Order date rejected
	@Test
	void invalidOrderDate_TC27() {
		Order order = new Order();
		assertFalse(order.verifyOrderDate("32102026"));
	}
	
//	TC05	
//	Verify order date is rejected when it has more than 8 digits
//	Order date = 121020261	
//	Error message: Invalid order date
	@Test 
	void invalidOrderDateMaxBoundary_TC05() {
		Order order = new Order();
		assertFalse(order.verifyOrderDate("121020261"));
	}
	
//	TC06	
//	Verify order date is rejected when it has less than 8 digits
//	Order date = 1210202	
//	Error message: Invalid order date
	@Test 
	void invalidOrderDateMinBoundary_TC06() {
		Order order = new Order();
		assertFalse(order.verifyOrderDate("1210202"));
	}
	
//	TC07	
//	Verify order date is rejected when it is not in DDMMYYYY format
//	Order date = 10312026
//	Error message: Invalid order date
	@Test 
	void invalidOrderDateWrongFormat_TC07() {
		Order order = new Order();
		assertFalse(order.verifyOrderDate("10312026"));
	}
	
//	TC08	
//	Verify order date is rejected when it has alphabetic characters
//	Order date = 1210202	
//	Error message: Invalid order date
	@Test 
	void invalidOrderDateAlphabetic_TC08() {
		Order order = new Order();
		assertFalse(order.verifyOrderDate("12Oct2026"));
	}
	
//	Tc09
//	Verify product quantity is accepted when it is integer that is exactly 1
//	Product Quantity= 1
//	Product Quantity accepted
	@Test
	void validProductQtyMinBoundary_TC12() {
		Order order = new Order();
		assertTrue(order.verifyQuantity(1));
	}
	
//	Tc10
//	Verify product quantity is accepted when it is integer that is > 1
//	Product Quantity= 10
//	Product Quantity accepted
	@Test
	void validProductQty_TC13() {
		Order order = new Order();
		assertTrue(order.verifyQuantity(10));
	}
	
//	Tc11
//	Verify product quantity is rejected when it is 0
//	Product Quantity= 0
//	Product Quantity rejected
	@Test
	void invalidProductQty_TC14() {
		Order order = new Order();
		assertFalse(order.verifyQuantity(0));
	}

//	TC12
//	Verify product price is accepted when it is greater than 0.00
//	Product price = 1.00	
//	Product Price Accepted
	@Test
	void validPriceMinBoundary_TC15() {
		Order order = new Order();
		assertTrue(order.verifyPrice("1.00"));
	}
	
//	TC13
//	Verify product price is rejected when it is 0.00
//	Product price = 0.00
//	Product Price Rejected
	@Test
	void invalidPrice_TC16() {
		Order order = new Order();
		assertFalse(order.verifyPrice("0.00"));
	}
	
//	TC14
//	Verify product price is accepted with exactly 2 numbers after the decimal point
//	Product price = 1.55
//	Product Price Rejected
	@Test
	void validPrice_TC17() {
		Order order = new Order();
		assertTrue(order.verifyPrice("1.55"));
	}
	
//	TC15
//	Verify product price is rejected with 1 number after the decimal point
//	Product price = 1.1	
//	Product Price Rejected
	@Test
	void invalidPriceOneDecimal_TC18() {
		Order order = new Order();
		assertFalse(order.verifyPrice("1.1"));
	}
	
//	TC16
//	Verify product price is rejected with 1 number after the decimal point
//	Product price = 1.123	
//	Product Price Rejected
	@Test
	void invalidPriceThreeDecimal_TC19() {
		Order order = new Order();
		assertFalse(order.verifyPrice("1.123"));
	}
	
//	TC17
//	Verify price * quantity returns total price
//	Price * quantity = 2.55 * 3
//	Total price = 7.65

	@Test
	void totalPrice_TC20() {
	    Order order = new Order();
	    assertEquals(7.65, order.totalPrice(3, "2.55"), 0.001);
	}
	
//  TC18
//	Verify order is created when all details are valid
//	12102026, Sun, 5, 1.50, Main Street
//	Order created successfully
	@Test
	void validOrder_TC21() {
		Order order = new Order();
		assertTrue(order.createOrder("12102026", "Sun", 5, "1.50"));
	}
	
//  TC19
//	Verify order creation fails when order date is invalid
//	"", Sun, 5, 1.50, Main Street
//	Order not created
	@Test
	void invalidOrderNoDate_TC22() {
		Order order = new Order();
		assertFalse(order.createOrder("", "Sun", 5, "1.50"));
	}
	
//  TC20
//	Verify order creation fails when product name is invalid
//	12102026, "", 5, 1.50, Main Street
//	Order not created
	@Test
	void invalidOrderNoName_TC23() {
		Order order = new Order();
		assertFalse(order.createOrder("12102026", "", 5, "1.50"));
	}
	
//  TC21
//	Verify order creation fails when product quantity is invalid
//	12102026, Sun, 0, 1.50, Main Street
//	Order not created
	@Test
	void invalidOrderInvalidQty_TC24() {
		Order order = new Order();
		assertFalse(order.createOrder("12102026", "Sun", 0, "1.50"));
	}
	
//  TC22
//	Verify order creation fails when product price is invalid
//	12102026, Sun, 5, 1.1, Main Street
//	Order not created
	@Test
	void invalidOrderInvalidPrice_TC25() {
		Order order = new Order();
		assertFalse(order.createOrder("12102026", "Sun", 5, "1.1"));
	}
}