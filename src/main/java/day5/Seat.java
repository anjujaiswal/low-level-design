package day5;

import java.util.Objects;

public class Seat {
    private final String row;
    private final int number;

    public Seat(String row, int number) {
        if(number <= 0 ){
            throw new IllegalArgumentException("Seat number must be positive");
        }
        if(row == null || row.isBlank()){
            throw new IllegalArgumentException("Row must not be blank");
        }
        this.row = row;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return number == seat.number && Objects.equals(row, seat.row);
    }

//    @Override
    public int hashCode() {
        return Objects.hash(row, number);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row='" + row + '\'' +
                ", number=" + number +
                '}';
    }
}
