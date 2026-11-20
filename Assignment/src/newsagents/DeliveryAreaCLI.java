package newsagents;

import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryAreaCLI {
	static Scanner scanner = new Scanner(System.in);
	static DeliveryAreaDAO deliveryAreaDAO = new DeliveryAreaDAO();
	static DeliveryArea deliveryArea;
	static int deliveryAreaId;
	public DeliveryAreaCLI() {

		boolean running = true;
		
		while(running) {
			int option = mainMenu();
			
			switch(option) {
			case 1:
				createDeliveryArea();
				break;
				
			case 2: 
				readDeliveryAreaId();
				break;
				
			case 3: 
				updateDeliveryArea();
				break;
				
			case 4:
				deleteDeliveryArea();
				break;
			
			case 5:
				readAllDeliveryAreas();
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
		System.out.println("=== Delivery Area Menu ===");
		System.out.println("1 - Create Delivery Area");
		System.out.println("2 - Read Delivery Area by ID");
		System.out.println("3 - Update Delivery Area");
		System.out.println("4 - Delete Delivery Area");
		System.out.println("5 - Read all Delivery Areas");
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
	
	public static void createDeliveryArea() {
		System.out.println("=== Create New Delivery Area ===");

		System.out.print("Area Name: ");
		String name = scanner.nextLine();

		System.out.print("Region Name: ");
		String region = scanner.nextLine();
		
		System.out.print("Driver ID: ");
		int driverId = scanner.nextInt();
		scanner.nextLine();
		
		deliveryArea = new DeliveryArea(name,region,driverId);

		deliveryAreaDAO = new DeliveryAreaDAO();

		if (deliveryAreaDAO.addDeliveryArea(deliveryArea)) {
			System.out.println("Delivery Area saved successfully.");
		} else {
			System.out.println("Failed to save Delivery Area.");
		}

	}
	
	public static void readDeliveryAreaId() {
		System.out.print("Enter Delivery Area Id: ");
		deliveryAreaId = scanner.nextInt();
		scanner.nextLine();
		
		deliveryArea = deliveryAreaDAO.findDeliveryAreaById(deliveryAreaId);
		
		if(deliveryArea !=null) {
			System.out.println("\nDelivery Area found: ");
			System.out.println("ID: " + deliveryArea.getDeliveryAreaId());
			System.out.println("Area Name: " + deliveryArea.getName());
			System.out.println("Region Name: " + deliveryArea.getRegion());
			System.out.println("Driver ID:  " + deliveryArea.getDriverId());
		} else {
			System.out.println("Delivery Area not found.");
		}
	}
	
	public static void updateDeliveryArea() {
		System.out.print("Enter Order Id to be updated");
		deliveryAreaId = scanner.nextInt();
		scanner.nextLine();
		
		deliveryArea = deliveryAreaDAO.findDeliveryAreaById(deliveryAreaId);
		
		if(deliveryArea !=null) {
			System.out.print("New Area Name: ");
			deliveryArea.setName(scanner.nextLine());
			
			System.out.print("New Region Name: ");
			deliveryArea.setRegion(scanner.nextLine());
			
			System.out.print("New Driver Id: ");
			deliveryArea.setDriverId(scanner.nextInt());
			scanner.nextLine();
			
			if (deliveryAreaDAO.updateArea(deliveryArea)) {
				System.out.println("Delivery Area updated successfully.");
			} else {
				System.out.println("Failed to update Delivery Area.");
			}
		
		
		} else {
			System.out.println("Delivery Area not found");
		}
	}
	
	public static void deleteDeliveryArea() {
		System.out.print("Enter Order Id to delete");
		deliveryAreaId = scanner.nextInt();
		scanner.nextLine();
		
		deliveryArea = deliveryAreaDAO.findDeliveryAreaById(deliveryAreaId);
		
		if(deliveryArea!=null) {
			if(deliveryAreaDAO.deleteArea(deliveryAreaId)) {
				System.out.println("Delivery Area deleted successfully.");
			} else {
				System.out.println("Failed to delete Delivery Area");
			}
		} else {
			System.out.println("Delivery Area not found");
		}
	}
	
	public static void readAllDeliveryAreas() {
		ArrayList<DeliveryArea> deliveryAreas = deliveryAreaDAO.getAllAreas();
		
		if(deliveryAreas.isEmpty()) {
			System.out.println("No Delivery Areas found");
		} else {
			System.out.println("\n=== All Delivery Areas ===");
			
			for(DeliveryArea area:deliveryAreas) {
				System.out.println("--------------------");
				System.out.println("ID: " + area.getDeliveryAreaId());
				System.out.println("Area Name: " + area.getName());
				System.out.println("Region Name: " + area.getRegion());
				System.out.println("Driver ID: " + area.getDriverId());
			}
		}
	}
	
	public static boolean exit(boolean running ) {
		running = false;
		System.out.println("Have a nice day!");
		return running;
	}
}
