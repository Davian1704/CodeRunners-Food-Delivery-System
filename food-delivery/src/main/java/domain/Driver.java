package domain;

public class Driver {

    private final String name;

    private final VehicleType vehicleType;

    public Driver(String name, VehicleType vehicleType) {
        this.name = name;
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getName() {
        return name;
    }


}
