package newsagents;

import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryPersonCLI {
	static Scanner scanner = new Scanner(System.in);
	static int deliveryPersonId;
	static DeliveryPersonDAO deliveryPersonDAO = new DeliveryPersonDAO();
	static DeliveryPerson deliveryPerson;
	public DeliveryPersonCLI() {

		
		boolean running = true;
		
		while (running) {
			int option = mainMenu();

			switch (option) {
			case 1:
				createDeliveryPerson();
				break;

			case 2:
				readDeliveryPersonId();
				break;

			case 3:
				updateDeliveryPerson();
				break;

			case 4:
				deleteDeliveryPerson();
				break;

			case 5:
				readAllDeliveryPersons();
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
		return option;
	}
	
	public static void createDeliveryPerson() {
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
	}
	
	public static void readDeliveryPersonId() {
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
	}
	
	public static void updateDeliveryPerson() {
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
	}
	
	public static void deleteDeliveryPerson() {
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
	}
	
	public static void readAllDeliveryPersons() {
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
	}
	
	public static boolean exit(boolean running ) {
		running = false;
		System.out.println("Have a nice day!");
		return running;
	}
}