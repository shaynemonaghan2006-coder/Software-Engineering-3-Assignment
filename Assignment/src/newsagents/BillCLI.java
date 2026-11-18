
package newsagents;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class BillCLI {

    private Scanner scanner = new Scanner(System.in);
    private BillDAO billDAO = new BillDAO();

    // Check bill ID
    private boolean validBillId(int id) {
        return id >= 1;
    }

    // Check payment status
    private boolean validStatus(String status) {
        return status.equals("paid") ||
               status.equals("pending");
    }

    // Check payment date
    private boolean validDate(String date) {
        try {
            DateTimeFormatter format =
                DateTimeFormatter.ofPattern("dd-MM-uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

            LocalDate.parse(date, format);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // Check total amount
    private boolean validAmount(double amount) {
        return Double.isFinite(amount) &&
               amount >= 0 &&
               amount <= 99.99;
    }

    public BillCLI() {

        int option = 0;

        while (option != 6) {

            System.out.println("\n=== Bill Menu ===");
            System.out.println("1. Add Bill");
            System.out.println("2. Find Bill");
            System.out.println("3. Update Bill");
            System.out.println("4. Delete Bill");
            System.out.println("5. Display All Bills");
            System.out.println("6. Exit");
            System.out.println("Enter your choice:");

            try {

                option = Integer.parseInt(scanner.nextLine());

                switch (option) {

                    // ADD BILL
                    case 1:

                        System.out.println("Enter Payment Status:");
                        String status = scanner.nextLine();

                        if (!validStatus(status)) {
                            System.out.println("Invalid Payment Status.");
                            break;
                        }

                        System.out.println("Enter Payment Date (DD-MM-YYYY):");
                        String date = scanner.nextLine();

                        if (!validDate(date)) {
                            System.out.println("Invalid Payment Date.");
                            break;
                        }

                        System.out.println("Enter Total Amount:");
                        double amount = Double.parseDouble(scanner.nextLine());

                        if (!validAmount(amount)) {
                            System.out.println("Invalid Total Amount.");
                            break;
                        }

                        Bill newBill = new Bill(status, date, amount);

                        if (billDAO.addBill(newBill)) {
                            System.out.println("Bill added successfully.");
                        } else {
                            System.out.println("Bill could not be added.");
                        }

                        break;

                    // FIND BILL
                    case 2:

                        System.out.println("Enter Bill ID:");
                        int searchId = Integer.parseInt(scanner.nextLine());

                        if (!validBillId(searchId)) {
                            System.out.println("Invalid Bill ID.");
                            break;
                        }

                        Bill found = billDAO.findBillById(searchId);

                        if (found != null) {
                            displayBill(found);
                        } else {
                            System.out.println("Bill not found.");
                        }

                        break;

                    // UPDATE BILL
                    case 3:

                        System.out.println("Enter Bill ID to update:");
                        int updateId = Integer.parseInt(scanner.nextLine());

                        if (!validBillId(updateId)) {
                            System.out.println("Invalid Bill ID.");
                            break;
                        }

                        Bill bill = billDAO.findBillById(updateId);

                        if (bill == null) {
                            System.out.println("Bill not found.");
                            break;
                        }

                        System.out.println("Enter Payment Status:");
                        String newStatus = scanner.nextLine();

                        if (!validStatus(newStatus)) {
                            System.out.println("Invalid Payment Status.");
                            break;
                        }

                        System.out.println("Enter Payment Date (DD-MM-YYYY):");
                        String newDate = scanner.nextLine();

                        if (!validDate(newDate)) {
                            System.out.println("Invalid Payment Date.");
                            break;
                        }

                        System.out.println("Enter Total Amount:");
                        double newAmount = Double.parseDouble(scanner.nextLine());

                        if (!validAmount(newAmount)) {
                            System.out.println("Invalid Total Amount.");
                            break;
                        }

                        bill.setPaymentStatus(newStatus);
                        bill.setPaymentDate(newDate);
                        bill.setTotalAmount(newAmount);

                        if (billDAO.updateBill(bill)) {
                            System.out.println("Bill updated successfully.");
                        } else {
                            System.out.println("Bill could not be updated.");
                        }

                        break;

                    // DELETE BILL
                    case 4:

                        System.out.println("Enter Bill ID to delete:");
                        int deleteId = Integer.parseInt(scanner.nextLine());

                        if (!validBillId(deleteId)) {
                            System.out.println("Invalid Bill ID.");
                            break;
                        }

                        if (billDAO.deleteBill(deleteId)) {
                            System.out.println("Bill deleted successfully.");
                        } else {
                            System.out.println("Bill not found.");
                        }

                        break;

                    // DISPLAY ALL BILLS
                    case 5:

                        ArrayList<Bill> bills = billDAO.getAllBills();

                        if (bills.isEmpty()) {
                            System.out.println("No bills found.");
                        } else {
                            for (Bill currentBill : bills) {
                                displayBill(currentBill);
                            }
                        }

                        break;

                    // EXIT
                    case 6:

                        System.out.println("Have a nice day!");
                        break;

                    default:

                        System.out.println("Invalid option.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Display bill
    public void displayBill(Bill bill) {

        System.out.println("\nBill ID: " + bill.getBillId());
        System.out.println("Payment Status: " + bill.getPaymentStatus());
        System.out.println("Payment Date: " + bill.getPaymentDate());
        System.out.println("Total Amount: " + bill.getTotalAmount());
    }
}
