package newagents;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Order {
	
	public boolean verifyProductName(String pname) {
		if(pname.equals("")) {
			return false;
		}
		return true;
	}
	
	public boolean verifyOrderDate(String orderDate) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMuuuu").withResolverStyle(ResolverStyle.STRICT);
			LocalDate.parse(orderDate, formatter);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public boolean verifyAddress(String address) {
		if(address.equals("")) {
			return false;
		}
		return true;
	}
	
	public boolean verifyQuantity(int qty) {
	    if (qty >= 1) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public boolean verifyPrice(String price) {
	    if (price.matches("\\d+\\.\\d{2}")
	            && Double.parseDouble(price) > 0) {
	        return true;
	    }
	    return false;
	}
	
	public double totalPrice(int qty, String price) {
		Double newPrice = Double.parseDouble(price);
		return qty*newPrice;
	}
	
	public boolean createOrder(String orderDate, String pName, int qty, String price, String address) {
//		Order order = new Order(orderDate,pName, qty, price, address);
		if(verifyProductName(pName) && verifyOrderDate(orderDate) && verifyAddress(address) && verifyQuantity(qty) && verifyPrice(price)) {
			return true;
		}
		return false;
	}
	
	public Order() {
		
	}
	
	public Order(String orderDate, String pName, int qty, String price, String address) {
		
	}
	
	
}
