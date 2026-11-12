package newsagents;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Order {
	private int orderId;
	private String orderDate;
	private String productName;
	private String price;
	private int quantity;
	private int customerId;

	public boolean verifyProductName(String pname) {
		if (pname == null || pname.trim().isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean verifyOrderDate(String orderDate) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
					.withResolverStyle(ResolverStyle.STRICT);
			LocalDate.parse(orderDate, formatter);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCustId() {
		return customerId;
	}

	public void setCustId(int custId) {
		this.customerId = custId;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public boolean verifyQuantity(int qty) {
		if (qty >= 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyPrice(String price) {
		if (price == null || price.trim().isEmpty()) {
			return false;
		}

		if (price.matches("\\d+\\.\\d{2}") && Double.parseDouble(price) > 0) {
			return true;
		}

		return false;
	}

	public boolean verifyCustomerId(int id) {
		CustomerDAO customerDAO = new CustomerDAO();
		return customerDAO.findCustomerById(id) != null && id > 0;
	}

	public double totalPrice(int qty, String price) {
		Double newPrice = Double.parseDouble(price);
		return qty * newPrice;
	}

	public boolean createOrder(String orderDate, String pName, int qty, String price, int custId) {
		if (verifyProductName(pName) && verifyOrderDate(orderDate) && verifyQuantity(qty) && verifyPrice(price) && verifyCustomerId(custId)) {
			return true;
		}
		return false;
	}

	public Order(String orderDate, String productName, int quantity, String price, int custId) {
		super();
		this.orderDate = orderDate;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.customerId = custId;
	}

	public Order() {
	}

}
