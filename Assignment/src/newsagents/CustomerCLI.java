package newsagents;

import java.util.Scanner;

public class CustomerCLI {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("=== Create New Customer ===");

		System.out.print("First Name: ");
		String firstName = scanner.nextLine();

		System.out.print("Surname: ");
		String surname = scanner.nextLine();

		System.out.print("Address: ");
		String address = scanner.nextLine();

		System.out.print("Phone Number: ");
		String phoneNumber = scanner.nextLine();

		Customer customer = new Customer(firstName, surname, address, phoneNumber);

		CustomerDAO customerDAO = new CustomerDAO();

		if (customerDAO.addCustomer(customer)) {
			System.out.println("Customer saved successfully.");
		} else {
			System.out.println("Failed to save customer.");
		}

		scanner.close();
	}
}
