package service;

import model.*;
import exception.NoSpotAvailableException;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingLot {

    private Map<VehicleType, Queue<ParkingSpot>> availableSpots = new HashMap<>();
    private Map<String, Ticket> activeTickets = new HashMap<>();
    private ReentrantLock lock = new ReentrantLock();

    public ParkingLot(List<ParkingFloor> floors) {

        for (VehicleType type : VehicleType.values()) {
            availableSpots.put(type, new LinkedList<>());
        }

        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.getSpots()) {
                availableSpots.get(spot.getType()).offer(spot);
            }
        }
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        lock.lock();
        try {
            Queue<ParkingSpot> spots = availableSpots.get(vehicle.getType());

            if (spots.isEmpty()) {
                throw new NoSpotAvailableException("No spot available for " + vehicle.getType());
            }

            ParkingSpot spot = spots.poll();
            spot.assignVehicle(vehicle);

            String ticketId = UUID.randomUUID().toString();
            Ticket ticket = new Ticket(ticketId, vehicle, spot);
            activeTickets.put(ticketId, ticket);

            return ticket;
        } finally {
            lock.unlock();
        }
    }

    public double unparkVehicle(String ticketId) {
        lock.lock();
        try {
            Ticket ticket = activeTickets.get(ticketId);
            if (ticket == null) {
                throw new RuntimeException("Invalid ticket ID");
            }

            long hours = Math.max(1,
                    Duration.between(ticket.getEntryTime(), java.time.LocalDateTime.now()).toHours());

            FeeStrategy strategy = getStrategy(ticket.getVehicle().getType());
            double fee = strategy.calculateFee(hours);

            ticket.closeTicket(fee);

            ParkingSpot spot = ticket.getSpot();
            spot.removeVehicle();
            availableSpots.get(spot.getType()).offer(spot);

            activeTickets.remove(ticketId);

            return fee;
        } finally {
            lock.unlock();
        }
    }

    private FeeStrategy getStrategy(VehicleType type) {
        return switch (type) {
            case CAR -> new CarFeeStrategy();
            case MOTORCYCLE -> new BikeFeeStrategy();
            case BUS -> new BusFeeStrategy();
        };
    }

    public void showAvailability() {
        for (VehicleType type : VehicleType.values()) {
            System.out.println(type + " Available: " + availableSpots.get(type).size());
        }
    }
}