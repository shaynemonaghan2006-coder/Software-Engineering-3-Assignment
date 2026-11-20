
package newsagents;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class BillCLI {

    static Scanner scanner = new Scanner(System.in);
    static BillDAO billDAO = new BillDAO();
    static Bill bill;
    static int billId;

    public BillCLI() {

        boolean running = true;

        while (running) {

            int option = mainMenu();

            switch (option) {

            case 1:
                addBill();
                break;

            case 2:
                findBill();
                break;

            case 3:
                updateBill();
                break;

            case 4:
                deleteBill();
                break;

            case 5:
                displayAllBills();
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
        System.out.println("=== Bill Menu ===");
        System.out.println("1 - Add Bill");
        System.out.println("2 - Find Bill");
        System.out.println("3 - Update Bill");
        System.out.println("4 - Delete Bill");
        System.out.println("5 - Display All Bills");
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

    // Check bill ID
    public static boolean validBillId(int id) {
        return id >= 1;
    }

    // Check payment status
    public static boolean validStatus(String status) {
        return status.equals("paid") ||
               status.equals("pending");
    }

    // Check payment date
    public static boolean validDate(String date) {

        try {
            DateTimeFormatter format =
                DateTimeFormatter.ofPattern("dd/MM/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

            LocalDate.parse(date, format);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // Check total amount
    public static boolean validAmount(double amount) {
        return Double.isFinite(amount) &&
               amount >= 0 &&
               amount <= 99.99;
    }

    // ADD BILL
    public static void addBill() {

        System.out.println("=== Add New Bill ===");

        System.out.println("Enter Payment Status:");
        String status = scanner.nextLine();

        if (!validStatus(status)) {
            System.out.println("Invalid Payment Status.");
            return;
        }

        System.out.println("Enter Payment Date (DD/MM/YYYY):");
        String date = scanner.nextLine();

        if (!validDate(date)) {
            System.out.println("Invalid Payment Date.");
            return;
        }

        System.out.println("Enter Total Amount:");

        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("Invalid Total Amount.");
            return;
        }

        if (!validAmount(amount)) {
            System.out.println("Invalid Total Amount.");
            return;
        }

        bill = new Bill(status, date, amount);

        if (billDAO.addBill(bill)) {
            System.out.println("Bill added successfully.");
        } else {
            System.out.println("Bill could not be added.");
        }
    }

    // FIND BILL
    public static void findBill() {

        System.out.print("Enter Bill ID: ");

        try {
            billId = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("Invalid Bill ID.");
            return;
        }

        if (!validBillId(billId)) {
            System.out.println("Invalid Bill ID.");
            return;
        }

        bill = billDAO.findBillById(billId);

        if (bill != null) {
            displayBill(bill);
        } else {
            System.out.println("Bill not found.");
        }
    }

    // UPDATE BILL
    public static void updateBill() {

        System.out.print("Enter Bill ID to update: ");

        try {
            billId = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("Invalid Bill ID.");
            return;
        }

        if (!validBillId(billId)) {
            System.out.println("Invalid Bill ID.");
            return;
        }

        bill = billDAO.findBillById(billId);

        if (bill == null) {
            System.out.println("Bill not found.");
            return;
        }

        System.out.println("Enter Payment Status:");
        String newStatus = scanner.nextLine();

        if (!validStatus(newStatus)) {
            System.out.println("Invalid Payment Status.");
            return;
        }

        System.out.println("Enter Payment Date (DD/MM/YYYY):");
        String newDate = scanner.nextLine();

        if (!validDate(newDate)) {
            System.out.println("Invalid Payment Date.");
            return;
        }

        System.out.println("Enter Total Amount:");

        double newAmount;

        try {
            newAmount = Double.parseDouble(scanner.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("Invalid Total Amount.");
            return;
        }

        if (!validAmount(newAmount)) {
            System.out.println("Invalid Total Amount.");
            return;
        }

        bill.setPaymentStatus(newStatus);
        bill.setPaymentDate(newDate);
        bill.setTotalAmount(newAmount);

        if (billDAO.updateBill(bill)) {
            System.out.println("Bill updated successfully.");
        } else {
            System.out.println("Bill could not be updated.");
        }
    }

    // DELETE BILL
    public static void deleteBill() {

        System.out.print("Enter Bill ID to delete: ");

        try {
            billId = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("Invalid Bill ID.");
            return;
        }

        if (!validBillId(billId)) {
            System.out.println("Invalid Bill ID.");
            return;
        }

        if (billDAO.deleteBill(billId)) {
            System.out.println("Bill deleted successfully.");
        } else {
            System.out.println("Bill not found.");
        }
    }

    // DISPLAY ALL BILLS
    public static void displayAllBills() {

        ArrayList<Bill> bills = billDAO.getAllBills();

        if (bills.isEmpty()) {
            System.out.println("No bills found.");

        } else {
            System.out.println("\n=== All Bills ===");

            for (Bill currentBill : bills) {
                displayBill(currentBill);
            }
        }
    }

    // DISPLAY BILL
    public static void displayBill(Bill bill) {

        System.out.println("\nBill ID: " + bill.getBillId());
        System.out.println("Payment Status: " + bill.getPaymentStatus());
        System.out.println("Payment Date: " + bill.getPaymentDate());
        System.out.println("Total Amount: " + bill.getTotalAmount());
    }

    // EXIT
    public static boolean exit(boolean running) {

        running = false;
        System.out.println("Have a nice day!");

        return running;
    }
}
