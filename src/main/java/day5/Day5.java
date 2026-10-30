package day5;

import java.security.Permission;
import java.util.List;

//Part A: Composition (small)
//
//Engine interface with start(), plus PetrolEngine and ElectricEngine
//Car(Engine engine): rejects null; start() delegates to the engine
//        In main, start one of each. Then add HybridEngine without changing Car.


//Part B: Cinema seat booking (main part)
//
//Seat: row (String) and number (int), both final
//Validation: row isn’t null/blank, number is positive
//        Write equals, hashCode, and toString by hand (no record, no IntelliJ generation)
//SeatAlreadyBookedException (custom, unchecked)
//Screen
//Constructor takes a list of valid seats
//void book(Seat seat):
//        throw IllegalArgumentException if the seat doesn’t exist on this screen
//throw SeatAlreadyBookedException if it’s already booked
//void cancel(Seat seat): throw IllegalStateException if it wasn’t booked
//int availableCount()
//Choose your collections deliberately: what’s the right type for “booked seats”?
//Main (happy path → failure tests → summary):
//Book new Seat("A", 1), then try booking another new Seat("A", 1). It must throw SeatAlreadyBookedException.
//Failure tests: a seat not on the screen, cancelling an unbooked seat, a seat with number 0
//Print the available count before and after
public class Day5 {
    public static void main(String[] args) {
        Car car1 = new Car(new PetrolEngine());
        car1.start();
        Car car2 = new Car(new ElectricEngine());
        car2.start();
        Car car3 = new Car(new HybridEngine());
        car3.start();

        //happy path
        Seat seat1 = new Seat("A",1);
        Seat seat2 = new Seat("A",2);
        Screen screen = new Screen(List.of(seat1,seat2));
        System.out.println("Total seats available: " + screen.availableCount());
        screen.book(seat1);
        System.out.println("Total seats available: " + screen.availableCount());
        screen.cancel(seat1);
        System.out.println("Total seats available: " + screen.availableCount());

        //failure paths

        try{
            new Seat("A",0);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        try{
            new Seat("",2);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        try{
            new Seat("  ",2);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try{
            new Seat(null,2);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        try{
            screen.book(new Seat("A",3));
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        try{
            screen.cancel(new Seat("A",3));
        } catch( IllegalStateException e ){
            System.out.println(e.getMessage());
        }
        try{
            screen.book( new Seat("A", 1));
            screen.book( new Seat("A", 1));
        } catch( SeatAlreadyBookedException e ){
            System.out.println(e.getMessage());
        }

    }
}
