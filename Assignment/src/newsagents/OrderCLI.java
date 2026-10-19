package newsagents;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderCLI {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		OrderDAO orderDAO = new OrderDAO();
		Order order;
		
		while(running) {
			System.out.println();
			System.out.println("=== Order Menu ===");
			System.out.println("1 - Create Order");
			System.out.println("2 - Read Order by ID");
			System.out.println("3 - Update Order");
			System.out.println("4 - Delete Order");
			System.out.println("5 - Read all Orders");
			System.out.println("6 - Exit");
			System.out.println();
			System.out.print("Option: ");
			int option = scanner.nextInt();
			scanner.nextLine();
			
			switch(option) {
			case 1:
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
				
				order = new Order(OrderDate,productName,quantity,price,custId);

				orderDAO = new OrderDAO();

				if (orderDAO.addOrder(order)) {
					System.out.println("Order saved successfully.");
				} else {
					System.out.println("Failed to save order.");
				}
				break;
				
			case 2: 
				System.out.print("Enter Order Id");
				int orderId = scanner.nextInt();
				scanner.nextLine();
				
				order = orderDAO.findOrderById(orderId);
				
				if(order !=null) {
					System.out.println("\nOrder found: ");
					System.out.println("ID: " + order.getOrderId());
					System.out.println("Order Date: " + order.getOrderDate());
					System.out.println("Product Name: " + order.getProductName());
					System.out.println("Quantity: " + order.getQuantity());
					System.out.println("Price: " + order.getPrice());
					System.out.println("Customer ID: " + order.getCustId());
				} else {
					System.out.println("Order not found.");
				}
				break;
				
			case 3: 
				System.out.print("Enter Order Id to be updated");
				orderId = scanner.nextInt();
				scanner.nextLine();
				
				order = orderDAO.findOrderById(orderId);
				
				if(order !=null) {
					System.out.print("New Order Date: ");
					order.setOrderDate(scanner.nextLine());
					
					System.out.print("New Product Name: ");
					order.setProductName(scanner.nextLine());
					
					System.out.print("New Quantity");
					order.setQuantity(scanner.nextInt());
					
					System.out.print("New Customer Id: ");
					order.setCustId(scanner.nextInt());
					scanner.nextLine();
					
					System.out.print("New price: ");
					order.setPrice(scanner.nextLine());
					
					if (orderDAO.addOrder(order)) {
						System.out.println("Order updated successfully.");
					} else {
						System.out.println("Failed to update order.");
					}
				
				
				} else {
					System.out.println("Order not found");
				}
				break;
				
			case 4:
				System.out.print("Enter Order Id to delete");
				orderId = scanner.nextInt();
				scanner.nextLine();
				
				order = orderDAO.findOrderById(orderId);
				
				if(order!=null) {
					if(orderDAO.deleteOrder(orderId)) {
						System.out.println("Order deleted successfully.");
					} else {
						System.out.println("Failed to delete order");
					}
				} else {
					System.out.println("Order not found");
				}
				break;
			
			case 5:
				ArrayList<Order> orders = orderDAO.getAllOrders();
				
				if(orders.isEmpty()) {
					System.out.println("No orders found");
				} else {
					System.out.println("\n=== All Orders ===");
					
					for(Order ord:orders) {
						System.out.println("--------------------");
						System.out.println("ID: " + ord.getOrderId());
						System.out.println("Order Date: " + ord.getOrderDate());
						System.out.println("Product Name: " + ord.getProductName());
						System.out.println("Quantity: " + ord.getQuantity());
						System.out.println("Price: " + ord.getPrice());
						System.out.println("Customer ID: " + ord.getCustId());
					}
				}
				break;
			
			case 6: 
				running = false;
				System.out.println("Have a nice day!");
				break;
			
			default:
				System.out.println("Invalid option chosen.");
				break;
			}
		}
		
		scanner.close();
	}
}
