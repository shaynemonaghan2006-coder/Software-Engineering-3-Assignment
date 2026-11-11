

import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryPersonCLI {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		DeliveryPersonDAO customerDAO = new DeliveryPersonDAO();
		DeliveryPerson customer;
		while (running) {
			System.out.println();
			System.out.println("=== DeliveryPerson Menu ===");
			System.out.println("1 - Create DeliveryPerson");
			System.out.println("2 - Read DeliveryPerson by ID");
			System.out.println("3 - Update DeliveryPerson");
			System.out.println("4 - Delete DeliveryPerson");
			System.out.println("5 - Read all DeliveryPerson");
			System.out.println("6 - Exit");
			System.out.println();
			System.out.print("Option: ");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				System.out.println("=== Create New DeliveryPerson ===");

				System.out.print("First Name: ");
				String firstName = scanner.nextLine();
				
				System.out.print("Surname: ");
				String surname = scanner.nextLine();
				
				System.out.print("Address: ");
				String address = scanner.nextLine();
				
				System.out.print("Phone Number: ");
				String phoneNumber = scanner.nextLine();
				
				customer = new DeliveryPerson(firstName, surname, address, phoneNumber);

				

				if (customerDAO.addCustomer(customer)) {
					System.out.println("DeliveryPerson saved successfully.");
				} else {
					System.out.println("Failed to save DeliveryPerson.");
				}
				break;

			case 2:
				System.out.print("Enter DeliveryPerson ID: ");
				int customerId = scanner.nextInt();
				scanner.nextLine();
				
				customer = customerDAO.findCustomerById(customerId);

				if (customer != null) {
					System.out.println("\nDeliveryPerson found:");
					System.out.println("ID: " + customer.getCustomerId());
					System.out.println("First Name: " + customer.getFirstName());
					System.out.println("Surname: " + customer.getSurname());
					System.out.println("Address: " + customer.getAddress());
					System.out.println("Phone Number: " + customer.getPhoneNumber());
				} else {
					System.out.println("DeliveryPerson not found.");
				}
				break;

			case 3:
				System.out.print("Enter DeliveryPerson ID to update: ");
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
						System.out.println("DeliveryPerson updated successfully.");
					} else {
						System.out.println("Failed to update DeliveryPerson.");
					}

				} else {
					System.out.println("DeliveryPerson not found.");
				}
				break;

			case 4:
				System.out.print("Enter DeliveryPerson ID to delete: ");
				customerId = scanner.nextInt();
				scanner.nextLine();
				customer = customerDAO.findCustomerById(customerId);

				if (customer != null) {
					if (customerDAO.deleteCustomer(customerId)) {
						System.out.println("DeliveryPerson deleted successfully.");
					} else {
						System.out.println("Failed to delete DeliveryPerson.");
					}
				} else {
					System.out.println("DeliveryPerson not found.");
				}
				break;

			case 5:
				ArrayList<DeliveryPerson> customers = customerDAO.getAllCustomers();

				if (customers.isEmpty()) {
					System.out.println("No DeliveryPerson found.");
				} else {
					System.out.println("\n=== All Customers ===");

					for (DeliveryPerson cust : customers) {
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