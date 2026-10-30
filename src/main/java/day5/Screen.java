package day5;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Screen {
    private final Set<Seat> seats;
    private final Set<Seat> booked = new HashSet<>();

    public Screen(List<Seat> seats) {
        if (seats == null || seats.isEmpty()) {
            throw new IllegalArgumentException("Screen must have at least one seat");
        }
        this.seats = new HashSet<>(seats);
    }
    public void book(Seat seat){
        if(!seats.contains(seat)){
            throw new IllegalArgumentException("Seat  " + seat + " is not present in the screen");
        }

        if(booked.contains(seat)){
            throw new SeatAlreadyBookedException("Seat "+seat+" is already booked");
        }
        booked.add(seat);

    }

//    void cancel(Seat seat): throw IllegalStateException if it wasn’t booked

    public void cancel(Seat seat){

        if(!booked.contains(seat)){
            throw new IllegalStateException("Seat  " + seat + " is not booked");
        }
        booked.remove(seat);
    }

//    int availableCount()
    public int availableCount(){
        return seats.size()-booked.size();
    }
}
