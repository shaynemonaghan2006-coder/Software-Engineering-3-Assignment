package newsagents;

import java.util.ArrayList;
import java.util.Scanner;

public class NewsAgentCLI {

    private static final Scanner scanner = new Scanner(System.in);
    private static final NewsAgentDAO dao = new NewsAgentDAO();

    public NewsAgentCLI() {

        int choice;

        do {
            System.out.println("\n===== NEWS AGENT MENU =====");
            System.out.println("1. Add News Agent");
            System.out.println("2. Find News Agent by ID");
            System.out.println("3. View All News Agents");
            System.out.println("4. Update News Agent");
            System.out.println("5. Delete News Agent");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    addNewsAgent();
                    break;
                case 2:
                    findNewsAgent();
                    break;
                case 3:
                    viewAllNewsAgents();
                    break;
                case 4:
                    updateNewsAgent();
                    break;
                case 5:
                    deleteNewsAgent();
                    break;
                case 6:
                    System.out.println("Exiting News Agent Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);
    }

    //Adds a news agent
    private static void addNewsAgent() {

        System.out.println("\n--- Add News Agent ---");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter phone number (10 digits): ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        NewsAgent agent =
                new NewsAgent(0, name, address, phoneNumber, email);

        if (dao.addNewsAgent(agent)) {
            System.out.println("News agent added successfully.");
        } else {
            System.out.println("Could not add news agent.");
        }
    }

    //Finds news agent by ID
    private static void findNewsAgent() {

        System.out.print("Enter News Agent ID: ");
        int id = readInt();

        NewsAgent agent = dao.findNewsAgentById(id);

        if (agent != null) {
            System.out.println(agent);
        } else {
            System.out.println("News agent not found.");
        }
    }

    //Gets all news agents
    private static void viewAllNewsAgents() {

        ArrayList<NewsAgent> agents = dao.getAllNewsAgents();

        if (agents.isEmpty()) {
            System.out.println("No news agents found.");
            return;
        }

        for (NewsAgent agent : agents) {
            System.out.println("--------------------");
            System.out.println(agent);
        }
    }

    //Updates a news agent
    private static void updateNewsAgent() {

        System.out.print("Enter News Agent ID to update: ");
        int id = readInt();

        NewsAgent existing = dao.findNewsAgentById(id);

        if (existing == null) {
            System.out.println("News agent not found.");
            return;
        }

        System.out.println("Current details:");
        System.out.println(existing);

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new address: ");
        String address = scanner.nextLine();

        System.out.print("Enter new phone number (10 digits): ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter new email: ");
        String email = scanner.nextLine();

        NewsAgent updated =
                new NewsAgent(id, name, address, phoneNumber, email);

        if (dao.updateNewsAgent(updated)) {
            System.out.println("News agent updated successfully.");
        } else {
            System.out.println("Could not update news agent.");
        }
    }

    //Deletes a news agent
    private static void deleteNewsAgent() {

        System.out.print("Enter News Agent ID to delete: ");
        int id = readInt();

        NewsAgent existing = dao.findNewsAgentById(id);

        if (existing == null) {
            System.out.println("News agent not found.");
            return;
        }

        System.out.println(existing);
        System.out.print("Confirm deletion? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            if (dao.deleteNewsAgent(id)) {
                System.out.println("News agent deleted successfully.");
            } else {
                System.out.println("Could not delete news agent.");
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
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
