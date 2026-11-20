package newsagents;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerCLI {
	static Scanner scanner = new Scanner(System.in);
	static CustomerDAO customerDAO = new CustomerDAO();
	static Customer customer;
	static int customerId;
	
	public CustomerCLI() {

		
		boolean running = true;
		
		while (running) {
			
			int option = mainMenu();

			switch (option) {
			case 1:
				createCustomer();
				break;

			case 2:
				readCustomerId();
				break;

			case 3:
				updateCustomer();
				break;

			case 4:
				deleteCustomer();
				break;

			case 5:
				readAllCustomers();
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
		System.out.println("=== Customer Menu ===");
		System.out.println("1 - Create Customer");
		System.out.println("2 - Read Customer by ID");
		System.out.println("3 - Update Customer");
		System.out.println("4 - Delete Customer");
		System.out.println("5 - Read all Customers");
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
	
	public static void createCustomer() {
		System.out.println("=== Create New Customer ===");

		System.out.print("First Name: ");
		String firstName = scanner.nextLine();
		
		System.out.print("Surname: ");
		String surname = scanner.nextLine();
		
		System.out.print("Address: ");
		String address = scanner.nextLine();
		
		System.out.print("Phone Number: ");
		String phoneNumber = scanner.nextLine();
		
		customer = new Customer(firstName, surname, address, phoneNumber);

		

		if (customerDAO.addCustomer(customer)) {
			System.out.println("Customer saved successfully.");
		} else {
			System.out.println("Failed to save customer.");
		}
	}
	
	public static void readCustomerId() {
		System.out.print("Enter Customer ID: ");
		customerId = scanner.nextInt();
		scanner.nextLine();
		
		customer = customerDAO.findCustomerById(customerId);

		if (customer != null) {
			System.out.println("\nCustomer found:");
			System.out.println("ID: " + customer.getCustomerId());
			System.out.println("First Name: " + customer.getFirstName());
			System.out.println("Surname: " + customer.getSurname());
			System.out.println("Address: " + customer.getAddress());
			System.out.println("Phone Number: " + customer.getPhoneNumber());
		} else {
			System.out.println("Customer not found.");
		}
	}
	
	public static void updateCustomer() {
		System.out.print("Enter Customer ID to update: ");
		customerId = scanner.nextInt();
		scanner.nextLine();
		customer = customerDAO.findCustomerById(customerId);

		if (customer != null) {

			System.out.print("New First Name: ");
			customer.setFirstName(scanner.nextLine());

			System.out.print("New Surname: ");
			customer.setSurname(scanner.nextLine());

			System.out.print("New Address: ");
			customer.setAddress(scanner.nextLine());

			System.out.print("New Phone Number: ");
			customer.setPhoneNumber(scanner.nextLine());
		
			if (customerDAO.updateCustomer(customer)) {
				System.out.println("Customer updated successfully.");
			} else {
				System.out.println("Failed to update customer.");
			}

		} else {
			System.out.println("Customer not found.");
		}
	}
	
	public static void deleteCustomer() {
		System.out.print("Enter Customer ID to delete: ");
		customerId = scanner.nextInt();
		scanner.nextLine();
		customer = customerDAO.findCustomerById(customerId);

		if (customer != null) {
			if (customerDAO.deleteCustomer(customerId)) {
				System.out.println("Customer deleted successfully.");
			} else {
				System.out.println("Failed to delete customer.");
			}
		} else {
			System.out.println("Customer not found.");
		}
	}
	
	public static void readAllCustomers() {
		ArrayList<Customer> customers = customerDAO.getAllCustomers();

		if (customers.isEmpty()) {
			System.out.println("No customers found.");
		} else {
			System.out.println("\n=== All Customers ===");

			for (Customer cust : customers) {
				System.out.println("--------------------");
				System.out.println("ID: " + cust.getCustomerId());
				System.out.println("First Name: " + cust.getFirstName());
				System.out.println("Surname: " + cust.getSurname());
				System.out.println("Address: " + cust.getAddress());
				System.out.println("Phone Number: " + cust.getPhoneNumber());
			}
		}
	}
	
	public static boolean exit(boolean running ) {
		running = false;
		System.out.println("Have a nice day!");
		return running;
	}
	
}
