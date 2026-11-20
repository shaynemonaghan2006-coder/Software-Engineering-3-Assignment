package newsagents;

import java.util.ArrayList;
import java.util.Scanner;

public class NewsAgentCLI {

   static Scanner scanner = new Scanner(System.in);
   static NewsAgentDAO dao = new NewsAgentDAO();
   static int newsAgentId; 

   public NewsAgentCLI() {

        

        boolean running = true;
        	
        while(running) {
            int option = mainMenu();

            switch (option) {
                case 1:
                    createNewsAgent();
                    break;
                case 2:
                    readNewsAgentId();
                    break;
                case 3:
                    updateNewsAgent();
                    break;
                case 4:
                    deleteNewsAgent();
                    break;
                case 5:
                    readAllNewsAgents();
                    break;
                case 6:
                    running = exit(running);
                    break;
                default:
                    System.out.println("Invalid option chosen.");
            }

        } 
    }
    
    public static int mainMenu() {
    	System.out.println();
        System.out.println("=== NEWS AGENT MENU ===");
        System.out.println("1 - Create News Agent");
        System.out.println("2 - Read News Agent by ID");
        System.out.println("3 - Update News Agent");
        System.out.println("4 - Delete News Agent");
        System.out.println("5 - Read All News Agents");
        System.out.println("6 - Exit");
        System.out.print("Option: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }
    //Adds a news agent
    private static void createNewsAgent() {

        System.out.println("=== Create News Agent ===");

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
    private static void readNewsAgentId() {

        System.out.print("Enter News Agent ID: ");
        int id = scanner.nextInt(); 

        NewsAgent agent = dao.findNewsAgentById(id);

        if (agent != null) {
            System.out.println("\nNewsAgent found: ");
            System.out.println("ID: " + agent.getNewsAgentId());
            System.out.println("Name: " + agent.getName());
            System.out.println("Address: " + agent.getAddress());
            System.out.println("Phone Number: " + agent.getPhoneNumber());
            System.out.println("Email: " + agent.getEmail());
        } else {
            System.out.println("News agent not found.");
        }
    }

    //Gets all news agents
    private static void readAllNewsAgents() {

        ArrayList<NewsAgent> agents = dao.getAllNewsAgents();

        if (agents.isEmpty()) {
            System.out.println("No news agents found.");
            return;
        }
        else {
        	System.out.println("\n=== All NewsAgents ===");
        

	        for (NewsAgent agent : agents) {
	            System.out.println("--------------------");
	            System.out.println("ID: " + agent.getNewsAgentId());
	            System.out.println("Name: " + agent.getName());
	            System.out.println("Address: " + agent.getAddress());
	            System.out.println("Phone Number: " + agent.getPhoneNumber());
	            System.out.println("Email: " + agent.getEmail());
	        }
        }
    }

    //Updates a news agent
    private static void updateNewsAgent() {

        System.out.print("Enter News Agent ID to update: ");
        int id = scanner.nextInt();

        NewsAgent existing = dao.findNewsAgentById(id);

        if (existing == null) {
            System.out.println("News agent not found.");
            return;
        }

        System.out.println("Current details:");
        System.out.println(existing);

        scanner.nextLine();
        
        System.out.println("Enter new name: ");
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
        int id = scanner.nextInt();

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
    
    public static boolean exit(boolean running ) {
		running = false;
		System.out.println("Have a nice day!");
		return running;
	}

    
    
    
}
