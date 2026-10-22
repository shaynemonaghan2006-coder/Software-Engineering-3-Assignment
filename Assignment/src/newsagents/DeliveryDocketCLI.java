package newagents;

import java.util.Scanner;

public class DeliveryDocketCLI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Create New Delivery Docket ===");

        System.out.print("Order ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());

        System.out.print("Delivery Date (YYYY-MM-DD): ");
        String deliveryDate = scanner.nextLine();

        System.out.print("Delivery Status: ");
        String deliveryStatus = scanner.nextLine();

        DeliveryDocket docket =
                new DeliveryDocket(
                        orderId,
                        deliveryDate,
                        deliveryStatus);

        DeliveryDocketDAO deliveryDocketDAO = new DeliveryDocketDAO();

        if (deliveryDocketDAO.addDeliveryDocket(docket)) {
            System.out.println(
                    "Delivery docket saved successfully.");
        } else {
            System.out.println(
                    "Failed to save delivery docket.");
        }

        scanner.close();
    }
}