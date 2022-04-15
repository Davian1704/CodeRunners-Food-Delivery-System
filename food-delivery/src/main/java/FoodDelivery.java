import java.util.*;

import domain.*;

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
        double discounted = 0.0;

        for(Delivery d : deliveries)
            if(d.getDuration() > 100) {
                d.computeTotalValue();
                discounted += d.getTotalValue() * 0.1;
            }

        return discounted;
    }

    public Map<VehicleType, Double> computeIncomesPerVehicleType() {

        Map<VehicleType, Double> result = new HashMap< VehicleType, Double>();

        if(deliveries != null)
        for(Delivery d : deliveries) {
            VehicleType v = d.getDriver().getVehicleType();
            d.computeTotalValue();

            if(result.containsKey(v))
            result.put(v, result.get(v) + d.getTotalValue());

            else
                result.put(v, d.getTotalValue());
        }

        return result;
    }

    public Collection<String> mostSuccessfulDrivers() {

        class CustomPair implements  Comparable{
            String name;
            double income;

            public CustomPair(String name, double income) {
                this.name = name;
                this.income = income;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getIncome() {
                return income;
            }

            public void setIncome(double income) {
                this.income = income;
            }

            @Override
            public int compareTo(Object o) {
                return (int) -(this.income - ((CustomPair)o).income);
            }
        }

        Map < String, Double > result = new HashMap<>();

        if(deliveries == null)
            return new LinkedList<>();

        for(Delivery d : deliveries)
        {
            String name = d.getDriver().getName();
            d.computeTotalValue();

            if(result.containsKey(name))
                result.put(name, result.get(name) + d.getTotalValue());

            else
                result.put(name, d.getTotalValue());
        }

        List < CustomPair > l = new ArrayList<CustomPair>();

        for(Map.Entry<String,Double> entry : result.entrySet())
            l.add(new CustomPair(entry.getKey(), entry.getValue()));

        l.sort(CustomPair::compareTo);

        List < String > rez = new LinkedList<String>();

        for(CustomPair c : l)
            rez.add(c.getName());

        return rez;
    }


    public Map<String, Collection<String>> topNProductsPerRestaurants(final int n) {

        class CustomPair implements Comparable
        {
            String name;
            int quantity;

            public CustomPair(String name, int quantity) {
                this.name = name;
                this.quantity = quantity;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            @Override
            public int compareTo(Object o) {
                return ((CustomPair)o).quantity - quantity;
            }
        }


        HashMap < String, HashMap < String, Integer > > result = new HashMap<>();

        if(deliveries == null || n <= 0) return new HashMap<String, Collection<String>>();

        for(Delivery d : deliveries)
        {

            String restaurantName = d.getRestaurant().getName();

            if(result.containsKey(restaurantName))
            {
                List < Item > items = d.getItems();

                for(Item i : items)
                    if(result.get(restaurantName).containsKey(i.getName()))
                        result.get(restaurantName).put(i.getName(),result.get(restaurantName).get(i.getName()) + 1);
                    else
                        result.get(restaurantName).put(i.getName(), 1);
            }

            else
            {
                result.put(restaurantName, new HashMap<>());

                List < Item > items = d.getItems();

                for(Item i : items)
                    if(result.get(restaurantName).containsKey(i.getName()))
                        result.get(restaurantName).put(i.getName(),result.get(restaurantName).get(i.getName()) + 1);
                    else
                        result.get(restaurantName).put(i.getName(), 1);

            }

        }

        HashMap < String, Collection < String >  > rez = new HashMap<>();

        for(Map.Entry<String, HashMap<String, Integer>> entry : result.entrySet())
        {
            HashMap<String, Integer> aux = new HashMap<>();

            aux = entry.getValue();
            List < CustomPair > l = new ArrayList< CustomPair >();

            for(Map.Entry < String, Integer > entry1 : aux.entrySet())
                l.add(new CustomPair(entry1.getKey(), entry1.getValue()));

            l.sort(CustomPair::compareTo);

            List < String > l2 = new ArrayList<>();

            for(int i = 0; i < n; i++)
                if(i < l.size())
                    l2.add(l.get(i).getName());

            rez.put(entry.getKey(), l2);

        }

        return rez;
    }

    public Map<String, Double> totalDeliveryValuePerRestaurants() {

        HashMap < String, Double > result = new HashMap<>();

        if(deliveries == null)
            return new HashMap<String, Double>();

        for(Delivery d : deliveries)
        {
            d.computeTotalValue();
            RestaurantType rt = d.getRestaurant().getRestaurantType();
            String restaurantName = d.getRestaurant().getName();
            double bill = d.getTotalValue();

            if(rt == RestaurantType.ROMANIAN) {
                bill *= 0.9;
                if (d.getDuration() > 100)
                    bill *= 0.9;
            }

           else
            if(d.getDuration() > 100)
                bill = 0.9 * d.getTotalValue();

            if(result.containsKey(restaurantName))
                result.put(restaurantName, result.get(restaurantName) + bill);

            else
                result.put(restaurantName, bill);

        }

        return result;
    }
}
