package domain;

public class Restaurant {

    private final String name;

    private final RestaurantType restaurantType;

    public Restaurant(String name, RestaurantType restaurantType) {
        this.name = name;
        this.restaurantType = restaurantType;
    }

    public String getName() {
        return name;
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }
}
