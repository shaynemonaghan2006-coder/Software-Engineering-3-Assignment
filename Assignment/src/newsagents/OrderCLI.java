package newsagents;

import java.util.Scanner;

public class OrderCLI {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("=== Create New Order ===");

		System.out.print("Order Date: ");
		String OrderDate = scanner.nextLine();

		System.out.print("Product Name: ");
		String productName = scanner.nextLine();

		System.out.print("Quantity: ");
		int quantity = scanner.nextInt();
		
		System.out.print("Customer ID: ");
		int custId = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Price: ");
		String price = scanner.nextLine();
		
		

		Order order = new Order(OrderDate,productName,quantity,price,custId);

		OrderDAO orderDAO = new OrderDAO();

		if (orderDAO.addOrder(order)) {
			System.out.println("Order saved successfully.");
		} else {
			System.out.println("Failed to save order.");
		}

		scanner.close();
	}
}
