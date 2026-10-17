package newsagents;

public class Customer {
	private int custId;
	private String firstName;
	private String surname;
	private String address;
	private String phoneNumber;
	
	public Customer() {
		
	}
	
	public Customer(String firstName2, String surname2, String address2, String phoneNumber2) {
		super();
		this.firstName = firstName2;
		this.surname = surname2;
		this.address = address2;
		this.phoneNumber = phoneNumber2;
	}

	public boolean verifyFirstName(String firstName) {
		return firstName != null && !firstName.trim().isEmpty();
	}

	public boolean verifySurname(String surname) {
		return surname != null && !surname.trim().isEmpty();
	}

	public boolean verifyAddress(String address) {
		return address != null && !address.trim().isEmpty();
	}

	public boolean verifyPhoneNumber(String phoneNumber) {
		return phoneNumber != null && phoneNumber.matches("\\d{10}");
	}

	public boolean createCustomerAccount(String firstName, String surname, String address, String phoneNumber) {

		if (verifyFirstName(firstName) && verifySurname(surname) && verifyAddress(address)
				&& verifyPhoneNumber(phoneNumber)) {

			this.firstName = firstName;
			this.surname = surname;
			this.address = address;
			this.phoneNumber = phoneNumber;

			return true;
		}

		return false;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public int getCustomerId() {
		return custId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setCustomerId(int custId) {
		this.custId = custId;
	}
	
}
