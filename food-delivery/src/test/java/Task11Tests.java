import java.util.List;

import org.junit.jupiter.api.Test;

import domain.Client;
import domain.Delivery;
import domain.Driver;
import domain.Item;
import domain.Restaurant;
import domain.RestaurantType;
import domain.VehicleType;

import static org.assertj.core.api.Assertions.assertThat;

public class Task11Tests extends UnitTest {

    @Test
    public void givenItemWithNullName_whenAddItemToDeliveryItemList_thenItemNotInserted() {
        //Given
        final Item item = new Item(null, 10);
        final Client client = new Client();
        final Driver driver = new Driver("John", VehicleType.BIKE);
        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);
        final Delivery delivery = new Delivery(client, driver, restaurant, 15);

        //When
        delivery.addItem(item);

        //Then
        final List<Item> itemList = delivery.getItems();
        assertThat(itemList).isEmpty();
    }

    @Test
    public void givenItemWithDescriptionContainingOnlyWhiteCharacters_whenAddItemToDeliveryItemList_thenItemNotInserted() {
        //Given
        final Item item = new Item("    ", 10);
        final Client client = new Client();
        final Driver driver = new Driver("John", VehicleType.BIKE);
        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);
        final Delivery delivery = new Delivery(client, driver, restaurant, 15);

        //When
        delivery.addItem(item);

        //Then
        assertThat(delivery.getItems()).isEmpty();
    }

    @Test
    public void givenItemWithNegativePrice_whenAddItemToDeliveryItemList_thenItemNotInserted() {
        //Given
        final Item item = new Item("spicy sauce", -1.5);
        final Client client = new Client();
        final Driver driver = new Driver("John", VehicleType.BIKE);

        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);
        final Delivery delivery = new Delivery(client, driver, restaurant, 15);

        //When
        delivery.addItem(item);

        //Then
        assertThat(delivery.getItems()).isEmpty();
    }

    @Test
    public void givenItemWithZeroPrice_whenAddItemToDeliveryItemList_thenItemNotInserted() {
        //Given
        final Item item = new Item("mild sauce", 0);
        final Client client = new Client();
        final Driver driver = new Driver("John", VehicleType.BIKE);

        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);
        final Delivery delivery = new Delivery(client, driver, restaurant, 15);

        //When
        delivery.addItem(item);

        //Then
        assertThat(delivery.getItems()).isEmpty();
    }

    @Test
    public void givenValidItem_whenAddItemToDeliveryItemListOnce_thenItemIsInsertedOnce() {
        //Given
        final String itemName = "soy milk";
        final double itemPrice = 10;
        final Item item = new Item(itemName, itemPrice);
        final Client client = new Client();
        final Driver driver = new Driver("John", VehicleType.BIKE);

        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);
        final Delivery delivery = new Delivery(client, driver, restaurant, 15);

        //When
        delivery.addItem(item);

        //Then
        final List<Item> itemList = delivery.getItems();
        assertThat(itemList).hasSize(1);

        assertItemFields(itemList.get(0), itemName, itemPrice);
    }

    @Test
    public void givenValidItem_whenAddItemToDeliveryItemListTwice_thenItemIsInsertedTwice() {
        //Given
        final String itemName = "coke";
        final double itemPrice = 7.5;
        final Item item = new Item(itemName, itemPrice);
        final Client client = new Client();
        final Driver driver = new Driver("John", VehicleType.BIKE);

        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);
        final Delivery delivery = new Delivery(client, driver, restaurant, 15);

        //When
        delivery.addItem(item);
        delivery.addItem(item);

        //Then
        final List<Item> itemList = delivery.getItems();
        assertThat(itemList).hasSize(2);

        assertItemFields(itemList.get(0), itemName, itemPrice);
        assertItemFields(itemList.get(1), itemName, itemPrice);
    }

    private void assertItemFields(final Item item, final String itemName, final double itemPrice) {
        assertThat(item.getName()).isEqualTo(itemName);
        assertThat(item.getPrice()).isEqualTo(itemPrice);
    }
}
