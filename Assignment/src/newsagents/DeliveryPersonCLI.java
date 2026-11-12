package newsagents;

import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryPersonCLI {

	public DeliveryPersonCLI() {

		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		DeliveryPersonDAO deliveryPersonDAO = new DeliveryPersonDAO();
		DeliveryPerson deliveryPerson;
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
				
				deliveryPerson = new DeliveryPerson(firstName, surname, address, phoneNumber);

				

				if (deliveryPersonDAO.addDeliveryPerson(deliveryPerson)) {
					System.out.println("Delivery Person saved successfully.");
				} else {
					System.out.println("Failed to save DeliveryPerson.");
				}
				break;

			case 2:
				System.out.print("Enter DeliveryPerson ID: ");
				int deliveryPersonId = scanner.nextInt();
				scanner.nextLine();
				
				deliveryPerson = deliveryPersonDAO.findDeliveryPersonById(deliveryPersonId);

				if (deliveryPerson != null) {
					System.out.println("\nDeliveryPerson found:");
					System.out.println("ID: " + deliveryPerson.getDeliveryPersonId());
					System.out.println("First Name: " + deliveryPerson.getFirstName());
					System.out.println("Surname: " + deliveryPerson.getSurname());
					System.out.println("Address: " + deliveryPerson.getAddress());
					System.out.println("Phone Number: " + deliveryPerson.getPhoneNumber());
				} else {
					System.out.println("DeliveryPerson not found.");
				}
				break;

			case 3:
				System.out.print("Enter DeliveryPerson ID to update: ");
				deliveryPersonId = scanner.nextInt();
				scanner.nextLine();
				deliveryPerson = deliveryPersonDAO.findDeliveryPersonById(deliveryPersonId);

				if (deliveryPerson != null) {

					System.out.print("New First Name: ");
					deliveryPerson.setFirstName(scanner.nextLine());

					System.out.print("New Surname: ");
					deliveryPerson.setSurname(scanner.nextLine());

					System.out.print("New Address: ");
					deliveryPerson.setAddress(scanner.nextLine());

					System.out.print("New Phone Number: ");
					deliveryPerson.setPhoneNumber(scanner.nextLine());
				
					if (deliveryPersonDAO.updateDeliveryPerson(deliveryPerson)) {
						System.out.println("Delivery Person updated successfully.");
					} else {
						System.out.println("Failed to update Delivery Person.");
					}

				} else {
					System.out.println("Delivery Person not found.");
				}
				break;

			case 4:
				System.out.print("Enter Delivery Person ID to delete: ");
				deliveryPersonId = scanner.nextInt();
				scanner.nextLine();
				deliveryPerson = deliveryPersonDAO.findDeliveryPersonById(deliveryPersonId);

				if (deliveryPerson != null) {
					if (deliveryPersonDAO.deleteDeliveryPerson(deliveryPersonId)) {
						System.out.println("DeliveryPerson deleted successfully.");
					} else {
						System.out.println("Failed to delete DeliveryPerson.");
					}
				} else {
					System.out.println("DeliveryPerson not found.");
				}
				break;

			case 5:
				ArrayList<DeliveryPerson> deliveryPersons = deliveryPersonDAO.getAllDeliveryPerson();

				if (deliveryPersons.isEmpty()) {
					System.out.println("No DeliveryPerson found.");
				} else {
					System.out.println("\n=== All Customers ===");

					for (DeliveryPerson dp : deliveryPersons) {
						System.out.println("--------------------");
						System.out.println("ID: " + dp.getDeliveryPersonId());
						System.out.println("First Name: " + dp.getFirstName());
						System.out.println("Surname: " + dp.getSurname());
						System.out.println("Address: " + dp.getAddress());
						System.out.println("Phone Number: " + dp.getPhoneNumber());
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