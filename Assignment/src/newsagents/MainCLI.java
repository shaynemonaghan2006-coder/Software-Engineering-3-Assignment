package newsagents;

import java.util.Scanner;

public class MainCLI {
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {

		boolean running = true;
		
		while(running) {
			int option = mainMenu();
			
			switch(option) {
			case 1:
				new CustomerCLI();
				break;
				
			case 2: 
				new OrderCLI();
				break;
				
			case 3: 
				new DeliveryAreaCLI();
				break;
				
			case 4:
				new BillCLI();
				break;
			
			case 5:
				new DeliveryDocketCLI();
				break;
			
			case 6:
				new PublicationCLI();
				break;
			
			case 7:
				new NewsAgentCLI();
				break;
				
			case 8:
				new DeliveryPersonCLI();
				break;
			
			case 9: 
				running = exit(running);
				break;
			
			default:
				System.out.println("Invalid option chosen.");
				break;
			}
		}
		
		scanner.close();
	}
	
	public static int mainMenu() {
		System.out.println();
		System.out.println("=== Main Menu ===");
		System.out.println("1 - Customers");
		System.out.println("2 - Orders");
		System.out.println("3 - Delivery Areas");
		System.out.println("4 - Bills");
		System.out.println("5 - Delivery Dockets");
		System.out.println("6 - Publications");
		System.out.println("7 - Newsagents");
		System.out.println("8 - Delivery People");
		System.out.println("9 - Exit");
		System.out.println();
		System.out.print("Option: ");
		int option = scanner.nextInt();
		scanner.nextLine();
		return option;
	}
	
	public static boolean exit(boolean running ) {
		running = false;
		System.out.println("Have a nice day!");
		return running;
	}
	
}
