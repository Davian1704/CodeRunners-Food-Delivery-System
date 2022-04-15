import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.Test;

import domain.Client;
import domain.Delivery;
import domain.Driver;
import domain.Item;
import domain.Restaurant;
import domain.RestaurantType;
import domain.VehicleType;

import static org.assertj.core.api.Assertions.assertThat;

public class Task4Tests extends UnitTest {

    private final FoodDelivery foodDelivery = new FoodDelivery();

    @Test
    public void givenNoDeliveries_whenComputeIncomesPerVehicleType_thenReturnEmptyMap() {
        //Given

        //When
        final Map<VehicleType, Double> map = foodDelivery.computeIncomesPerVehicleType();

        //Then
        assertThat(map).isEmpty();
    }

    @Test
    public void givenDeliveries_whenComputeIncomesPerVehicleType_thenReturnMap() {
        //Given
        final Client client = new Client();
        final Driver bikeDriver = new Driver("John", VehicleType.BIKE);
        final Driver bikeDriver2 = new Driver("Jim", VehicleType.BIKE);
        final Driver carDriver = new Driver("Martin", VehicleType.CAR);
        final Driver motorbikeDriver = new Driver("Fabian", VehicleType.MOTORBIKE);
        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);

        final Item pizza = new Item("pizza", 15);
        final Item sauce = new Item("sauce", 3);

        final Delivery delivery1 = new Delivery(client, bikeDriver, restaurant, 150);
        delivery1.addItem(pizza);
        delivery1.addItem(sauce);

        final Delivery delivery2 = new Delivery(client, bikeDriver2, restaurant, 150);
        delivery2.addItem(pizza);

        final Delivery delivery3 = new Delivery(client, carDriver, restaurant, 50);
        delivery3.addItem(pizza);
        delivery3.addItem(pizza);
        delivery3.addItem(sauce);
        delivery3.addItem(sauce);

        final Delivery delivery4 = new Delivery(client, motorbikeDriver, restaurant, 200);
        delivery4.addItem(pizza);
        delivery4.addItem(pizza);
        delivery4.addItem(pizza);
        delivery4.addItem(sauce);
        delivery4.addItem(sauce);

        foodDelivery.setDeliveries(Arrays.asList(delivery1, delivery2, delivery3, delivery4));

        //When
        final Map<VehicleType, Double> map = foodDelivery.computeIncomesPerVehicleType();

        //Then
        assertThat(map).hasSize(3);

        assertThat(map).containsKey(VehicleType.BIKE);
        assertThat(map.get(VehicleType.BIKE)).isEqualTo(33);

        assertThat(map).containsKey(VehicleType.CAR);
        assertThat(map.get(VehicleType.CAR)).isEqualTo(36);

        assertThat(map).containsKey(VehicleType.MOTORBIKE);
        assertThat(map.get(VehicleType.MOTORBIKE)).isEqualTo(51);

        assertThat(map).doesNotContainKey(VehicleType.SCOOTER);
    }

}
