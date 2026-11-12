
package newsagents;

import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryDocketCLI {

    public DeliveryDocketCLI() {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();
        DeliveryDocket docket;

        while (running) {

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
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {

            case 1:
                System.out.println("=== Create New Delivery Docket ===");

                System.out.print("Delivery Date (dd-mm-yyyy): ");
                String deliveryDate = scanner.nextLine();

                System.out.print("Delivery Status: ");
                String deliveryStatus = scanner.nextLine();

                docket = new DeliveryDocket(
                        deliveryDate,
                        deliveryStatus);

                if (deliveryDocketDAO.addDeliveryDocket(docket)) {
                    System.out.println("Delivery docket saved successfully.");
                } else {
                    System.out.println("Failed to save delivery docket.");
                }
                break;

            case 2:
                System.out.print("Enter Docket ID: ");
                int docketId = Integer.parseInt(scanner.nextLine());

                docket = deliveryDocketDAO.findDeliveryDocketById(docketId);

                if (docket != null) {
                    System.out.println("\nDelivery Docket found:");
                    System.out.println("Docket ID: " + docketId);
                    System.out.println("Delivery Date: " + docket.getDeliveryDate());
                    System.out.println("Delivery Status: " + docket.getDeliveryStatus());
                } else {
                    System.out.println("Delivery docket not found.");
                }
                break;

            case 3:
                System.out.print("Enter Docket ID to update: ");
                docketId = Integer.parseInt(scanner.nextLine());

                docket = deliveryDocketDAO.findDeliveryDocketById(docketId);

                if (docket != null) {

                    System.out.print("New Delivery Date (DD-MM-YYYY): ");
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
                break;

            case 4:
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
                break;

            case 5:
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
    }
}
