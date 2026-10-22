import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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


class DeliveryPersonTest {

    private List<DeliveryPerson> deliveryPersons;

    @BeforeEach
    void setUp() {
        deliveryPersons = new ArrayList<>();
    }

    // Mimics "save": the person is only added to the list if the constructor accepts the details.
    private DeliveryPerson save(String name, String address, String phone) {
        DeliveryPerson dp = new DeliveryPerson(name, address, phone);
        deliveryPersons.add(dp);
        return dp;
    }

    private DeliveryPerson johnMurphy() {
        return save("John Murphy", "12 Main Street", "0851112223");
    }



    @Test
    @DisplayName("US1 TC1: valid details are saved and an ID is assigned")
    void us1_tc1_validDetailsSavedWithId() {
        DeliveryPerson dp = save("Rajesh Kuthrapali", "Disney Land,Paris", "0851234567");

        assertEquals("Rajesh Kuthrapali", dp.getName());
        assertEquals("Disney Land,Paris", dp.getAddress());
        assertEquals("0851234567", dp.getPhone());
        assertTrue(dp.getId() > 0, "An ID should be assigned");
        assertEquals(1, deliveryPersons.size());
    }

    @Test
    @DisplayName("US1 TC1: each new delivery person gets a different ID")
    void us1_tc1_idsAreUnique() {
        DeliveryPerson first = save("Donna Raymond", "27 Park Avenue", "0831112233");
        DeliveryPerson second = save("Siobhan Kelly", "8 Bridge Street", "0874445566");

        assertNotEquals(first.getId(), second.getId());
        assertEquals(first.getId() + 1, second.getId());
    }

