package newsagents;

public class NewsAgent {

    private int newsAgentId;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    public NewsAgent(int newsAgentId, String name, String address,
                     String phoneNumber, String email) {
        this.newsAgentId = newsAgentId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getNewsAgentId() {
        return newsAgentId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean verifyNewsAgentId(int id) {
        return id > 0;
    }

    public boolean verifyName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public boolean verifyAddress(String address) {
        return address != null && !address.trim().isEmpty();
    }

    public boolean verifyPhoneNumber(String phoneNumber) {
        return phoneNumber != null
                && phoneNumber.matches("[0-9]{10}");
    }

    public boolean verifyEmail(String email) {
        return email != null
                && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    @Override
    public String toString() {
        return "News Agent ID: " + newsAgentId
                + "\nName: " + name
                + "\nAddress: " + address
                + "\nPhone Number: " + phoneNumber
                + "\nEmail: " + email;
    }
}
