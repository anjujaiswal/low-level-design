package day7;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

//When a vehicle enters, assign the nearest suitable free spot (lowest floor first) and issue a ticket.
//When it exits with its ticket, calculate the fee by duration: Bike ₹10/hour, Car ₹20/hour, Truck ₹50/hour. Any partial hour counts as a full hour.
//Show the number of free spots per floor, by size.
//If no suitable spot is free, entry fails with a clear error.
public class ParkingLot {
    private final HashMap<String , Ticket> tickets = new HashMap<>();
    private final SortedSet<Floor> floors = new TreeSet<>(Comparator.comparingInt(Floor::getFloorNo));


    public void addFloor(Floor floor){
        floors.add(floor);
    }

    public Ticket entry(Vehicle vehicle, LocalDateTime time){
        for(Ticket ticket: tickets.values()){
            Vehicle ticketVehicle = ticket.getVehicle();
            if(ticketVehicle.getVehicleId().equals(vehicle.getVehicleId())){
                throw new IllegalStateException("Same vehicle " + vehicle.getVehicleId()+" cannot be parked");
            }
        }

        for(Floor floor: floors){
            SortedSet<Spot> spots = floor.getSpots();
            VehicleType vehicleType = vehicle.getVehicleType();
            for( Spot spot : floor.getSpots()){
                if (spot.isFree() && vehicleType.canFitIn(spot.getSizeType())) {
                    Ticket ticket = new Ticket("TICKET"+ UUID.randomUUID().toString(),spot,vehicle, time)  ;
                    spot.setStatus(Status.CLOSE);
                    tickets.put(ticket.getTicketId(),ticket);
                    return ticket;
                }
            }

        }
        throw new IllegalStateException("No spots avalaible");
    }
    public double exit(String ticketId, LocalDateTime exitTime){
        // 1. find the stored ticket, or throw
        // 2. hours = ticket.billableHours(exitTime)      (validates the time)
        // 3. fee = vehicleType.calculateFee(hours)
        // 4. spot.free(); tickets.remove(ticketId)
        // 5. return fee
        if(!tickets.containsKey(ticketId)){
            throw new IllegalStateException("This ticket is not valid");
        }
        Ticket ticket = tickets.get(ticketId);
        if(ticket.getLocalDateTime().isAfter(exitTime)){
            throw new IllegalStateException("Ticket cannot be after the exit time");
        }

        long hours = ticket.billableHours(exitTime);
        double fee = ticket.getVehicle().getVehicleType().calculateFee(hours);
        tickets.remove(ticket.getTicketId());
        ticket.getSpot().setStatus(Status.OPEN);
        return fee;
    }

    public void freeSlots(){
        for(Floor floor : floors){
            int small = 0, medium = 0, large = 0;
            for(Spot spot : floor.getSpots()){
                if(spot.isFree()){
                    if(spot.getSizeType() == SizeType.SMALL){
                        small++;
                    }
                    else if(spot.getSizeType() == SizeType.MEDIUM)
                        medium++;
                    else
                        large++;
                }
            }
            System.out.println("FloorNO: " + floor.getFloorNo() + " Small spot count : "+ small +
                    " Medium count: " + medium + " Large count: "+ large);
        }
    }
}
