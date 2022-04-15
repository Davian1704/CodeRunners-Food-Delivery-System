import domain.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class Task7Tests extends UnitTest {

    private final FoodDelivery foodDelivery = new FoodDelivery();

    @Test
    public void givenNoDeliveries_whenTotalDeliveryValuePerRestaurants_thenReturnEmptyMap() {
        //Given

        //When
        final Map<String, Double> map = foodDelivery.totalDeliveryValuePerRestaurants();

        //Then
        assertThat(map).isEmpty();
    }

    @Test
    public void givenDeliveries_whenTotalDeliveryValuePerRestaurants_thenReturnMap() {
        //Given
        final Client client = new Client();
        final Driver bikeDriver = new Driver("John", VehicleType.BIKE);
        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);
        final Restaurant restaurant2 = new Restaurant("UzinaDeMici", RestaurantType.ROMANIAN);

        final Item pizza = new Item("pizza", 15);
        final Item sauce = new Item("sauce", 3);
        final Item romanianLittles = new Item("romanian littles", 5);
        final Item pie = new Item("pie", 8);
        final Item mustard = new Item("mustard", 2);

        final Delivery delivery1 = new Delivery(client, bikeDriver, restaurant, 150);
        delivery1.addItem(pizza);
        delivery1.addItem(pizza);
        delivery1.addItem(sauce);
        final Delivery delivery2 = new Delivery(client, bikeDriver, restaurant, 50);
        delivery2.addItem(pizza);
        delivery2.addItem(sauce);

        final Delivery delivery3 = new Delivery(client, bikeDriver, restaurant2, 150);
        delivery3.addItem(romanianLittles);
        delivery3.addItem(romanianLittles);
        delivery3.addItem(mustard);
        delivery3.addItem(pie);

        final Delivery delivery4 = new Delivery(client, bikeDriver, restaurant2, 50);
        delivery4.addItem(romanianLittles);
        delivery4.addItem(romanianLittles);
        delivery4.addItem(romanianLittles);
        delivery4.addItem(mustard);

        foodDelivery.setDeliveries(Arrays.asList(delivery1, delivery2, delivery3, delivery4));

        //When
        final Map<String, Double> map = foodDelivery.totalDeliveryValuePerRestaurants();

        //Then
        assertThat(map).hasSize(2);

        assertThat(map).containsKey("Ristorante");
        assertThat(map.get("Ristorante")).isEqualTo(47.7);

        assertThat(map).containsKey("UzinaDeMici");
        assertThat(map.get("UzinaDeMici")).isEqualTo(31.5);
    }

}
