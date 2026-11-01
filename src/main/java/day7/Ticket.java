package day7;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ticket {
    private final String ticketId;
    private final Spot spot;
    private final Vehicle vehicle;
    private final LocalDateTime localDateTime;
    private Status status;

    public Ticket(String ticketId, Spot spot, Vehicle vehicle, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.spot = spot;
        this.vehicle = vehicle;
        this.localDateTime = entryTime;
        this.status = Status.OPEN;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Spot getSpot() {
        return spot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void closeTicket() {
        if(status == Status.CLOSE){
            throw new IllegalArgumentException("Ticket is already closed");
        }
        status = Status.CLOSE;
    }

    public long billableHours(LocalDateTime existTime){
        Duration parked =  Duration.between(this.getLocalDateTime(),existTime);
        long hours = parked.toHours();                             // full hours only (2h 10m → 2)
        if (parked.minusHours(hours).isPositive()) {               // any leftover time at all?
            hours++;                                               // partial hour counts as full → 3
        }
        return hours;
    }

}
