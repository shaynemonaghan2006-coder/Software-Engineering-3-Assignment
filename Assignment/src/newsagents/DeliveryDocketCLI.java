
package newsagents;

import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryDocketCLI {
	static Scanner scanner = new Scanner(System.in);
    static int docketId;
   
    static DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();
    static DeliveryDocket docket;
    
    public DeliveryDocketCLI() {

    	boolean running = true;

        while (running) {
        	int option = mainMenu();
            
            switch (option) {

            case 1:
            	createDeliveryDocket();
            	break;

            case 2:
                readDeliveryDocketId();
                break;

            case 3:
                updateDeliveryDocket();
                break;

            case 4:
                deleteDeliveryDocket();
                break;

            case 5:
                readAllDockets();
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
        System.out.println("=== Delivery Docket Menu ===");
        System.out.println("1 - Create Delivery Docket");
        System.out.println("2 - Read Delivery Docket by ID");
        System.out.println("3 - Update Delivery Docket");
        System.out.println("4 - Delete Delivery Docket");
        System.out.println("5 - Read all Delivery Dockets");
        System.out.println("6 - Exit");
        System.out.println();
        System.out.print("Option: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }
    
    public static void createDeliveryDocket() {
    	 System.out.println("=== Create New Delivery Docket ===");
         
         System.out.print("Order id: ");
         int orderId = scanner.nextInt();
         scanner.nextLine();

         System.out.print("Delivery Date (dd/mm/yyyy): ");
         String deliveryDate = scanner.nextLine();

         System.out.print("Delivery Status: ");
         String deliveryStatus = scanner.nextLine();

         docket = new DeliveryDocket(
         		orderId,
                 deliveryDate,
                 deliveryStatus);
         
         deliveryDocketDAO = new DeliveryDocketDAO();

         if (deliveryDocketDAO.addDeliveryDocket(docket)) {
             System.out.println("Delivery docket saved successfully.");
         } else {
             System.out.println("Failed to save delivery docket.");
         }
    }
    
    public static void readDeliveryDocketId() {
    	System.out.print("Enter Docket ID: ");
        int docketId = Integer.parseInt(scanner.nextLine());

        docket = deliveryDocketDAO.findDeliveryDocketById(docketId);

        if (docket != null) {
            System.out.println("\nDelivery Docket found:");
            System.out.println("Docket ID: " + docketId);
            System.out.println("Delivery Date: " + docket.getDeliveryDate());
            System.out.println("Delivery Status: " + docket.getDeliveryStatus());
            System.out.println("Order Id: " + docket.getOrderId() );
        } else {
            System.out.println("Delivery docket not found.");
        }
    }
    
    public static void updateDeliveryDocket() {
    	System.out.print("Enter Docket ID to update: ");
        docketId = Integer.parseInt(scanner.nextLine());

        docket = deliveryDocketDAO.findDeliveryDocketById(docketId);

        if (docket != null) {
        	
        	System.out.print("New Order Id: ");
        	docket.setOrderId(scanner.nextInt());
        	scanner.nextLine();
        	
            System.out.print("New Delivery Date (DD/MM/YYYY): ");
            docket.setDeliveryDate(scanner.nextLine());

            System.out.print("New Delivery Status: ");
            docket.setDeliveryStatus(scanner.nextLine());

            if (deliveryDocketDAO.updateDeliveryDocket(docket, docketId)) {
                System.out.println("Delivery docket updated successfully.");
            } else {
                System.out.println("Failed to update delivery docket.");
            }

        } else {
            System.out.println("Delivery docket not found.");
        }
    }
    
    public static void deleteDeliveryDocket() {
    	System.out.print("Enter Docket ID to delete: ");
        docketId = Integer.parseInt(scanner.nextLine());

        docket = deliveryDocketDAO.findDeliveryDocketById(docketId);

        if (docket != null) {

            if (deliveryDocketDAO.deleteDeliveryDocket(docketId)) {
                System.out.println("Delivery docket deleted successfully.");
            } else {
                System.out.println("Failed to delete delivery docket.");
            }

        } else {
            System.out.println("Delivery docket not found.");
        }
    }
    
    public static void readAllDockets() {
    	ArrayList<DeliveryDocket> dockets =
                deliveryDocketDAO.getAllDeliveryDockets();

        if (dockets.isEmpty()) {
            System.out.println("No delivery dockets found.");
        } else {
            System.out.println("\n=== All Delivery Dockets ===");

            for (DeliveryDocket currentDocket : dockets) {
                System.out.println("--------------------");
                System.out.println("Delivery Date: " + currentDocket.getDeliveryDate());
                System.out.println("Delivery Status: " + currentDocket.getDeliveryStatus());
                System.out.println("Order Id: " + currentDocket.getOrderId());
            }
        }
    }
    
    public static boolean exit(boolean running ) {
		running = false;
		System.out.println("Have a nice day!");
		return running;
	}
}
