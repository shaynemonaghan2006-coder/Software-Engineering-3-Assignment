package newsagents;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderCLI {
	static Scanner scanner = new Scanner(System.in);
	static OrderDAO orderDAO = new OrderDAO();
	static Order order;
	static int orderId;
	public OrderCLI() {

		boolean running = true;
		
		while(running) {
			int option = mainMenu();
			
			switch(option) {
			case 1:
				createOrder();
				break;
				
			case 2: 
				readOrderId();
				break;
				
			case 3: 
				updateOrder();
				break;
				
			case 4:
				deleteOrder();
				break;
			
			case 5:
				readAllOrders();
				break;
			
			case 6: 
				running = exit(running);
				break;
			
			default:
				System.out.println("Invalid option chosen.");
				break;
			}
		}
		
		
	}
	
	public static int mainMenu() {
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
		try {
	        int option = Integer.parseInt(scanner.nextLine());
	        return option;
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid option. Please enter a number.");
	        return -1;
	    }
	}

//	public static void checkInventory() {
//
//        System.out.println("=== Check & Reduce Stock ===");
//
//        System.out.print("Publication Name: ");
//        String name = scanner.nextLine();
//
//        System.out.print("Amount Needed: ");
//        int amountNeeded = scanner.nextInt();
//        scanner.nextLine();
//
//        if (publicationDAO.validatingInventoary(amountNeeded, name)) {
//            System.out.println("Stock updated. " + amountNeeded
//                    + " copies of " + name + " removed.");
//        } else {
//            System.out.println("Not enough stock, or publication not found.");
//        }
//    }
	
	public static void createOrder() {
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

		
		order = new Order(OrderDate,productName,quantity,custId);
			
		orderDAO = new OrderDAO();
			
		if (orderDAO.addOrder(order)) {
			System.out.println("Order saved successfully.");
		} else {
			System.out.println("Failed to save order.");
		}
		PublicationDAO pDAO = new PublicationDAO();
		if (pDAO.updateStock(productName, quantity)) {
            System.out.println("Order saved successfully.");
        } else {
            System.out.println("Order saved, but stock update failed.");
        }
		
	}
	
	public static void readOrderId() {
		System.out.print("Enter Order Id");
		orderId = scanner.nextInt();
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
	}
	
	public static void updateOrder() {
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
			
			
			if (orderDAO.updateOrder(order)) {
				System.out.println("Order updated successfully.");
			} else {
				System.out.println("Failed to update order.");
			}
		
		
		} else {
			System.out.println("Order not found");
		}
	}
	
	public static void deleteOrder() {
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
	}
	
	public static void readAllOrders() {
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
	}
	
	public static boolean exit(boolean running ) {
		running = false;
		System.out.println("Have a nice day!");
		return running;
	}
}
