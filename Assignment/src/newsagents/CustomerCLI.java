package newsagents;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerCLI {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer;
		while (running) {
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
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
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
				break;

			case 2:
				System.out.print("Enter Customer ID: ");
				int customerId = scanner.nextInt();
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
				break;

			case 3:
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
				break;

			case 4:
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
				break;

			case 5:
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
