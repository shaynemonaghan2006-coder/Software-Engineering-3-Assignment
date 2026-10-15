
public class DeliveryPerson {

    private static int nextId = 1;

    private final int id;
    private String name;
    private String address;
    private String phone;

    public DeliveryPerson(String name, String address, String phone) {
        this.id = nextId++;
        setName(name);
        setAddress(address);
        setPhone(phone);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        this.name = name.trim();
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be blank.");
        }
        this.address = address.trim();
    }

    public void setPhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be 10 digits.");
        }
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Address: " + address + " | Phone: " + phone;
    }
}
