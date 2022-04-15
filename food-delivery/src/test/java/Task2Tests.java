import org.junit.jupiter.api.Test;

import domain.Client;
import domain.Delivery;
import domain.Driver;
import domain.Item;
import domain.Restaurant;
import domain.RestaurantType;
import domain.VehicleType;

import static org.assertj.core.api.Assertions.assertThat;

public class Task2Tests extends UnitTest {

    @Test
    public void givenDeliveryWithoutItems_whenComputeTotalValue_thenTotalIsZero() {
        //Given
        final Client client = new Client();
        final Driver driver = new Driver("John", VehicleType.BIKE);
        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);
        final Delivery emptyDelivery = new Delivery(client, driver, restaurant, 15);

        //When
        emptyDelivery.computeTotalValue();

        //Then
        final double value = emptyDelivery.getTotalValue();
        assertThat(value).isEqualTo(0.0);
    }

    @Test
    public void givenDeliveryWithItems_whenComputeTotalValue_thenTotalIsComputed() {
        //Given
        final Client client = new Client();
        final Driver driver = new Driver("John", VehicleType.BIKE);
        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);
        final Delivery emptyDelivery = new Delivery(client, driver, restaurant, 20);
        final Item item1 = new Item("Food item 1", 1.5);
        final Item item2 = new Item("Food item 2", 5.7);
        emptyDelivery.addItem(item1);
        emptyDelivery.addItem(item2);

        //When
        emptyDelivery.computeTotalValue();

        //Then
        final double value = emptyDelivery.getTotalValue();
        assertThat(value).isEqualTo(7.2);
    }
}
