import java.util.Iterator;
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

public class Task12Tests extends UnitTest {


    public Delivery mockDelivery() {
        final Client client = new Client();
        final Driver driver = new Driver("John", VehicleType.BIKE);
        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);
        final Delivery delivery = new Delivery(client, driver, restaurant, 15);

        delivery.addItem(new Item("spicy sauce", 5));
        delivery.addItem(new Item("spicy sauce", 5));
        delivery.addItem(new Item("pizza", 15));

        return delivery;
    }

    @Test
    public void givenNullItemName_whenRemoveItemFromDeliveryItemList_thenNoItemRemoved() {
        //Given
        final Delivery delivery = mockDelivery();

        //When
        delivery.removeItem(null);

        //Then
        assertThat(delivery.getItems()).hasSize(3);
    }

    @Test
    public void givenItemNameContainingOnlyWhitespaces_whenRemoveItemFromDeliveryItemList_thenNoItemRemoved() {
        //Given
        final Delivery delivery = mockDelivery();

        //When
        delivery.removeItem("     ");

        //Then
        assertThat(delivery.getItems()).hasSize(3);
    }

    @Test
    public void givenNonexistentItem_whenRemoveItemFromDeliveryItemList_thenNoItemRemoved() {
        //Given
        final Delivery delivery = mockDelivery();

        //When
        delivery.removeItem("soy milk");

        //Then
        assertThat(delivery.getItems()).hasSize(3);
    }

    @Test
    public void givenExistentItem_whenRemoveItemFromDeliveryItemList_thenItemRemoved() {
        //Given
        final Delivery delivery = mockDelivery();
        final String itemToBeRemoved = "pizza";
        //When
        delivery.removeItem(itemToBeRemoved);

        //Then
        final List<Item> items = delivery.getItems();
        assertThat(items).hasSize(2);

        final Iterator<Item> iterator = items.iterator();
        iterator.forEachRemaining(item -> assertThat(item.getName()).isNotEqualTo(itemToBeRemoved));
    }

    @Test
    public void givenItemThatExistsTwice_whenRemoveItemFromDeliveryItemList_thenItemRemovedOnce() {
        //Given
        final Delivery delivery = mockDelivery();

        //When
        delivery.removeItem("spicy sauce");

        //Then
        final List<Item> items = delivery.getItems();
        assertThat(items).hasSize(2);

        final Iterator<Item> iterator = items.iterator();
        final Item firstItem = iterator.next();
        assertThat(firstItem.getName()).isEqualTo("spicy sauce");

        final Item secondItem = iterator.next();
        assertThat(secondItem.getName()).isEqualTo("pizza");
    }

}
