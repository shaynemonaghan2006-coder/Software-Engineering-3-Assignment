package newsagents;

import java.util.ArrayList;
import java.util.Scanner;

public class PublicationCLI {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PublicationDAO dao = new PublicationDAO();

    public PublicationCLI() {

        int choice;

        do {
            System.out.println("\n===== PUBLICATION MENU =====");
            System.out.println("1. Add Publication");
            System.out.println("2. Find Publication by ID");
            System.out.println("3. View All Publications");
            System.out.println("4. Update Publication");
            System.out.println("5. Delete Publication");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    addPublication();
                    break;
                case 2:
                    findPublication();
                    break;
                case 3:
                    viewAllPublications();
                    break;
                case 4:
                    updatePublication();
                    break;
                case 5:
                    deletePublication();
                    break;
                case 6:
                    System.out.println("Exiting Publication Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);
    }

    //Adds a Publication
    private static void addPublication() {

        System.out.println("\n--- Add Publication ---");

        System.out.print("Enter publication name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = readDouble();

        System.out.print("Enter frequency (e.g. Daily, Weekly): ");
        String frequency = scanner.nextLine();

        System.out.print("Enter type (e.g. Newspaper, Magazine): ");
        String type = scanner.nextLine();

        System.out.print("Enter stock quantity: ");
        int stock = readInt();

        //insert inStock funxtion in this if statement

        if(dao.isValidPublicationName(name)){
                    Publication publication =
                new Publication(0, name, price, frequency, type, stock);

            if (dao.addPublication(publication)) {
                System.out.println("Publication added successfully.");
            } 
            else 
            {
            System.out.println("Could not add publication. Check the details.");
            }
        }
    }

    //Finds a Publication by ID
    private static void findPublication() {

        System.out.print("Enter Publication ID: ");
        int id = readInt();

        Publication publication = dao.findPublicationById(id);

        if (publication != null) {
            System.out.println(publication);
        } else {
            System.out.println("Publication not found.");
        }
    }

    //Gets all Publications
    private static void viewAllPublications() {

        ArrayList<Publication> publications = dao.getAllPublications();

        if (publications.isEmpty()) {
            System.out.println("No publications found.");
            return;
        }

        for (Publication publication : publications) {
            System.out.println("--------------------");
            System.out.println(publication);
        }
    }

    //Updates a Publication
    private static void updatePublication() {

        System.out.print("Enter Publication ID to update: ");
        int id = readInt();

        Publication existing = dao.findPublicationById(id);

        if (existing == null) {
            System.out.println("Publication not found.");
            return;
        }

        System.out.println("Current details:");
        System.out.println(existing);

        System.out.print("Enter new publication name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new price: ");
        double price = readDouble();

        System.out.print("Enter new frequency: ");
        String frequency = scanner.nextLine();

        System.out.print("Enter new type: ");
        String type = scanner.nextLine();

        System.out.print("Enter new stock quantity: ");
        int stock = readInt();

        Publication updated =
                new Publication(id, name, price, frequency, type, stock);

        if (dao.updatePublication(updated)) {
            System.out.println("Publication updated successfully.");
        } else {
            System.out.println("Could not update publication.");
        }
    }

    //Deletes a Publication
    private static void deletePublication() {

        System.out.print("Enter Publication ID to delete: ");
        int id = readInt();

        Publication existing = dao.findPublicationById(id);

        if (existing == null) {
            System.out.println("Publication not found.");
            return;
        }

        System.out.println(existing);
        System.out.print("Confirm deletion? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            if (dao.deletePublication(id)) {
                System.out.println("Publication deleted successfully.");
            } else {
                System.out.println("Could not delete publication.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    //Reads an integer
    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid whole number: ");
            }
        }
    }

    //Reads a double
    private static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid price: ");
            }
        }
    }
}
