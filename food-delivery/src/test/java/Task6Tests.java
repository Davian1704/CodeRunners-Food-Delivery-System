import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

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

public class Task6Tests extends UnitTest {

    private final FoodDelivery foodDelivery = new FoodDelivery();

    @Test
    public void givenNoDeliveries_whenTop1ProductsPerRestaurants_thenReturnEmptyMap() {
        //Given

        //When
        final Map<String, Collection<String>> map = foodDelivery.topNProductsPerRestaurants(1);

        //Then
        assertThat(map).isEmpty();
    }

    @Test
    public void givenDeliveries_whenTopNegativeProductsPerRestaurants_thenReturnEmptyMap() {
        //Given
        final Client client = new Client();
        final Driver bikeDriver = new Driver("John", VehicleType.BIKE);
        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);

        final Item pizza = new Item("pizza", 15);
        final Item sauce = new Item("sauce", 3);

        final Delivery delivery1 = new Delivery(client, bikeDriver, restaurant, 150);
        delivery1.addItem(pizza);
        delivery1.addItem(sauce);

        foodDelivery.setDeliveries(singletonList(delivery1));

        //When
        final Map<String, Collection<String>> map = foodDelivery.topNProductsPerRestaurants(-1);

        //Then
        assertThat(map).isEmpty();
    }

    @Test
    public void givenDeliveries_whenTopZeroProductsPerRestaurants_thenReturnEmptyMap() {
        //Given
        final Client client = new Client();
        final Driver bikeDriver = new Driver("John", VehicleType.BIKE);
        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);

        final Item pizza = new Item("pizza", 15);
        final Item sauce = new Item("sauce", 3);

        final Delivery delivery1 = new Delivery(client, bikeDriver, restaurant, 150);
        delivery1.addItem(pizza);
        delivery1.addItem(sauce);

        foodDelivery.setDeliveries(singletonList(delivery1));

        //When
        final Map<String, Collection<String>> map = foodDelivery.topNProductsPerRestaurants(0);

        //Then
        assertThat(map).isEmpty();
    }

    @Test
    public void givenDeliveries_whenTop2ProductsPerRestaurants_thenReturnMap() {
        //Given
        final Client client = new Client();
        final Driver bikeDriver = new Driver("John", VehicleType.BIKE);
        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);
        final Restaurant restaurant2 = new Restaurant("UzinaDeMici", RestaurantType.ROMANIAN);

        final Item pizza = new Item("pizza", 15);
        final Item pasta = new Item("pasta", 20);
        final Item sauce = new Item("sauce", 3);
        final Item romanianLittles = new Item("romanian littles", 5);
        final Item pie = new Item("pie", 8);
        final Item mustard = new Item("mustard", 2);

        final Delivery delivery1 = new Delivery(client, bikeDriver, restaurant, 150);
        delivery1.addItem(pizza);
        delivery1.addItem(pizza);
        delivery1.addItem(sauce);
        delivery1.addItem(sauce);
        delivery1.addItem(sauce);
        delivery1.addItem(pasta);

        final Delivery delivery2 = new Delivery(client, bikeDriver, restaurant2, 150);
        delivery2.addItem(romanianLittles);
        delivery2.addItem(romanianLittles);
        delivery2.addItem(romanianLittles);
        delivery2.addItem(romanianLittles);
        delivery2.addItem(mustard);
        delivery2.addItem(pie);
        delivery2.addItem(pie);

        foodDelivery.setDeliveries(Arrays.asList(delivery1, delivery2));

        //When
        final Map<String, Collection<String>> map = foodDelivery.topNProductsPerRestaurants(2);

        //Then
        assertThat(map).isNotEmpty();

        assertThat(map).containsKey("Ristorante");
        assertThat(map.get("Ristorante")).containsExactly("sauce", "pizza");

        assertThat(map).containsKey("UzinaDeMici");
        assertThat(map.get("UzinaDeMici")).containsExactly("romanian littles", "pie");
    }

    @Test
    public void givenDeliveries_whenTop3ProductsPerRestaurants_thenReturnMap() {
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
        delivery1.addItem(sauce);
        delivery1.addItem(sauce);

        final Delivery delivery2 = new Delivery(client, bikeDriver, restaurant2, 150);
        delivery2.addItem(romanianLittles);
        delivery2.addItem(romanianLittles);
        delivery2.addItem(romanianLittles);
        delivery2.addItem(romanianLittles);
        delivery2.addItem(mustard);
        delivery2.addItem(pie);
        delivery2.addItem(pie);

        foodDelivery.setDeliveries(Arrays.asList(delivery1, delivery2));

        //When
        final Map<String, Collection<String>> map = foodDelivery.topNProductsPerRestaurants(3);

        //Then
        assertThat(map).isNotEmpty();

        assertThat(map).containsKey("Ristorante");
        assertThat(map.get("Ristorante")).containsExactly("sauce", "pizza");

        assertThat(map).containsKey("UzinaDeMici");
        assertThat(map.get("UzinaDeMici")).containsExactly("romanian littles", "pie", "mustard");
    }

}
