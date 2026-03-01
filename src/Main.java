import model.*;
import service.ParkingLot;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== SMART PARKING LOT SETUP =====");

        System.out.print("Enter number of floors: ");
        int floorCount = sc.nextInt();

        List<ParkingFloor> floors = new ArrayList<>();

        for (int i = 1; i <= floorCount; i++) {
            sc.nextLine(); // clear buffer
            ParkingFloor floor = new ParkingFloor("F" + i);

            System.out.println("\n--- Setup for Floor F" + i + " ---");

            System.out.print("Enter number of CAR spots: ");
            int carSpots = sc.nextInt();

            System.out.print("Enter number of MOTORCYCLE spots: ");
            int bikeSpots = sc.nextInt();

            System.out.print("Enter number of BUS spots: ");
            int busSpots = sc.nextInt();

            for (int j = 1; j <= carSpots; j++) {
                floor.addSpot(new ParkingSpot("F" + i + "_C" + j, VehicleType.CAR));
            }

            for (int j = 1; j <= bikeSpots; j++) {
                floor.addSpot(new ParkingSpot("F" + i + "_B" + j, VehicleType.MOTORCYCLE));
            }

            for (int j = 1; j <= busSpots; j++) {
                floor.addSpot(new ParkingSpot("F" + i + "_BUS" + j, VehicleType.BUS));
            }

            floors.add(floor);
        }

        ParkingLot parkingLot = new ParkingLot(floors);

        System.out.println("\n===== PARKING LOT READY =====");

        while (true) {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Unpark Vehicle");
            System.out.println("3. Show Availability");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {

                    case 1 -> {
                        System.out.print("Enter License Plate: ");
                        String plate = sc.nextLine();

                        System.out.print("Enter Type (CAR/MOTORCYCLE/BUS): ");
                        VehicleType type = VehicleType.valueOf(sc.nextLine().toUpperCase());

                        Vehicle vehicle = new Vehicle(plate, type);
                        Ticket ticket = parkingLot.parkVehicle(vehicle);

                        System.out.println("Vehicle Parked Successfully!");
                        System.out.println("Ticket ID: " + ticket.getTicketId());
                        System.out.println("Allocated Spot: " + ticket.getSpot().getSpotId());
                    }

                    case 2 -> {
                        System.out.print("Enter Ticket ID: ");
                        String ticketId = sc.nextLine();

                        double fee = parkingLot.unparkVehicle(ticketId);
                        System.out.println("Vehicle Unparked Successfully!");
                        System.out.println("Total Fee: ₹" + fee);
                    }

                    case 3 -> {
                        System.out.println("\n===== CURRENT AVAILABILITY =====");
                        parkingLot.showAvailability();
                    }

                    case 4 -> {
                        System.out.println("Exiting System...");
                        return;
                    }

                    default -> System.out.println("Invalid choice!");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}