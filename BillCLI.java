package newagents;

import java.util.Scanner;

public class BillCLI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Create New Bill ===");

        System.out.print("Receipt ID: ");
        int receiptId = Integer.parseInt(scanner.nextLine());

        System.out.print("Order Reference: ");
        String orderReference = scanner.nextLine();

        System.out.print("Driver ID: ");
        int driverId = Integer.parseInt(scanner.nextLine());

        System.out.print("Payment Date (YYYY-MM-DD): ");
        String paymentDate = scanner.nextLine();

        Bill bill =
                new Bill(
                        receiptId,
                        orderReference,
                        driverId,
                        paymentDate);

        BillDAO billDAO = new BillDAO();

        if (billDAO.addBill(bill)) {
            System.out.println(
                    "Bill saved successfully.");
        } else {
            System.out.println(
                    "Failed to save bill.");
        }

        scanner.close();
    }
}