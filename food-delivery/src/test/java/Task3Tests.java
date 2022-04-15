import java.util.Arrays;

import org.junit.jupiter.api.Test;

import domain.Client;
import domain.Delivery;
import domain.Driver;
import domain.Item;
import domain.Restaurant;
import domain.RestaurantType;
import domain.VehicleType;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Tests extends UnitTest {

    private final FoodDelivery foodDelivery = new FoodDelivery();

    @Test
    public void givenDeliveryUnder100Minutes_whenComputeDiscountedValue_thenReturnZero() {
        //Given
        final Client client = new Client();
        final Driver driver = new Driver("John", VehicleType.BIKE);
        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);
        final Delivery emptyDelivery = new Delivery(client, driver, restaurant, 15);

        foodDelivery.setDeliveries(singletonList(emptyDelivery));

        //When
        final double discountedValue = foodDelivery.discountedValue();

        //Then
        assertThat(discountedValue).isEqualTo(0.0);
    }

    @Test
    public void givenDeliveryIn100Minutes_whenComputeDiscountedValue_thenReturnZero() {
        //Given
        final Client client = new Client();
        final Driver driver = new Driver("John", VehicleType.BIKE);
        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);
        final Delivery emptyDelivery = new Delivery(client, driver, restaurant, 100);

        foodDelivery.setDeliveries(singletonList(emptyDelivery));

        //When
        final double discountedValue = foodDelivery.discountedValue();

        //Then
        assertThat(discountedValue).isEqualTo(0.0);
    }

    @Test
    public void givenDeliveryLongerThan100Minutes_whenComputeDiscountedValue_thenReturnDiscount() {
        //Given
        final Item pizza = new Item("pizza", 15);
        final Item sauce = new Item("sauce", 3);

        final Client client = new Client();
        final Driver driver = new Driver("John", VehicleType.BIKE);
        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);
        final Delivery delivery = new Delivery(client, driver, restaurant, 150);
        delivery.addItem(pizza);
        delivery.addItem(sauce);

        foodDelivery.setDeliveries(singletonList(delivery));

        //When
        final double discountedValue = foodDelivery.discountedValue();

        //Then
        assertThat(discountedValue).isEqualTo(1.8);
    }

    @Test
    public void givenDeliveryList_whenComputeDiscountedValue_thenReturnDiscount() {
        //Given
        final Item pizza = new Item("pizza", 15);
        final Item sauce = new Item("sauce", 3);

        final Client client = new Client();
        final Driver driver = new Driver("John", VehicleType.BIKE);
        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);

        final Delivery delivery1 = new Delivery(client, driver, restaurant, 150);
        delivery1.addItem(pizza);
        delivery1.addItem(sauce);

        final Delivery delivery2 = new Delivery(client, driver, restaurant, 50);
        delivery2.addItem(pizza);
        delivery2.addItem(pizza);
        delivery2.addItem(sauce);

        final Delivery delivery3 = new Delivery(client, driver, restaurant, 200);
        delivery3.addItem(pizza);
        delivery3.addItem(pizza);
        delivery3.addItem(sauce);
        delivery3.addItem(sauce);

        foodDelivery.setDeliveries(Arrays.asList(delivery1, delivery2, delivery3));

        //When
        final double discountedValue = foodDelivery.discountedValue();

        //Then
        assertThat(discountedValue).isEqualTo(5.4);
    }

}
