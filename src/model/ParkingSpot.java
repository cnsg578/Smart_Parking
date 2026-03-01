package model;

public class ParkingSpot {
    private String spotId;
    private VehicleType type;
    private boolean occupied;
    private Vehicle vehicle;

    public ParkingSpot(String spotId, VehicleType type) {
        this.spotId = spotId;
        this.type = type;
        this.occupied = false;
    }

    public String getSpotId() {
        return spotId;
    }

    public VehicleType getType() {
        return type;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.occupied = true;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.occupied = false;
    }
}