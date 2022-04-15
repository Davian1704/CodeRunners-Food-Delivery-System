import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import domain.Client;
import domain.Delivery;
import domain.Driver;
import domain.Item;
import domain.Restaurant;
import domain.VehicleType;

public class FoodDelivery {

    List<Client> clients;
    List<Driver> drivers;
    List<Restaurant> restaurants;
    List<Delivery> deliveries;

    List<Item> items;

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double discountedValue() {
        //TODO(implementation)
        return 0.0;
    }

    public Map<VehicleType, Double> computeIncomesPerVehicleType() {
        //TODO(implementation)
        return new HashMap<>();
    }

    public Collection<String> mostSuccessfulDrivers() {
        //TODO(implementation)
        return new LinkedList<>();
    }

    public Map<String, Collection<String>> topNProductsPerRestaurants(final int n) {
        //TODO(implementation)
        return new HashMap<>();
    }

    public Map<String, Double> totalDeliveryValuePerRestaurants() {
        //TODO(implementation)
        return new HashMap<>();
    }
}
