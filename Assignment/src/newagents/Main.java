import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<DeliveryPerson> deliveryPersons = new ArrayList<>();

        deliveryPersons.add(new DeliveryPerson("Rajesh Kuthrapali", "Disney Land,Paris", "0851234567"));
        deliveryPersons.add(new DeliveryPerson("connor mc gregor", "5 Church Road", "0869876543"));
        deliveryPersons.add(new DeliveryPerson("Donna Raymond", "27 Park Avenue", "0831112233"));
        deliveryPersons.add(new DeliveryPerson("Siobhan Kelly", "8 Bridge Street", "0874445566"));
        deliveryPersons.add(new DeliveryPerson("Liam O'Brien", "41 Mill Lane", "0897778899"));

        System.out.println("Delivery Persons:");
        for (DeliveryPerson dp : deliveryPersons) {
            System.out.println(dp);
        }
    }
}