    @Test
    @DisplayName("US1 TC2: blank name is rejected with a message and nothing is saved")
    void us1_tc2_blankNameRejected() {
        for (String blank : new String[]{"", "   ", null}) {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> save(blank, "5 Church Road", "0869876543"));
            assertEquals("Name cannot be blank.", ex.getMessage());
        }
        assertTrue(deliveryPersons.isEmpty(), "Nothing should be saved");
    }

    @Test
    @DisplayName("US1 TC2: blank address is rejected with a message and nothing is saved")
    void us1_tc2_blankAddressRejected() {
        for (String blank : new String[]{"", "   ", null}) {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> save("connor mc gregor", blank, "0869876543"));
            assertEquals("Address cannot be blank.", ex.getMessage());
        }
        assertTrue(deliveryPersons.isEmpty(), "Nothing should be saved");
    }

    @Test
    @DisplayName("US1 TC3: phone numbers that are not 10 digits are rejected with a message")
    void us1_tc3_invalidPhoneRejected() {
        String[] invalidPhones = {"08512", "08512345678", "", "085123456a", "085 123 4567", null};
        for (String phone : invalidPhones) {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> save("Liam O'Brien", "41 Mill Lane", phone),
                    "Phone should be rejected: " + phone);
            assertEquals("Phone number must be 10 digits.", ex.getMessage());
        }
        assertTrue(deliveryPersons.isEmpty(), "Nothing should be saved");
    }

    @Test
    @DisplayName("US1 TC4: a newly saved delivery person appears in the list")
    void us1_tc4_appearsInList() {
        DeliveryPerson john = johnMurphy();

        assertTrue(deliveryPersons.contains(john));
        assertEquals("John Murphy", deliveryPersons.get(0).getName());
    }


    @Test
    @DisplayName("US2 TC1: the list shows all 3 saved delivery persons")
    void us2_tc1_listShowsAllRecords() {
        DeliveryPerson a = save("Rajesh Kuthrapali", "Disney Land,Paris", "0851234567");
        DeliveryPerson b = save("connor mc gregor", "5 Church Road", "0869876543");
        DeliveryPerson c = save("Donna Raymond", "27 Park Avenue", "0831112233");

        assertEquals(3, deliveryPersons.size());
        assertTrue(deliveryPersons.containsAll(List.of(a, b, c)));
    }

    @Test
    @DisplayName("US2 TC2: selecting John Murphy shows name, address and phone number")
    void us2_tc2_selectedRecordShowsDetails() {
        johnMurphy();
        save("Donna Raymond", "27 Park Avenue", "0831112233");

        DeliveryPerson selected = deliveryPersons.stream()
                .filter(dp -> dp.getName().equals("John Murphy"))
                .findFirst()
                .orElseThrow();

        assertEquals("John Murphy", selected.getName());
        assertEquals("12 Main Street", selected.getAddress());
        assertEquals("0851112223", selected.getPhone());

        String displayed = selected.toString();
        assertTrue(displayed.contains("John Murphy"));
        assertTrue(displayed.contains("12 Main Street"));
        assertTrue(displayed.contains("0851112223"));
        assertTrue(displayed.contains("ID: " + selected.getId()));
    }

    @Test
    @DisplayName("US2 TC3: with all records deleted the list is empty (UI shows 'no delivery persons')")
    void us2_tc3_emptyListAfterDeletingAll() {
        DeliveryPerson a = johnMurphy();
        DeliveryPerson b = save("Donna Raymond", "27 Park Avenue", "0831112233");

        deliveryPersons.remove(a);
        deliveryPersons.remove(b);

        assertTrue(deliveryPersons.isEmpty());
    }

    @Test
    @DisplayName("US3 TC1: valid phone and address changes are saved")
    void us3_tc1_validUpdateSaved() {
        DeliveryPerson john = johnMurphy();

        john.setPhone("0867654321");
        john.setAddress("5 High St");

        assertEquals("0867654321", john.getPhone());
        assertEquals("5 High St", john.getAddress());
    }

    @Test
    @DisplayName("US3 TC2: blank name is rejected on update and the old name is kept")
    void us3_tc2_blankNameRejectedOnUpdate() {
        DeliveryPerson john = johnMurphy();

        for (String blank : new String[]{"", "   ", null}) {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> john.setName(blank));
            assertEquals("Name cannot be blank.", ex.getMessage());
        }
        assertEquals("John Murphy", john.getName());
    }

    @Test
    @DisplayName("US3 TC2: blank address is rejected on update and the old address is kept")
    void us3_tc2_blankAddressRejectedOnUpdate() {
        DeliveryPerson john = johnMurphy();

        for (String blank : new String[]{"", "   ", null}) {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> john.setAddress(blank));
            assertEquals("Address cannot be blank.", ex.getMessage());
        }
        assertEquals("12 Main Street", john.getAddress());
    }

    @Test
    @DisplayName("US3 TC2: a 9-digit phone number is rejected on update and the old phone is kept")
    void us3_tc2_shortPhoneRejectedOnUpdate() {
        DeliveryPerson john = johnMurphy();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> john.setPhone("086765432"));
        assertEquals("Phone number must be 10 digits.", ex.getMessage());
        assertEquals("0851112223", john.getPhone());
    }

    @Test
    @DisplayName("US3 TC3: re-opening the record shows the updated phone and address")
    void us3_tc3_updatedDetailsShownWhenViewedAgain() {
        DeliveryPerson john = johnMurphy();
        john.setPhone("0867654321");
        john.setAddress("5 High St");

        DeliveryPerson reopened = deliveryPersons.stream()
                .filter(dp -> dp.getId() == john.getId())
                .findFirst()
                .orElseThrow();

        assertEquals("0867654321", reopened.getPhone());
        assertEquals("5 High St", reopened.getAddress());
        assertTrue(reopened.toString().contains("0867654321"));
        assertTrue(reopened.toString().contains("5 High St"));
    }

    @Test
    @DisplayName("US3: leading/trailing spaces are trimmed from name and address on update")
    void us3_updateTrimsWhitespace() {
        DeliveryPerson john = johnMurphy();

        john.setName("  John Murphy Jr  ");
        john.setAddress("  5 High St  ");

        assertEquals("John Murphy Jr", john.getName());
        assertEquals("5 High St", john.getAddress());
    }



    @Test
    @Disabled("Not testable yet: DeliveryPerson has no delete/confirmation prompt (UI/controller code needed)")
    @DisplayName("US4 TC1: selecting 'Delete' on John Murphy shows a confirmation prompt")
    void us4_tc1_confirmationPromptAppears() {
        fail("Implement once a delete/confirm feature exists");
    }

    @Test
    @DisplayName("US4 TC2: confirming the deletion removes John from the list")
    void us4_tc2_confirmedDeleteRemovesPerson() {
        DeliveryPerson john = johnMurphy();
        save("Donna Raymond", "27 Park Avenue", "0831112233");

        boolean confirmed = true;
        if (confirmed) {
            deliveryPersons.remove(john);
        }

        assertFalse(deliveryPersons.contains(john));
        assertEquals(1, deliveryPersons.size());
    }

    @Test
    @DisplayName("US4 TC3: cancelling the deletion keeps John in the list")
    void us4_tc3_cancelledDeleteKeepsPerson() {
        DeliveryPerson john = johnMurphy();

        boolean confirmed = false;
        if (confirmed) {
            deliveryPersons.remove(john);
        }

        assertTrue(deliveryPersons.contains(john));
        assertEquals(1, deliveryPersons.size());
    }

    @Test
    @Disabled("Not testable yet: no Delivery/assignment class exists to link a delivery person to deliveries")
    @DisplayName("US4 TC4: a delivery person with assigned deliveries cannot be deleted")
    void us4_tc4_assignedDeliveriesBlockDeletion() {
        fail("Implement once deliveries can be assigned to a delivery person");
    }
}
