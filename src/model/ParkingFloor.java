package model;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private String floorId;
    private List<ParkingSpot> spots;

    public ParkingFloor(String floorId) {
        this.floorId = floorId;
        this.spots = new ArrayList<>();
    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }
}