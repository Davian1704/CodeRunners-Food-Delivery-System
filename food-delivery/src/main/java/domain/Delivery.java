package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Delivery {

    public Delivery(final Client client, final Driver driver, final Restaurant restaurant, final int duration) {
        this.id = new Random().nextLong();
        this.client = client;
        this.driver = driver;
        this.restaurant = restaurant;
        this.duration = duration;
        this.totalValue = -1.0;
        items = new ArrayList<>();
    }

    private final Long id;

    private final Client client;

    private final Driver driver;

    private final Restaurant restaurant;

    private final List<Item> items;

    private final int duration;// in minutes

    private final double totalValue;

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Driver getDriver() {
        return driver;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getDuration() {
        return duration;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void addItem(Item item) {
        //TODO(implementation)
    }

    public void removeItem(String itemName) {
        //TODO(implementation)
    }

    public void computeTotalValue() {
        //TODO(implementation)
    }
}
