package newsagents;

import java.util.ArrayList;
import java.util.Scanner;

public class BillCLI {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		BillDAO billDAO = new BillDAO();
		Bill bill;

		while (running) {
			System.out.println();
			System.out.println("=== Bill Menu ===");
			System.out.println("1 - Create Bill");
			System.out.println("2 - Read Bill by Receipt ID");
			System.out.println("3 - Update Bill");
			System.out.println("4 - Delete Bill");
			System.out.println("5 - Read all Bills");
			System.out.println("6 - Exit");
			System.out.println();
			System.out.print("Option: ");

			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				System.out.println("=== Create New Bill ===");

				System.out.print("Receipt ID: ");
				int receiptId = scanner.nextInt();
				scanner.nextLine();

				System.out.print("Order Reference: ");
				String orderReference = scanner.nextLine();

				System.out.print("Driver ID: ");
				int driverId = scanner.nextInt();
				scanner.nextLine();

				System.out.print("Payment Date (YYYY-MM-DD): ");
				String paymentDate = scanner.nextLine();

				bill = new Bill(
						orderReference,
						driverId,
						paymentDate);

				if (billDAO.addBill(bill)) {
					System.out.println("Bill saved successfully.");
				} else {
					System.out.println("Failed to save bill.");
				}
				break;

			case 2:
				System.out.print("Enter Receipt ID: ");
				receiptId = scanner.nextInt();
				scanner.nextLine();

				bill = billDAO.findBillById(receiptId);

				if (bill != null) {
					System.out.println("\nBill found:");
					System.out.println("Receipt ID: " + bill.getReceiptId());
					System.out.println("Order Reference: " + bill.getOrderReference());
					System.out.println("Driver ID: " + bill.getDriverId());
					System.out.println("Payment Date: " + bill.getPaymentDate());
				} else {
					System.out.println("Bill not found.");
				}
				break;

			case 3:
				System.out.print("Enter Receipt ID to update: ");
				receiptId = scanner.nextInt();
				scanner.nextLine();

				bill = billDAO.findBillById(receiptId);

				if (bill != null) {
					System.out.print("New Order Reference: ");
					bill.setOrderReference(scanner.nextLine());

					System.out.print("New Driver ID: ");
					bill.setDriverId(scanner.nextInt());
					scanner.nextLine();

					System.out.print("New Payment Date (YYYY-MM-DD): ");
					bill.setPaymentDate(scanner.nextLine());

					if (billDAO.updateBill(bill)) {
						System.out.println("Bill updated successfully.");
					} else {
						System.out.println("Failed to update bill.");
					}
				} else {
					System.out.println("Bill not found.");
				}
				break;

			case 4:
				System.out.print("Enter Receipt ID to delete: ");
				receiptId = scanner.nextInt();
				scanner.nextLine();

				bill = billDAO.findBillById(receiptId);

				if (bill != null) {
					if (billDAO.deleteBill(receiptId)) {
						System.out.println("Bill deleted successfully.");
					} else {
						System.out.println("Failed to delete bill.");
					}
				} else {
					System.out.println("Bill not found.");
				}
				break;

			case 5:
				ArrayList<Bill> bills = billDAO.getAllBills();

				if (bills.isEmpty()) {
					System.out.println("No bills found.");
				} else {
					System.out.println("\n=== All Bills ===");

					for (Bill currentBill : bills) {
						System.out.println("--------------------");
						System.out.println("Receipt ID: " + currentBill.getReceiptId());
						System.out.println("Order Reference: " + currentBill.getOrderReference());
						System.out.println("Driver ID: " + currentBill.getDriverId());
						System.out.println("Payment Date: " + currentBill.getPaymentDate());
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
