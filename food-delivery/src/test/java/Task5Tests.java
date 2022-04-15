import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import domain.Client;
import domain.Delivery;
import domain.Driver;
import domain.Item;
import domain.Restaurant;
import domain.RestaurantType;
import domain.VehicleType;

import static org.assertj.core.api.Assertions.assertThat;

public class Task5Tests extends UnitTest {

    private final FoodDelivery foodDelivery = new FoodDelivery();

    @Test
    public void givenNoDeliveries_whenGetMostSuccessfulDrivers_thenReturnEmptyMap() {
        //Given

        //When
        final Collection<String> drivers = foodDelivery.mostSuccessfulDrivers();

        //Then
        assertThat(drivers).isEmpty();
    }

    @Test
    public void givenDeliveries_whenComputeIncomesPerVehicleType_thenReturnMap() {
        //Given
        final Client client = new Client();
        final Driver bikeDriver = new Driver("John", VehicleType.BIKE);
        final Driver carDriver = new Driver("Martin", VehicleType.CAR);
        final Driver motorbikeDriver = new Driver("Fabian", VehicleType.MOTORBIKE);
        final Restaurant restaurant = new Restaurant("Ristorante", RestaurantType.CONTINENTAL);

        final Item pizza = new Item("pizza", 15);
        final Item sauce = new Item("sauce", 3);

        final Delivery delivery1 = new Delivery(client, bikeDriver, restaurant, 150);
        delivery1.addItem(pizza);
        delivery1.addItem(sauce);

        final Delivery delivery2 = new Delivery(client, bikeDriver, restaurant, 150);
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
        final Collection<String> drivers = foodDelivery.mostSuccessfulDrivers();

        //Then
        assertThat(drivers).hasSize(3);

        final Iterator<String> iterator = drivers.iterator();

        final String driver1 = iterator.next();
        assertThat(driver1).isEqualTo("Fabian");

        final String driver2 = iterator.next();
        assertThat(driver2).isEqualTo("Martin");

        final String driver3 = iterator.next();
        assertThat(driver3).isEqualTo("John");
    }

}
