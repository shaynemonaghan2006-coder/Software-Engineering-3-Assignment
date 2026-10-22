import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Command-line menu for managing delivery persons.
 * Uses the existing DeliveryPerson class unchanged; records are kept in memory.
 */
public class DeliveryPersonCLI {

    private static final List<DeliveryPerson> deliveryPersons = new ArrayList<>();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Delivery Person Manager ===");
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("1. Create delivery person");
            System.out.println("2. List delivery persons");
            System.out.println("3. View delivery person");
            System.out.println("4. Update delivery person");
            System.out.println("5. Delete delivery person");
            System.out.println("0. Exit");
            switch (prompt("Choose an option: ")) {
                case "1": create(); break;
                case "2": list(); break;
                case "3": view(); break;
                case "4": update(); break;
                case "5": delete(); break;
                case "0": running = false; break;
                default: System.out.println("Invalid option, please try again.");
            }
            if (!in.hasNextLine() && running) { // input closed (e.g. piped input ended)
                running = false;
            }
        }
        System.out.println("Goodbye.");
    }

    // ---------- Operations ----------

    private static void create() {
        System.out.println("-- Create delivery person --");
        try {
            String name = prompt("Name: ");
            String address = prompt("Address: ");
            String phone = prompt("Phone (10 digits): ");
            DeliveryPerson dp = new DeliveryPerson(name, address, phone);
            deliveryPersons.add(dp);
            System.out.println("Saved. Assigned ID: " + dp.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + " Nothing was saved.");
        }
    }

    private static void list() {
        if (deliveryPersons.isEmpty()) {
            System.out.println("There are no delivery persons.");
            return;
        }
        System.out.println("-- Delivery persons (" + deliveryPersons.size() + ") --");
        for (DeliveryPerson dp : deliveryPersons) {
            System.out.println(dp);
        }
    }

    private static void view() {
        DeliveryPerson dp = selectById();
        if (dp != null) {
            System.out.println("ID:      " + dp.getId());
            System.out.println("Name:    " + dp.getName());
            System.out.println("Address: " + dp.getAddress());
            System.out.println("Phone:   " + dp.getPhone());
        }
    }

    private static void update() {
        DeliveryPerson dp = selectById();
        if (dp == null) return;

        System.out.println("Press Enter to keep the current value.");
        String name = prompt("Name [" + dp.getName() + "]: ");
        String address = prompt("Address [" + dp.getAddress() + "]: ");
        String phone = prompt("Phone [" + dp.getPhone() + "]: ");

        // Remember old values so a failed update changes nothing.
        String oldName = dp.getName(), oldAddress = dp.getAddress(), oldPhone = dp.getPhone();
        try {
            if (!name.isEmpty()) dp.setName(name);
            if (!address.isEmpty()) dp.setAddress(address);
            if (!phone.isEmpty()) dp.setPhone(phone);
            System.out.println("Updated: " + dp);
        } catch (IllegalArgumentException e) {
            dp.setName(oldName);
            dp.setAddress(oldAddress);
            dp.setPhone(oldPhone);
            System.out.println("Error: " + e.getMessage() + " No changes were saved.");
        }
    }

    private static void delete() {
        DeliveryPerson dp = selectById();
        if (dp == null) return;

        String answer = prompt("Delete " + dp.getName() + " (ID " + dp.getId() + ")? (y/n): ");
        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
            deliveryPersons.remove(dp);
            System.out.println("Deleted.");
        } else {
            System.out.println("Cancelled. " + dp.getName() + " was kept.");
        }
    }

    // ---------- Helpers ----------

    private static DeliveryPerson selectById() {
        if (deliveryPersons.isEmpty()) {
            System.out.println("There are no delivery persons.");
            return null;
        }
        String text = prompt("Enter delivery person ID: ");
        try {
            int id = Integer.parseInt(text);
            for (DeliveryPerson dp : deliveryPersons) {
                if (dp.getId() == id) return dp;
            }
        } catch (NumberFormatException e) {
            System.out.println("ID must be a number.");
            return null;
        }
        System.out.println("No delivery person found with that ID.");
        return null;
    }

    private static String prompt(String message) {
        System.out.print(message);
        return in.hasNextLine() ? in.nextLine().trim() : "0";
    }
}
