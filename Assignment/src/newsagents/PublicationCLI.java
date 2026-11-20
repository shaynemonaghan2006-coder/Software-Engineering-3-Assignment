
package newsagents;

import java.util.ArrayList;
import java.util.Scanner;

public class PublicationCLI {

    static Scanner scanner = new Scanner(System.in);
    static PublicationDAO publicationDAO = new PublicationDAO();
    static Publication publication;
    static int publicationId;

    public PublicationCLI() {

        boolean running = true;

        while (running) {
            int option = mainMenu();

            switch (option) {
            case 1:
                addPublication();
                break;

            case 2:
                findPublication();
                break;

            case 3:
                updatePublication();
                break;

            case 4:
                deletePublication();
                break;

            case 5:
                viewAllPublications();
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
        System.out.println("=== Publication Menu ===");
        System.out.println("1 - Add Publication");
        System.out.println("2 - Find Publication by ID");
        System.out.println("3 - Update Publication");
        System.out.println("4 - Delete Publication");
        System.out.println("5 - View All Publications");
        System.out.println("6 - Exit");
        System.out.println();

        System.out.print("Option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        return option;
    }

    // Add Publication
    public static void addPublication() {

        System.out.println("=== Add New Publication ===");

        System.out.print("Publication Name: ");
        String name = scanner.nextLine();

        System.out.print("Price: ");
        String price = scanner.nextLine();
        

        System.out.print("Frequency: ");
        String frequency = scanner.nextLine();

        System.out.print("Type: ");
        String type = scanner.nextLine();

        System.out.print("Stock Quantity: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        publication = new Publication(0, name, price, frequency, type, stock);
        
        if (publicationDAO.addPublication(publication)) {
            System.out.println("Publication added successfully.");
        } else {
            System.out.println("Failed to add publication.");
        }
    }

    // Find Publication by ID
    public static void findPublication() {

        System.out.print("Enter Publication ID: ");
        publicationId = scanner.nextInt();
        scanner.nextLine();

        publication = publicationDAO.findPublicationById(publicationId);

        if (publication != null) {
            System.out.println("\nPublication found:");
            System.out.println("ID: " + publication.getPublicationId());
            System.out.println("Name: " + publication.getPublicationName());
            System.out.println("Price: " + publication.getPrice());
            System.out.println("Frequency: " + publication.getFrequency());
            System.out.println("Type: " + publication.getType());
            System.out.println("Stock Quantity: " + publication.getStockQuantity());
        } else {
            System.out.println("Publication not found.");
        }
    }

    // Update Publication
    public static void updatePublication() {

        System.out.print("Enter Publication ID to update: ");
        publicationId = scanner.nextInt();
        scanner.nextLine();

        publication = publicationDAO.findPublicationById(publicationId);

        if (publication != null) {

            System.out.print("New Publication Name: ");
            publication.setPublicationName(scanner.nextLine());

            System.out.print("New Price: ");
            publication.setPrice(scanner.nextLine());
           

            System.out.print("New Frequency: ");
            publication.setFrequency(scanner.nextLine());

            System.out.print("New Type: ");
            publication.setType(scanner.nextLine());

            System.out.print("New Stock Quantity: ");
            publication.setStockQuantity(scanner.nextInt());
            scanner.nextLine();

            if (publicationDAO.updatePublication(publication)) {
                System.out.println("Publication updated successfully.");
            } else {
                System.out.println("Failed to update publication.");
            }

        } else {
            System.out.println("Publication not found.");
        }
    }

    // Delete Publication
    public static void deletePublication() {

        System.out.print("Enter Publication ID to delete: ");
        publicationId = scanner.nextInt();
        scanner.nextLine();

        publication = publicationDAO.findPublicationById(publicationId);

        if (publication != null) {

            if (publicationDAO.deletePublication(publicationId)) {
                System.out.println("Publication deleted successfully.");
            } else {
                System.out.println("Failed to delete publication.");
            }

        } else {
            System.out.println("Publication not found.");
        }
    }

    // View All Publications
    public static void viewAllPublications() {

        ArrayList<Publication> publications = publicationDAO.getAllPublications();

        if (publications.isEmpty()) {
            System.out.println("No publications found.");
        } else {

            System.out.println("\n=== All Publications ===");

            for (Publication pub : publications) {
                System.out.println("--------------------");
                System.out.println("ID: " + pub.getPublicationId());
                System.out.println("Name: " + pub.getPublicationName());
                System.out.println("Price: " + pub.getPrice());
                System.out.println("Frequency: " + pub.getFrequency());
                System.out.println("Type: " + pub.getType());
                System.out.println("Stock Quantity: " + pub.getStockQuantity());
            }
        }
    }



    // Exit Menu
    public static boolean exit(boolean running) {

        running = false;
        System.out.println("Have a nice day!");

        return running;
    }
}
