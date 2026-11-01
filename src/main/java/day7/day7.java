package day7;

import java.time.LocalDateTime;
import java.util.List;

//The requirements
//The parking lot has multiple floors. Each floor has spots of three sizes: small, medium and large.
//Vehicles are bikes, cars and trucks. A bike fits any spot, a car fits medium or large, and a truck fits only large.
//When a vehicle enters, assign the nearest suitable free spot (lowest floor first) and issue a ticket.
//When it exits with its ticket, calculate the fee by duration: Bike ₹10/hour, Car ₹20/hour, Truck ₹50/hour. Any partial hour counts as a full hour.
//Show the number of free spots per floor, by size.
//If no suitable spot is free, entry fails with a clear error.
public class day7 {
    public static void main(String[] args) {
        //happy path
        Spot spot1 = new Spot("1",SizeType.SMALL,1);
        Spot spot2 = new Spot("2",SizeType.MEDIUM,2);
        Spot spot3 = new Spot("3",SizeType.LARGE,3);
        Spot spot4 = new Spot("4",SizeType.MEDIUM,4);
        Spot spot5 = new Spot("5",SizeType.LARGE,5);

        Floor floor1 = new Floor("floorId1", List.of(spot5,spot2),1);
        Floor floor2 = new Floor("floorId2", List.of(spot3,spot4,spot1),2);

        Vehicle vehicle1 = new Vehicle("123",VehicleType.TRUCK);
        Vehicle vehicle2 = new Vehicle("124",VehicleType.BIKE);
        Vehicle vehicle3 = new Vehicle("125",VehicleType.CAR);

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);
        Ticket ticket1 = parkingLot.entry(vehicle1, LocalDateTime.of(2026, 11, 1, 10, 0));
        Ticket ticket2 = parkingLot.entry(vehicle2,LocalDateTime.of(2026, 11, 1, 12, 0));
        parkingLot.freeSlots();
        double fee = parkingLot.exit(ticket1.getTicketId(), LocalDateTime.of(2026, 11, 1, 15, 0));
        System.out.println("FEE: " + fee);
        parkingLot.freeSlots();
        Ticket ticket3 = parkingLot.entry(vehicle3, LocalDateTime.of(2026, 11, 1, 16, 0));
        parkingLot.freeSlots();



    }

}
